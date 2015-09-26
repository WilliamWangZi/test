/**
 * 
 */

if(number!=null){
	var width = 600;
	var height = 600;
	var svg = d3.select("body").select(".d3").append("svg")
							.attr("width",width)
							.attr("height",height);
	
	var xAxisScale = d3.scale.ordinal()
					.domain(d3.range(sum.length))
					.rangeRoundBands([0,500]);
	
	var xAxisScale1=d3.scale.ordinal()
					.domain([0,0])
					.rangeRoundBands([0,500]);	
	
	var yAxisScale = d3.scale.linear()
					.domain([0,d3.max(sum)])
					.range([500,0]);
						
	var xAxis = d3.svg.axis()
					.scale(xAxisScale)
					.orient("bottom");
	
	var xAxis1 = d3.svg.axis()
				.scale(xAxisScale1)
				.orient("bottom");
	
	var yAxis = d3.svg.axis()
					.scale(yAxisScale)
					.orient("left");

	var xScale = d3.scale.ordinal()
					.domain(d3.range(sum.length))
					.rangeRoundBands([0,500],0.05);
						
	var yScale = d3.scale.linear()
					.domain([0,d3.max(sum)])
					.range([0,500]);
	
	svg.selectAll("rect")
	   .data(sum)
	   .enter()
	   .append("rect")
	   .attr("x", function(d,i){
			return 30 + xScale(i);
	   } )
	   .attr("y",function(d,i){
			return 50 + 500 - yScale(d) ;
	   })
	   .attr("width", function(d,i){
			return xScale.rangeBand();
	   })
	   .attr("height",yScale)
	   .attr("fill","red");
	   
	svg.selectAll("text")
        .data(sum)
        .enter().append("text")
        .attr("x", function(d,i){
			return 30 + xScale(i);
	   } )
	   .attr("y",function(d,i){
			return 50 + 500 - yScale(d) ;
	   })
        .attr("dx", function(d,i){
			return xScale.rangeBand()/3;
	   })
        .attr("dy", 15)
		.attr("text-anchor", "begin")
		.attr("font-size", 14)
		.attr("fill","white")
        .text(function(d,i){
			return d;
		});
	   var y=50+500-500/d3.max(sum)*70;
	   var z=50+500-500/d3.max(sum)*60;
	svg.append("g")
		.attr("transform","translate(30,550)")
		.call(xAxis)
		.append("text")
		.text("班级人数")
		.attr('transform', 'translate(500, 0)');
		
		svg.append("g")
		.attr("class","axis")
		.attr("transform","translate(30,50)")
		.call(yAxis)
		.append("text")
	.text("分数线");
		
	svg.append("g")
	.append("text")
	.text("平均分线")
	.attr('transform', 'translate(530,'+y+')');
	
	svg.append("g")
	.append("text")
	.text("及格线")
	.attr('transform', 'translate(530,'+z+')');
	
	svg.append("line")
	.attr("x1",30)
	.attr("y1",y)
	.attr("x2",530)
	.attr("y2",y)
	.attr("stroke","black")
	.attr("stroke-width",2);
	
	svg.append("line")
	.attr("x1",30)
	.attr("y1",z)
	.attr("x2",530)
	.attr("y2",z)
	.attr("stroke","black")
	.attr("stroke-width",2);
	

	
}

