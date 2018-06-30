package org.eclipse.cpacep.util;

import java.util.ArrayList;
import java.util.List;

public class Statistics {

	private String header;
	private List<String> body;

	public Statistics() {
		this.body = new ArrayList<String>();
	}

	public void addLine(String line) {
		this.body.add(line);
	}

	public void removeLastLine() {
		this.body.remove(body.size() - 1);
	}

	public void removeHeaderFromBody() {
		body.remove(header);
	}

	public String getLastAddedLine() {
		return body.get(body.size() - 1);
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
