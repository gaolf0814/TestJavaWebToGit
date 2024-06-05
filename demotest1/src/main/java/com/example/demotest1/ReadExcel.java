package com.example.demotest1;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public class ReadExcel {
    public static void main(String[] args) {


        String xlmFilePath = "D:\\JavaProject\\TestJavaWebToGit\\demotest1\\src\\main\\resources\\dengru.xlsm"; // .xlsm文件路径
        String xlsFilePath = "D:\\JavaProject\\TestJavaWebToGit\\demotest1\\src\\main\\resources\\cilo.xls"; // .xls文件路径
        String xlsxOutputPath = "D:\\JavaProject\\TestJavaWebToGit\\demotest1\\src\\main\\resources\\去重的.xlsx"; // .xlsx输出文件路径

        try {
            // 从.xlsm文件读取数据到Set
            Set<String> dataSet = readDataToSet(xlmFilePath, 7, 2); // 读取第一个工作表的第一列

            // 从.xls文件读取并检查数据，如果不在Set中，则写入.xlsx文件
            checkAndWriteData(xlsFilePath, xlsxOutputPath, dataSet, 0, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 读取特定列的数据到Set集合
    public static Set<String> readDataToSet(String filePath, int sheetIndex, int columnIndex) throws Exception {
        Set<String> dataSet = new HashSet<>();
//        try (InputStream inputStream = new FileInputStream(filePath)) {
//            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
////            Sheet sheet = workbook.getSheetAt(sheetIndex);
//            Sheet sheet = workbook.getSheet("西诺他唑组");
//            for (Row row : sheet) {
//                Cell cell = row.getCell(columnIndex - 1); // 列索引从0开始
//                if (cell != null) {
//                    // 检查单元格的数据类型
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            // 单元格是字符串类型，可以直接获取字符串值
//                            String value = cell.getStringCellValue();
//                            dataSet.add(value);
//                            break;
//                        case NUMERIC:
//                            // 单元格是数值类型，根据需要获取值（例如，格式化为字符串）
//                            String numericValue = String.valueOf(cell.getNumericCellValue());
//                            dataSet.add(numericValue);
//                            break;
//                        // 可以根据需要处理其他数据类型...
//                        default:
//                            // 其他类型，或者未知类型，可以选择跳过或者处理
//                            break;
//                    }
//                }
//            }
//        }
        return dataSet;
    }

    // 检查Set中是否不包含某个值，如果不包含，则写入新的.xlsx文件
    public static void checkAndWriteData(String sourceXlsFilePath, String targetXlsxFilePath, Set<String> dataSet, int sheetIndex, int columnIndex) throws Exception {
        try (InputStream inputStream = new FileInputStream(sourceXlsFilePath);
             OutputStream outputStream = new FileOutputStream(targetXlsxFilePath)) {

            // 正确使用XSSFWorkbook来读取源文件
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
//            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Sheet sheet = workbook.getSheet("SQL Results");


            // 创建一个新的XSSFWorkbook实例用于输出
            XSSFWorkbook outputWorkbook = new XSSFWorkbook();
            Sheet outputSheet = outputWorkbook.createSheet("OutputData1");

            int rowNum = 0; // 开始写入的新行号
            for (Row row : sheet) {
                if (row != null) {
                    Cell cell = row.getCell(columnIndex - 1); // 列索引从0开始
                    if (cell != null) {
                        String value = cell.getStringCellValue();
                        if (!dataSet.contains(value)) {
                            // 如果Set中不包含该值，创建新行并复制所有单元格数据
                            Row newRow = outputSheet.createRow(rowNum++);
                            for (Cell cellToCopy : row) {
                                Cell newCell = newRow.createCell(cellToCopy.getColumnIndex());
                                copyCell(newCell, cellToCopy, outputWorkbook);
                            }
                            dataSet.add(value);
                        }
                    }
                }

            }

            System.out.println(dataSet.size()-1);

            // 写入新的.xlsx文件
            outputWorkbook.write(outputStream);
        }
    }

    // 复制单元格内容
    private static void copyCell(Cell newCell, Cell cellToCopy, XSSFWorkbook outputWorkbook) {
        switch (cellToCopy.getCellType()) {
            case STRING:
                newCell.setCellValue(cellToCopy.getStringCellValue());
                break;
            case NUMERIC:
                newCell.setCellValue(cellToCopy.getNumericCellValue());
                break;
            // 根据需要处理其他单元格类型...
            default:
                break;
        }
    }


}
