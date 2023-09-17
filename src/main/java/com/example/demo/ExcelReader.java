package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;


import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.springframework.stereotype.Component;

@Component
public class ExcelReader {

  public List<List<String>> readJExcel(String fileLocation)
      throws IOException, BiffException {

    try (InputStream in = Files.newInputStream(new File(fileLocation).toPath())) {
      XSSFWorkbookFactory workbookFactory = new XSSFWorkbookFactory();
      try (XSSFWorkbook workbook = workbookFactory.create(in)) {
        Sheet sheet = workbook.getSheetAt(0);
        List<List<String>> strings = new ArrayList<>();
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
          List<String> row = new ArrayList<>();
          for (int j = 0; j <= 6; j++) {
            try{
              row.add(check(sheet.getRow(i).getCell(j)));
            } catch (Exception e) {
              row.add("");
            }
          }
          if (row != null && !row.isEmpty() && row.size() > 0)
          strings.add(row);
        }
        return strings;
      }
    }
  }

  public List<String> readWork(String fileLocation)
      throws IOException, BiffException {
    try (InputStream in = Files.newInputStream(new File(fileLocation).toPath())) {
      XSSFWorkbookFactory workbookFactory = new XSSFWorkbookFactory();
      List<String> strings = new ArrayList<>();
      try (XSSFWorkbook workbook = workbookFactory.create(in)) {
        Sheet sheet = workbook.getSheetAt(0);
        strings.add(sheet.getRow(1).getCell(9).getStringCellValue());
        strings.add(String.valueOf(sheet.getRow(2).getCell(9).getNumericCellValue()));
        strings.add(sheet.getRow(1).getCell(10).getStringCellValue());
        strings.add(String.valueOf(sheet.getRow(2).getCell(10).getNumericCellValue()));
      }
      return strings;
    }
  }

  public String check(Cell cell) {
    switch (cell.getCellType()) {
      case STRING:
        return String.valueOf(cell.getStringCellValue());
      case NUMERIC:
        return String.valueOf(cell.getNumericCellValue());
      case BOOLEAN:
        return String.valueOf(cell.getBooleanCellValue());
      case FORMULA:
        switch (cell.getCachedFormulaResultType()) {
          case NUMERIC:
            return String.valueOf(cell.getNumericCellValue());
          case STRING:
            return String.valueOf(cell.getStringCellValue());
          default:
            return "";
        }
      default:
        return "";
    }
  }
}
