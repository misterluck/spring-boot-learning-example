var websocket = null;

function connect() {
    var username = $('#username').val();
    if (username != null && username != "") {
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://127.0.0.1:8097/websocket/ID="+$("#username").val());
        }else if('MozWebSocket' in window) {
            websocket = new MozWebSocket("ws://127.0.0.1:8097/websocket/ID="+$("#username").val());
        }else {
            alert("该浏览器不支持websocket");
        }

        var response = $('#response');
        websocket.onopen = function (message) {
            console.log("连接成功" + message.data);
            response.html("连接成功");
        }

        websocket.onmessage = function (message) {
            console.log("message:" + message.data);
            response.html(message.data);
        }

        websocket.onclose = function (message) {
            console.log("连接中断" + message.data);
            response.html("连接中断");
        }

    }else {
        alert("请输入您的昵称!");
    }

}

function sendMessage() {
    websocket.send($("#message").val());
}