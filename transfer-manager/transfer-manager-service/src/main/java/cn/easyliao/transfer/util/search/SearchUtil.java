package cn.easyliao.transfer.util.search;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.easyliao.transfer.common.utils.CommonUtil;



public class SearchUtil {
	
	protected final static Logger LOG = LoggerFactory
			.getLogger(SearchUtil.class);

	public static final String ITEMS = "items";
	public static final String ITEM = "item";
	public static final String LI = "li";
	public static final String LIID = "id";
	public static final String LICLASS = "class";
	public static final String TYPE = "type";
	public static final String TITLE = "title";
	public static final String LITYPE_STRING = "string";
	public static final String LITYPE_COMBOBOX = "combobox";
	public static final String LITYPE_INT = "int";
	public static final String LITYPE_DATE = "date";
	public static final String LITYPE_DOUBLE = "double";
	public static final String LITYPE = "type";
	
	public static final String LITYPE_NO = "no";
	public static final String LISEARCH_NAME  = "search";
	public static final String LI_NAME  = "search";
	
	public static final String LISEARCH_TYPE  = "searchtype";
	public static final String LISEARCHTYPE_GT = "gt";
	public static final String LISEARCHTYPE_LINK = "%";
	public static final String LISEARCHTYPE_MULLINK = "%%";
	public static final String LISEARCHTYPE_EG = "eq";
	public static final String LISEARCHTYPE_LT = "lt";
	
	

	public static final int  HIDDENFLAG = 1;
	public static final int  LINETEXTFLAG = 2;
	public static final int  DATETIIMEFALSE = 3;
	public static final int  MULTEXTFLAG = 4;
	public static final int  NUMBERFLAG = 5;
	public static final int  COMBOBOXFLAG = 9;
	
	
	
	public static final String HTMLFALG = "datasearch";
	
	
	private static String  hiddenHtml = "<input  type=\"hidden\" name=\"%name%\" value=\"%value%\"></input>";
	private static String linetexthtml  = "<span class='"+HTMLFALG+"_lable'>%labtext%</span><input  class=\"easyui-textbox "+HTMLFALG+"_input\" type=\"text\" name=\"%name%\" value=\"%value%\" data-options=\"required:false\"/>";
	private static String lineinthtml  = "<span class='"+HTMLFALG+"_lable'>%labtext%</span><input  class=\"easyui-numberbox "+HTMLFALG+"_input\" type=\"text\" name=\"%name%\" value=\"%value%\" data-options=\"required:false\"/>";
	
	private static String comboboxhtml  = "<span class='"+HTMLFALG+"_lable'>%labtext%</span><input  class=\"easyui-combobox "+HTMLFALG+"_input\" type=\"text\" name=\"%name%\" value=\"%value%\" data-options=\"%param%\"/>";
	
	
	private static String teahtml  = "<span class='"+HTMLFALG+"_lable'>%labtext%</span><input  class=\"easyui-textbox "+HTMLFALG+"_input\" type=\"text\" name=\"%name%\" value=\"%value%\" data-options=\"required:false\"/>";
	
