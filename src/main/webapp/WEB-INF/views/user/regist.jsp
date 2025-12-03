<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link type="text/css" rel="stylesheet" href="/css/brich.css">
<link type="text/css" rel="stylesheet" href="/css/regist.css">
<script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/user/regist.js"></script>
<link type="text/css" rel="stylesheet" href="/css/account/account.css">
<script type="text/javascript" src="/js/common/areamodal.js"></script>
<script type="text/javascript" src="/js/common/validate.js"></script>
</head>
<body>
     <!-- 성공 메시지 처리 -->
    <c:if test="${not empty successMessage}">
        <script>
            alert('${successMessage}');
            window.location.href = '/login';
        </script>
    </c:if>
    <div class="regist-wrapper">
        <div class="regist-header">
            Brich
        </div>
        <div class="regist-main">
            <c:if test="${role eq 'advertiser' }">
                <form:form modelAttribute="requestUserRegistVO" 
                           method="post" 
                           action="/regist" 
                           enctype="multipart/form-data"
                           class="user-regist-form">
                    <div class="right-flex">
                        <div class="input-flex short"> 
                            <label for="id" class="require" id="regist-id">아이디 <span class="require-mark">*</span></label>
                            <input type="text" id="id" name="logId" class="logId" value="${registData.logId}" placeholder="아이디를 입력해주세요"/>
                            <form:errors path="logId" cssClass="validate-require" />
                        </div>
                        <div class="regist-side-btn"><div class="duplicate-id">중복 확인</div></div>
                    </div>
                    <div class="input-flex">
                        <label for="name" class="require">이름 <span class="require-mark">*</span></label>   
                        <input type="text" id="name" name="nm" placeholder="이름을 입력해주세요" value="${registData.nm}"/>
                         <form:errors path="nm" cssClass="validate-require" />
                    </div>
                    <div class="input-flex">
                        <label for="company" class="require cmpny-name">사업자명 <span class="require-mark">*</span></label>
                        <input type="text" id="company" name="cmpny" placeholder="사업자명을 입력해주세요" value="${registData.cmpny}"/>
                        <form:errors path="cmpny" cssClass="validate-require" />
                        
                        
                    </div>
                    <div class="file-input-flex">
                        <label for="company" class="require">사업자 등록증 <span class="require-mark">*</span></label>
                        <input id="fileInput" type="file" name="file" multiple="multiple" />
                        <div id="fileList"></div>
                    </div>
                
         
                    <div class="right-flex">
                        <div class="input-flex short">
                            <label for="email" class="require" >이메일 <span class="require-mark">*</span></label>
                            <input type="text" id="email" name="eml" value="${registData.eml}" placeholder="이메일을 입력해주세요"/>
                            <form:errors path="eml" cssClass="validate-require" />
                        </div>
                        <div class="regist-side-btn email-send"><div>인증 번호</div></div>
                    </div>
                    <div class="right-flex">
                        <div class="input-flex short">
                           <label for="email-confirm" class="email-confirm">인증번호 입력</label>
                           <input type="text" id="email-confirm" name="emailConfirm" placeholder="인증번호를 입력하세요"/>
                        </div>
                        <div class="regist-side-btn email-verify"><div>인증 확인</div></div>
                    </div>
                    <span class="email-check-timer"></span>
                    <span class="email-confirm-message"></span>
                    
                    <div class="input-flex">
                        <label for="password" class="require">비밀번호 <span class="require-mark">*</span></label>
                        <input type="password" id="password" name="pswrd" placeholder="8~16자리 비밀번호 입력" maxlength="16"/>
                         <form:errors path="pswrd" cssClass="validate-require" />
                        <input type="password" id="password-confirm" name="pswrdConfirm" placeholder="비밀번호 확인"/> 
                        <c:if test="${not empty passwordError}"> 
                           <span class='validate-password-confirm'>비밀번호가 일치하지 않습니다.</span>
                        </c:if>
                    </div>
                    <input type="hidden" name="autr" value="1007"/>
                    <button type="button" class="regist-btn" data-dependencies="#id,#name,#company,#email,#email-confirm,#password,#password-confirm ">회원가입</button>
                </form:form>
            </c:if>
            <c:if test="${role eq 'blogger'}">
                <form:form modelAttribute="requestUserRegistVO" method="post" action="/regist" class="user-regist-form">
                    <div class="right-flex">
                        <div class="input-flex short"> 
                            <label for="id" class="require">아이디 <span class="require-mark">*</span></label>
                            <input type="text" id="id" name="logId" class="logId" placeholder="아이디를 입력해주세요"/>
                            <form:errors path="logId" cssClass="validate-require" /> 
                        </div>
                        <div class="regist-side-btn"><div class="duplicate-id">중복 확인</div></div>
                    </div>
                    <div class="input-flex">
                        <label for="name" class="require">이름 <span class="require-mark">*</span></label>   
                        <input type="text" id="name" name="nm" placeholder="이름을 입력해주세요"/>
                        <form:errors path="nm" cssClass="validate-require" />
                        
                    </div>
                    <div class="right-flex">
                        <div class="input-flex short">
                            <label for="email" class="require">이메일 <span class="require-mark">*</span></label>
                            <input type="text" name="eml" id="email" placeholder="이메일을 입력해주세요"/>
                            <form:errors path="eml" cssClass="validate-require" />
                        </div>
                            <div class="regist-side-btn email-send"><div>인증 번호</div>
                        </div>
                    </div>
                    <div class="right-flex">
                        <div class="input-flex short">
                           <label for="email-confirm" class="require">인증번호 입력 </span></label>
                           <input type="text" id="email-confirm" name="emailConfirm" placeholder="인증번호를 입력하세요"/>
                        </div>
                        <div class="regist-side-btn email-verify blogger"><div>인증 확인</div></div>
                    </div>
                    <span class="email-check-timer"></span>
                    <span class="email-confirm-message"></span>
                    <div class="input-flex">
                        <label for="password" class="require">비밀번호 <span class="require-mark">*</span></label>
                        <input type="password" id="password" name="pswrd" placeholder="8~16자리 비밀번호 입력" maxlength="16"/>
                        <form:errors path="pswrd" cssClass="validate-require" />
                        <input type="password" id="password-confirm" name="pswrdConfirm" placeholder="비밀번호 확인"/> 
                        <c:if test="${not empty passwordError}"> 
                           <span class='validate-password-confirm'>비밀번호가 일치하지 않습니다.</span>
                        </c:if>
                        
                    </div>
                    <div class="input-flex">
                        <label class="optional-label">카테고리</label>
                        <div>
                            <c:forEach items="${common.categoryList}" var="category" begin="1">
							    <input name="cdIdList" type="checkbox" value="${category.cdId}"/>${category.cdNm}
							</c:forEach>
                        </div>
                    </div>
                    <div class="input-flex">
					    <label class="optional-label">지역</label>
					    <div class="area-flex">
					        <div class="selected-area-list"></div>
					        <div class="add-area-btn">지역 추가</div>
					    </div>
					    <!-- hidden input 영역 -->
					    <div class="hidden-area-list"></div>
					</div>
                    <input type="hidden" name="autr" value="1003"/>
                    
                    <button type="button" class="regist-btn">회원가입</button>
                </form:form>   
            </c:if>
        </div>
    </div>
    <c:set var="doCityList" value="${common.doAndCityList}" scope="request" />
    <jsp:include page="/WEB-INF/views/layout/areamodal.jsp" />
</body>
</html>