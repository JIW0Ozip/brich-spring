/**
 * 
 */

$().ready(function(){
	
	var activeColor = "#6A52E8";
	var defaultColor= "#7B61FF";
	$("#verify-btn").on("mousedown touchstart", function() {
	  $(this).css("background-color", activeColor);
	});

	$("#verify-btn").on("mouseup mouseleave touchend", function() {
	  $(this).css("background-color", defaultColor);
	  $()
	});
	
	const modal = $("#verify-modal");

	  // 모달 열기
	  $("#verify-btn").on("click", function() {
	    modal.fadeIn(200);
	  });

	  // 닫기 버튼
	  $(".close").on("click", function() {
	    modal.fadeOut(200);
	  });

	  // 배경 클릭 시 닫기
	  $(window).on("click", function(e) {
	    if ($(e.target).is(modal)) modal.fadeOut(200);
	  });

	  $(document).on("click", "#copy-btn", async function () {
	    const code = $("#code-text").text().trim();
	    const $btn = $(this);

	    // 최신 API 시도
	    try {
	      if (navigator.clipboard && window.isSecureContext) {
	        await navigator.clipboard.writeText(code);
	      } else {
	        // 폴백: 임시 textarea 활용
	        const ta = document.createElement("textarea");
	        ta.value = code;
	        ta.style.position = "fixed";
	        ta.style.top = "-1000px";
	        document.body.appendChild(ta);
	        ta.focus();
	        ta.select();
	        const ok = document.execCommand("copy");
	        document.body.removeChild(ta);
	        if (!ok) throw new Error("execCommand copy failed");
	      }
	      // UX 피드백
	      const original = $btn.text();
	      $btn.text("복사됨!").prop("disabled", true);
	      setTimeout(() => {
	        $btn.text(original).prop("disabled", false);
	      }, 1200);
	    } catch (e) {
	      console.error(e);
	      alert("복사에 실패했어요. 직접 드래그해서 복사해 주세요.");
	    }
	  });
	  $("#generate-code").on("click", function() {
	    $.ajax({
	      url: "/api/verify-code",
	      method: "POST",
	      success: function(res) {
	        $("#verification-code").html(
	          `<strong id="code-text" style='color:#6A52E8;'>${res.code}</strong>
			  <button class="copy-btn" id="copy-btn" type="button" style="margin-left:8px;">복사</button>`
	        );
	        $("#verify-result").text("이 코드를 블로그 소개글에 넣고 인증을 진행하세요.");
			$("#generate-code").attr("disabled", true);
			$("#generate-code").css("background-color","#aaa");
	      }
	    });
	  });
	  
	  $("#run-verification").on("mousedown touchstart", function() {
	    $(this).css("background-color", "#079634");
	  });

	  $("#run-verification").on("mouseup mouseleave touchend", function() {
	    $(this).css("background-color", "#28a745");
	    $()
	  });
	  // 인증 시작
	  $("#run-verification").on("click", function() {
	    $("#verify-result").text("인증 진행 중...");

	    // ✅ input의 value 가져오기
	    var blogURL = $("#blog-url").val().trim();
		var usrID = $("#run-verification").data("user-id");


	    // ✅ 비어 있는지 확인
	    if (blogURL === "") {
	      $("#verify-result").html("<span style='color:red'>블로그 주소를 입력해주세요.</span>");
	      return; // 더 이상 진행하지 않음
	    }

	    $.ajax({
	      url: "/api/verify",
	      method: "POST",
	      contentType: "application/json",
	      data: JSON.stringify({
	        blogUrl: blogURL,
	        usrId: usrID
	      }),
	      success: function(res) {
	        if (res.success) {
	          $("#verify-result").html("<span style='color:green'>인증에 성공하였습니다. 블로그 관리 화면으로 이동합니다.</span>");
	          setTimeout(function() {
	            window.location.href = "/blog/" + usrID + "/manage";
	          }, 3000);
	        } else {
	          $("#verify-result").html("<span style='color:red'>인증 실패 ❌</span>");
	        }
	      },
	      error: function() {
	        $("#verify-result").html("<span style='color:red'>서버 오류 발생 ❌</span>");
	      }
	    });
	  });

})