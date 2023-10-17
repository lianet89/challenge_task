package com.example.challenge.dto;

public class SinkDto {

	String kind;

	String id;

	public SinkDto() {
		super(); // TODO Auto-generated constructor stub
	}

	public SinkDto(String kind, String id) {
		super();
		this.kind = kind;
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
