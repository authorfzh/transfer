  /*验证身份证合法性*/
  var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子   
  var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X   
  function IdCardValidate(idCard) {
      idCard = trim(idCard.replace(/ /g, ""));//去掉字符串头尾空格                     
      if (idCard.length == 15) {
          return isValidityBrithBy15IdCard(idCard);//进行15位身份证的验证    
      } else if (idCard.length == 18) {   
          var a_idCard = idCard.split("");// 得到身份证数组   
          if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   //进行18位身份证的基本验证和第18位的验证
              return true;   
          }else {   
              return false;   
          }   
      } else {   
          return false;   
      }   
  }   
  
  /**  
   * 判断身份证号码为18位时最后的验证位是否正确  
   * @param a_idCard 身份证号码数组  
   * @return  
   */  
  function isTrueValidateCodeBy18IdCard(a_idCard) {   
      var sum = 0;                             // 声明加权求和变量   
      if (a_idCard[17].toLowerCase() == 'x') {   
          a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作   
      }   
      for ( var i = 0; i < 17; i++) {   
          sum += Wi[i] * a_idCard[i];            // 加权求和   
      }   
      valCodePosition = sum % 11;                // 得到验证码所位置   
      if (a_idCard[17] == ValideCode[valCodePosition]) {   
          return true;   
      } else {   
          return false;   
      }   
  }   
  
  /**  
    * 验证18位数身份证号码中的生日是否是有效生日  
    * @param idCard 18位书身份证字符串  
    * @return  
    */  
  function isValidityBrithBy18IdCard(idCard18){   
      var year =  idCard18.substring(6,10);   
      var month = idCard18.substring(10,12);   
      var day = idCard18.substring(12,14);   
      var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
      // 这里用getFullYear()获取年份，避免千年虫问题   
      if(temp_date.getFullYear()!=parseFloat(year)||temp_date.getMonth()!=parseFloat(month)-1||temp_date.getDate()!=parseFloat(day)){   
          return false;   
      }else{   
    	  var birthDate = new Date(year, month, day);
      	  var myDate = new Date();
      	  var myYear = myDate.getFullYear();
	      var myMonth = myDate.getMonth()+1;
	      var myDay = myDate.getDate();
	      var date = new Date(myYear-200, myMonth, myDay);
	      if(birthDate < date){
	    	  return false;   
	      }else{
	    	  return true;   
	      }
      }   
  }   
  /**  
   * 验证15位数身份证号码中的生日是否是有效生日  
   * @param idCard15 15位书身份证字符串  
   * @return  
   */  
  function isValidityBrithBy15IdCard(idCard15){   
        var year =  idCard15.substring(6,8);   
        var month = idCard15.substring(8,10);   
        var day = idCard15.substring(10,12);   
        var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
        // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
        if(temp_date.getYear()!=parseFloat(year)||temp_date.getMonth()!=parseFloat(month)-1||temp_date.getDate()!=parseFloat(day)){   
            return false;   
        }else{  
        	var birthDate = new Date(year, month, day);
        	var myDate = new Date();
			var myYear = myDate.getFullYear();
			var myMonth = myDate.getMonth()+1;
			var myDay = myDate.getDate();
			var date = new Date(myYear-200, myMonth, myDay);
        	if(birthDate < date){
        		return false;   
        	}else{
        		return true;   
        	}
        }   
  }

  //去掉字符串头尾空格   
  function trim(str) {   
      return str.replace(/(^\s*)|(\s*$)/g, "");   
  }
  
  //去掉字符串中所有空格   
  function trimAll(str) {   
      return str.replace(/\s+/g,"");  
  }
  
  //字符串中是否包含中文
  function  isChineseStr(str) {  
      return (validateRegExp.isChinese).test(str);  
  }
  
  //判断去掉空格后的字符串
  function  getAfterTrimStr(str){
	  if(str){
		  if((validateRegExp.isEnglish).test(str)){
			  return trim(str);
		  }else{
			  return trimAll(str);
		  }
	  }
	  return "";
  }
  
  //身份证号的过滤
  function  getIdNumberStr(idNumber){
	  if(idNumber){
		 return idNumber.toUpperCase().replace('×','X');
	  }
	  return "";
  }

  /*验证的正则表达式*/
  var validateRegExp = {
  	//name:/^[\u4e00-\u9fa5]{2,20}$|^[a-zA-Z\.\s]{1,20}$/,//名字验证  
	//name:/(^[\u0391-\uFFE5]{2,16}$)|(^[a-zA-Z.\s]{2,28}$)/,	
	name:/(^[\u4E00-\u9FA5]{2,16}$)|(^[a-zA-Z\s]{2,28}$)/,
  	mobile:/^(13|14|15|17|18|19|16)[0-9]{9}$/,//手机
  	email:/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.([a-zA-Z0-9_-])+)+$/, //邮件
  	tel:/^(0[1-9][0-9])-\d{8}$|^(0[1-9]{3}-(\d{7,8}))$/,//固话
  	tell:/^\d{3}-\d{8}$|\d{4}-\d{7,8}$/,//固话
  	heigth:/^[1-2]+([0-9]{2})$/,//身高
  	numchar:/^(([a-zA-Z0-9]+)|([0-9a-zA-Z]+))[a-zA-Z0-9]*$/,//证书字母数字组合
  	//base:"^[A-Za-z0-9\\u4e00-\\u9fa5]+$",//基本数据验证^
  	base:/[,<:;]/,//基本数据验证
  	num:/^[0-9]*$/,//数字,
  	telmobile:/^\d{3}-\d{8}|\d{4}-\d{7,8}|(13|14|15|16|17|18|19)[0-9]{9}/,
  	password:/^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,20}$/, //密码
  	cardNumber:/^(\d{16}|\d{19})$/,  //银行卡号验证
  	doubleNum:/^[0-9]+(.[0-9]{1,2})?$/,  //验证小数点后两位数字
  	//passportNumber:/^1[45][0-9]{7}|G[0-9]{8}|E[0-9]{7,8}|P[0-9]{7,8}|S[0-9]{7,8}|D[0-9]{7,8}$/,
  	passportNumber:/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{3,16}$/,//护照号码
  	firmName:/^([A-Za-z]|[\u4E00-\u9FA5]){2,30}$/,  //企业名称
  	idCardNumber:/^[0-9a-zA-Z]+$/, //证件号码
  	ename:/^[a-zA-Z\s]{2,28}$/,  //英文名称
  	postCode:/^[0-9]\d{5}$/, //邮编
  	integer:/^[1-9]\d*$/, // 正整数
  	date:/^(\d{4})-(\d{2})-(\d{2})$/,     // 日期
  	care:/^[\u4e00-\u9fa5]/,              // 职业  汉字
  	phone:/^[0-9]\d{6,7}$/,               //【1】固话验证	   
  	phoneTwo:/^0[1-9]{3}$|^0[1-9][0-9]$/,  //【0】固话验证 
  	tranum:/^\+?(0|[1-9][0-9]*)$/,	//出行次数
  	commnum:/^[1-9]\d*|0$/,	//年收入
  	benefitProportion:/^([1-9]|[1-9]\d|100)$/,  //受益比例 1 - 100的正整数
  	clerkCode:/^[0-9]{8}$/,  //业务员工号
  	isChinese:/[\u4e00-\u9fa5]/g,
  	isEnglish:/[A-Za-z]/g
  };
  
  /**
   * 密码安全级别验证
   * 6位包含数字、英文 低级
   * 6-8位含数字、英文 中级
   * 6-8位数字、英文、特殊符号中级
   * 8-10位数字、英文 中级 
   * 8-10位数字、英文、特殊符号高级 
   * 10以上包含数字英文高级
   */
  function AnalyzePasswordSecurityLevel(password){
	    var Level1= 0;
	    var Level2 = 1;
	    var len = password.length;
	    if (len < 6||len >20||password==""||password==undefined){
	        return 0;
	    }else {
	        if((/[a-z]/.test(password))|| (/[A-Z]/.test(password))){
	        	Level1++;    //lowercase
	        }
	        if(/[0-9]/.test(password)){
	        	Level1++;    //digital
	        }
	        if(containSpecialChar(password)){
	        	Level2++;    //specialcase
	        }
	        
	        if(Level1<=1){
	        	return 0;
	        }else{
	        	if(len==6){
	        		return 1;
	        	}else if(len>6&&len<=8){
	        		return 2;
	        	}else if(len>8){
	        		if(Level2==1){
	        			return 3;
	        		}else if(len>10){
	        			return 3;
	        		}else{
	        			return 2
	        		}
	        	}
	        	
	        }
	        
	    }
  }

  //是否包含特殊字符
  function containSpecialChar(str){   
    var containSpecial = RegExp(/[(\ )(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\-)(\_)(\+)(\=)(\[)(\])(\{)(\})(\|)(\\)(\;)(\:)(\')(\")(\,)(\.)(\/)(\<)(\>)(\?)(\)]+/);
    return (containSpecial.test(str));   
  }