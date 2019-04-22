package cn.easyliao.transfer.util.excelutil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/7/4.
 */
public class ExportExcelUtil {

    private final static Logger log = LoggerFactory.getLogger(ExportExcelUtil.class);

    /**
     * @param list   bean对象集合
     * @param output 文件输出流
     * @param clazz  bean对象
     * @param suffix 文件后缀
     * @param <T>
     * @return
     */
    public static <T> boolean exportExcel(List<T> list, OutputStream output, Class<T> clazz, String suffix) {

        Field[] allFields = clazz.getDeclaredFields();// 得到所有定义字段
        List<Field> fields = new ArrayList<Field>();

        int sheetSize = 0;
        Workbook workbook = null;

        // 得到所有field并存放到一个list中.
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ExcelVO.class)) {
                fields.add(field);
            }
        }

        //如果后缀为null,则生成xls类型
        if ("xls".equals(suffix) || suffix == null) {
            //excel2003中每个sheet中最多有65536行
            sheetSize = 65536;
            workbook = new HSSFWorkbook();// 产生工作薄对象
            log.info("suffix is xls!");
        } else if ("xlsx".equals(suffix)) {
            //excel2007中每个sheet中最多有1048576行
            sheetSize = 1048576;
            workbook = new SXSSFWorkbook();// 产生工作簿对象
            log.info("suffix is xlsx!");
        } else {
            System.out.println("文件格式不正确");
            log.error("suffix is error!");
        }

        CellStyle style = workbook.createCellStyle();
        CellStyle styleText = workbook.createCellStyle();
        //单元格为文本格式
        DataFormat format = workbook.createDataFormat();
        styleText.setDataFormat(format.getFormat("@"));
        style.setDataFormat(format.getFormat("@"));

        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);//粗体显示
        font.setColor(Font.COLOR_RED);//红色字体

//        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(font);

        double sheetNo = Math.ceil(list.size() / sheetSize);// 取出一共有多少个sheet.
        for (int index = 0; index <= sheetNo; index++) {
            Sheet sheet = workbook.createSheet();// 产生工作表对象
            Row row;
            Cell cell;

            row = sheet.createRow(0);// 产生一行
            row.setRowStyle(styleText);
            // 写入各个字段的列头名称
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                ExcelVO attr = field.getAnnotation(ExcelVO.class);
                int col = ExcelCol.getExcelCol(attr.column());// 获得列号
                cell = row.createCell(col);// 创建列
                cell.setCellType(Cell.CELL_TYPE_STRING);// 设置列中写入内容为String类型
                cell.setCellValue(attr.name());// 写入列名
                cell.setCellStyle(style);//字体样式

                // TODO: 2017/8/15 px像素单位  宽40 高23
                sheet.setColumnWidth(i, 256 * 40);//设置列宽
                row.setHeight((short) 460);//设置列高 20 * 23
            }

            int startNo = index * sheetSize;
            int endNo = Math.min(startNo + sheetSize, list.size());
            // 写入各条记录,每条记录对应excel表中的一行
            for (int i = startNo; i < endNo; i++) {
                row = sheet.createRow(i + 1 - startNo);
                T vo = (T) list.get(i); // 得到导出对象.
                for (int j = 0; j < fields.size(); j++) {
                    Field field = fields.get(j);// 获得field.
                    field.setAccessible(true);// 设置实体类私有属性可访问
                    ExcelVO attr = field.getAnnotation(ExcelVO.class);

                    // TODO: 2017/8/15 px像素单位  宽40 高23
                    sheet.setColumnWidth(i, 256 * 40);//设置列宽
                    row.setHeight((short) 460);//设置列高 20 * 23

                    try {
                        // 根据ExcelVO中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
                        cell = row.createCell(ExcelCol.getExcelCol(attr.column()));// 创建cell
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(field.get(vo) == null ? ""
                                : String.valueOf(field.get(vo)));// 如果数据存在就填入,不存在填入空格.
                        cell.setCellStyle(styleText);//设置单元格为文本格式
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        try {
            output.flush();
            workbook.write(output);
            output.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Excel导出失败", e.getMessage(), e);
            return false;
        }
    }
}
