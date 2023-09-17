package com.example.demo;

import java.util.Date;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;


import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExcelWriter {

  @Autowired
  public ExcelReader excelReader;

  public void writeExcel(String loginp, String namep, String time, String weight, String full, String count, String robot, String text) throws IOException, WriteException, BiffException {
    File currDir = new File(".");
    String path = currDir.getAbsolutePath();
    String fileLocation = path.substring(0, path.length() - 1) + "Guta.xlsx";
    try (InputStream in = Files.newInputStream(new File(fileLocation).toPath())) {
      XSSFWorkbookFactory workbookFactory = new XSSFWorkbookFactory();
      try (XSSFWorkbook workbook = workbookFactory.create(in)) {
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i <= sheet.getLastRowNum() + 1; i++) {
          Row row = sheet.getRow(i);
          if (row == null) {
            sheet.createRow(i);
            row = sheet.getRow(i);
          }
          if (row.getCell(0) == null || excelReader.check(row.getCell(0)).isEmpty()) {
            row.createCell(0).setCellValue(loginp);
            row.createCell(2).setCellValue(time);
            if (robot.equals("1")) {
              row.createCell(1).setCellValue(sheet.getRow(1).getCell(9).getStringCellValue());
              row.createCell(3).setCellValue(sheet.getRow(2).getCell(9).getNumericCellValue());
            } else {
              row.createCell(1).setCellValue(sheet.getRow(1).getCell(10).getStringCellValue());
              row.createCell(3).setCellValue(sheet.getRow(2).getCell(10).getNumericCellValue());
            }
            row.createCell(4).setCellValue(row.getCell(3).getNumericCellValue() * Integer.parseInt(count));
            row.createCell(5).setCellValue(Integer.parseInt(count));
            row.createCell(6).setCellValue(text);
            break;
          }
        }
        FileOutputStream fileOut = new FileOutputStream(fileLocation);
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        System.out.println("Збережено: " + loginp + " Час: " + new Date());
      } catch (Exception e) {
        System.out.println("ПОМИЛКА 1: " + loginp + " Час: " + new Date());
        System.out.println("1" + e);
      }
    } catch (Exception e) {
      System.out.println("ПОМИЛКА 2: " + loginp + " Час: " + new Date());
      System.out.println("2" + e);
    }

  }
}