	private static String datehtml  = "<span class='"+HTMLFALG+"_lable'>%labtext%</span><input  class=\"easyui-datebox "+HTMLFALG+"_input\" type=\"text\" name=\"%name%\" value=\"%value%\" data-options=\"required:false\"/>";

	
	
	
	
	
	/**
	 * 根据不同的值类型返回不同的html值
	 * @param type
	 * @param labtext
	 * @param name
	 * @param value
	 * @return
	 */
	private static String GetHtmlByType( int type , String labtext, String name ,Object value ,String param)
	{
		String str = "";
		switch( type )
		{
		case HIDDENFLAG:
			
			str = hiddenHtml.replaceAll("%name%", name);
			str = str.replaceAll("%value%", ""+value);
			
			break;
		case COMBOBOXFLAG:
			
			str = comboboxhtml.replaceAll("%labtext%", labtext);
			str = str.replaceAll("%name%", name);
			str = str.replaceAll("%value%",(String)value);
			str = str.replaceAll("%param%", param);
			break;
			
		case NUMBERFLAG:
			str = lineinthtml.replaceAll("%labtext%", labtext);
			str = str.replaceAll("%name%", name);
			str = str.replaceAll("%value%", StringEscapeUtils.unescapeHtml4(""+value));
			break;
		case LINETEXTFLAG:
			
			str = linetexthtml.replaceAll("%labtext%", labtext);
			str = str.replaceAll("%name%", name);
			str = str.replaceAll("%value%", StringEscapeUtils.unescapeHtml4(""+value));
			break;
		case DATETIIMEFALSE:
			str = datehtml.replaceAll("%labtext%", labtext);
			
			str = str.replaceAll("%name%", name);
			str = str.replaceAll("%value%", ""+value);
			
			break;
		case MULTEXTFLAG:
			str = datehtml.replaceAll("%labtext%", labtext);
			
			str = str.replaceAll("%name%", name);
			str = str.replaceAll("%value%", StringEscapeUtils.unescapeHtml4(""+value));
			break;
		
		
		  default:
			  break;
		}
		
		return str;
	}
	
	
	private static Element GetChildById(Element parent, String nodename,
			String idname) {
		List<Element> lists = parent.getChildren(nodename);
		if (lists.size() <= 0)
			return null;
		for (int i = 0; i < lists.size(); i++) {
			Element ele = lists.get(i);
			String id = ele.getAttributeValue(LIID);
			if ((id != null) && (id.equals(idname)))
				return ele;
		}
		return null;

	}

	
	public static int loadSearchCondition( String tempfile, String itemname, Object criteria, Object searchItem  )
	{
		
		int  retval = 0;
		SAXBuilder sb = new SAXBuilder();
		Document doc;
	  try {
		doc = sb.build(SearchUtil.class.getResourceAsStream(tempfile));
		Element root = doc.getRootElement();
		Element items = root.getChild(ITEMS);
		Element item = GetChildById(items, ITEM, itemname);
		if (item == null) {
			return retval;
		}
		String tclass = item.getAttributeValue(LICLASS);
		String title = item.getAttributeValue(TITLE);
		List<Element> lis = item.getChildren(LI);

		{
			int i = 0;
			int j = 0;
			

			for (int ii = 0; ii < lis.size(); ii++) {
				Element el = lis.get(ii);
				if (el.getAttributeValue(LITYPE).equals(LITYPE_NO)) {
					;
				} else {
					String cename =  el.getAttributeValue(LI_NAME);
					String searchname =  el.getAttributeValue(LISEARCH_NAME);
					
					Field fcd;
					fcd = CommonUtil.getDeclaredField(searchItem,searchname);
					fcd.setAccessible(true);
					
					
					Object value = fcd.get(searchItem );
				
					if( (value != null)&&(( ""+value).equals("") == false))
					{
					
						 String searchtype = el.getAttributeValue(LISEARCH_TYPE);
				   String datatype = el.getAttributeValue(LITYPE);
				   if( datatype != null)
				   {
					   cename   = cename.substring(0,1).toUpperCase()+cename.substring(1);
					   String criName = "and"+cename;
					   Class [] argsClass = new Class[1];
					   Object[] args  = new Object[1];
					   args[0] = value;
					   if( datatype.equals( LITYPE_STRING )||datatype.equals( LITYPE_COMBOBOX )
							   )
					   {
						   argsClass[0] = String.class;
					   }
					   
					   if( datatype.equals( LITYPE_DATE ) )
					   {
						   argsClass[0] = Date.class;
					   }
					   
					   if( datatype.equals( LITYPE_INT) )
					   {
						   argsClass[0] = Integer.class;
					   }
					   
					   if( datatype.equals( LITYPE_DOUBLE ) )
					   {
						   argsClass[0] = Double.class;
					   }
					   
					   
					   if( searchtype.equals( LISEARCHTYPE_EG) )
					   {
						   criName += "EqualTo";
						    
					   }
					   
					   if( searchtype.equals( LISEARCHTYPE_GT ) )
					   {
						   
						   criName += "GreaterThanOrEqualTo";
					   }
					   
					   if( searchtype.equals( LISEARCHTYPE_LT ) )
					   {
						   criName += "LessThanOrEqualTo";
					   }
					   
					   if( searchtype.equals( LISEARCHTYPE_LINK ) )
					   {
						   criName += "Like";
						   args[0] = value+"%";
					   }
					   if( searchtype.equals( LISEARCHTYPE_MULLINK ) )
					   {
						   criName += "Like";
						   args[0] = "%"+value+"%";
					   }
					   
					   
					   Method method = criteria.getClass().getMethod(criName,  argsClass );
					   method.invoke( criteria, args);
				   }
					}
				}

			}
		}
	} catch (JDOMException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	
	} catch (SecurityException e) {
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		e.printStackTrace();
	
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
	}
	return retval;
  }

