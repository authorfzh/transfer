/******************************************************* 
* 
* 使用此JS脚本之前请先仔细阅读帮助文档! 
* 
* @author Fuly 
* @version 3.2.1 
* @date 2015/9/29 
* 
**********************************************************/
function mToken(obj){
	this.obj = obj;	
	
	this.SAR_OK										=	0;
	this.SAR_FALSE									= 	1;
	
	//分组加密算法标识
	this.SGD_SM1_ECB								=	0x00000101;
	this.SGD_SM1_CBC								=	0x00000102;
	this.SGD_SM1_CFB								=	0x00000104;
	this.SGD_SM1_OFB								=	0x00000108;
	this.SGD_SM1_MAC								=	0x00000110;
	this.SGD_SSF33_ECB								=	0x00000201;
	this.SGD_SSF33_CBC								=	0x00000202;
	this.SGD_SSF33_CFB								=	0x00000204;
	this.SGD_SSF33_OFB								=	0x00000208;
	this.SGD_SSF33_MAC								=	0x00000210;
	this.SGD_SM4_ECB								=	0x00000401;
	this.SGD_SM4_CBC								=	0x00000402;
	this.SGD_SM4_CFB								=	0x00000404;
	this.SGD_SM4_OFB								=	0x00000408;
	this.SGD_SM4_MAC								=	0x00000410;
	
	//非对称密码算法标识
	this.SGD_RSA									=	0x00010000;
	this.SGD_SM2_1									=	0x00020100; //ECC签名
	this.SGD_SM2_2									=	0x00020200; //ECC密钥交换
	this.SGD_SM2_3									=	0x00020400; //ECC加密
	
	//密码杂凑算法标识
	this.SGD_SM3									=	0x00000001;
	this.SGD_SHA1									=	0x00000002;
	this.SGD_SHA256									=	0x00000004;
	this.SGD_RAW									= 	0x00000080;
	this.SGD_MD5									= 	0x00000081;
	this.SGD_SHA384									= 	0x00000082;
	this.SGD_SHA512									= 	0x00000083;

	
	this.SGD_CERT_VERSION							=	0x00000001;
	this.SGD_CERT_SERIAL							= 	0x00000002;
	this.SGD_CERT_ISSUE								= 	0x00000005;
	this.SGD_CERT_VALID_TIME						= 	0x00000006;
	this.SGD_CERT_SUBJECT							= 	0x00000007;
	this.SGD_CERT_DER_PUBLIC_KEY					= 	0x00000008;
	this.SGD_CERT_DER_EXTENSIONS					= 	0x00000009;
	this.SGD_CERT_ISSUER_CN							= 	0x00000021;
	this.SGD_CERT_ISSUER_O							= 	0x00000022;
	this.SGD_CERT_ISSUER_OU							= 	0x00000023;
	this.SGD_CERT_SUBJECT_CN						= 	0x00000031;
	this.SGD_CERT_SUBJECT_O							= 	0x00000032;
	this.SGD_CERT_SUBJECT_OU						= 	0x00000033;
	this.SGD_CERT_SUBJECT_EMALL						= 	0x00000034;
	
	this.SGD_CERT_CRL								= 	0x00000041;
                                            

	this.SGD_DEVICE_SORT							= 	0x00000201;
	this.SGD_DEVICE_TYPE							= 	0x00000202;
	this.SGD_DEVICE_NAME							= 	0x00000203;
	this.SGD_DEVICE_MANUFACTURER					= 	0x00000204;
	this.SGD_DEVICE_HARDWARE_VERSION				= 	0x00000205;
	this.SGD_DEVICE_SOFTWARE_VERSION				= 	0x00000206;
	this.SGD_DEVICE_STANDARD_VERSION				= 	0x00000207;
	this.SGD_DEVICE_SERIAL_NUMBER					= 	0x00000208;
	this.SGD_DEVICE_SUPPORT_SYM_ALG					= 	0x00000209;
	this.SGD_DEVICE_SUPPORT_ASYM_ALG				= 	0x0000020A;
	this.SGD_DEVICE_SUPPORT_HASH_ALG				= 	0x0000020B;
	this.SGD_DEVICE_SUPPORT_STORANGE_SPACE			= 	0x0000020C;
	this.SGD_DEVICE_SUPPORT_FREE_SAPCE				= 	0x0000020D;
	this.SGD_DEVICE_RUNTIME							= 	0x0000020E;
	this.SGD_DEVICE_USED_TIMES						= 	0x0000020F;
	this.SGD_DEVICE_LOCATION						= 	0x00000210;
	this.SGD_DEVICE_DESCRIPTION						= 	0x00000211;
	this.SGD_DEVICE_MANAGER_INFO					= 	0x00000212;
	this.SGD_DEVICE_MAX_DATA_SIZE					= 	0x00000213;
	
	this.TRUE										=	1;
	this.FALSE										=	0;
	
	this.GM3000PCSC									=	0;
	this.GM3000										=	1;
	this.K7											=	2;
	this.K5											=	3;
	this.TF											=	4;
	
	
	var g_mTokenPlugin = null;
	var g_deviceNames = null;
	
	
	this.SOF_GetLastError = function()
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_GetLastError();
	}
	
	function isIe()
	{
		return ("ActiveXObject" in window);
	}
	
	function isMobile()
	{
		var browser = {
			versions : function() {
				var u = navigator.userAgent, app = navigator.appVersion;
				return {//移动终端浏览器版本信息   
					trident : u.indexOf('Trident') > -1, //IE内核  
					presto : u.indexOf('Presto') > -1, //opera内核  
					webKit : u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核  
					gecko : u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核  
					mobile : !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端  
					ios : !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端  
					android : u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器  
					iPhone : u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器  
					iPad : u.indexOf('iPad') > -1, //是否iPad    
					webApp : u.indexOf('Safari') == -1
				//是否web应该程序，没有头部与底部  
				};ie
			}(),
			language : (navigator.browserLanguage || navigator.language).toLowerCase()
		}
		
		if ((browser.versions.mobile) && (browser.versions.ios || browser.versions.android || browser.versions.iPhone || browser.versions.iPad))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	this.SOF_LoadLibrary = function(type)
	{
		var ret;
		if(g_mTokenPlugin == null)

		{			
				g_mTokenPlugin = new mTokenPlugin();
		}
		
		if(type == this.GM3000PCSC)
		{
			ret = g_mTokenPlugin.SOF_LoadLibrary("mtoken_gm3000_pcsc.dll", "libgm3000_pcsc.1.0.so", "libgm3000_pcsc.1.0.dylib");
		}
		else if(type == this.GM3000)
		{
			if(isMobile())
			{
				ret = g_mTokenPlugin.SOF_LoadLibrary("1", "mToken OTG", "com.longmai.security.plugin.driver.otg.OTGDriver");
			}
			else
			{
				ret = g_mTokenPlugin.SOF_LoadLibrary("mtoken_gm3000.dll", "libgm3000.1.0.so", "libgm3000.1.0.dylib");
			}
		}
		else if(type == this.K7)
		{
			ret = g_mTokenPlugin.SOF_LoadLibrary("mtoken_k7.dll", "libk7.1.0.so", "libk7.1.0.dylib");	
		}
		else if(type == this.K5)
		{
			if(isMobile())
			{
				ret = g_mTokenPlugin.SOF_LoadLibrary("2", "mToken K5 Bluetooth", "com.longmai.security.plugin.driver.ble.BLEDriver");
			}
			else
			{
				ret = g_mTokenPlugin.SOF_LoadLibrary("mtoken_k5.dll", "libk5.1.0.so", "libk5.1.0.dylib");
			}
		}
		else if(type == this.TF)
		{
			if(isMobile())
			{
				ret = g_mTokenPlugin.SOF_LoadLibrary("0", "mToken TF/SD Card", "com.longmai.security.plugin.driver.tf.TFDriver");
			}else
			{
				return -3;
			}
					
		}
		else
		{
			return -1;
		}
		
		if(ret != 0)
		{
//			g_mTokenPlugin = null;
			return -2;
		}
		return this.SAR_OK;
	};
	
	
	this.SOF_EnumDevice = function()
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		var array = g_mTokenPlugin.SOF_EnumDevice();
		if(array == null || array.length <= 0)
		{
			return null;
		}
		
		return array.split("||");
		
	};
	
	
	this.SOF_GetVersion = function()
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_GetVersion();
	};
	
	
	this.SOF_GetDeviceInstance = function(DeviceName, ApplicationName)
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_GetDeviceInstance(DeviceName, ApplicationName);
	};
	
	
	this.SOF_GetUserList = function()
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		var array = g_mTokenPlugin.SOF_GetUserList();
		if(array == null || array.length <= 0)
			return null;
			
		var list = new Array();
		var user = array.split("&&&");
		var length = user.length;
		for(var i = 0; i < length; i++)
		{
			list[i] = user[i].split("||");
		}
		
		return list;
	};
	
	
	this.SOF_ExportUserCert = function(ContainerName, KeySpec)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_ExportUserCert(ContainerName, KeySpec);
	};
	
	
	this.SOF_GetDeviceInfo = function(Type)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_GetDeviceInfo(Type);
	};
	
	
	this.SOF_GetCertInfo = function(Base64EncodeCert, Type)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_GetCertInfo(Base64EncodeCert, Type);
	};
	
	
	this.SOF_Login = function(PassWd)
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_Login(PassWd);
	};
	
	
	this.SOF_LogOut = function()
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_LogOut();
	};
	
	
	this.SOF_GetPinRetryCount = function()
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_GetPinRetryCount();
	};
	
	
	this.SOF_ChangePassWd = function(OldPassWd, NewPassWd)
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_ChangePassWd(OldPassWd, NewPassWd);
	};
	
	
	this.SOF_SetDigestMethod = function(DigestMethod)
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_SetDigestMethod(DigestMethod);
	};
	
	
	this.SOF_SetUserID = function(UserID)
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_SetUserID(UserID);
	};
	
	
	this.SOF_SetEncryptMethodAndIV = function(EncryptMethod, EncryptIV)
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_SetEncryptMethodAndIV(EncryptMethod, EncryptIV);
	};
	
	
	this.SOF_EncryptFileToPKCS7 = function(Cert, InFile, OutFile, type)
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_EncryptFileToPKCS7(Cert, InFile, OutFile, type);
	};
	
	
	this.SOF_SignFileToPKCS7 = function(ContainerName, InFile, OutFile, type)
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_SignFileToPKCS7(ContainerName, InFile, OutFile, type);
	};
	
	
	this.SOF_VerifyFileToPKCS7 = function(strPkcs7Data, InFilePath, type)
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_VerifyFileToPKCS7(strPkcs7Data, InFilePath, type);
	};
	this.SOF_DecryptFileToPKCS7 = function(ContainerName, keySpec, Pkcs7Data, InFile, OutFile, type)
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_DecryptFileToPKCS7(ContainerName, keySpec, Pkcs7Data, InFile, OutFile, type);
	};
	
	
	this.SOF_DigestData = function(ContainerName, nData, InDataLen)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_DigestData(ContainerName, nData, InDataLen);
	};
	
	this.SOF_SignData = function(ContainerName, ulKeySpec, InData, InDataLen)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_SignData(ContainerName, ulKeySpec, InData, InDataLen);
	};
	
	this.SOF_SignDataToPKCS7 = function(ContainerName, ulKeySpec, InData, InDataLen, ulDetached)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_SignDataToPKCS7(ContainerName, ulKeySpec, InData, InDataLen, ulDetached);
	};
	
	
	this.SOF_SignDataEx = function(ContainerName, ulKeySpec, InData, InDataLen)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_SignDataEx(ContainerName, ulKeySpec, InData, InDataLen);
	};
	
	
	this.SOF_VerifyDataToPKCS7 = function(Base64EncodeCert, InData, SignedValue)
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_VerifyDataToPKCS7(Base64EncodeCert, InData, SignedValue);
	};
	
	
	this.SOF_VerifySignedDataEx = function(Base64EncodeCert, InData, SignedValue)
	{
		if(g_mTokenPlugin == null)
		{
			return -1;
		}
		
		return g_mTokenPlugin.SOF_VerifySignedDataEx(Base64EncodeCert, InData, SignedValue);
	};
	
	
	this.SOF_EncryptData = function(Base64EncodeCert, InData, InDataLen)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_EncryptData(Base64EncodeCert, InData, InDataLen);
	};
	
	
	this.SOF_DecryptData = function(ContainerName, ulKeySpec, InData)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_DecryptData(ContainerName, ulKeySpec, InData);
	};
	
	
	this.SOF_GenRemoteUnblockRequest = function()
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_GenRemoteUnblockRequest();
	};
	
	
	this.SOF_GenResetpwdResponse = function(request, soPin, userPin)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_GenResetpwdResponse(request, soPin, userPin);
	};
	
	
	this.SOF_RemoteUnblockPIN = function(request)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_RemoteUnblockPIN(request);
	};
	
	
	this.SOF_SignDataToPKCS7 = function(ContainerName, ulKeySpec, InData, ulInDataLen, ulDetached)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_SignDataToPKCS7(ContainerName, ulKeySpec, InData, ulInDataLen, ulDetached);
	};


	this.SOF_VerifyDataToPKCS7 = function(strPkcs7Data, OriginalText, ulDetached)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_VerifyDataToPKCS7(strPkcs7Data, OriginalText, ulDetached);
	};
	
	this.SOF_ExportPubKey = function(containerName, cerType)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_ExportPubKey(containerName, cerType);
	}
	
	this.SOF_EncryptbyPubKey = function(strPubKey, strInput, cerType)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_EncryptbyPubKey(strPubKey, strInput, cerType);
	}
	
	this.SOF_DecryptbyPrvKey = function(containerName, cerType, strAsymCipher)
	{
		if(g_mTokenPlugin == null)
		{
			return null;
		}
		
		return g_mTokenPlugin.SOF_DecryptbyPrvKey(containerName, cerType, strAsymCipher);
	}
}


