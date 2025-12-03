from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import StaleElementReferenceException
from selenium.common.exceptions import TimeoutException
from bs4 import BeautifulSoup
from datetime import datetime
from datetime import datetime, timedelta
from fake_useragent import UserAgent

import os
import json
import requests
import re
import time 
import math
import sys


sys.stdout.reconfigure(encoding='utf-8')
# # Chrome 드라이버 옵션 설정
options = Options()
options.add_argument("--headless")  # 화면 없이 실행
options.add_argument("--disable-gpu")
options.add_argument("--no-sandbox")
options.add_argument("--disable-dev-shm-usage")
ua = UserAgent()

# random user-agent 설정
def get_pc_user_agent():
    while True:
        candidate = ua.random
        if not re.search(r'Mobile|Android|iPhone', candidate, re.I):
            return candidate
user_agent = get_pc_user_agent()
options.add_argument(f'user-agent={user_agent}')



# service = Service("/path/to/chromedriver")  # chromedriver 경로
driver = webdriver.Chrome(options)

def is_toplist_open(driver):
    try:
        wrapper = driver.find_element(By.ID, "toplistWrapper")

        # display, height, aria 상태 감지
        display = driver.execute_script("return window.getComputedStyle(arguments[0]).display;", wrapper)
        height = driver.execute_script("return arguments[0].offsetHeight;", wrapper)
        aria_hidden = wrapper.get_attribute("aria-hidden") or wrapper.get_attribute("area-hidden")

        visible = wrapper.is_displayed()
        aria_open = (aria_hidden is None) or (aria_hidden.lower() == "false")

        # 🔧 완화된 조건: display != none 또는 height > 50
        return (display != "none" or height > 50) and aria_open and visible

    except Exception:
        return False


from selenium.common.exceptions import StaleElementReferenceException, TimeoutException

def ensure_toplist_open(driver, timeout=5):
    """
    네이버 블로그의 Toplist(목록)가 닫혀 있을 경우 열리도록 보장한다.
    StaleElementReferenceException 발생 시 재시도하도록 수정.
    """
    try:
        # 이미 프레임 안이라면 생략
        try:
            driver.find_element(By.ID, "toplistWrapper")
        except:
            switched = switch_to_main_frame(driver)
            if not switched:
                print(" mainFrame 전환 실패 → toplist 열기 건너뜀")
                return

        span_elem = WebDriverWait(driver, timeout).until(
            EC.presence_of_element_located((By.ID, "toplistSpanBlind"))
        )
        span_text = span_elem.text.strip()
        print(f"현재 목록 상태 텍스트: {span_text}")

        if "목록닫기" in span_text:
            print(" 목록이 이미 열려 있음 → 클릭 생략")
            return

        print("목록이 닫혀 있음 → 열기 시도")

        # 1초 대기 (네이버 내부 JS 핸들러 로드 대기)
        time.sleep(1)

        # 최대 3번 재시도
        for attempt in range(3):
            try:
                toggle_btn = WebDriverWait(driver, timeout).until(
                    EC.element_to_be_clickable((By.CSS_SELECTOR, "a.btn_openlist._toggleTopList"))
                )

                driver.execute_script("""
                    const el = arguments[0];
                    el.scrollIntoView({behavior:'instant', block:'center'});
                    const evt = new MouseEvent('click', {bubbles:true, cancelable:true, view:window});
                    el.dispatchEvent(evt);
                """, toggle_btn)

                print(f"클릭 시도 {attempt+1}회 완료")

                # display 상태가 block으로 바뀔 때까지 대기
                WebDriverWait(driver, timeout * 3).until(
                    lambda d: d.execute_script(
                        "return window.getComputedStyle(document.querySelector('#toplistWrapper')).display"
                    ) != "none"
                )
                print(" 목록 열림 확인 완료")
                return
            except StaleElementReferenceException:
                print(f" StaleElementReference 발생 → {attempt+1}번째 재시도")
                time.sleep(0.5)
                continue

        print(" 3회 재시도 후 실패")

    except TimeoutException:
        print(" Timeout: 목록이 열리지 않음 (이미 열려 있거나 토글 실패)")

