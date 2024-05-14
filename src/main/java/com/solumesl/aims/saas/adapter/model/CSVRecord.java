package com.solumesl.aims.saas.adapter.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CSVRecord {
	private List<Map<String, String>> records =  new ArrayList<Map<String, String>>();

	public void clear() {
		records.clear();
	}

	public int getRecordCount() {
		return records.size();
	}
	
	public boolean add(Map<String, String> map) {
		return this.records.add(map);
	}

	public List<Map<String, String>> getRecords() {
		return records;
	}
}
