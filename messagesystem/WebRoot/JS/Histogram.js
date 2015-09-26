/**
 * 
 */

if(number!=null){
	var width=400;
	var height=500;
	var svg=d3.select("body").select(".d3").append("svg").attr("height",height).attr("width",width).attr("class","histog");
	

	var bin_num = 10;
	var histogram = d3.layout.histogram()
						.range([0,100])
					    .bins(bin_num)
						.frequency(true);
	var data = histogram(sum);
	var graphics = svg.append("g")
						.attr("transform","translate(30,50)");

	var max_height = 400;
	var rect_step = 30;
	var heights = [];
	for(var i=0;i<data.length;i++){
		heights.push( data[i].y );
	}
	var yScale = d3.scale.linear()
						.domain([0,d3.max(heights)])
						.range([0,max_height]);

//绘制矩形
graphics.selectAll("rect")
.data(data)
.enter()
.append("rect")
.attr("x",function(d,i){
return i * rect_step; 
})
.attr("y", function(d,i){
return max_height - yScale(d.y);
})
.attr("width", function(d,i){
return rect_step - 2; 
})
.attr("height", function(d){
return yScale(d.y);
})
.attr("fill","steelblue");

//绘制坐标轴的直线
graphics.append("line")
.attr("stroke","black")
.attr("stroke-width","1px")
.attr("x1",0)
.attr("y1",max_height)
.attr("x2",data.length * rect_step)
.attr("y2",max_height);

//绘制坐标轴的分隔符直线
graphics.selectAll(".linetick")
.data(data)
.enter()
.append("line")
.attr("stroke","black")
.attr("stroke-width","1px")
.attr("x1",function(d,i){
return i * rect_step + rect_step/2;
})
.attr("y1",max_height)
.attr("x2",function(d,i){
return i * rect_step + rect_step/2;
})
.attr("y2",max_height + 5);

//绘制文字
graphics.selectAll("text")
.data(data)
.enter()
.append("text")
.attr("font-size","10px")
.attr("x",function(d,i){
return i * rect_step; 
})
.attr("y", function(d,i){
return max_height;
})
.attr("dx",rect_step/2 - 8)
.attr("dy","15px")
.text(function(d){
return Math.floor(d.x);
});

svg.append("g").selectAll("text")
.data(data)
.enter()
.append("text")
.attr("font-size","10px")
.attr("x",function(d,i){
return i * rect_step+30; 
})
.attr("y", function(d,i){
return max_height+30;
})
.attr("dx",rect_step/2 - 8)
.attr("dy","5px")
.text(function(d){
	if(d.y!=0){
		return Math.floor(d.y);
	}
});
var describe="下图为各分数段所占有的人数";
svg.append("g").append("text").text(describe).attr('transform', 'translate(70,30)');
}