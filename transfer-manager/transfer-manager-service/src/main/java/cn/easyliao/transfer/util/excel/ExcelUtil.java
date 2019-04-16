package cn.easyliao.transfer.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import cn.easyliao.transfer.common.utils.CommonUtil;

/**
 * 
 * @author cm
 *
 */

public class ExcelUtil {

	public static final String ITEMS = "items";
	public static final String ITEM = "item";
	public static final String LI = "li";
	public static final String LIID = "id";
	public static final String LICLASS = "class";
	public static final String TYPE = "type";
	public static final String STARTROW = "startrow";
	public static final String STARTCELL = "startcell";
	public static final String LINAME = "name";
	public static final String LITYPE = "type";
	public static final String TITLE = "title";
	public static final String CLASS = "class";
	public static final String LIREQUIRED = "required";

	public static final String LITYPE_ARRAY = "array";
	public static final String LITYPE_STRING = "string";
	public static final String LITYPE_INT = "int";
	public static final String LITYPE_DATE = "date";
	public static final String LITYPE_DOUBLE = "double";
	public static final String LITYPE_NO = "no";
	public static final String TRIM_BLANK="trim";

	public static final String HEADROW = "headrow";

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

	public static <T extends Object> List<T> Read(String tempfile,
			String itemname, InputStream is) throws IOException, ParseException {
		SAXBuilder sb = new SAXBuilder();
		Document doc;
		try {
			doc = sb.build(ExcelUtil.class.getResourceAsStream(tempfile));
			Element root = doc.getRootElement();
			Element items = root.getChild(ITEMS);
			Element item = GetChildById(items, ITEM, itemname);
			if (item == null) {
				return null;
			}
			String tclass = item.getChildText(CLASS);
			// 反射

			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			List<T> infos = new ArrayList<T>();

			List<Element> lis = item.getChildren(LI);
			// 循环工作表Sheet
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				

				int nocell = -1;
				int rowstep = 1;
				int startRow = item.getAttribute(STARTROW).getIntValue();
				
				for (int rowNum = startRow; rowNum <= hssfSheet.getLastRowNum(); rowNum+=rowstep) {
					rowstep=1;
					T info;
					Class<?> cls = Class.forName(tclass);
					info = (T) cls.newInstance();

					int baddata = 0;
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					int startCell = item.getAttribute(STARTCELL).getIntValue();
					for (int i = startCell; i < hssfRow.getLastCellNum(); i++) {
						HSSFCell brandIdHSSFCell = hssfRow.getCell(i);
						String value = null;
						if( brandIdHSSFCell == null) 
						{
							value = null;
						}else
							value = getCellStringValue(brandIdHSSFCell);
						Element li = lis.get(i - startCell);
						
						String trim = li.getAttributeValue(TRIM_BLANK);
						if ( (trim != null )&&( trim.equals("true")) )
						{
							if( value != null)
							{
								value = CommonUtil.replaceBlank(value);
							}
						}
								
						
						
						
						if( (value == null )||(  value.equals("")) )
						{
							String required = li.getAttributeValue(LIREQUIRED);
							if ( (required != null )&&( required.equals("true")) )
							{
								baddata = 1;
								break;
							}
							
						}
						String type = li.getAttributeValue(LITYPE);
						if (type.equals(LITYPE_NO)) {
							nocell = i ;
						}
						
						String name = li.getAttributeValue(LINAME);
						if ((name != null) && (name.equals("") == false)) {
							Field fd = CommonUtil.getDeclaredField(info,name);
							fd.setAccessible(true);

							

							if ((type == null) || (type.equals(LITYPE_STRING))) {

								fd.set(info, value);
							} else if (type.equals(LITYPE_INT)) {
								try {
									Integer ivalue = Integer.parseInt(value);
									fd.set(info, ivalue);
								} catch (Exception e) {

								}

							} else if (type.equals(LITYPE_ARRAY)) {

								int step = 1;
								ArrayList<Object> values = new ArrayList<Object>();
								for(;;)
								{
									values.add(value);
									
									if( hssfSheet.getLastRowNum() < (rowNum+step))
										break;
									
								HSSFRow hssfRow2 = hssfSheet.getRow(rowNum+step);
								HSSFCell brandIdHSSFCell2 = hssfRow2.getCell(nocell);
								if( brandIdHSSFCell2 != null) 
								{
									String value2 = getCellStringValue(brandIdHSSFCell2);
									if( (value2 != null)&&(value2.equals("") == false  ) )
									{
										break;
									}
								}
								step++;
								HSSFCell brandIdHSSFCell3 = hssfRow2.getCell( i );
								if( brandIdHSSFCell3 == null)
									break;
								
								value = getCellStringValue(brandIdHSSFCell3);
								
								
								}
								if( rowstep < step )
									 rowstep = step;
								
								if( fd.getType() == String[].class)
								{
								  String [] nvalues = new 	String[values.size()];
								  for( int k = 0;k<values.size();k++)
								  {
									  nvalues[k] = (String)values.get(k);
									  
								  }
								  fd.set(info, nvalues);
									
									
									
								}
								
							
							} else if (type.equals(LITYPE_DOUBLE)) {
								try {
									Double dvalue = Double.parseDouble(value);
									fd.set(info, dvalue);
								} catch (Exception e) {

								}

							} else if (type.equals(LITYPE_DATE)) {
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy年MM月dd日");
								try {
									Date date = brandIdHSSFCell
											.getDateCellValue();
									if (date != null) {
										fd.set(info, date);

									}
								} catch (Exception e) {
									e.printStackTrace();
									try {
										Date date1 = sdf.parse(value);
										fd.set(info, date1);

									} catch (Exception e1) {
										e1.printStackTrace();
									}
								}

							}

						}

					}

					if( baddata == 1)
						 continue;
					infos.add(info);

				}

			}
			return infos;

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (SecurityException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		return new ArrayList<T>();

	}

	public static <T> byte[] export(String tempfile, String itemname,
			List<T> lists) {

		SAXBuilder sb = new SAXBuilder();
		Document doc;

		try {
			doc = sb.build(ExcelUtil.class.getResourceAsStream(tempfile));
			Element root = doc.getRootElement();
			Element items = root.getChild(ITEMS);
			Element item = GetChildById(items, ITEM, itemname);
			if (item == null) {
				return null;
			}
			String tclass = item.getAttributeValue(CLASS);
			String title = item.getAttributeValue(TITLE);
			int startRow = item.getAttribute(STARTROW).getIntValue();
			int startCell = item.getAttribute(STARTCELL).getIntValue();
			int headRow = item.getAttribute(HEADROW).getIntValue();

			List<Element> lis = item.getChildren(LI);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(title);
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
			// 表格标题名称
			insertCell(row, 0, title);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			// 创建一个居中格式
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			// 从第二行开始
			row = sheet.createRow((int) headRow);
			HSSFCell cell = null;
			// excel头
			for (int ii = 0; ii < lis.size(); ii++) {
				Element el = lis.get(ii);

				cell = row.createCell(ii + startCell);
				cell.setCellValue(el.getText());
				cell.setCellStyle(style);
			}
			
			int nocell = -1;
			int rowstep = 1;
			
			// 第五步，写入实体数据 实际应用中这些数据从数据库得到

			for (int i = 0,ij=0; i < lists.size();i++, ij+=rowstep) {
				rowstep = 1;
				row = sheet.createRow((int) ij + startRow);
				T info = lists.get(i);
				// 创建单元格，并设置值
				int j = 0;

				for (int ii = 0; ii < lis.size(); ii++) {
					Element el = lis.get(ii);
					if (el.getAttributeValue(LITYPE).equals(LITYPE_NO)) {
						insertCell(row, ii, i + 1);
					} else {

						String fname = el.getAttributeValue("name");
						Field fd = CommonUtil.getDeclaredField(info,fname);
						fd.setAccessible(true);

						if (el.getAttributeValue(LITYPE).equals(LITYPE_STRING)) {
							String tvalue = (String) fd.get(info);
							insertCell(row, ii, tvalue);
						} else if (el.getAttributeValue(LITYPE).equals(
								LITYPE_INT)) {
							Integer tvalue = (Integer) fd.get(info);
							insertCell(row, ii, tvalue);

						} else if (el.getAttributeValue(LITYPE).equals(
								LITYPE_DOUBLE)) {
							Double tvalue = (Double) fd.get(info);
							insertCell(row, ii, tvalue);

						} else if (el.getAttributeValue(LITYPE).equals(LITYPE_ARRAY)) {

							int step = 1;
							if( fd.getType() == String[].class)
							{
								String[] tvalue = (String[]) fd.get(info);
								if( tvalue != null)
								{
							    step = tvalue.length;
							    if( step > rowstep )
							    {
							    	for( int k = rowstep; k< step; k++ )
							    	{
							    	   sheet.createRow((int) ij + startRow+k);
							    	}
							    }
							    for( int k = 0;k<step ;k++)
							    {
							    	HSSFRow trow = sheet.getRow(ij + startRow+k);
							    	insertCell(trow, ii, tvalue[k]);
							    	
							    }
							    if( step > rowstep )
							    	rowstep = step;
								}
								
								
							}
							
							
						} else if (el.getAttributeValue(LITYPE).equals(
								LITYPE_DATE)) {
							Date tvalue = (Date) fd.get(info);

							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy年MM月dd日");
							if (tvalue != null) {

								String str = sdf.format(tvalue);
								insertCell(row, ii, str);
							} else
								insertCell(row, ii, "");

						}
					}

				}
			}

			wb.write(out);
			return out.toByteArray();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public static List<Map<String, Object>> ReadEx(String tempfile,
			String itemname, InputStream is) throws IOException, ParseException {
		
		SAXBuilder sb = new SAXBuilder();
		Document doc;
		try {
			doc = sb.build(ExcelUtil.class.getResourceAsStream(tempfile));
			Element root = doc.getRootElement();
			Element items = root.getChild(ITEMS);
			Element item = GetChildById(items, ITEM, itemname);
			if (item == null) {
				return null;
			}

			// 反射

			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

			List<Map<String, Object>> infos = new ArrayList<Map<String, Object>>();
			List<Element> liclass = item.getChildren(CLASS);
			List<Element> lis = item.getChildren(LI);
			// 循环工作表Sheet
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}

				int nocell = -1;
				int rowstep = 1;
				int startRow = item.getAttribute(STARTROW).getIntValue();
				for (int rowNum = startRow; rowNum <= hssfSheet.getLastRowNum(); rowNum+=rowstep) {
					int baddata = 0;
					rowstep = 1;
					Map<String, Object> info = new HashMap<String, Object>();

					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					int startCell = item.getAttribute(STARTCELL).getIntValue();
					for (int i = startCell; i < hssfRow.getLastCellNum(); i++) {
						HSSFCell brandIdHSSFCell = hssfRow.getCell(i);
						String value = null;
						if( brandIdHSSFCell  != null )
							value = getCellStringValue(brandIdHSSFCell);
						Element li = lis.get(i - startCell);
						
						if( (value == null )||(  value.equals("")) )
						{
							String required = li.getAttributeValue(LIREQUIRED);
							if ( (required != null )&&( required.equals("true")) )
							{
								baddata = 1;
								break;
							}
							
						}
						
						String type = li.getAttributeValue(LITYPE);
						if (type.equals(LITYPE_NO)) {
							nocell = i ;
						}
						
						
						String classmapname = li.getAttributeValue(CLASS);
						Object eleobject = null;
						if (info.containsKey(classmapname) == false) {
							for (int ci = 0; ci < liclass.size(); ci++) {
								Element clsEle = liclass.get(ci);
								if (clsEle.getAttributeValue(LIID).equals(
										classmapname)) {
									Class<?> cls = Class.forName(clsEle
											.getText());
									Object t = cls.newInstance();
									info.put(classmapname, t);
									eleobject = t;
									break;
								}

							}

						} else {
							eleobject = info.get(classmapname);
						}
						if (eleobject == null)
							continue;

						String name = li.getAttributeValue(LINAME);
						if ((name != null) && (name.equals("") == false)) {
							Field fd = CommonUtil.getDeclaredField(eleobject,name); 
							fd.setAccessible(true);

							

							if ((type == null) || (type.equals(LITYPE_STRING))) {

								fd.set(eleobject, value);
							} else if (type.equals(LITYPE_INT)) {
								try {
									Integer ivalue = Integer.getInteger(value);
									fd.set(eleobject, ivalue);
								} catch (Exception e) {

								}

							} else if (type.equals(LITYPE_DOUBLE)) {
								try {
									Double dvalue = Double.parseDouble(value);
									fd.set(eleobject, dvalue);
								} catch (Exception e) {

								}
							} else if (type.equals(LITYPE_ARRAY)) {

								int step = 1;
								ArrayList<Object> values = new ArrayList<Object>();
								for(;;)
								{
									values.add(value);
									
									if( hssfSheet.getLastRowNum() < (rowNum+step))
										break;
									
								HSSFRow hssfRow2 = hssfSheet.getRow(rowNum+step);
								HSSFCell brandIdHSSFCell2 = hssfRow2.getCell(nocell);
								if( brandIdHSSFCell2 != null) 
								{
									String value2 = getCellStringValue(brandIdHSSFCell2);
									if( (value2 != null)&&(value2.equals("") == false  ) )
									{
										break;
									}
								}
								step++;
								HSSFCell brandIdHSSFCell3 = hssfRow.getCell( i );
								if (brandIdHSSFCell3 == null) break;
								value = getCellStringValue(brandIdHSSFCell3);
								
								
								}
								if( rowstep < step )
									 rowstep = step;
								
								if( fd.getType() == String[].class)
								{
								  String [] nvalues = new 	String[values.size()];
								  for( int k = 0;k<values.size();k++)
								  {
									  nvalues[k] = (String)values.get(k);
									  
								  }
								  fd.set(info, nvalues);
									
									
									
								}
								

							} else if (type.equals(LITYPE_DATE)) {
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy年MM月dd日");
								try {
									Date date = brandIdHSSFCell
											.getDateCellValue();
									if (date != null) {
										fd.set(eleobject,date);

									}
								} catch (Exception e) {
									// e.printStackTrace();
									try {
										Date date1 = sdf.parse(value);
										fd.set(eleobject, date1);

									} catch (Exception e1) {
										e1.printStackTrace();
									}
								}

							}

						}

					}

					if( baddata == 1)
						 continue;
					infos.add(info);

				}

			}
			return infos;

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (SecurityException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		return new ArrayList<Map<String, Object>>();

	}

	public static byte[] exportEx(String tempfile, String itemname,
			List<Map<String, Object>> lists) {
		
		SAXBuilder sb = new SAXBuilder();
		Document doc;

		try {
			doc = sb.build(ExcelUtil.class.getResourceAsStream(tempfile));
			Element root = doc.getRootElement();
			Element items = root.getChild(ITEMS);
			Element item = GetChildById(items, ITEM, itemname);
			if (item == null) {
				return null;
			}
			String tclass = item.getAttributeValue(CLASS);
			String title = item.getAttributeValue(TITLE);
			int startRow = item.getAttribute(STARTROW).getIntValue();
			int startCell = item.getAttribute(STARTCELL).getIntValue();
			int headRow = item.getAttribute(HEADROW).getIntValue();

			List<Element> liclass = item.getChildren(CLASS);
			List<Element> lis = item.getChildren(LI);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(title);
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
			// 表格标题名称
			insertCell(row, 0, title);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			// 创建一个居中格式
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			// 从第二行开始
			row = sheet.createRow((int) headRow);
			HSSFCell cell = null;
			// excel头
			for (int ii = 0; ii < lis.size(); ii++) {
				Element el = lis.get(ii);

				cell = row.createCell(ii + startCell);
				cell.setCellValue(el.getText());
				cell.setCellStyle(style);
			}
			// 第五步，写入实体数据 实际应用中这些数据从数据库得到

			for (int i = 0; i < lists.size(); i++) {
				row = sheet.createRow((int) i + startRow);
				Map<String, Object> info = lists.get(i);
				// 创建单元格，并设置值
				int j = 0;

				for (int ii = 0; ii < lis.size(); ii++) {
					Element el = lis.get(ii);

					String classmapname = el.getAttributeValue(CLASS);
					Object eleobject = null;
					if (info.containsKey(classmapname) == false) {
						for (int ci = 0; ci < liclass.size(); ci++) {
							Element clsEle = liclass.get(ci);
							if (clsEle.getAttributeValue(LIID).equals(
									classmapname)) {
								Class<?> cls = Class.forName(clsEle.getText());
								Object t = cls.newInstance();
								info.put(classmapname, t);
								eleobject = t;
								break;
							}

						}

					} else {
						eleobject = info.get(classmapname);
					}

					if (el.getAttributeValue(LITYPE).equals(LITYPE_NO)) {
						insertCell(row, ii, i + 1);
					} else {
						if (eleobject == null)
							continue;
						String fname = el.getAttributeValue("name");
						Field fd =CommonUtil.getDeclaredField(eleobject,fname);
						fd.setAccessible(true);

						if (el.getAttributeValue(LITYPE).equals(LITYPE_STRING)) {
							String tvalue = (String) fd.get(eleobject);
							insertCell(row, ii, tvalue);
						} else if (el.getAttributeValue(LITYPE).equals(
								LITYPE_INT)) {
							Integer tvalue = (Integer) fd.get(eleobject);
							insertCell(row, ii, tvalue);

						} else if (el.getAttributeValue(LITYPE).equals(
								LITYPE_DOUBLE)) {
							Double tvalue = (Double) fd.get(eleobject);
							insertCell(row, ii, tvalue);

						} else if (el.getAttributeValue(LITYPE).equals(
								LITYPE_DATE)) {
							Date tvalue = (Date) fd.get(eleobject);

							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy年MM月dd日");
							if (tvalue != null) {

								String str = sdf.format(tvalue);
								insertCell(row, ii, str);
							} else
								insertCell(row, ii, "");

						}
					}

				}
			}

			wb.write(out);
			return out.toByteArray();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// 获取表格里的值
	public static String getCellStringValue(HSSFCell cell) {
		String cellValue = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:// 字符串类型
			cellValue = cell.getStringCellValue();
			if (cellValue.trim().equals("") || cellValue.trim().length() <= 0)
				cellValue = " ";
			break;
		case HSSFCell.CELL_TYPE_NUMERIC: // 数值类型
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA: // 公式
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = " ";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			break;
		default:
			break;
		}
		return cellValue;
	}

	// 添加单元格
	private static void insertCell(HSSFRow row, int i, Object object) {
		if (object == null) {
			row.createCell(i).setCellValue("");
		} else {
			row.createCell(i).setCellValue(object.toString());
		}
	}

	

}
