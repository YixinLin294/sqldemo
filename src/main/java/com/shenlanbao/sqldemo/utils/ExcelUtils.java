package com.shenlanbao.sqldemo.utils;

import com.shenlanbao.sqldemo.model.Template;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ExcelUtils {

    public static void main(String[] args) {
        ExcelUtils excelUtils = new ExcelUtils();
        String title = "INSERT INTO `verbal_trick_template_title` (title, tab_id) values ('','');";
        List<String> sqls = new ArrayList<>();
        for (int sheetIndex = 1; sheetIndex < 6; sheetIndex++) {
            ArrayList<Map<String, String>> result = excelUtils.readExcelToObj("C:\\Users\\slb\\Desktop\\异议处理（更正）(20200316).xlsx", sheetIndex);
            for (Map<String, String> map : result) {
//                System.out.println(map);
            }
            //插入标题
            Set<String> questionSet = new LinkedHashSet<>();
            for (Map<String, String> map : result) {
                String question = map.get("question");
                questionSet.add(question);
            }
            System.out.println(questionSet);
            for (String s : questionSet) {
                String sql = title.substring(0, 67) + s + "', " + sheetIndex + ");";
                sqls.add(sql);
            }
            System.out.println(questionSet.size());
        }
        for (String sql : sqls) {
            System.out.println(sql);
        }
    }

    /**
     * 读取excel数据
     * @param path
     */
    public ArrayList<Map<String, String>> readExcelToObj(String path, Integer sheetIndex) {

        Workbook wb = null;
        ArrayList<Map<String, String>> result = null;
        try {
            wb = WorkbookFactory.create(new File(path));
            result = readExcel(wb, sheetIndex, 1, 0);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取excel数据
     * @param file
     */
    public ArrayList<Map<String, String>> readExcelToObj(MultipartFile file, Integer sheetIndex) {

        Workbook wb = null;
        ArrayList<Map<String, String>> result = null;
        try {
            wb = WorkbookFactory.create(file.getInputStream());
            result = readExcel(wb, sheetIndex, 1, 0);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 读取excel文件
     * @param wb
     * @param sheetIndex sheet页下标： 从0开始
     * @param startReadLine 开始读取的行：从0开始
     * @param tailLine 去除最后读取的行
     * @return
     */
    private ArrayList<Map<String, String>> readExcel(Workbook wb, int sheetIndex, int startReadLine, int tailLine) {
        Sheet sheet = wb.getSheetAt(sheetIndex);
        Row row = null;
        ArrayList<Map<String, String>> result = new ArrayList<>();
        for (int i = startReadLine; i < sheet.getLastRowNum()-tailLine+1; i++) {
            row = sheet.getRow(i);
            Map<String, String> map = new HashMap<>();
            for (Cell c : row) {
                String returnStr = "";
                boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
                // 判断是否具有合并单元格
                if (isMerge) {
                    String rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
//                    System.out.println(rs + "---------- ");
                    returnStr = rs;
                } else {
//                    System.out.println(c.getRichStringCellValue() + "++++ ");
                    returnStr = c.getRichStringCellValue().getString();
                }
                if (c.getColumnIndex() == 0) {
                    map.put("question", returnStr);
                } else if (c.getColumnIndex() == 1) {
                    map.put("answer",returnStr);
                }
            }
            result.add(map);
        }
        return result;
    }

    /**
     * 获取合并单元格的值
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    private String getMergedRegionValue(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell);
                }
            }
        }
        return null;
    }

    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    private String getCellValue(Cell cell) {
        if(cell == null)
            return "";
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return cell.getCellFormula();
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return "";
    }

    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    private boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }
}
