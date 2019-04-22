  // 点击复制事件
var copyCode = function(){
    var text=document.getElementById("code");
            text.select(); // 选择对象
            document.execCommand("Copy"); // 执行浏览器复制
}
