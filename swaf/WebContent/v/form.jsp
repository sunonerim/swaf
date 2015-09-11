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


<script src="/m/js/jsrender.js"></script>


<script>
//배열로 필드정보를 로드한다.  원래는 데이터베이스에서 필드정보를 가져와야한다. 
var	field_metas = [
	 	 {"fieldType":"phone", "fieldName":"#Phone Nmber" , "fieldLabel": "Phone Number"}
		,{"fieldType":"name",  "fieldName":"name" , "fieldLabel": "Name"}
		,{"fieldType":"email", "fieldName":"Email" , "fieldLabel": "email"}
		,{"fieldType":"password", "fieldName":"field04" , "fieldLabel": "password"}
];


$(document).ready(function(){
	// 서버에서 필드 정보를 가져온 후 여기서 필드를 추가한다. 
	addFields( "#insert-form", field_metas );
 	
	$("#submit-btn").click(function(){
		console.log( "submit-btn clicked..." );
		
		var	param =  new Object() ;
		
		$("#insert-form input").each(function(){
			param[this.id] = $(this).val();
		});
				
		 
		$.ajax( {
			url : "/m/s/insert",
			data: param
		}).done(function(data){
				
		});
		
	});

}); // end of $(document).ready

function	addFields( parent_obj_id, fields){
	for ( var i=0; i<fields.length; i++) {
		var field = fields[i]; 
		//console.log( field.fieldType );
		addField( parent_obj_id, field);
	}
}

function	addField( parent_obj_id, field){
	//console.log( field.fieldType );
	
	switch( field.fieldType ) {
	case "email":
		/* console.log("email  " + field.fieldName ); */
		break;
		
	case "password":
		/* console.log("password  " + field.fieldName ); */
		addPasswordField( parent_obj_id, field);
		break;
		
	default:
		/* console.log("default  " + field.fieldName ); */
		addTextField ( parent_obj_id, field);
		break;
	}
}

function	addTextField( parent_obj_id, field_data){	
	var template = $.templates("#text-field-tmpl");		
	var htmlOutput = template.render(field_data);
	$(parent_obj_id).append(htmlOutput);	
}

function	addEmailField( parent_obj_id, field_data){	
	var template = $.templates("#email-field-tmpl");		
	var htmlOutput = template.render(field_data);
	$(parent_obj_id).append(htmlOutput);	
}

function	addPasswordField( parent_obj_id, field_data){	
	var template = $.templates("#password-field-tmpl");		
	var htmlOutput = template.render(field_data);
	$(parent_obj_id).append(htmlOutput);	
}



</script>


<script id="text-field-tmpl" type="text/x-jsrender">
<div class="form-group">
    <label for="{{:fieldName}}" class="col-sm-2 control-label">{{:fieldLabel}}</label>
    <div class="col-sm-10">
      <input type="textfield" class="form-control" id="{{:fieldName}}" placeholder="{{:fieldPlaceHolder}}">
    </div>
  </div>
</script>


<script id="password-field-tmpl"  type="text/x-jsrender">
  <div class="form-group">
    <label for="{{:name}}" class="col-sm-2 control-label">{{:fieldLabel}}</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="{{:fieldName}}" placeholder="Password">
    </div>
  </div>
</script>

<script id="email-field-tmpl"  type="text/x-jsrender">
  <div class="form-group">
    <label for="{{:name}}" class="col-sm-2 control-label">{{:fieldLabel}}</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="{{:fieldName}}" placeholder="{{:fieldPlaceHolder}}">
    </div>
  </div>
</script>

<script id="boolean-field-tmpl"  type="text/x-jsrender">
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> {{:fieldLabel}}
        </label>
      </div>
    </div>
  </div>
</script>
  
  
<title>FORM</title>

</head>
<body>

<div id="result"></div>

<form class="form-horizontal" id="insert-form">
  
</form>
<div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button id="submit-btn" class="btn btn-default">Insert New Record</button>
    </div>
  </div>
 

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
		<div class="btn-group btn-group-xs" role="group">
		    <button type="button" class="btn btn-default" id="add-text-field-btn">TEXT</button>
		    <button type="button" class="btn btn-default" id="add-email-field-btn">EMAIL</button>
		    <button type="button" class="btn btn-default" id="add-pic-field-btn">PICTURE</button>
		    <button type="button" class="btn btn-default" id="add-name-field-btn">NAME</button>
		    <button type="button" class="btn btn-default" id="add-money-field-btn">MONEY</button>
		    <button type="button" class="btn btn-default" id="add-etc-field-btn">....</button>		  		  
		  </div>
    </div>
  </div>
</div>  
  
</body>
</html>