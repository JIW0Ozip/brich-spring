var stompClient = null;
var chtRmId = null;
var myId = null;
var myName = null;
var myCmpny = null;
var auth = null;
var startUrl = null;
var targetId = null;
var cmpnId = null;

// 페이징 관련 변수
var currentPage = 0;
var pageSize = 10;
var isLoading = false;
var hasMoreMessages = true;

$().ready(function() {
    // 사용자 정보 및 채팅방 정보 초기화
    auth = $(".content-box").data("auth");
    myId = $(".content-box").data("usr-id");
    myName = $(".content-box").data("nm");
    myCmpny = $(".content-box").data("cmpny");
    startUrl = auth === 1004 ? '/adv' : '/blgr';
    chtRmId = $(".chat-main").data("chtrm-id");
    cmpnId = $(".content-title-text").data("cmpn-id");
    targetId = $(".content-box").data("target-id");

    console.log("채팅방 ID:", chtRmId);
    console.log("내 ID:", myId);
    console.log("권한:", auth);
    console.log("사업자명:", myCmpny);

    // WebSocket 연결
    connect();

    // 신고하기/나가기 메뉴 열기
    $(".chat-leave-btn-rm").on("click", function(event) {
        event.stopPropagation();
        $(".report-btn-rm, .leave-chat-btn-rm").show();
    });
    
    //신고하기 버튼 클릭 후 신고 페이지로 이동
    $(".report-btn-rm").on("click", function () {
        window.location.href = "/report/write/" + targetId; 
    });

    // 메시지 전송 버튼 클릭
    $(".chat-send-btn").off("click").on("click", function() {
        sendMessage();
    });

    // Enter 키로 메시지 전송
    $(".chat-text-input").off("keydown").on("keydown", function(e) {
        if (e.isComposing || e.keyCode === 229) {
            return;
        }
        
        if (e.key === "Enter" && !e.shiftKey) {
            e.preventDefault();
            sendMessage();
        }
    });

    // 파일 첨부 아이콘 클릭
    $(".file-insert").off("click").on("click", function() {
        $("#file-input").click();
    });

    // 파일 선택 시 바로 전송
    $("#file-input").off("change").on("change", function() {
        var files = this.files;
        if (files && files.length > 0) {
            sendFileMessage(files);
            $(this).val("");
        }
    });

    // 메뉴 외부 클릭시 메뉴 숨김
        $(document).on("click", function() {
            $(".report-btn-rm, .leave-chat-btn-rm").hide();
            $(".modal").fadeOut(200);
        });
    
    // 페이지 벗어날 때 WebSocket 연결 해제
    $(window).on("beforeunload", function() {
        disconnect();
    });

    //캠페인 상세로 이동
    $(".content-title-text").on("click", function() {
        window.location.href = "/campaigndetail/" + cmpnId;
    });
    
    // 스크롤 이벤트 초기화
    initScrollEvent();

    // 메시지 로드
    loadChatMessages(0);
});

/**
 * WebSocket 연결
 */
function connect() {
    if (stompClient !== null && stompClient.connected) {
        console.log("이미 WebSocket이 연결되어 있습니다.");
        return;
    }
    
    console.log("=== WebSocket 연결 시작 ===");
    
    var socket = new SockJS('/ws-chat');
    stompClient = Stomp.over(socket);
    
    stompClient.connect({}, function(frame) {
        console.log('WebSocket 연결 성공:', frame);
        
        // 채팅방 메시지 구독
        stompClient.subscribe('/topic/chat/' + chtRmId, function(message) {
            var chatMessage = JSON.parse(message.body);
            console.log('메시지 수신:', chatMessage);
            displayMessage(chatMessage);
            
            // 다른 사람의 메시지면 읽음 처리
            if (chatMessage.usrId !== myId) {
                markMessagesAsRead();
            }
        });

        // 읽음 처리 알림 구독
        stompClient.subscribe('/topic/chat/' + chtRmId + '/read', function(message) {
            var payload = JSON.parse(message.body);
            console.log('읽음 처리 알림:', payload);
            
            // 내가 보낸 메시지의 읽음 표시 업데이트
            if (payload.usrId !== myId) {
                updateReadStatus();
            }
        });

        // 채팅방 입장 시 읽음 처리
        markMessagesAsRead();
        
    }, function(error) {
        console.error('WebSocket 연결 실패:', error);
        // 5초 후 재연결 시도
        setTimeout(function() {
            console.log('WebSocket 재연결 시도...');
            connect();
        }, 5000);
    });
}

