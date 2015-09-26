/**
 * 
 */
var data1=new Array();
for(h=0;h<course.length;h++){
	var ob={type:course[h]};
	data1.push(ob);
}
if(pass!=null){
var width = 600;
	var height = 600;
	var svg = d3.select("body").select(".d3").append("svg")
							.attr("width",width)
							.attr("height",height);
	var yAxisScale = d3.scale.linear()
					.domain([0,100])
					.range([500,0]);
	
	
	var yAxis = d3.svg.axis()
					.scale(yAxisScale)
					.orient("left");

	var xScale = d3.scale.ordinal()
					.domain(d3.range(pass.length))
					.rangeRoundBands([0,500],0.5);
						
	var yScale = d3.scale.linear()
					.domain([0,100])
					.range([0,500]);
	
	svg.selectAll("rect")
	   .data(pass)
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
        .data(course)
        .enter().append("text")
        .attr("x", function(d,i){
			return 30 + xScale(i);
	   } )
	   .attr("y",function(d,i){
			return 50 + 510 ;
	   })
        .attr("dx", function(d,i){
			return xScale.rangeBand()/3;
	   })
        .attr("dy", 15)
		.attr("text-anchor", "begin")
		.attr("font-size", 14)
		.attr("fill","black")
        .text(function(d,i){
			return d;
		});

	svg.append("g")
		.append("text")
		.text("科目")
		.attr('transform', 'translate(530, 550)');
		
		svg.append("g")
		.attr("class","axis")
		.attr("transform","translate(30,50)")
		.call(yAxis)
		.append("text")
	.text("及格率");
		svg.append("line")
		.attr("x1",30)
		.attr("y1",550)
		.attr("x2",530)
		.attr("y2",550)
		.attr("stroke","black")
		.attr("stroke-width",5);

	

	

	
}
