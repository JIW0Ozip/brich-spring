var stompClient = null;
var chtRmIdList = [];
$().ready(function() {
    var chtRmIdString = $(".content-box").data("chtid-list");
    console.log(chtRmIdString);
    chtRmIdList = chtRmIdString
          .substring(1, chtRmIdString.length - 1) 
          .split(',')                              
          .map(function(id) { 
              return id.trim().replace(/'/g, '').replace(/"/g, ''); 
          });
      
      console.log("파싱 결과:", chtRmIdList);
    
    connect();
    $(window).on("beforeunload", function() {
        disconnect();
    });

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
        //모든 채팅방을 구독
        if (chtRmIdList && chtRmIdList.length > 0) {
            for (var i = 0; i < chtRmIdList.length; i++) {
                var roomId = chtRmIdList[i];
                console.log('구독 중:', roomId);

                stompClient.subscribe('/topic/chat/' + roomId, function(message) {
                    var chatMessage = JSON.parse(message.body);
                    console.log('메시지 수신:', chatMessage);

                    // 채팅방 목록 새로고침
                    loadChatRoomList(currentFilter, currentPage);
                });
            }
            console.log('총 ' + chtRmIdList.length + '개 채팅방 구독 완료');
        }

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
