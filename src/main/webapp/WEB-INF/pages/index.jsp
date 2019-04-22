<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@ include file="./common/dhead_index.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>中转站系统</title>
    <script type="text/javascript">
        var menuJson = <%=request.getAttribute("menuJson") %>;
    </script>
</head>

<body class="index_body">

    <!-- 头部信息  -->
    <nav class="navbar navbar-static-top header" role="navigation" style="min-height: 50px;box-shadow: 0px 0px 8px 0 #425780;">
    </nav>
    <!-- 左侧菜单盒子 -->
    <nav class="main-menu"  id="left_menu">
    </nav>
    <!-- 二级导航菜单 -->
    <nav   class="navbar-default navbar-side" role="navigation" >
    </nav>

    <!-- 右侧内容盒子   -->
    <div  class="gray-bg page-wrapper" >
        <!-- 加载  -->
        <iframe  src=""  id="myiframe" scrolling="auto"  frameborder="0" style="min-width:860px;"> </iframe>
    </div>


</body>
</html>