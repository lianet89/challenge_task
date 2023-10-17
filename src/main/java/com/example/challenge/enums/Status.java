package com.example.challenge.enums;

public enum Status {

	OK("ok"), DONE("done");

	public final String status;

	private Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
