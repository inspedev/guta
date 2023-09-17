package com.example.demo;


import java.util.List;

import java.io.File;
import java.io.IOException;

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

  @GetMapping("/")
  public ModelAndView login(@RequestParam(required = false) Boolean logged, HttpServletResponse response) throws IOException, BiffException, WriteException {
    ModelAndView modelAndView = new ModelAndView();
    File currDir = new File(".");
    String path = currDir.getAbsolutePath();
    String fileLocation = path.substring(0, path.length() - 1) + "Guta.xlsx";
    List<List<String>> strings = excelReader.readJExcel(fileLocation);
    modelAndView.addObject("strings", strings);
    List<String> a = excelReader.readWork(fileLocation);
    modelAndView.addObject("next", a);
    modelAndView.setViewName("index");
    return modelAndView;
  }

  @PostMapping(value = "/add")
  public void add(String loginp, String namep, String time, String weight, String full, String count, String robot_v, String text, HttpServletResponse response) throws IOException, BiffException, WriteException {
    excelWriter.writeExcel(loginp, namep, time, weight, full, count, robot_v, text);
    response.sendRedirect("/");
  }

}
