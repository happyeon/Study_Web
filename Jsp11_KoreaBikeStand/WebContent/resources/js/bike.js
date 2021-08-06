$(function(){
	getJsonData();
});

function getJsonData(){
	// resources/json/bike.json을 mydata라는 변수로 가져옴
	$.getJSON("resources/json/bike.json", function(mydata){		//ajax를 짧게 바꿔놓은 것(https://api.jquery.com/jQuery.getJSON/#jqxhr-object)
	
		// JSON.parse     : json형태의 문자열을 -> json 객체(javascript object)
		// JSON.stringify : json 객체 -> json 형태의 문자열로
		$.ajax({
			url: "bike.do",
			method: "post",
			data: {"command": "getdata", "mydata": JSON.stringify(mydata)},		// mydata를 json 문자열로 변환
			dataType: "json",		// 받아올 데이터의 형식
			success: function(msg){
				var $tbody = $("tbody");
				var list = msg.result;
				for (var i = 0; i < list.length; i++) {
					var $tr = $("<tr>");
					$tr.append($("<td>").append(list[i].name));
					$tr.append($("<td>").append(list[i].addr));
					$tr.append($("<td>").append(list[i].latitude));
					$tr.append($("<td>").append(list[i].longitude));
					$tr.append($("<td>").append(list[i].bike_count));
					
					$tbody.append($tr);
				}
			},
			error: function(){
				alert("통신 실패");
			}
		});
	});
}