def switch_to_main_frame(driver, timeout=10):
    """
    mainFrame을 안전하게 전환하는 함수.
    ID 또는 NAME 기준으로 모두 탐색, 없을 경우 스킵.
    """
    try:
        WebDriverWait(driver, timeout).until(
            EC.any_of(
                EC.frame_to_be_available_and_switch_to_it((By.ID, "mainFrame")),
                EC.frame_to_be_available_and_switch_to_it((By.NAME, "mainFrame"))
            )
        )
        print(" mainFrame 전환 성공")
        return True
    except TimeoutException:
        print(" mainFrame을 찾지 못했습니다. 현재 URL:", driver.current_url)
        # 디버깅용 HTML 저장
        with open("no_frame_debug.html", "w", encoding="utf-8") as f:
            f.write(driver.page_source)
        return False
    
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
LATEST_POST_DIR = os.path.join(BASE_DIR, "latest_post")

def save_last_url(blog_url, url):
    """
    각 블로그 주소별로 최신 포스트 URL을 저장.
    예: latest_post/kse4966.json
    """
    # 블로그 ID 추출
    match = re.search(r'blog.naver.com/([^/?]+)', blog_url)
    blog_id = match.group(1) if match else "unknown"

    # 저장 폴더 생성 (없으면 자동 생성)
    folder = "latest_post"
    os.makedirs(folder, exist_ok=True)

    # 파일 경로
    filepath = os.path.join(folder, f"{blog_id}.json")

    # 데이터 저장
    with open(filepath, "w", encoding="utf-8") as f:
        json.dump({"latest_post_url": url}, f, ensure_ascii=False, indent=2)

    print(f" 최신 포스트 URL 저장 완료 → {filepath}")


import os
import json
import re

def find_project_root(target_folder_name="brich-project"):

    current_dir = os.path.abspath(__file__)
    while True:
        current_dir = os.path.dirname(current_dir)
        if os.path.basename(current_dir) == target_folder_name:
            return current_dir
        if current_dir == os.path.dirname(current_dir):  # 루트 도달
            break
    return None

def load_last_url(blog_url):

    #  루트 자동 탐색
    root_dir = find_project_root("brich-project")
    if not root_dir:
        print("프로젝트 루트를 찾을 수 없습니다.")
        return None

    folder = os.path.join(root_dir, "latest_post")

    # 블로그 ID 추출
    match = re.search(r'blog.naver.com/([^/?]+)', blog_url)
    blog_id = match.group(1) if match else "unknown"

    filepath = os.path.join(folder, f"{blog_id}.json")

    # 파일 확인
    if os.path.exists(filepath):
        with open(filepath, "r", encoding="utf-8") as f:
            data = json.load(f)
            print(f"불러온 경로: {filepath}")
            return data.get("latest_post_url", None)
    else:
        print(f" {filepath} 파일이 존재하지 않습니다.")
        return None

from urllib.parse import urlparse, parse_qs

def normalize_post_url(url: str) -> str:
    """
    네이버 블로그 포스트 URL에서 blogId와 logNo만 남기고 나머지 쿼리 파라미터 제거
    예: https://blog.naver.com/PostView.naver?blogId=abc&logNo=123&categoryNo=0 -> 
        https://blog.naver.com/PostView.naver?blogId=abc&logNo=123
    """
    try:
        if not url.startswith("http"):
            return url  # 절대경로 아닐 경우 그대로 반환

        parsed = urlparse(url)
        query = parse_qs(parsed.query)

        blog_id = query.get("blogId", [None])[0]
        log_no = query.get("logNo", [None])[0]

        if blog_id and log_no:
            return f"https://blog.naver.com/PostView.naver?blogId={blog_id}&logNo={log_no}"
        else:
            return url
    except Exception as e:
        print(f"URL 정규화 실패: {url} ({e})")
        return url


