<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    
<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.5/css/jquery.dataTables.css">
  
<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
  
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.5/js/jquery.dataTables.js"></script>




<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script>
      
$(document).ready(function(){

	// 여기서 데이터 로딩 한다.	
	$.ajax( {
		url : "/m/s/sample",		
	}).done(function(data){
		console.log( data.Data.contents[0].title );
		/* eval("var data3 = " + data + ";");
		console.log( data3.Data.contents[0].title ); */

	    $('#table_id').DataTable( {
	        data: data.Data.contents,
	        "columns": [
	                    { "title": "title" , "data":"title"},	                    
	                    { "title": "writer", "data": "writer" }
	                ]
	    } );
	});
    
});
</script>

<title>List </title>
</head>
<body>
<table id="table_id" class="table table-striped table-condensed">
</table>
</body>
</html>