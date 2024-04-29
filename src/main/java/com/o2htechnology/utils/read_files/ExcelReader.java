package com.o2htechnology.utils.read_files;

import com.o2htechnology.utils.common_utils.PageData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {
    private static List<String> columnHeaders = new ArrayList<>(Arrays.asList("menu", "category", "chemical", "cat_no", "cas_no", "mol_f", "mol_wt", "status", "chemical_name", "smile", "synonym", "image_src"));

    public static void writeToExcel(Map<String, Map<String, Map<String, Map<String, String>>>> dataMap, String worksheetName, String fileName) {
//        TODO make the method accpet dynamic values such as file path and file name
        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(worksheetName);
            Row row = sheet.createRow(0);
            int column = 0;
            for (String columnHeader : columnHeaders) {
                Cell cell = row.createCell(column);
                cell.setCellValue(columnHeader);
                column++;
            }
            int rowNumber = 1;
            Set<String> menus = dataMap.keySet();
//            TODO handle when a key returns null value
            for (String menu : menus) {
                Map<String, Map<String, Map<String, String>>> categoryMap = dataMap.get(menu);
                Set<String> categories = categoryMap.keySet();
                for (String category : categories) {
                    Map<String, Map<String, String>> chemicalMap = new LinkedHashMap<>(categoryMap.get(category));
                    Set<String> chemicals = chemicalMap.keySet();
                    for (String chemical : chemicals) {
                        Row chemicalRow = sheet.createRow(rowNumber++);
                        Cell cell = chemicalRow.createCell(columnHeaders.indexOf("menu"));
                        cell.setCellValue(menu);
                        cell = chemicalRow.createCell(columnHeaders.indexOf("category"));
                        cell.setCellValue(category);
                        cell = chemicalRow.createCell(columnHeaders.indexOf("chemical"));
                        cell.setCellValue(chemical);
                        Map<String, String> propertyMap = chemicalMap.get(chemical);
                        Set<String> properties = propertyMap.keySet();
                        for (String property : properties) {
                            if (property.contains(".cat_no")) {
                                cell = chemicalRow.createCell(columnHeaders.indexOf("cat_no"));
                                cell.setCellValue(propertyMap.get(property));
                            }
                            if (property.contains(".cas_no")) {
                                cell = chemicalRow.createCell(columnHeaders.indexOf("cas_no"));
                                cell.setCellValue(propertyMap.get(property));
                            }
                            if (property.contains(".mol_f")) {
                                cell = chemicalRow.createCell(columnHeaders.indexOf("mol_f"));
                                cell.setCellValue(propertyMap.get(property));
                            }
                            if (property.contains(".mol_wt")) {
                                cell = chemicalRow.createCell(columnHeaders.indexOf("mol_wt"));
                                cell.setCellValue(propertyMap.get(property));
                            }
                            if (property.contains(".status")) {
                                cell = chemicalRow.createCell(columnHeaders.indexOf("status"));
                                cell.setCellValue(propertyMap.get(property));
                            }
                            if (property.contains(".chemical_name")) {
                                cell = chemicalRow.createCell(columnHeaders.indexOf("chemical_name"));
                                cell.setCellValue(propertyMap.get(property));
                            }
                            if (property.contains(".smile")) {
                                cell = chemicalRow.createCell(columnHeaders.indexOf("smile"));
                                cell.setCellValue(propertyMap.get(property));
                            }
                            if (property.contains(".synonym")) {
                                cell = chemicalRow.createCell(columnHeaders.indexOf("synonym"));
                                cell.setCellValue(propertyMap.get(property));
                            }
                            if (property.contains(".image_src")) {
                                cell = chemicalRow.createCell(columnHeaders.indexOf("image_src"));
                                cell.setCellValue(propertyMap.get(property));
                            }
                        }
                    }
                    chemicalMap.clear();
                }
            }
            String filePath = System.getProperty(PageData.SystemProperties.USER_DIR) + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "project_data" + File.separator + fileName;
            File file = new File(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException ioException) {
        }
    }
}
