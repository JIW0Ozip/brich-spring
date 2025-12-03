<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="modal">
    <div class="modal-area">
        <div class="grid-item">지역</div>
        <div class="do-city grid-item">
            <c:forEach items="${doCityList}" var="doCity">
                <input type="radio" name="do-city" id="${doCity.cdId}" data-do-name="${doCity.cdNm}" />
                <label for="${doCity.cdId}" class="button_120_50">${doCity.cdNm}</label>
            </c:forEach>
        </div>
        
        <div class="city-gu-gun grid-item">
        </div>
        
        <div class="checked-cities grid-item">
        </div>
        
        <div class="modal-button-list grid-item">
            <button type="button" class="modal-close area-close">닫기</button>
            <button type="button" class="modal-submit">선택</button>
        </div>
    </div>
</div>