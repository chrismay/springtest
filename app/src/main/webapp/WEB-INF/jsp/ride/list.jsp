<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
var reloadRides = function(){
	var list = $('<ul>');

	$.ajax({ url:'list.json', 
		accepts:'application/json', 
		success:function(data) {
		   $.each(data,function(){
			  var rideDate = new Date(this.date)
			  var item = $('<li>' + rideDate + ' ' + this.route.name + ' ' + this.comments +  '</li>' );
			  list.append(item);
		   })	   
	  $("#ridesList").html(list);
	}});
}
</script>
</head>
<body>
<h1>Rides</h1>
<div id="ridesList">
</div>
<script type="text/javascript">
reloadRides();
</script>
</body>
</html>