package cn.easyliao.transfer.util.excelutil;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/7/4.
 */
public class ImportExcelUtil {

    private final static Logger log = LoggerFactory.getLogger(ImportExcelUtil.class);

    /**
     * @param input 文件输入流
     * @param clazz bean对象
     * @param <T>
     * @return
     */
    public static <T> List<T> importExcel(InputStream input, Class<T> clazz) {

        List<T> list = new ArrayList<T>();
        try {
            Workbook book = WorkbookFactory.create(input);
            Sheet sheet = book.getSheetAt(0);// 默认指向第1个sheet.
            int rows = sheet.getPhysicalNumberOfRows();// 得到数据的行数
            Field[] allFields = clazz.getDeclaredFields();// 得到类的所有field.
            if (rows > 0) {// 有数据时才处理
                List<Field> fieldList = new ArrayList<Field>(allFields.length);// 定义一个List用于存放列field.
                for (Field field : allFields) {
                    // 将有注解的field存放到List中.
                    if (field.isAnnotationPresent(ExcelVO.class)) {
                        ExcelVO attr = field.getAnnotation(ExcelVO.class);
                        int col = ExcelCol.getExcelCol(attr.column());// 获得列号
                        // System.out.println(col + "====" + field.getName());
                        field.setAccessible(true);// 设置类的私有字段属性可访问.
                        fieldList.add(field);
                    }
                }
                for (int i = 1; i < rows; i++) {// 从第2行开始取数据,默认第一行是表头.
                    // Cell cell = sheet.getRow(i).getCell(col);

                    JSONObject jsonObject = new JSONObject();

                    T entity = null;
                    for (Field field : fieldList) {
                        ExcelVO attr = field.getAnnotation(ExcelVO.class);
                        int col = ExcelCol.getExcelCol(attr.column());
                        Cell cell = sheet.getRow(i).getCell(col);//得到一行中的所有单元格对象.

                        if (cell == null) {
                            continue;
                        }
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String cellValue = cell.getStringCellValue();// 单元格中的内容.

                        if (cellValue.equals("")) {
                            continue;
                        }
                        //把数字当成String来读，避免出现1读成1.0的情况
                        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }

                     /*   if (0 == cell.getCellType()) {
                            //判断是否为日期类型
                            if(HSSFDateUtil.isCellDateFormatted(cell)){
                                //用于转化为日期格式
                                Date d = cell.getDateCellValue();
                                DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                                str[k] = formater.format(d);
                            }else{
                                // 用于格式化数字，只保留数字的整数部分
                                DecimalFormat df = new DecimalFormat("########");
                                str[k] = df.format(cell.getNumericCellValue());
                            }*/

                        entity = (entity == null ? clazz.newInstance() : entity);// 如果不存在实例则新建.
                        // System.out.println(cells[j].getContents());
                        //  Field field = fieldList.get(i);
                        // 取得类型,并根据对象类型设置值.
                        Class<?> fieldType = field.getType();
                        if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
                            field.set(entity, Integer.parseInt(cellValue));
                        } else if (String.class == fieldType) {
                            field.set(entity, String.valueOf(cellValue));
                        } else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
                            field.set(entity, Long.valueOf(cellValue));
                        } else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
                            field.set(entity, Float.valueOf(cellValue));
                        } else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
                            field.set(entity, Short.valueOf(cellValue));
                        } else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
                            field.set(entity, Double.valueOf(cellValue));
                        } else if (Character.TYPE == fieldType) {
                            if ((cellValue != null) && (cellValue.length() > 0)) {
                                field.set(entity, Character.valueOf(cellValue.charAt(0)));
                            }
                        }
                    }
                    if (entity != null) {
                        list.add(entity);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Eccel导入失败", e.getMessage(), e);
        }
        return list;
    }
}
