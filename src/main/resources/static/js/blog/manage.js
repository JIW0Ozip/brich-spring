$(document).ready(function() {
    $('table').DataTable({
		responsive: {
		  details: false 
		},
		columnDefs: [
		  { className: 'all', targets: '_all' }
		],
		lengthChange: false, 
		info:false,
		searching:false,
		language: {
		  search: "검색:",
		  lengthMenu: "_MENU_개씩 보기",
		  info: "총 _TOTAL_개 중 _START_~_END_",
		  infoEmpty: "표시할 데이터가 없습니다.",
		  zeroRecords: "검색 결과가 없습니다.",
		  paginate: {
		    first: "처음",
		    last: "마지막",
		    next: "다음",
		    previous: "이전"
		  }
		},
		autoWidth: false, 
		columnDefs: [
		  { width: "80px", targets: 0 },   
		  { width: "400px", targets: 1 },  
		  { width: "300px", targets: 2 },  
		]
    });
	
	$(document).on("click", ".btn-reason", function () {
	  const postId = $(this).data("id");

	  $.ajax({
	    url: `/api/user/${postId}/return-reason`,
	    type: "GET",
	    success: function (data) {
	      let html = "";

	      if (typeof data === "string") {
	        html = `<p>${data}</p>`;
	      } else if (Array.isArray(data)) {
	        data.forEach((r, idx) => {
	          html += `
	            <div class="reason-block">
	              <h4>반려 사유 ${idx + 1}</h4>
	              <p class="reason-text">${r.postRetnRsn || "사유 없음"}</p>
	          `;

	          if (r.retnFile && r.retnFile.length > 0) {
	            html += `<div class="file-list"><strong>첨부 파일:</strong><ul>`;
	            r.retnFile.forEach(file => {
	              html += `
	                <li>
	                  <a href="/api/file/download/${file.flId}" target="_blank" class="download-link">
	                    ${file.flNm || "파일 이름 없음"}
	                  </a>
	                </li>
	              `;
	            });
	            html += `</ul></div>`;
	          } else {
	            html += `<p class="no-file">첨부 파일 없음</p>`;
	          }

	          html += `<hr></div>`;
	        });
	      }

	      $("#reason-detail").html(html);
	      $("#reason-modal").fadeIn(200);
	    },
	    error: function () {
	      alert("반려 사유를 불러오는 중 오류가 발생했습니다.");
	    }
	  });
	});

	$(".close, #reason-modal").on("click", function (e) {
	  if ($(e.target).is(".modal, .close")) $("#reason-modal").fadeOut(200);
	});
});

