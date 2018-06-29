package org.eclipse.cpacep.util;

import java.util.List;

public class Statistics {

  private String header;
  private List<String> body;

  public Statistics(String header, List<String> body) {
    this.header = header;
    this.body = body;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getHeader() {
    return header;
  }

  public void setBody(List<String> body) {
    this.body = body;
  }

  public List<String> getBody() {
    return body;
  }

}
