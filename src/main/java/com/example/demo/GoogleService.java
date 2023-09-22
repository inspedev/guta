package com.example.demo;

import java.util.List;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleService {
  List<List<Object>> getSpreadsheetValues() throws IOException, GeneralSecurityException;
}