function mTokenPlugin(){

	var url = "http://127.0.0.1:51235/alpha";
	
	var xmlhttp ;
	function AjaxIO(json) {
		if(xmlhttp == null) {
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		if("https:" == document.location.protocol)
		{
			url = "https://127.0.0.1:51245/alpha";
		}
		xmlhttp.open("POST", url, false);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlhttp.send("json=" + json);
	}


	this.SOF_GetLastError = function()
	{
		var json = '{"function":"SOF_GetLastError"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.errorCode;
		}else{
			return -2;
		}
	}
	
	this.SOF_LoadLibrary = function(windows, linux, mac)
	{
		var json = '{"function":"SOF_LoadLibrary", "winDllName":"' + windows + '", "linuxSOName":"' + linux + '", "macDylibName":"' + mac + '"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;

		}else{
			return -2;
		}
		
	};
	
	
	this.SOF_EnumDevice = function()
	{
		var json = '{"function":"SOF_EnumDevice"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.array;
		}else{
			return null;
		}
		
	};
	
	
	this.SOF_GetVersion = function()
	{
		var json = '{"function":"SOF_GetVersion"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.version;
		}else{
			return -2;
		}
	};
	
	
	this.SOF_GetDeviceInstance = function(DeviceName, ApplicationName)
	{
		var json = '{"function":"SOF_GetDeviceInstance", "deviceName":"' + DeviceName + '", "applicationName":"' + ApplicationName + '"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;
		}else{
			return -2;
		}
		
	};
	
	
	this.SOF_GetUserList = function()
	{
		var json = '{"function":"SOF_GetUserList"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.array;
		}else{
			return null;
		}
	};
	
	
	this.SOF_ExportUserCert = function(ContainerName, KeySpec)
	{
		
		var json = '{"function":"SOF_ExportUserCert", "containerName":"' + ContainerName + '", "keySpec":' + KeySpec + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.cert;
		}else{
			return null;
		}
	};
	
	
	this.SOF_GetDeviceInfo = function(Type)
	{
		var json = '{"function":"SOF_GetDeviceInfo", "type":' + Type + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.info;
		}else{
			return null;
		}
		
	};
	
	
	this.SOF_GetCertInfo = function(Base64EncodeCert, Type)
	{
		var json = '{"function":"SOF_GetCertInfo", "base64EncodeCert":"' + Base64EncodeCert + '", "type":' + Type + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.info;
		}else{
			return null;
		}
		
	};
	
	
	this.SOF_Login = function(PassWd)
	{
		
		var json = '{"function":"SOF_Login", "passWd":"' + PassWd + '"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;
		}else{
			return -2;
		}
	};
	
	
	this.SOF_LogOut = function()
	{
		
		var json = '{"function":"SOF_LogOut"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;
		}else{
			return -2;
		}
	};
	
	
	this.SOF_GetPinRetryCount = function()
	{
		
		var json = '{"function":"SOF_GetPinRetryCount"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.retryCount;
		}else{
			return -2;
		}
	};
	
	
	this.SOF_ChangePassWd = function(OldPassWd, NewPassWd)
	{
		
		var json = '{"function":"SOF_ChangePassWd", "oldUpin":"' + OldPassWd + '", "newUpin":"' + NewPassWd + '"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;
		}else{
			return -2;
		}
	};
	
	
	this.SOF_SetDigestMethod = function(DigestMethod)
	{
		
		var json = '{"function":"SOF_SetDigestMethod", "digestMethod":' + DigestMethod + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;
		}else{
			return -2;
		}
	};
	
	
	this.SOF_SetUserID = function(UserID)
	{
		
		var json = '{"function":"SOF_SetUserID", "userID":"' + UserID + '"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;
		}else{
			return -2;
		}
	};
	
	
	this.SOF_SetEncryptMethodAndIV = function(EncryptMethod, EncryptIV)
	{
		
		var json = '{"function":"SOF_SetEncryptMethodAndIV", "encryptMethod":' + EncryptMethod + ', "encryptIV":"' + EncryptIV + '"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;
		}else{
			return -2;
		}
	};
	
	
	this.SOF_EncryptFileToPKCS7 = function(Cert, InFile, OutFile, type)
	{
		
		var json = '{"function":"SOF_EncryptFileToPKCS7", "cert":"' + Cert + '", "inFile":"' + InFile.replace(/\\/g, '\\\\') + '", "outFile":"' + OutFile.replace(/\\/g, '\\\\') + '", "type":' + type + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.envelopData;
		}else{
			return null;
		}
	};
	
	
	this.SOF_SignFileToPKCS7 = function(ContainerName, KeySpec, InFile, type)
	{
		
		var json = '{"function":"SOF_SignFileToPKCS7", "containerName":"' + ContainerName + '", "KeySpec":' + KeySpec + ', "inFile":"' + InFile.replace(/\\/g, '\\\\') + '", "type":' + type + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.signed;
		}else{
			return null;
		}
	};
	
	
	this.SOF_VerifyFileToPKCS7 = function(strPkcs7Data, InFilePath, type)
	{
		var json = '{"function":"SOF_VerifyFileToPKCS7", "strPkcs7Data":"' + strPkcs7Data + '", "inFile":"' + InFilePath.replace(/\\/g, '\\\\') + '", "type":' + type + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;
		}else{
			return -2;
		}
		
	};
	
	
	this.SOF_DecryptFileToPKCS7 = function(ContainerName, keySpec, Pkcs7Data, InFile, OutFile, type)
	{
		
		var json = '{"function":"SOF_DecryptFileToPKCS7", "containerName":"' + ContainerName + '", "keySpec":' + keySpec + ', "pkcs7Data":"' + Pkcs7Data + '", "inFile":"' + InFile.replace(/\\/g, '\\\\') + '", "outFile":"' + OutFile.replace(/\\/g, '\\\\') + '", "type":' + type + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;
		}else{
			return -2;
		}
	};
	
	
	this.SOF_DigestData = function(ContainerName, InData, InDataLen)
	{
		
		var json = '{"function":"SOF_DigestData", "containerName":"' + ContainerName + '", "inData":"' + InData + '", "inDataLen":' + InDataLen + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.digest;
		}else{
			return null;
		}
	};
	
	
	this.SOF_SignData = function(ContainerName, ulKeySpec, InData, InDataLen)
	{
		
		var json = '{"function":"SOF_SignData", "containerName":"' + ContainerName + '", "keySpec":' + ulKeySpec + ', "inData":"' + InData + '", "inDataLen":' + InDataLen + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.signed;
		}else{
			return null;
		}
	};
	
	
	this.SOF_SignDataEx = function(ContainerName, ulKeySpec, InData, InDataLen)
	{
		
		var json = '{"function":"SOF_SignDataEx", "containerName":"' + ContainerName + '", "keySpec":' + ulKeySpec + ', "inData":"' + InData + '", "inDataLen":' + InDataLen + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.signed;
		}else{
			return null;
		}
	};
	
	
	this.SOF_VerifySignedData = function(Base64EncodeCert, InData, SignedValue)
	{
		
		var json = '{"function":"SOF_VerifySignedData", "base64EncodeCert":"' + Base64EncodeCert + '", "inData":"' + InData + '", "signedValue":"' + SignedValue + '"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;
		}else{
			return -2;
		}
	};
	
	
	this.SOF_VerifySignedDataEx = function(Base64EncodeCert, InData, SignedValue)
	{
		var json = '{"function":"SOF_VerifySignedDataEx", "base64EncodeCert":"' + Base64EncodeCert + '", "inData":"' + InData + '", "signedValue":"' + SignedValue + '"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;
		}else{
			return -2;
		}
	};
	
	
	this.SOF_EncryptData = function(Base64EncodeCert, InData, InDataLen)
	{
		
		var json = '{"function":"SOF_EncryptData", "base64EncodeCert":"' + Base64EncodeCert + '", "inData":"' + InData + '", "inDataLen":' + InDataLen + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.encrypedData;
		}else{
			return null;
		}
	};
	
	
	this.SOF_DecryptData = function(ContainerName, ulKeySpec, InData)
	{
		
		var json = '{"function":"SOF_DecryptData", "containerName":"' + ContainerName + '", "keySpec":' + ulKeySpec + ', "inData":"' + InData + '"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.decryptedData;
		}else{
			return null;
		}
	};
	
	
	this.SOF_GenRemoteUnblockRequest = function()
	{
		
		var json = '{"function":"SOF_GenRemoteUnblockRequest"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.request;
		}else{
			return null;
		}
	};
	
	
	this.SOF_GenResetpwdResponse = function(request, soPin, userPin)
	{
		
		var json = '{"function":"SOF_GenResetpwdResponse", "request":"' + request + '", "soPin":"' + soPin + '", "userPin":"' + userPin + '"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.request;
		}else{
			return null;
		}
	};
	
	
	this.SOF_RemoteUnblockPIN = function(request)
	{
		
		var json = '{"function":"SOF_RemoteUnblockPIN", "request":"' + request + '"}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;
		}else{
			return null;
		}
	};
	
	
	this.SOF_SignDataToPKCS7 = function(ContainerName, ulKeySpec, InData, ulInDataLen, ulDetached)
	{
		
		var json = '{"function":"SOF_SignDataToPKCS7", "containerName":"' + ContainerName + '", "keySpec":' + ulKeySpec + ', "inData":"' + InData + '", "inDataLen":' + ulInDataLen + ', "detached":' + ulDetached + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.pkcs7;
		}else{
			return null;
		}
	};
	
	
	this.SOF_VerifyDataToPKCS7 = function(strPkcs7Data, OriginalText, ulDetached)
	{
		
		var json = '{"function":"SOF_VerifyDataToPKCS7", "pkcs7":"' + strPkcs7Data + '", "original":"' + OriginalText + '", "detached":' + ulDetached + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.rtn;
		}else{
			return null;
		}
	};

	//导出容器中指定类型的公钥
	this.SOF_ExportPubKey = function(containerName, keySpec)
	{
		var json = '{"function":"SOF_ExportPubKey", "containerName":"' + containerName + '", "keySpec":' + keySpec + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.pubKey;
		}else{
			return null;
		}
	}
	
	//公钥加密
	this.SOF_EncryptbyPubKey = function(strPubKey, strInput, cerType)
	{
		var json = '{"function":"SOF_EncryptbyPubKey", "pubKey":"' + strPubKey + '", "asymPlain":"' + strInput + '", "keySpec":' + cerType + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.asymCipher;
		}else{
			return null;
		}
	}
	
	//私钥解密
	this.SOF_DecryptbyPrvKey = function(containerName, cerType, strAsymCipher)
	{
		var json = '{"function":"SOF_DecryptbyPrvKey", "containerName":"' + containerName + '", "asymCipher":"' + strAsymCipher + '", "keySpec":' + cerType + '}';
		try
		{
			AjaxIO(json);
		}
		catch (e)
		{
			return -3;
		}
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = eval("(" + xmlhttp.responseText + ")");
			return obj.asymPlain;
		}else{
			return null;
		}
	}
}
