<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="../template/header.jsp" />

<h1>Results
	<a href="game/edit" class="btn btn-info pull-right" role="button">edit Game</a>
</h1>

<c:import url="../template/alerts.jsp" />

<br>
<c:forEach items="${results}" var="result">
<c:out value="${result.balance}"></c:out>
</c:forEach>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawCharts);
      function drawCharts() {
        var data, options, chart;
    	
    	  // first Chart
    	data = google.visualization.arrayToDataTable(${json});

        options = {
          title: 'Company Performance',
          hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0}
        };

        chart = new google.visualization.AreaChart(document.getElementById('chart_div1'));
        chart.draw(data, options);
        
    	 // second Chart
        data = google.visualization.arrayToDataTable(${json});

        options = {
          title: 'Company Performance',
          hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0}
        };

        chart = new google.visualization.AreaChart(document.getElementById('chart_div2'));
        chart.draw(data, options); 
      }
      
      // make charts responsive
      $(window).resize(function(){
    	  drawCharts();
    	});
    </script>
    
<div id="chart_div1" style="width: 100%; height: 250px; padding-bottom:50px;"></div>
<div id="chart_div2" style="width: 100%; height: 250px; padding-bottom:50px;"></div>

<c:import url="../template/footer.jsp" />