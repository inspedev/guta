package com.example.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SheetServiceGoogle implements GoogleService {
  private static final Logger LOGGER = LoggerFactory.getLogger(SheetServiceGoogle.class);
  @Value("${spreadsheet.id}")
  private String spreadsheetId;
  private static Sheets sheetsService;
  @Autowired
  private AuthGoogle googleAuthorizationConfig;

  @Override
  public List<List<Object>> getSpreadsheetValues() throws IOException, GeneralSecurityException {
    Sheets sheetsService = googleAuthorizationConfig.getSheetsService();
    Sheets.Spreadsheets.Values.BatchGet request =
        sheetsService.spreadsheets().values().batchGet(spreadsheetId);
    request.setRanges(getSpreadSheetRange());
    request.setMajorDimension("ROWS");
    BatchGetValuesResponse response = request.execute();
    return response.getValueRanges().get(0).getValues();
  }

  private List<String> getSpreadSheetRange() throws IOException, GeneralSecurityException {
    Sheets sheetsService = googleAuthorizationConfig.getSheetsService();
    Sheets.Spreadsheets.Get request = sheetsService.spreadsheets().get(spreadsheetId);
    Spreadsheet spreadsheet = request.execute();
    Sheet sheet = spreadsheet.getSheets().get(0);
    int row = sheet.getProperties().getGridProperties().getRowCount();
    int col = sheet.getProperties().getGridProperties().getColumnCount();
    return Collections.singletonList("R1C1:R".concat(String.valueOf(row))
        .concat("C").concat(String.valueOf(col)));
  }

  public void write(String loginp, String namep, String time, String weight, String full, String count, String robot, String text) throws IOException, GeneralSecurityException {
    Sheets.Spreadsheets sheets = googleAuthorizationConfig.getSheetsService().spreadsheets();
    ValueRange body = new ValueRange()
        .setValues(Collections.singletonList(
            Arrays.asList(loginp, namep, time, Double.parseDouble(weight) * Double.parseDouble(count), weight, count, text)));
    int start = getSpreadsheetValues().size() + 1;
    UpdateValuesResponse result = sheets.values()
        .update(spreadsheetId, "A" + start, body)
        .setValueInputOption("RAW")
        .execute();
  }
}
