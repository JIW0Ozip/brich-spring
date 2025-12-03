<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이용약관</title>
<link type="text/css" rel="stylesheet" href="/css/brich.css">
<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/user/regist.js"></script>
</head>
<body>
	<div class="regist-wrapper">
		<div class="regist-header">Brich</div>
		<c:choose>
			<c:when test="${role eq 'blogger'}">
				<div class="regist-main">
					<div class="term-check">
						<div class="term-check-input">
							<label for="term">이용약관 </label> <input type="checkbox" id="term"
								name="check" />
						</div>
						<div class="term-content"><br>Brich는 블로거와 광고주를 연결하는 매칭 플랫폼 서비스를
							제공합니다.<br><br>회원은 서비스 이용 시 관련 법령과 본 약관을 준수해야 하며, 타인의 권리를 침해하거나 부정한 방법으로
							서비스를 이용할 수 없습니다.<br><br>구독 서비스는 결제 후 즉시 이용 가능하며, 청약철회 기간 내 환불이 가능합니다.
							회사는 서비스 개선을 위해 필요시 내용을 변경할 수 있으며, 중요한 변경사항은 사전에 공지합니다.<br><br>회원이 약관을
							위반하거나 부적절한 행위를 할 경우 서비스 이용이 제한될 수 있습니다.</div>
					</div>
				</div>
				<br>
				<div class="personal-check">
					<div class="term-check-input">
						<label for="personal">개인정보 수집 및 이용 </label> <input type="checkbox"
							id="personal" name="check" />
					</div>
					<div class="term-content"><br>Brich는 회원가입 및 서비스 제공을 위해 이름, 이메일,
						연락처, 블로그 주소 등 필요한 정보를 수집합니다.<br><br>수집된 정보는 회원 관리, 매칭 서비스 제공, 블로그 지수 분석,
						결제 및 정산 처리, 고객 문의 응대 목적으로만 사용됩니다. 개인정보는 회원 탈퇴 또는 보관 기간 종료 시 즉시
						파기되며, 법령에 따라 보관이 필요한 경우 해당 기간 동안만 보관합니다.<br><br>회원은 언제든지 본인의 개인정보 열람, 수정,
						삭제를 요청할 수 있습니다.</div>
				</div>
				<br>
				<div class="term-check-all term-check-input">
					<label for="check-all">전체 동의 </label> <input type="checkbox"
						id="check-all" />
				</div>
				<div class="next-btn" data-role="${role}">다음</div>
	</div>
	</c:when>
	<c:when test="${role eq 'advertiser'}">
		<div class="regist-main">
			<div class="term-check">
				<div class="term-check-input">
					<label for="term">이용약관 </label> <input type="checkbox"
						id="term" name="check" />
				</div>
				<div class="term-content"><br>Brich는 블로거와 광고주를 연결하는 매칭 플랫폼 서비스를
                            제공합니다.<br><br>회원은 서비스 이용 시 관련 법령과 본 약관을 준수해야 하며, 타인의 권리를 침해하거나 부정한 방법으로
                            서비스를 이용할 수 없습니다.<br><br>구독 서비스는 결제 후 즉시 이용 가능하며, 청약철회 기간 내 환불이 가능합니다.
                            회사는 서비스 개선을 위해 필요시 내용을 변경할 수 있으며, 중요한 변경사항은 사전에 공지합니다.<br><br>회원이 약관을
                            위반하거나 부적절한 행위를 할 경우 서비스 이용이 제한될 수 있습니다.</div>
			</div>
		</div>
		<br>
		<div class="personal-check">
			<div class="term-check-input">
				<label for="personal">개인정보 수집 및 이용 </label> <input type="checkbox"
					id="personal" name="check" />
			</div>
			<div class="term-content"><br>Brich는 회원가입 및 서비스 제공을 위해 이름, 이메일,
                        연락처, 블로그 주소 등 필요한 정보를 수집합니다.<br><br>수집된 정보는 회원 관리, 매칭 서비스 제공, 블로그 지수 분석,
                        결제 및 정산 처리, 고객 문의 응대 목적으로만 사용됩니다. 개인정보는 회원 탈퇴 또는 보관 기간 종료 시 즉시
                        파기되며, 법령에 따라 보관이 필요한 경우 해당 기간 동안만 보관합니다.<br><br>회원은 언제든지 본인의 개인정보 열람, 수정,
                        삭제를 요청할 수 있습니다.</div>
		</div>
		<br>
		<div class="term-check-all term-check-input">
			<label for="check-all">전체 동의 </label> <input type="checkbox"
				id="check-all" />
		</div>
		<div class="next-btn" data-role="${role}">다음</div>
		</div>
	</c:when>
	</c:choose>
	</div>
</body>
</html>