/**
 * WebSocket 연결 해제
 */
function disconnect() {
    if (stompClient !== null && stompClient.connected) {
        stompClient.disconnect(function() {
            console.log('WebSocket 연결 해제');
        });
    }
}

/**
 * 채팅 메시지 내역 로드 (페이징)
 */
function loadChatMessages(page) {
    if (isLoading || !hasMoreMessages) return;
    
    isLoading = true;
    
    
    
    $.get(startUrl + "/chat/room/" + chtRmId + "/messages", {
        page: page,
        size: pageSize
    }, function(response) {
        if (response.body.messages && response.body.messages.length > 0) {
            var messages = response.body.messages;
            hasMoreMessages = response.body.hasNext;
            
            var chatArea = $(".message-container");
            /*
            if (page === 0) {
                // 첫 로딩: 기존 메시지 삭제
                chatArea.empty();
                
                messages.forEach(function(message) {
                    var messageHtml = createMessageHtml(message);
                    chatArea.append(messageHtml);
                });
                
                // 스크롤을 최하단으로
                //DOM이 로딩되고 실행 - 껌뻑이는 현상 개선용
                requestAnimationFrame(function() {
                    scrollToBottom();
                });
            } */
            
            //메시지 로딩 시 스크롤 상단 찍었다가 아래로 내려오는 현상 수정
            if (page === 0) {
                var chatArea = $(".message-container");
                chatArea.css('visibility', 'hidden');
                chatArea.empty();
                
                messages.forEach(function(message) {
                    var messageHtml = createMessageHtml(message);
                    chatArea.append(messageHtml);
                });
                
                // 이미지 찾기
                var images = chatArea.find("img");
                
                function showChat() {
                    chatArea.scrollTop(chatArea[0].scrollHeight);
                    chatArea.css('visibility', 'visible');
                }
                
                if (images.length === 0) {
                    //이미지 없으면 즉시 표시
                    showChat();
                } else {
                    //이미지 있으면 로딩 대기
                    var loadedCount = 0;
                    var totalImages = images.length;
                    var hasShown = false;
                    
                    images.each(function() {
                        var img = this;
                        
                        if (img.complete) {
                            // 이미 로드됨
                            loadedCount++;
                        } else {
                            // 로딩 중
                            $(img).on("load error", function() {
                                loadedCount++;
                                checkComplete();
                            });
                        }
                    });
                    
                    //됐는지 체크 함수
                    function checkComplete() {
                        if (!hasShown && loadedCount === totalImages) {
                            hasShown = true;
                            showChat();
                        }
                    }
                    
                    //이미 다 로드된 경우
                    checkComplete();
                    
                    //안전장치: 2초 넘으면 강제 표시
                    setTimeout(function() {
                        if (!hasShown) {
                            hasShown = true;
                            showChat();
                        }
                    }, 2000);
                }
            }
            else {
                // 추가 로딩: 기존 메시지 위에 추가
                var oldScrollHeight = chatArea[0].scrollHeight;
                
                // 메시지를 역순으로 prepend (오래된 것부터)
                for (var i = messages.length - 1; i >= 0; i--) {
                    var messageHtml = createMessageHtml(messages[i]);
                    chatArea.prepend(messageHtml);
                }
                
                // 스크롤 위치 유지
                var newScrollHeight = chatArea[0].scrollHeight;
                chatArea.scrollTop(newScrollHeight - oldScrollHeight);
            }
            
            currentPage = page;
        }
        
        isLoading = false;
    }).fail(function(error) {
        console.error("메시지 로드 실패:", error);
        isLoading = false;
    });
}

