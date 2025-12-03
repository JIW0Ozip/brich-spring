import os
import json
import re
from urllib.parse import urlparse, parse_qs

# ✅ 블로그 URL 정규화 함수 (page, category 등 제거)
def normalize_post_url(url: str) -> str:
    """
    네이버 블로그 포스트 URL에서 blogId와 logNo만 남기고 나머지 파라미터 제거.
    예) 
      https://blog.naver.com/PostView.naver?blogId=abc&logNo=123&currentPage=3
        → https://blog.naver.com/PostView.naver?blogId=abc&logNo=123
    """
    try:
        if not url.startswith("http"):
            return url

        parsed = urlparse(url)
        qs = parse_qs(parsed.query)
        blog_id = qs.get("blogId", [None])[0]
        log_no = qs.get("logNo", [None])[0]

        if blog_id and log_no:
            return f"{parsed.scheme}://{parsed.netloc}{parsed.path}?blogId={blog_id}&logNo={log_no}"
        else:
            return url
    except Exception as e:
        print(f"⚠️ URL 정규화 실패: {url} ({e})")
        return url


# ✅ 루트 경로 계산 (현재 파일 위치 기준)
base_dir = os.path.dirname(os.path.abspath(__file__))
latest_post_dir = os.path.join(base_dir, "latest_post")

# ✅ 대상 폴더 확인
if not os.path.exists(latest_post_dir):
    print(f"❌ 폴더를 찾을 수 없습니다: {latest_post_dir}")
    exit(1)

updated_count = 0

# ✅ 모든 JSON 파일 순회
for filename in os.listdir(latest_post_dir):
    if not filename.endswith(".json"):
        continue

    file_path = os.path.join(latest_post_dir, filename)
    try:
        with open(file_path, "r", encoding="utf-8") as f:
            data = json.load(f)

        if "latest_post_url" not in data:
            continue

        original_url = data["latest_post_url"]
        normalized_url = normalize_post_url(original_url)

        # 정규화 후 다르면 덮어쓰기
        if normalized_url != original_url:
            data["latest_post_url"] = normalized_url
            with open(file_path, "w", encoding="utf-8") as f:
                json.dump(data, f, ensure_ascii=False, indent=2)
            print(f"✅ 정리 완료: {filename}")
            updated_count += 1
        else:
            print(f"🔹 변경 없음: {filename}")

    except Exception as e:
        print(f"⚠️ 처리 중 오류 ({filename}): {e}")

print(f"\n🎉 총 {updated_count}개의 JSON이 정규화되었습니다.")
