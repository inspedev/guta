package com.example.demo;


import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  @Autowired
  public ExcelReader excelReader;
  @Autowired
  public ExcelWriter excelWriter;

  @Autowired
  private SheetServiceGoogle googleSheetsService;

  @GetMapping("/")
  public ModelAndView login(@RequestParam(required = false) Boolean logged, HttpServletResponse response) throws IOException, BiffException, WriteException, GeneralSecurityException {
    ModelAndView modelAndView = new ModelAndView();
    List<List<Object>> strings = googleSheetsService.getSpreadsheetValues();
    modelAndView.addObject("strings", strings);
    List<String> a = new ArrayList<>();
    a.add(strings.get(0).get(9).toString());
    a.add(strings.get(0).get(10).toString());
    a.add(strings.get(0).get(12).toString());
    a.add(strings.get(0).get(13).toString());
    modelAndView.addObject("next", a);
    modelAndView.setViewName("index");
    return modelAndView;
  }

  @PostMapping(value = "/")
  public void add(String loginp, String namep, String time, String weight, String full, String count, String robot_v, String text, HttpServletResponse response) throws IOException, BiffException, WriteException, GeneralSecurityException {
    googleSheetsService.write(loginp, namep, time, weight, full, count, robot_v, text);
    response.sendRedirect("/");
  }


  @GetMapping(value="ping")
  public String getSpreadsheetValues() throws IOException, GeneralSecurityException {
    googleSheetsService.getSpreadsheetValues();
    return "OK";
  }

}
