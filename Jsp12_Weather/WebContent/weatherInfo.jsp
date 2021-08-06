<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%-- 여긴 주석쓰지 않는 것을 권장 --%>
<c:catch var="err">
	<c:set var="weatherURL"
		value="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=${code}" />
	<c:import var="weather" url="${weatherURL}" />  <%--xml이 응답됨 --%>
	<x:parse var="wrss" xml="${weather}" />  <%-- 파싱 : 구조화되어 있는 데이터에서 원하는 데이터를 가져오기 위해 json객체로 만들어줌 (DOM과 비슷한 기능) --%>
{"pubDate":"<x:out select="$wrss/rss/channel/pubDate" />",
"temp":"<x:out
		select="$wrss/rss/channel/item/description/body/data/temp" />",
"reh":"<x:out select="$wrss/rss/channel/item/description/body/data/reh" />",
"pop":"<x:out select="$wrss/rss/channel/item/description/body/data/pop" />",
"x":"<x:out select="$wrss/rss/channel/item/description/header/x" />",
"y":"<x:out select="$wrss/rss/channel/item/description/header/y" />",
"wfKor":"<x:out
		select="$wrss/rss/channel/item/description/body/data/wfKor" />"}
</c:catch>
<c:if test="${err!=null}">
	${err}
</c:if>
