<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawCharts);
      function drawCharts() {
        var data, options, chart;
    	
        <c:forEach items="${chartInfos}" var="chartInfo">
    	data = google.visualization.arrayToDataTable(${chartInfo.json});

        options = {
          title: '${chartInfo.title}',
          hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0},
          chartArea: {width: '70%'}
        };

        chart = new google.visualization.AreaChart(document.getElementById('${chartInfo.title}'));
        chart.draw(data, options);
        </c:forEach>
      }
      
      // make charts responsive
      $(window).resize(function(){
    	  drawCharts();
    	});
    </script>
    
    <c:forEach items="${chartInfos}" var="chartInfo">
		<div id="${chartInfo.title}" style="width: 100%; height: 250px; padding-bottom:50px;"></div>
	</c:forEach>