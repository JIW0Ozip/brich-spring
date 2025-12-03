<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:if test="${not empty sessionScope.__LOGIN_USER__}">
    <c:set var="authCode" value="${sessionScope.__LOGIN_USER__.autr}" />
    <c:choose>
        <c:when test="${authCode eq '1001'}">
            <c:set var="auth" value="admin" scope="request"/>
        </c:when>
        <c:when test="${authCode eq '1002' or authCode eq '1003'}">
            <c:set var="auth" value="blgr" scope="request"/>
        </c:when>
        <c:when test="${authCode eq '1004'}">
            <c:set var="auth" value="adv" scope="request"/>
        </c:when>
    </c:choose>
</c:if>