(function(D){
	var doc = document,
		src = D.url,
		imgSrc = D.imgUrl,
		ssoName = new Array(),
		ssoUrl = new Array(),
		script = doc.createElement('script'),
		img = doc.createElement("img"),
		xt = doc.createElement("div"),
		B = doc.createElement("div");
		document.getElementsByTagName('body')[0].appendChild(B);
		B.setAttribute("id","div1");
		B.style.position = "absolute";
		B.style.width = D.width;
		B.style.height = "200px";
		B.style.top = D.Y;
		if(D.X == 0){
			B.style.left = "10px";
		}else{
			B.style.right = "10px";
		}
		script.setAttribute("src",src);
		script.type="text/javascript";    
		doc.getElementsByTagName('head')[0].appendChild(script);
	D.init = function(){
		//加载图片
		img.setAttribute("src",imgSrc);
		B.appendChild(img);
		//加载title
		B.appendChild(D.creatTitleBtn());
		xt.setAttribute("id","xtdiv");
		xt.setAttribute("style","display:block;");
		B.appendChild(xt);
	}
		
	//创建系统按钮
		D.creatBtn = function (value){
			var btn = doc.createElement("input");
			btn.setAttribute("type","button");
			btn.setAttribute("id","titleBtn");
			btn.style.width = D.width;
			btn.style.height = D.height;
			btn.style.backgroundColor = D.back;
			btn.style.fontSize = D.fontSize;
			btn.style.color = D.color;
			btn.style.marginTop = D.marginTop;
			btn.style.borderRadius = D.borderRadius;
			btn.value = value;
			return btn;
		}		
		
	//创建title
		D.creatTitleBtn = function(){
			var titleBtn = doc.createElement("button");
			titleBtn.style.width = D.titleWidth;
			titleBtn.style.height = D.titleHeight;
			titleBtn.style.backgroundColor = D.titleBack;
			titleBtn.style.fontSize = D.titleFontSize;
			titleBtn.style.color = D.titleColor;
			titleBtn.style.marginTop = D.titleMarginTop;
			titleBtn.style.borderRadius = D.titleBorderRadius;
			titleBtn.innerText = D.title;
			titleBtn.onclick = function (){
				if(xt.getAttribute("style") == "display:block;"){
					xt.setAttribute("style","display:none;");
				}else{
					xt.setAttribute("style","display:block;");
				}
			}
			return titleBtn;
		}
		
	//为div添加事件  
	　　B.onmousedown = function(ev){
	　　　　var oevent = ev || event;
	　　　　var distanceX = oevent.clientX - B.offsetLeft;
	　　　　var distanceY = oevent.clientY - B.offsetTop;
	　　　　doc.onmousemove = function(ev){
	　　　　　　var oevent = ev || event;
	　　　　　　B.style.left = oevent.clientX - distanceX + 'px';
	　　　　　　B.style.top = oevent.clientY - distanceY + 'px'; 
	　　　　};
	　　　　doc.onmouseup = function(){
	　　　　　　doc.onmousemove = null;
	　　　　　　doc.onmouseup = null;
	　　　　};
	};　
})(easyliao);

//回调函数
var jsonpCallback = function(result) {
	easyliao.init();
	for(var key in result){
		if('error' == key){
			//window.location.href = easyliao.url;
			
			alert(result[key]);
			window.location.href = "http://localhost/sso/login";
			return;	
		}
		var btn1 = easyliao.creatBtn(key);
			btn1.setAttribute("onclick","javascript:window.open('"+result[key]+"')");
			var xtBtn = document.getElementById("xtdiv")
			xtBtn.appendChild(btn1);
	}
}　
