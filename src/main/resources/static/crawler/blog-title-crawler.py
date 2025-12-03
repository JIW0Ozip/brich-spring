from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import StaleElementReferenceException
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.support.ui import WebDriverWait
from bs4 import BeautifulSoup
from datetime import datetime
from fake_useragent import UserAgent
import requests
import sys
import re
import json


sys.stdout.reconfigure(encoding='utf-8')

def to_mobile_blog_url(url: str) -> str:
    return url.replace("://blog.naver.com/", "://m.blog.naver.com/")

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

blog_url = sys.argv[1]

driver.get(blog_url)

title_elem = driver.find_element(By.XPATH, "//title")
blog_title = title_elem.get_attribute("textContent").strip()

# 뒤에 붙은 " : 네이버 블로그" 제거
if blog_title.endswith(": 네이버 블로그"):
    blog_title = blog_title.replace(": 네이버 블로그", "").strip()

print("블로그 제목:", blog_title)
payload = {
    "blgAddrs": blog_url,
    "blgTitle" : blog_title,
}

print("=== 전송 데이터 미리보기 ===")
print(json.dumps(payload, indent=2, ensure_ascii=False))
print("==========================")

url = "http://localhost:8080/api/title"
try:
    response = requests.post(
        url,
        headers={"Content-Type": "application/json"},
        json=payload
    )
    if response.status_code == 200:
        print("success")
    else:
        print(f"faild: {response.status_code} {response.text}")
except Exception as e:
    print("faild with: ", e)