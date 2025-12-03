
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>

            <div class="cmpn-top-area">
                <div class="cmpn-title flex-space-between">
                    <div class="cmpn-title-content">${param.campaignMyName}</div>
                </div>
              </div>
              <div class="cmpn-bottom-area flex-column">
                <div class="cmpn-profile-area flex-row flex-space-between text-align"> 
                    <div class="px24 profile-star middle-center">${sessionScope.__LOGIN_USER__.logId}</div>
                    <div class="profile-middle middle-center">
                      <c:choose>
                         <c:when test = "${sessionScope.__LOGIN_USER__.autr == 1002}">
                            <div class="green-24px  ">멤버쉽 ON</div>
                         </c:when>
                         <c:when test = "${sessionScope.__LOGIN_USER__.autr == 1003}">
                            <div class="red-24px ">멤버쉽 OFF</div>
                         </c:when>
                      </c:choose>
                    </div>
                    <div class="profile-end middle-center flex-column">
                        <span>연결 계정</span> 
                        <c:choose>
	                        <c:when test="${sessionScope.__LOGIN_USER__.blgTitle ne null}">
	                           <div>${sessionScope.__LOGIN_USER__.blgTitle }</div>
	                        </c:when>
	                        <c:otherwise>
	                           연결된 블로그가 없습니다.
	                        </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                