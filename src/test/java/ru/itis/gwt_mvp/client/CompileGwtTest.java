package ru.itis.gwt_mvp.client;

import com.google.gwt.junit.client.GWTTestCase;

public class CompileGwtTest extends GWTTestCase {
  
  @Override
  public String getModuleName() {
    return "ru.itis.gwt_mvp.App";
  }

  public void testSandbox() {
    assertTrue(true);
  }
  
}
