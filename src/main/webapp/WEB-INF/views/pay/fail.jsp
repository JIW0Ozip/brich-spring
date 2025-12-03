<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="kr">
  <head>
    <meta charset="utf-8" />
    <link rel="icon" href="https://static.toss.im/icons/png/4x/icon-toss-logo.png" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>결제 실패</title>
  </head>

  <body>
    <div id="info" class="box_section" style="width: 600px">
      <img width="100px" src="https://static.toss.im/lotties/error-spot-no-loop-space-apng.png" />
      <h2>결제를 실패했어요</h2>
      <div class="p-grid">
        <button class="button p-grid-col5" onclick="location.href='/help/inqr/write';" style="background-color: #e8f3ff; color: #1b64da">실시간 문의</button>
      </div>
      <div class="p-grid">
        <button class="button p-grid-col5" onclick="location.href='/campaignmain';" style="background-color: #e8f3ff; color: #1b64da">메인화면 돌아가기</button>
      </div>
    </div>

    <script>
      const urlParams = new URLSearchParams(window.location.search);

      const codeElement = document.getElementById("code");
      const messageElement = document.getElementById("message");

      codeElement.textContent = urlParams.get("code");
      messageElement.textContent = urlParams.get("message");
    </script>
  </body>
</html>

