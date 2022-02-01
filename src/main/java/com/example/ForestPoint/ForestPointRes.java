package com.example.ForestPoint;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class ForestPointRes {
	
	private String analdate;
	private String doname;
	private int regioncode;
	private String searchcd;
	private int std;
	private int meanavg;
	private int maxi;
	private int d1;
	private int d2;
	private int d3;
	private int d4;
	
}
