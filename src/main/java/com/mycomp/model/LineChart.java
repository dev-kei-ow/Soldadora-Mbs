package com.mycomp.model;

import java.util.List;

public class LineChart {

	private String name;
	private List<Integer> data;

	public LineChart() {

	}

	// Constructor
	public LineChart(String name, List<Integer> data) {
		this.name = name;
		this.data = data;
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

}