	/**
	 * 通过xml element项返回html
	 * @param info
	 * @param el
	 * @param infovalue
	 * @return
	 */
	private static  String GetHtmlByElement( Object info, Element el,Object infovalue )
	{
		String shtml = "";
		try {
		
		
			String show = el.getAttributeValue("show");
			if( (show != null )&&(show.equals("false")))
			{
				return shtml;
			}
			
			
		String fname = el.getAttributeValue("search");
		Field fd = null;
		if( infovalue == null)
		{
			fd = CommonUtil.getDeclaredField(info,fname);
			if( fd  == null)
			{
				LOG.error("fname="+fname);
			}
			fd.setAccessible(true);
		}
		
		String param = "";
		String tmptitle = null;
		String tmpname = (fd == null)?"":fname;
		if( el.getAttribute("title") != null)
		{
			tmptitle =   el.getAttributeValue("title");
		}else
			tmptitle = (fd == null)?"":el.getText()+":";
		
			
		if( el.getChild("opt") != null)
		{
			param =  el.getChild("opt").getText();
		}
		
		if (el.getAttributeValue(LITYPE).equals(LITYPE_STRING)) {
			String tvalue = (fd == null)?(String)infovalue:(String) fd.get(info);
			if( tvalue == null) tvalue ="";
			 shtml = GetHtmlByType( LINETEXTFLAG ,tmptitle,tmpname,  tvalue,param);
			
			
		} else if (el.getAttributeValue(LITYPE).equals(
				LITYPE_INT)) {
			Integer tvalue = (fd == null)?(Integer)infovalue:(Integer) fd.get(info);
			shtml = GetHtmlByType( NUMBERFLAG ,tmptitle,tmpname, ""+tvalue,param);
			
		} else if (el.getAttributeValue(LITYPE).equals(
				LITYPE_DOUBLE)) {
			 Double tvalue = (fd == null)?(Double)infovalue:(Double) fd.get(info);
			 shtml = GetHtmlByType( NUMBERFLAG ,tmptitle,tmpname, ""+tvalue,param);
			} else if (el.getAttributeValue(LITYPE).equals(
			LITYPE_COMBOBOX)) {
				String tvalue = (fd == null)?(String)infovalue:(String) fd.get(info);
			 shtml = GetHtmlByType( COMBOBOXFLAG ,tmptitle,tmpname, (tvalue==null)?"":tvalue,param);

		} else if (el.getAttributeValue(LITYPE).equals(
				LITYPE_DATE)) {
			Date tvalue = (fd == null)?(Date)infovalue:(Date) fd.get(info);
			
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy年MM月dd日");
			if (tvalue != null) {

				String str = sdf.format(tvalue);
				
				shtml = GetHtmlByType( DATETIIMEFALSE ,tmptitle,tmpname, ""+str,param);
				
			} else
				shtml = GetHtmlByType( DATETIIMEFALSE ,tmptitle,tmpname, "",param);
			
		}
		
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return shtml;
	}
	
	
	/**
	 * 将 info对象数据转成html格式 显示数据
	 * @param tempfile  模板xml文件
	 * @param itemname  xml文件中，某一个id
	 * @param info      类对象，如TCardata
	 * @return 返回html格式的数据
	 */
	public static <T>  String toHtml(String tempfile, String itemname,
			T info ) {
		String  htmlstr = "";
		SAXBuilder sb = new SAXBuilder();
		Document doc;
		try {
			doc = sb.build(SearchUtil.class.getResourceAsStream(tempfile));
			Element root = doc.getRootElement();
			Element items = root.getChild(ITEMS);
			Element item = GetChildById(items, ITEM, itemname);
			if (item == null) {
				return null;
			}
			String tclass = item.getAttributeValue(LICLASS);
			String title = item.getAttributeValue(TITLE);
			List<Element> lis = item.getChildren(LI);
			htmlstr = "<div  class='"+HTMLFALG+itemname+"'>";
			{
				int i = 0;
				int j = 0;
				for (int ii = 0; ii < lis.size(); ii++) {
				
					Element el = lis.get(ii);
					if (el.getAttributeValue(LITYPE).equals(LITYPE_NO)) {
						;
					} else {
						
						String html = GetHtmlByElement( info, el ,null);
						htmlstr += html;
					}

				}
			}
		    htmlstr +="</div>";
			
			return htmlstr;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		
		}
 
		return "";
	}


	public static  String  getAuthPath( String path, String dataPath )
	{
		//给用户添加权限
		String tmpPath = null;
		if( path != null)
		{
			tmpPath = path;
			if(dataPath != null)
			{
				if( dataPath.contains(path) )
				{
					tmpPath = dataPath;
				}
				
			}
		}else
		{
			tmpPath = dataPath;
		}
		return tmpPath;
		
	}
	
	
	
}
