package com.example.demo.types;

public enum RoleType {

	ROLE_HO("Head Office"),
	ROLE_BRANCH("Cabang");
	
	private String value; 

	private RoleType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
