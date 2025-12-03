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
import sys


sys.stdout.reconfigure(encoding='utf-8')
# Chrome 드라이버 옵션 설정
options = Options()
options.add_argument("--headless")  # 화면 없이 실행
options.add_argument("--disable-gpu")
options.add_argument("--no-sandbox")

# service = Service("/path/to/chromedriver")  # chromedriver 경로
driver = webdriver.Chrome(options)

driver.get(sys.argv[1]) # 블로그 주소를 인자로 받음
wait = WebDriverWait(driver, 3)
wait.until(EC.frame_to_be_available_and_switch_to_it((By.ID, "mainFrame")))
soup = BeautifulSoup(driver.page_source, 'html.parser')
# 1️⃣ p.caption.align 요소를 찾고
p_tag = soup.find('p', class_='caption align')

# 2️⃣ 그 안의 span.itemfont.col 요소를 찾은 후 텍스트 추출
span_text = p_tag.find('span', class_='itemfont col').get_text(strip=True)
verification_text = span_text
print("scrapped caption :", verification_text)
if sys.argv[2] in span_text:
    print("Verification successful")
else:
    print("Verification failed")


