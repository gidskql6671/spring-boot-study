package com.example.forestPoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TestResponse {
	private List<ForestPoint> data;
}
