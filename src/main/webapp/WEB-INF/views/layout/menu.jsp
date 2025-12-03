<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Brich</title>
    <link type="text/css" rel="stylesheet" href="/css/layoutmenu.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/common/layoutmenu.js"></script>
    
    
    ${param.css }
    ${param.chatCss}
    ${param.accountCss}
    ${param.reportCss}
    ${param.helpCss}
    ${param.scripts}
    ${param.chatroomListscripts}
    ${param.chatscripts}
    ${param.chatroomscripts}
    ${param.sockjs}
    ${param.stompjs}
    ${param.accountJs}
    ${param.reportscripts}
  </head>
  <body>
    <div class="wrapper"
         data-menu-auth="${sessionScope.__LOGIN_USER__.autr}">
      <div class="top-menu">
        <div class="top-menu-content">
            <div class="top-menu-left flex-row">
            <img src="/img/logo.png"/>
                
                <div class="brich-title height-center">
                    <a href="/campaignmain">Brich</a>
                </div>
            </div>
            <div class="top-menu-right flex-row">
                <div class="top-menu-search-area">
                  <input class="search-input menu" type="text" name="searchKeywordMenu" placeholder="Search" />
                </div>
                <div class="top-menu-profile-area flex-row">
                    <div class="menu-profilename height-center">${sessionScope.__LOGIN_USER__.logId}</div>
                    <c:choose>
                      <c:when test="${sessionScope.__LOGIN_USER__ ne null}">
                          <a class="logout" href="/logout">로그아웃</a>
                      </c:when>
                      <c:otherwise>
                          <a class="logout" href="/login">로그인</a>
                      </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
      </div>
      <div class="application-main">
        <div class="side-menu">
          <nav class="menu-nav">
            <ul class="common-menu">
              <c:if test="${empty sessionScope.__LOGIN_USER__ }">
                <li class="logout-menu">
                  <span>MENU</span>
                  <ul class="menu-content">
                    <li class="menu-dashboard-selected"><a href="" class="dashboard-font">Dashboard</a></li>
                    <li class="menu-campaignmain-selected"><a href="/campaignmain">캠페인</a></li>
                  </ul>
                </li>
              </c:if>
              <c:if test="${sessionScope.__LOGIN_USER__ ne null 
                  and (sessionScope.__LOGIN_USER__.autr == 1002 
                    or sessionScope.__LOGIN_USER__.autr == 1003)}">
                <li class="blg-menu">
                  <span>MENU</span>
                  <ul class="menu-content">
                    <li class="menu-dashboard-selected"><a id="menu-image" href="/blog/${sessionScope.__LOGIN_USER__.usrId}/dashboard" class="dashboard-font"> Dashboard</a></li> 
                    <li class="menu-campaignmain-selected"><a href="/campaignmain">캠페인</a></li>
                    <li class="my-campaign "><div class="flex-row flex-space-between height-center">
                        MY 캠페인
                        <div class="caret-down"></div>
                        <div class="caret-up display-none"></div>
                        </div> 
                          <ul class="my-sub-menu display-none">
                            <li class="menu-my-submitted-campaign-selected"><a href="/blgr/submittedmycampaign">신청한 캠페인</a></li>
                            <li class="menu-my-ongoing-campaign-selected"><a href="/blgr/campaignongoing">진행중 캠페인</a></li>
                            <li class="menu-my-closed-campaign-selected"><a href="/blgr/closedcampaign">마감된 캠페인</a></li>
                            <li class="last-item menu-my-fav-campaign-selected"><a href="/blgr/favcampaign">관심 캠페인</a></li>
                          </ul>
                    </li>
                    <li class="menu-my-blog-selected"><a href="/blog/${sessionScope.__LOGIN_USER__.usrId}/manage">포스팅 관리</a></li>
                    <li class="menu-message-selected"><a href="/blgr/chat/rooms">메세지</a></li>
                  </ul>
                </li>
              </c:if>
              
              <c:if test="${sessionScope.__LOGIN_USER__ ne null 
                  and sessionScope.__LOGIN_USER__.autr == 1004}">
                <li class="advertiser-menu">
                  <span>MENU</span>
                  <ul class="menu-content">
                    <li class="menu-campaignmain-selected"><a href="/campaignmain">캠페인</a></li>
                    <li class="menu-my-campaign-list"><a href="/adv/campaign/list">MY 캠페인</a></li> 
                    <li class="menu-my-campaign-write"><a href="/adv/campaign/write">캠페인 만들기</a></li>
                    <li class="menu-message-selected"><a href="/adv/chat/campaigns">메세지</a></li>
                  </ul>
                </li>
              </c:if>
              <c:if test="${sessionScope.__LOGIN_USER__ ne null 
                  and sessionScope.__LOGIN_USER__.autr == 1001}">
                <li class="admin-menu">
                  <span>MENU</span>
                  <li class="menu-campaignmain-selected"><a href="/campaignmain" class="admin-campaign">캠페인</a></li>
                  <span class="menu-admin-header">ADMIN MENU</span>
                  <ul class="menu-content">
                    <li class="menu-admin-user-selected"><a href="/admin/user_list">회원 관리</a></li>
                    <li class="menu-admin-inqr-selected"><a href="/admin/inqr_list">문의 관리</a></li>
                    <li class="menu-admin-report-selected"><a href="/admin/report_list">신고 관리</a></li>
                    <li class="menu-admin-campaign-selected"><a href="/admin/campaign-list">캠페인 관리</a></li>
                    <li class="menu-admin-category-selected"><a href="/admin/category-manage">카테고리 관리</a></li>
                    <!-- <li><a href="/">도움말 관리</a></li>  -->
                  </ul>
                </li>
              </c:if>
              <c:if test="${sessionScope.__LOGIN_USER__ ne null 
                  and sessionScope.__LOGIN_USER__.autr != 1001}">
                <li class="others-menu">
                  <span>OTHERS</span>
                  <ul class="menu-content">
                    <c:choose>
                        <c:when test="${sessionScope.__LOGIN_USER__.autr eq 1004}">
                            <li class="menu-account-selected other-item"><a href="/adv/account/info">계정 관리</a></li>
                        </c:when>
                        <c:otherwise> 
                            <li class="menu-account-selected other-item"><a href="/blgr/account/info">계정 관리</a></li>
                        </c:otherwise>   
                    </c:choose>
                    <li class="menu-help-selected other-item"><a href="/help/inqr/list">Help</a></li>
                    <li class="menu-report-selected other-item"><a href="/report/list">신고</a></li>
                  </ul>
                </li>
            </c:if>
            </ul>
          </nav>
        </div>