/**
 * 스크롤 이벤트 - 상단 도달 시 이전 메시지 로드
 */
function initScrollEvent() {
    var container = $(".message-container");
    
    container.on("scroll", function() {
        // 스크롤이 맨 위에서 20px 이내면 이전 메시지 로드함
        if (container.scrollTop() < 20 && !isLoading && hasMoreMessages) {
            console.log("이전 메시지 로드...");
            loadChatMessages(currentPage + 1);
        }
    });
}

/**
 * 화면에 메시지 생성
 * sessionAttribute에 있는 나의 아이디 값이랑 메시지에 담긴 id를 비교함
 */
function createMessageHtml(message) {
    var formattedTime = formatChatDate(message.crtDt);
    
    // 내 메시지
    if (message.usrId === myId) {
        var readCheckImg = message.rdYn === "Y" 
            ? "<img class='read-check' src='/img/read_receipt.png' />" 
            : "";
        
        var fileImagesHtml = generateFileHtml(message, myId);
        
        return "<div class='my-message'>" +
                readCheckImg +
                "<div class='message-time'>" + formattedTime + "</div>" +
                "<div class='message-content-wrapper'>" +
                    fileImagesHtml +
                    (message.msgCn && message.msgCn.trim() 
                        ? "<div class='my-message-text'>" + message.msgCn + "</div>" 
                        : "") +
                "</div>" +
            "</div>";
    } 
    // 상대방 메시지
    else {
        //신고를 위해 상대방 아이디를 저장
        if(targetId === null) {
            targetId = message.usrId;
        }
        var senderName = message.cmpny && message.cmpny.trim() 
            ? message.cmpny 
            : message.usrNm;
        
        var fileImagesHtml = generateFileHtml(message, message.usrId);
        
        return "<div class='other-message'>" +
                "<div class='other-message-text-box'>" +
                    "<div class='other-name'>" + senderName + "</div>" +
                    "<div class='message-content-wrapper'>" +
                        fileImagesHtml +
                        (message.msgCn && message.msgCn.trim() 
                            ? "<div class='other-message-text'>" + message.msgCn + "</div>" 
                            : "") +
                    "</div>" +
                "</div>" +
                "<div class='message-time'>" + formattedTime + "</div>" +
            "</div>";
    }
}

/**
 * 파일 HTML 생성
 */
function generateFileHtml(message, usrId) {
    var html = "";
    
    if (message.attchGrpId && message.fileList && message.fileList.length > 0) {
        message.fileList.forEach(function(file) {
            var ext = file.flNm ? file.flNm.substring(file.flNm.lastIndexOf('.') + 1).toLowerCase() : '';
            var fileUrl = '/file/' + usrId + '/' + message.attchGrpId + '/' + file.flId;
            
            if (['jpg', 'jpeg', 'png', 'gif', 'webp'].includes(ext)) {
                html += "<a href='" + fileUrl + "' download='" + file.flNm + "'>" +
                    "<img class='chat-img' src='" + fileUrl + "' />" +
                    "</a>";
            } else {
                html += "<div class='chat-file'>" +
                    "<a href='" + fileUrl + "' download='" + file.flNm + "'>" +
                    file.flNm +
                    "</a>" +
                    "</div>";
            }
        });
    }
    
    return html;
}

/**
 * 메시지 화면에 표시 (WebSocket으로 받은 새로운 메시지)
 */
function displayMessage(message, shouldScroll) {
    var chatArea = $(".message-container");
    var messageHtml = createMessageHtml(message);
    
    chatArea.append(messageHtml);
    
    if (shouldScroll !== false) {
        scrollToBottom();
    }
}

