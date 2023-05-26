package com.yutech.back.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AircraftAvailableSeats {
	private String flightNo;

	private String seatNo;

	private String seatType;

	private String date;
}
