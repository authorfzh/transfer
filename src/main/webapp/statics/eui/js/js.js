$(document).ready(function(){
    var iframeId = "page/demo.html";// 记录当前的iframe的id
    // 这个是左侧下拉列表
    var arrlist = [
      {
          "title":"表单",
          "className":["list"],
          "list":[
              {
                "title":"基本表单",
                "src":"page/x_form/basic_form.html"
                /* "src":"page/x_form/form.html"*/
              },{
                "title":"输入状态",
                "src":"page/x_form/basic_form_input.html"
              },{
                "title":"下拉框",
                "src":"page/x_form/basic_form_select.html"
              },{
                "title":"文本域",
                "src":"page/x_form/basic_form_textarea.html"
              }
          ]
      },{
          "title":"按钮",
          "className":["list"],
          "list":[
              {
                  "title":"按钮",
                  "src":"page/button/iframe-button.html"
              }
          ]
      },{
          "title":"菜单",
          "className":["list"],
          "list":[
              {
                  "title":"二级菜单",
                  "src":"page/menu/index.html"
              }
          ]
      },{
        "title":"表格",
        "className":["list"],
        "list":[
            {
                "title":"分页表格",
                "src":"page/table/index.html"
            },{
                "title":"树表格",
                "src":"page/table/treeIndex.html"
            },{
                "title":"搜索表格",
                "src":"page/table/searchIndex.html"
            }
        ]
    },{
      "title":"其他",
      "className":["list"],
      "list":[
          {
              "title":"登录页",
              "src":"page/other/index.html"
          },{
            "title":"首页",
            "src":"page/other/home_index.html"
        }
      ]
    }
  ];
    var iframelist = [];
    function iframelistfun(){// 这里获取所有的iframe的src属性,做成数组
      arrlist.forEach((item,index)=>{
        for(var i=0;i<item.list.length;i++){
          iframelist.push(item.list[i].src);
        }
      })
    }
    iframelistfun();
  // 渲染左边导航栏的参数
  $.each(arrlist,(index,item)=>{
      var div = $("<div></div>").appendTo($(".sidebar-list"))
      var li = $("<a  class='list'><div>"+arrlist[index].title+"</div></a>").appendTo(div);
      var ul = $("<ul class='list-show'></ul>").appendTo(div);
      for(var i=0;i<arrlist[index].list.length;i++){
          $("<li><a  data-src='"+arrlist[index].list[i].src+"'>"+arrlist[index].list[i].title+"</a></li>").appendTo(ul)
      }
  })
  // 搜索框事件
    $(".seach-input").focus(function(){
      $(".seach").css("background"," rgba(255, 255, 255, 1)");
    });
    $(".seach-input").blur(function(){
      $(".seach").css("background"," rgba(255, 255, 255, 0.5)");
    });

  // 下拉清单事件
    $(".sidebar-list a div").click(function(){
      $(this).parent().parent().siblings().find($("ul")).slideUp();
      $(this).parent().next().slideToggle();
      $(".sidebar-list div>a").removeClass("cur")
      $(this).parent().addClass("cur")
    })
  // 点击下拉列表显示不同的iframe
    $(".sidebar-list ul li a").click(function(){
      iframeId = $(this).attr("data-src");
      $("iframe").attr("src",iframeId);
    })

  // 左右切换事件
    $(".content-box .back").click(function(){
      for(var i=0;i<iframelist.length;i++){
        if(iframelist[i] ==  iframeId&&iframelist[i-1] !=  undefined){
          iframeId=iframelist[i-1];
        }else{
          alert("页面不存在")
          return;
        }
      }
      console.log(iframeId)
      $("iframe").attr("src",iframeId);
    })
    $(".content-box .next").click(function(){
      for(var i=0;i<iframelist.length;i++){
        if(iframelist[i] ==  iframeId&&iframelist[i+1] !=  undefined){
          iframeId=iframelist[i+1];
        }else{
          alert("页面不存在")
          return;
        }
      }
      console.log(iframeId)
      $("iframe").attr("src",iframeId);
    })
});