# 네이버 블로그 진입
blg_url = sys.argv[1]
driver.get(blg_url) # 블로그 ID를 인자로 받음
wait = WebDriverWait(driver, 30)

# 1. iframe이 로드될 때까지 대기 후 전환
if not switch_to_main_frame(driver):
    print(" mainFrame이 없는 페이지로 판단되어 프레임 전환 생략")


blog_link = WebDriverWait(driver, 3).until(
    EC.element_to_be_clickable((By.XPATH, "//a[contains(@href, 'PostList.naver') and contains(@class, 'itemfont') and contains(@class, '_doNclick') and contains(@class, '_param(false|blog|)')]"))
    ,print("블로그 탭 클릭")
)
blog_link.click()

# 전체보기 클릭
try:
    all_posts_link = WebDriverWait(driver, 5).until(
        EC.element_to_be_clickable((By.XPATH, "//a[@id='category0' and contains(text(), '전체보기')]"))
    )
    driver.execute_script("arguments[0].click();", all_posts_link)
    print("전체보기 클릭 완료")
except TimeoutException:
    print("전체보기 버튼을 찾을 수 없습니다.")




#  목록 열림 상태 보장
ensure_toplist_open(driver)

# 2 잠깐 대기 (목록 DOM 완전히 갱신될 때까지)
time.sleep(0.5)

# 3 목록이 열린 상태의 HTML로 새로 파싱
soup = BeautifulSoup(driver.page_source, 'html.parser')

# 4 페이지 개수 추출
page_count_elem = soup.select_one('h4.category_title.pcol2')
numeric_chars = [char for char in page_count_elem.text if char.isdigit()]
numeric_string = "".join(numeric_chars)


# list_size = soup.select_one('#listCountView').text
# list_size = re.findall(r'\d+', list_size)[0]
last_url = load_last_url(blg_url)
stop_collecting = False
links = []  # set으로 중복 방지
seen = set()
total_pages = math.ceil(int(numeric_string) / 5)



for page_num in range(1, 30):
    # 현재 페이지 HTML 새로 파싱
    soup = BeautifulSoup(driver.page_source, 'html.parser')
    
    # 링크 수집 (절대경로 + 정확한 클래스 필터)
    for a in soup.find_all('a', href=True):
        href = a['href']
        href = normalize_post_url(href)
        classes = a.get('class', [])
        if (
            href.startswith('https://blog.naver.com/PostView.naver?blogId=') and
            all(c in classes for c in ['pcol2', '_setTop', '_setTopListUrl']) and
            not a.has_attr('logno') and
            not a.has_attr('onclick') and
            href not in seen
        ):
            if last_url and href == last_url:
                print(f" 마지막 수집 포스트 도달: {href} → 크롤링 중단")
                stop_collecting = True
                break
            
            links.append(href)  
            seen.add(href) # set이라 중복 안 됨

    if stop_collecting:
        break
    print(f"[PAGE {page_num}] 수집된 링크 수: {len(links)}")

    # 다음 페이지 버튼 클릭
    next_xpath = f"//a[contains(@class,'_goPageTop') and contains(@class,'_param({page_num+1})')]"
    try:
        next_button = WebDriverWait(driver, 5).until(
            EC.element_to_be_clickable((By.XPATH, next_xpath))
        )
        driver.execute_script("arguments[0].click();", next_button)
        
        # 페이지가 실제로 바뀔 때까지 대기
        WebDriverWait(driver, 10).until(EC.staleness_of(next_button))
        WebDriverWait(driver, 2).until(
            EC.presence_of_element_located((By.CSS_SELECTOR, "table.blog2_list"))
        )
    except TimeoutException:
        print(f"페이지 {page_num}에서 다음 버튼을 찾을 수 없음 (마지막 페이지일 수 있음).")
        break




