<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@ include file="./common/dhead_login.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>中转站系统</title>
    <script>
        $(document).ready(function(){
            $("#LiErrorMsg").hide();
            $("#LiSuccessMsg").hide();
            document.onkeydown = function(event) {
                var e = event || window.event;
                if (e && e.keyCode == 13) {
                    $("#LiErrorMsg").hide();
                    $("#LiSuccessMsg").hide();
                    login();
                }
            };
        });
        function login(){
            var userName = $("#username").val();
            var userPwd = $("#password").val();
            if(userName == null || userPwd == null || userName == "" || userPwd == "") {
                $("#LiErrorMsg").show();
                $("#ErrorMsg").html("用户名和密码不能为空");
                return false;
            }
            loginServer(userName,userPwd);
        }

        function loginServer(userName,userPwd)
        {
            $("#LiErrorMsg").hide();
            $("#LiSuccessMsg").show();
            document.getElementById("SuccessMsg").innerHTML ="正在验证用户名和密码...";
            $.ajax({
                type:"POST",
                url:"./loginVali",
                data:'username='+userName+"&password="+userPwd,
                dataType: "json",
                success:function(data){
                    if(data.code == 200){
                        goIndex();
                    }else{
                        $("#LiErrorMsg").show();
                        $("#LiSuccessMsg").hide();
                        document.getElementById("ErrorMsg").innerHTML =data.message;
                    }
                }
            });
        }
        function goIndex(){
            $("#LiErrorMsg").hide();
            $("#LiSuccessMsg").show();
            document.getElementById("SuccessMsg").innerHTML ="验证通过,正在进入系统...";
            window.location.href="./index.html";
        }
    </script>
</head>

<body class="login_body" >
<div class="login_main">
    <div class="login_left" >
    </div>
    <div class="login_right" >
        <div class="ibox-content" style="height:100%">
            <form name="form">
                <div class="login_ibox">
                    <ul>

                        <li id="LiSuccessMsg" class="success_li">
                            <i class="fa fa fa-check "></i><span id="SuccessMsg"></span>
                        </li>
                        <li id="LiErrorMsg" class="error_li">
                            <i class="fa fa-exclamation-circle "></i><span id="ErrorMsg"></span>
                        </li>
                        <li >
                            <input  type="text" name="username"  id="username" placeholder="请输入用户名" class="form-control loginContent" >
                        </li>
                        <li >
                            <input  type="password" name="password" id="password" placeholder="请输入登录密码" class="form-control loginContent" >
                        </li>
                        <li >
                            <button type="submit" onclick="login();return false;" class="btn btn_submit loginbtn" >登录</button>
                        </li>
                    </ul>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>