package com.admin.admin.vo;

public class Rooms {
private Long roomId;
	
	private Long roomNumber;

	public Rooms(Long roomNumber) {
		super();
		this.roomNumber = roomNumber;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Long roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Rooms() {
		super();
	}
	
	
}