/**
 * 메시지 전송
 */
function sendMessage() {
    console.log("=== sendMessage 호출 시작 ===");
    
    var messageContent = $(".chat-text-input").val().trim();
    
    if (!messageContent) {
        alert("메시지를 입력해주세요.");
        return;
    }

    if (!stompClient || !stompClient.connected) {
        alert("채팅 서버와 연결되지 않았습니다. 잠시 후 다시 시도해주세요.");
        connect();
        return;
    }

    var chatMessage = {
        chtRmId: chtRmId,
        usrId: myId,
        msgCn: messageContent,
        nm: myName,
        cmpny: myCmpny
    };

    console.log("메시지 전송 데이터:", chatMessage);
    stompClient.send("/app/chat/send", {}, JSON.stringify(chatMessage));
    console.log("=== sendMessage 전송 완료 ===");

    $(".chat-text-input").val("");
    $(".chat-text-input").focus();
}

/**
 * 파일 메시지 전송
 */
function sendFileMessage(files) {
    console.log("=== sendFileMessage 호출 ===", files);
    
    if (!files || files.length === 0) {
        return;
    }

    var formData = new FormData();
    
    for (var i = 0; i < files.length; i++) {
        formData.append("files", files[i]);
    }
    
    formData.append("chtRmId", chtRmId);
    formData.append("usrId", myId);
    formData.append("msgCn", "");
    formData.append("nm", myName);
    formData.append("cmpny", myCmpny);

    //파일 메시지 같은 경우는 MessageMapping이 아닌 PostMapping(실제 파일 데이터 때문에)
    $.ajax({
        url: startUrl + "/chat/message/send",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function(response) {
            console.log("파일 전송 성공:", response);
        },
        error: function(error) {
            console.error("파일 전송 실패:", error);
            alert("파일 전송에 실패했습니다.");
        }
    });
}

/**
 * 스크롤을 최하단으로 이동
 * 가장 최근 메시지가 제일 아래에 있을 것이므로 아래로 이동한 화면으로 시작함
 */
function scrollToBottom() {
    var container = $(".message-container");
    setTimeout(function() {
        container.scrollTop(container[0].scrollHeight);
    }, 100);
}

/**
 * 읽음 처리
 */
function markMessagesAsRead() {
    if (stompClient && stompClient.connected) {
        var payload = {
            chtRmId: chtRmId,
            usrId: myId
        };
        
        stompClient.send("/app/chat/read", {}, JSON.stringify(payload));
        console.log("읽음 처리 전송:", payload);
    }
}

/**
 * 읽음 상태 업데이트
 */
function updateReadStatus() {
    console.log("읽음 상태 업데이트");
    
    $(".my-message").each(function() {
        if ($(this).find(".read-check").length === 0) {
            $(this).prepend("<img class='read-check' src='/img/read_receipt.png' />");
        }
    });
}

/**
 * 채팅 메시지 시간 변환
 */
function formatChatDate(isoString) {
    if (!isoString) return "";

    const date = new Date(isoString);
    const now = new Date();

    const y = date.getFullYear();
    const m = date.getMonth() + 1;
    const d = date.getDate();
    const hh = date.getHours();
    const mm = date.getMinutes();

    const nowY = now.getFullYear();
    const nowM = now.getMonth() + 1;
    const nowD = now.getDate();

    const two = n => String(n).padStart(2, "0");

    if (y === nowY && m === nowM && d === nowD) {
        const ampm = hh < 12 ? "오전" : "오후";
        let hour12 = hh % 12;
        if (hour12 === 0) hour12 = 12;
        return `${ampm} ${hour12}:${two(mm)}`;
    }

    //메시지 날짜가 올해랑 같은 연도면 mm.dd로 표현함
    if (y === nowY) {
        return `${two(m)}.${two(d)}`;
    }

    return `${y}.${two(m)}.${two(d)}`;
}