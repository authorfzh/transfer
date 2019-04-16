$(".leftsidebar_box dt").css({"background-color":"rgb(84, 92, 100)"});
$(".leftsidebar_box dt img").attr("src","images/left/select_xl01.png");
$(function(){
    $(".leftsidebar_box dd").hide();
    $(".leftsidebar_box dt").eq(0).css({"background-color": "#279af2"});
    $(".leftsidebar_box dl").eq(0).find("dd").show();
    $(".leftsidebar_box dl").eq(0).find("dd").eq(0).css({"background-color": "#434f55"});
    $(".leftsidebar_box dt").click(function(){
        $(".leftsidebar_box dd").hide();
        $(".leftsidebar_box dt").css({"background-color":"rgb(84, 92, 100)"});
        $(".leftsidebar_box dd").css({"background-color":""});
        $(this).css({"background-color": "#279af2"});
        $(this).parent().find('dd').removeClass("menu_chioce");
        $(".leftsidebar_box dt img").attr("src","images/left/select_xl01.png");
        $(this).parent().find('img').attr("src","images/left/select_xl.png");
        $(".menu_chioce").slideUp(); 
        $(this).parent().find('dd').slideToggle();
        $(this).parent().find('dd').addClass("menu_chioce");
    });
    $(".leftsidebar_box dd").click(function(){
        $(this).css({"background-color": "#434f55"});
        $(".leftsidebar_box dd").not(this).css({"background-color": ""});
    });
    $(".leftsidebar_box dd").click(function(){
        $('#iFrame').attr('src',$(this).attr('tab_url'));
    });
    //用户资料
    $('#personal_data').click(function(){
        layer.open({
            title:'个人资料',
            type: 2,
            area: ['70rem', '56rem'],
            fixed: true,
            maxmin: true,
            content: 'model/user-show.html'
        });
    });
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;
        
        //日期
        laydate.render({
        elem: '#date'
        });
        laydate.render({
        elem: '#date1'
        });
        
        //自定义验证规则
        form.verify({
        title: function(value){
            if(value.length < 5){
            return '标题至少得5个字符啊';
            }
        }
        ,pass: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
        ,content: function(value){
            layedit.sync(editIndex);
        }
        });
        
        //监听提交
        form.on('submit(demo1)', function(data){
        layer.alert(JSON.stringify(data.field), {
            title: '最终的提交信息'
        })
        return false;
        });    
    });
    //修改密码
    $('#change_password').click(function(){
        layer.open({
            title:'修改密码',
            type: 2,
            area: ['70rem', '30rem'],
            fixed: true, 
            maxmin: true,
            content: 'model/password-edit.html'
        });
    });
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;
        
        //日期
        laydate.render({
        elem: '#date'
        });
        laydate.render({
        elem: '#date1'
        });
        
        //自定义验证规则
        form.verify({
        title: function(value){
            if(value.length < 5){
            return '标题至少得5个字符啊';
            }
        }
        ,pass: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
        ,content: function(value){
            layedit.sync(editIndex);
        }
        });
        
        //监听提交
        form.on('submit(demo2)', function(data){
        layer.alert(JSON.stringify(data.field), {
            title: '最终的提交信息'
        })
        return false;
        });    
    });
    //安全退出
    $('#affirm_info').click(function(index){
        layer.confirm('确认要退出吗', {
            btn: ['确认','取消']
        }, function(){
            layer.msg('退出成功', {
            time: 2000, //2s后自动关闭
        });
        });
    });
})