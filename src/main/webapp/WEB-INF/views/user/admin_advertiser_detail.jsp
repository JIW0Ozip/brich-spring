<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:if test="${not empty pathInfo && fn:contains(pathInfo, '/admin/user_modify')}">
    <script type="text/javascript" src="/js/user/admin_user_info_modify.js"></script>
</c:if>
 
<input type="hidden" id="login_usrId" value="${sessionScope.__LOGIN_USER__.usrId}"/>
<input type="hidden" id="usrId" value="${userInfo.usrId}"/>
<input type="hidden" id="autr" value="${userInfo.autr}"/>
<input type="hidden" id="flGrpId" value="${userInfo.flGrpId}"/>

<div class="detail-row file-upload-row">
    <div class="detail-key">사업자 등록증</div>
    <div class="detail-value">
        <c:choose>
            <%-- 수정 페이지 --%>
            <c:when test="${not empty pathInfo && fn:contains(pathInfo, '/admin/user_modify') }">
                
                <%-- 파일 선택 영역 --%>
                <div id="existFileList" class="file-list-container">
                    <c:set var="vaildFileCount" value="0"/>
                    <c:forEach items="${userInfo.fileGroupVO.file}" var="fileList">
                    
                       <%-- 기존 파일이 있다면, (flId가 있다면) --%>
                       <c:if test="${not empty fileList.flId}">
                       
                           <%-- 기존에 첨부되어 있던 파일들 --%>
                           <span class="file-item exist-file-item">
                               ${fileList.flNm} 
                               
                               <%-- 기존에 첨부되어 있던 파일 삭제 버튼 --%>
                               <button type="button" class="file-remove-btn btn-remove" data-file-id="${fileList.flId}">X</button>
                               
                               <%-- 유지시킬 기존 파일 ID 값 --%>
                               <input type="hidden" name="existFileIds" value="${fileList.flId}" />
                           </span>
                           <c:set var="vaildFileCount" value="${vaildFileCount + 1}"/>
                       </c:if>
                    </c:forEach>
                    
                    
                    <%-- 새로 첨부되는 파일 영역 --%>
                    <div id="newAddedFileList" class="new-file-input-list"></div>
                    
                    <%-- 새 파일 첨부 버튼 --%>
                    <button type="button" id="addNewFileBtn" class="add-new-file-btn btn-action">첨부 파일 +</button>
                    
                    <%-- 새 파일 첨부 버튼으로 추가되는 첨부 파일을 담아주는 영역 --%>
                    <div id="fileInputList" style="display: none;"></div>
                </div>
            </c:when>
        
            <%-- 상세 정보 페이지 --%>
            <c:when test="${not empty userInfo.fileGroupVO && not empty userInfo.fileGroupVO.file}">
                <c:set var="vaildFileCount" value="0"/>
                <c:forEach items="${userInfo.fileGroupVO.file}" var="fileList">
                   <c:if test="${not empty fileList.flId}">
                        <a href="/file/${userInfo.usrId}/${userInfo.flGrpId}/${fileList.flId}" 
                           title="클릭하여 해당 파일 다운로드" class="file-download-link">
                             &#128196;${fileList.flNm} 
                         </a>
                       <c:set var="vaildFileCount" value="${vaildFileCount + 1}"/>
                   </c:if>
                </c:forEach>
                
                <%-- file 객체 내에 flId 값이 없을 경우 --%>
                <c:if test="${vaildFileCount eq 0}">
                    -
                </c:if>
            </c:when>
            
            <%-- file 객체가 비어있을 경우 --%>
            <c:otherwise>
              -
            </c:otherwise>
        </c:choose>
    </div>
</div>


<div class="detail-row">
    <div class="detail-key">가입 승인</div>
    <div class="detail-value">${userInfo.registAcpt}</div>
</div>

<div class="detail-row">
    <div class="detail-key">진행 중인 캠페인 수</div>
    <div class="detail-value">${userInfo.cmpnProgressCnt}</div>
</div>

<div class="detail-row campaign-list-row">
    <div class="detail-key">진행 중인 캠페인</div>
    <div class="detail-value">
        <c:choose>
            <c:when test="${not empty userInfo.cmpnProgressList}">
                <c:forEach items="${userInfo.cmpnProgressList}" var="campaginInfo">
                    <a href="/admin/campaign-detail/${campaginInfo.cmpnId}">[${campaginInfo.cmpnTitle}]</a>
                </c:forEach>
            </c:when>
            
            <c:otherwise>
              -
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="detail-row">
    <div class="detail-key">완료한 캠페인 수</div>
    <div class="detail-value">${userInfo.cmpnCompletedCnt}</div>
</div>

<div class="detail-row last-row campaign-list-row">
    <div class="detail-key">완료한 캠페인</div>
    <div class="detail-value">
       <c:choose>
           <c:when test="${not empty userInfo.cmpnCompletedList}">
                <c:forEach items="${userInfo.cmpnCompletedList}" var="campaginInfo">
                    <a href="/admin/campaign-detail/${campaginInfo.cmpnId}">[${campaginInfo.cmpnTitle}]</a>
                </c:forEach>
           </c:when>
           
           <c:otherwise>
              -
           </c:otherwise>
       </c:choose>
    </div>
</div>
