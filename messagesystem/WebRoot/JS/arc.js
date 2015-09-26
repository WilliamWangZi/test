/**
 * 
 */
	if(number!=null){
	var width = 500;
		var height = 450;
		
		var svg = d3.select("body").select(".d3").append("svg")
								.attr("width",width)
								.attr("height",height);
		
		var pie = d3.layout.pie();
		
		var outerRadius = width / 4;
		var innerRadius = 0;
		var arc = d3.svg.arc()
						.innerRadius(innerRadius)
						.outerRadius(outerRadius);
		
		var color = d3.scale.category10();
		var a=outerRadius*2;
		var arcs = svg.selectAll("g")
					  .data(pie(pass))
					  .enter()
					  .append("g")   
					  .attr("transform","translate(150,"+a+")");
					  
		arcs.append("path")
			.attr("fill",function(d,i){
				return color(i);
			})  
			.attr("d",function(d){
				return arc(d);
			});
			
		arcs.append("text")
			.attr("transform",function(d){
				return "translate(" + arc.centroid(d) + ")";
			})
			.attr("text-anchor","middle")
			.text(function(d){
				return d.value;
			});
		var z="蓝色区域为及格人数，占"+pass[0]/number*100+"%";
		var x="橙色区域为不及格人数，占"+pass[1]/number*100+"%";
		var y=a+40;
		svg.append("g").append("text").text(z).attr('transform', 'translate('+y+','+a+')');
		a=a+20;
		svg.append("g").append("text").text(x).attr('transform', 'translate('+y+','+a+')');
				
	}
