package com.star.model;

/**
 *  @author: liudw
 *  @date: 2021-3-25 17:18
 */

public class MyHbaseCell {

	String rowkey;
	String family;
	String qualifier;
	String value;

	public MyHbaseCell() {}

	public String getRowkey() {
		return rowkey;
	}

	public void setRowkey(String rowkey) {
		this.rowkey = rowkey;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Stockdealrecords{" + "rowkey='" + rowkey + '\'' + ", family='" + family + '\'' + ", qualifier='" + qualifier + '\'' + ", value='" + value + '\'' + '}';
	}
}
