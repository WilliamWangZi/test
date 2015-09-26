/**
 * 
 */

var dataset=new Array();
var data1=new Array();
var data2=new Array();
for(h=0;h<type.length;h++){
	qvalue[h]=qvalue[h]-qavg[h];
	var ob={type:type[h],score:qavg[h]};
	data1.push(ob);
	var ob1={type:type[h],score:qvalue[h]};
	data2.push(ob1);
}
var obj1={name:"平均分",exam:data1};
dataset.push(obj1);
var obj2={name:"分值",exam:data2};
dataset.push(obj2);


if(number!=null){
	var width = 700;
	var height = 600;
	var svg = d3.select("body").select(".d3").append("svg")
							.attr("width",width)
							.attr("height",height)
							.attr("class","stack");

	var stack = d3.layout.stack()
						.values(function(d){ return d.exam; })
						.x(function(d){ return d.type; })
						.y(function(d){ return d.score; });
	var data = stack(dataset);
	var padding = { left:50, right:100, top:30, bottom:30 };
	var xRangeWidth = width - padding.left - padding.right;
	
	var xScale = d3.scale.ordinal()
					.domain( data[0].exam.map(function(d){ 
return d.type; 
}))
					.rangeBands([0, xRangeWidth],0.3);

	        //（定义域的最大值）
		var maxscore = d3.max(data[0].exam, function(d){ 
								return d.y0 + d.y; 
						});
		
		//最大高度（值域的最大值）
		var yRangeWidth = height - padding.top - padding.bottom;
		
		var yScale = d3.scale.linear()
						.domain([0, maxscore*2])		//定义域
						.range([0, yRangeWidth]);
		var color = d3.scale.category10();
		
		//添加分组元素
		var groups = svg.selectAll("g")
						.data(data)
						.enter()
						.append("g")
						.style("fill",function(d,i){ return color(i); });
		var rects = groups.selectAll("rect")
		.data(function(d){ return d.exam; })
		.enter()
		.append("rect")
		.attr("x",function(d){ return xScale(d.type); })
		.attr("y",function(d){ 
return yRangeWidth - yScale( d.y0 + d.y ); 

})
		.attr("width",function(d){ 
return xScale.rangeBand(); 
})
		.attr("height",function(d){ return yScale(d.y); })
		.attr("transform","translate(" + padding.left + "," + padding.top + ")");
		var xAxis = d3.svg.axis()
		.scale(xScale)
		.orient("bottom");

yScale.range([yRangeWidth, 0]);

var yAxis = d3.svg.axis()
			.scale(yScale)
			.orient("left");
			
svg.append("g")
	.attr("transform","translate(" + padding.left + "," + (height - padding.bottom) +  ")")
	.call(xAxis)
	.append("text")
		.text("题型")
		.attr('transform', 'translate('+xRangeWidth+', 0)');
		
svg.append("g")
	.attr("transform","translate(" + padding.left + "," + (height - padding.bottom - yRangeWidth) +  ")")
	.call(yAxis)
	.append("text")
		.text("分数");
var labHeight = 50;
var labRadius = 10;
var labelCircle = groups.append("circle")
.attr("cx",function(d){ return width - padding.right*0.98; })
.attr("cy",function(d,i){ return padding.top * 2 + labHeight * i; })
.attr("r",labRadius);

var labelText = groups.append("text")
.attr("x",function(d){ return width - padding.right*0.8; })
.attr("y",function(d,i){ return padding.top * 2 + labHeight * i; })
.attr("dy",labRadius/2)
.text(function(d){ return d.name; });
}