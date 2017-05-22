package com.xsx.ncd.define;

public class ReportJson<T> {
	
	private T report;

	public ReportJson() {

	}
	
	public ReportJson(T records) {
		this.report = records;
	}

	public T getReport() {
		return report;
	}

	public void setReport(T report) {
		this.report = report;
	}

}
