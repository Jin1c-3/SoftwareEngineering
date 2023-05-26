package com.yutech.back.entity.bo;

import lombok.Data;

@Data
public class AircraftAvailableSeats {
	private String flightNo;

	private String seatNo;

	private String seatType;

	private String date;
}
