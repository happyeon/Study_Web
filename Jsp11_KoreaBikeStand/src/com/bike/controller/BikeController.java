package com.bike.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.dao.BikeDao;
import com.bike.dto.BikeDto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/bike.do")
public class BikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		BikeDao dao = new BikeDao();
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		if (command.equals("view")) {
			response.sendRedirect("view.html");
		} else if (command.equals("getdata")) {
			if (dao.delete()) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
			
			// bike.js에서 resources/json/bike.json을 문자열로 바꿔서 mydata 라는 이름으로 보내줬음
			String data = request.getParameter("mydata");
			/*
			 * JsonElement : JsonObject, JsonArray, JsonPrimitive, JsonNull -> Json 요소이다.
			 * JsonObject : name-value 쌍으로 이루어진 객체 -> Json 객체 {String : JsonElement}
			 */
			JsonElement element = JsonParser.parseString(data);
			JsonObject jsonData = element.getAsJsonObject();	// {"fields": [{},{}..], "records":[{},{},..] }
			
			JsonElement records = jsonData.get("records");		
			JsonArray recordsArray = records.getAsJsonArray();
			
			// list는 db에 저장하기 위해
			List<BikeDto> list = new ArrayList<BikeDto>();
			// resultArray는 response하기 위해
			JsonArray resultArray = new JsonArray();
			
			for (int i = 0; i < recordsArray.size(); i++) {
				
				//JsonElement jsonElement = recordsArray.get(i);
				//JsonObject jsonObject = jsonElement.getAsJsonObject();
				//String name = jsonObject.get("자전거대여소명").getAsString();
				// 밑에 한 줄을 위에 3줄로 풀어씀
				
				/*
				String name = recordsArray.get(i).getAsJsonObject().get("자전거대여소명").getAsString();
				String addr = null;
				if (recordsArray.get(i).getAsJsonObject().get("소재지도로명주소") != null) {
					addr = recordsArray.get(i).getAsJsonObject().get("소재지도로명주소").getAsString();
				} else {
					addr = recordsArray.get(i).getAsJsonObject().get("소재지지번주소").getAsString();
				}
				
				Double latitude = recordsArray.get(i).getAsJsonObject().get("위도").getAsDouble();
				
				Double longitude = recordsArray.get(i).getAsJsonObject().get("경도").getAsDouble();
				
				int bike_count = recordsArray.get(i).getAsJsonObject().get("자전거보유대수").getAsInt();
				*/
				
				JsonObject recordsArrayToObject = recordsArray.get(i).getAsJsonObject();
				// recordsArrayToObject = recordsArray의 i번지에 있는 json 객체
				String name = recordsArrayToObject.get("자전거대여소명").getAsString();
				String addr = null;
				if (recordsArrayToObject.get("소재지도로명주소") != null) {
					addr = recordsArrayToObject.get("소재지도로명주소").getAsString();
				} else {
					addr = recordsArrayToObject.get("소재지지번주소").getAsString();
				}
				Double latitude = recordsArrayToObject.get("위도").getAsDouble();
				Double longitude = recordsArrayToObject.get("경도").getAsDouble();
				int bike_count = recordsArrayToObject.get("자전거보유대수").getAsInt();
				
				
				BikeDto dto = new BikeDto(name, addr, latitude, longitude, bike_count);
				list.add(dto);
				
				// json구조를 띄는 직렬화된 데이터를 JAVA의 객체로 역직렬화, 직렬화 해주는 자바 라이브러리
				Gson gson = new Gson();
				String jsonString = gson.toJson(dto);   //dto객체를 json으로 변환
				resultArray.add(JsonParser.parseString(jsonString));
			}
			
			if (dao.insert(list)) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
			}
			
			JsonObject result = new JsonObject();
			result.add("result", resultArray);
			
			response.getWriter().append(result+"");  // 묵시적 형변환
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
