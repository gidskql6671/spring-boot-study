package com.example.forestPoint;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ForestPointAPI {

	@Value("${openapi.ServiceKey}")
	private String serviceKey;

	private ResponseEntity<String> getResString() {
		RestTemplate rt = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<Object> entity = new HttpEntity<>(headers);
		String url = "http://apis.data.go.kr/1400377/forestPoint/forestPointListGeongugSearch"
				+ "?ServiceKey={serviceKey}"
				+ "&_type=json"
				+ "&excludeForecast=0";

		return rt.exchange(url, HttpMethod.GET, entity, String.class, serviceKey);

	}

	public List<ForestPoint> getResponse() {
		String body = getResString().getBody();

		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(body);

			JSONObject parsedResponse = (JSONObject) jsonObject.get("response");
			JSONObject parsedBody = (JSONObject) parsedResponse.get("body");
			JSONObject items = (JSONObject) parsedBody.get("items");
			JSONArray item = (JSONArray) items.get("item");

			ForestPoint[] result = (ForestPoint[])item.stream().map(itemObj -> {
						JSONObject obj = (JSONObject) itemObj;
						return ForestPoint.builder()
								.analdate(obj.get("analdate").toString())
								.d1(((Long) obj.get("d1")).intValue())
								.d2(((Long) obj.get("d2")).intValue())
								.d3(((Long) obj.get("d3")).intValue())
								.d4(((Long) obj.get("d4")).intValue())
								.doname(obj.get("doname").toString())
								.searchcd(obj.get("searchcd").toString())
								.maxi(((Long) obj.get("maxi")).intValue())
								.std(((Long) obj.get("std")).intValue())
								.meanavg(((Long) obj.get("meanavg")).intValue())
								.regioncode(((Long) obj.get("regioncode")).intValue())
								.build();
					}
			).toArray(ForestPoint[]::new);
			
			return new ArrayList<>(Arrays.asList(result));
		} catch (ParseException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
