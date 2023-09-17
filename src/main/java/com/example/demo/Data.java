package com.example.demo;

import java.util.Date;

public class Data {

  private String name;
  private String count;
  private Date date;

  public Data(String name, String count, Date date) {
    this.name = name;
    this.count = count;
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