print(f"총 고유 링크 수: {len(links)}")
for l in sorted(links):
    print(l)
   

# 클릭 후의 HTML 가져오기
html = driver.page_source
print(html[:1000])  # 앞부분만 출력해보기

count = 1
results = []

for idx, post_url in enumerate(links):
    driver.get(post_url)
    WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.TAG_NAME, 'iframe')))
    try:
        iframe = driver.find_element(By.ID, "mainFrame")
        driver.switch_to.frame(iframe)
    except Exception:
        pass
    
    # 프레임 전환 후
    WebDriverWait(driver, 10).until(lambda d: d.execute_script("return document.readyState") == "complete")

    soup = BeautifulSoup(driver.page_source, 'html.parser')

    like_elem = soup.select_one('span.u_likeit_text._count.num')
    # 공감 수가 로드되지 않았을 경우 다시 시도
    if not like_elem or not like_elem.text.strip():
        soup = BeautifulSoup(driver.page_source, 'html.parser')
        like_elem = soup.select_one('span.u_likeit_text._count.num')

    like_count = like_elem.text.strip() if like_elem and like_elem.text.strip() else 'N/A'

    # 날짜
    post_elem = soup.select_one('span.se_publishDate.pcol2')
    post_date = post_elem.text.strip() if post_elem else 'N/A'
    
    now = datetime.now()
    # ====== 날짜 파싱 ======
    if re.search(r'(시간|분|일)\s*전', post_date):
        print(f"최근 5일 이내 포스트 → 제외: {post_date}")
        continue  # 최근 5일 이내 포스트 제외
    else:
        try:
            post_datetime = datetime.strptime(post_date, "%Y. %m. %d. %H:%M")
            if now - post_datetime < timedelta(days=5):
                print(f"최근 5일 이내 포스트 → 제외: {post_date}")
                continue
        except ValueError:
            try:
                post_datetime = datetime.strptime(post_date, "%Y. %m. %d.")
            except ValueError:
                print(f"날짜 파싱 실패: {post_date}")
                continue

    # ====== 댓글 ======
    comment_elem = soup.select_one('em._commentCount')
    comment_count = comment_elem.text.strip() if comment_elem else 'N/A'

    results.append({
        'index': idx,
        'url': post_url,
        'date': post_datetime,
        'likes': like_count,
        'comments': comment_count
    })

    print(f"{post_url} | 날짜: {post_datetime} | 공감: {like_count} | 댓글: {comment_count} | 인덱스: {idx+1}")


# ====== 날짜 기준 정렬 ======
# results.sort(key=lambda x: x["date"], reverse=False)

if results:
    # 결과 중 가장 최신(날짜가 가장 큰) 포스트 찾기
    newest = max(results, key=lambda r: r["date"])

    # 블로그별 최신 포스트 URL 저장 (인자 수정)
    save_last_url(blg_url, newest["url"])

    print(f"최신 포스트 URL 저장 완료 → {newest['url']}")
else:
    print("새로운 포스트가 없습니다. (모두 5일 이내 또는 수집 실패)")

# ====== API 전송 ======
url = "http://localhost:8080/api/results"

def safe_int(value):
    try:
        return int(value)
    except:
        return 0
data = {
    "blgAddrs": blg_url,   
    "postList": [           
        {
            "pstUrl": r["url"],
            "pstCmnt": safe_int(r["comments"]),
            "pstLk": safe_int(r["likes"]),
            "pstdDt": r["date"].strftime("%Y-%m-%d %H:%M")
        }
        for r in results
    ]
}
print("=== 전송 데이터 미리보기 ===")
print(json.dumps(data, indent=2, ensure_ascii=False))
print("==========================")

response = requests.post(
    url,
    headers={"Content-Type": "application/json"},
    json=data  
)
driver.quit()
