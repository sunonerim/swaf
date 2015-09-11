/**
 *	Entity 필드구성 저옵에 따라서 UI에 입출력 필드를 생성하는 로직 
 */


$.templates({
		text:'<div class="form-group">\
				    <label for="{{:fieldName}}" class="col-sm-2 control-label">{{:fieldLabel}}</label>\
				    <div class="col-sm-10">\
				      <input type="textfield" class="form-control" id="{{:fieldName}}" value="{{:default}}" placeholder="{{:fieldPlaceHolder}}">\
				    </div>\
				</div>'
		,
		longtext:'<div class="form-group">\
		    <label for="{{:fieldName}}" class="col-sm-2 control-label">{{:fieldLabel}}</label>\
		    <div class="col-sm-10">\
			  <textarea class="form-control" id="{{:fieldName}}"></textarea>\
		    </div>\
		</div>'
		,
		number:'<div class="form-group">\
		    <label for="{{:fieldName}}" class="col-sm-2 control-label">{{:fieldLabel}}</label>\
		    <div class="col-sm-10">\
		      <input type="textfield" class="form-control" id="{{:fieldName}}" placeholder="{{:fieldPlaceHolder}}" value="{{:default}}">\
		    </div>\
		</div>'
		,
		checkbox:'<div class="form-group">\
					<div class="col-sm-offset-2 col-sm-10">\
						<div class="checkbox">\
							<label>\
							<input type="checkbox" id="{{:fieldName}}"> {{:fieldLabel}}\
				        	</label>\
				      	</div>\
				    </div>\
				  </div>'
		,
		password:'  <div class="form-group">\
					    <label for="{{:name}}" class="col-sm-2 control-label">{{:fieldLabel}}</label>\
					    <div class="col-sm-10">\
					      <input type="password" class="form-control" id="{{:fieldName}}" placeholder="Password">\
					    </div>\
					  </div>'
		,
		select:'<div class="form-group">\
					<label for="{{:name}}" class="col-sm-2 control-label">{{:fieldLabel}}</label>\
					<div class="col-sm-10">\
					<select class="form-control" id="{{:fieldName}}">\
					{{for options }}\
			 			<option>{{>option}}</option>\
					{{/for}}\
					</select>\
		    		</div>\
				</div>'
		,
		hidden:'<input type="hidden"  id="{{:fieldName}}" value="{{:default}}">'			
		,
		label:'<H1 id="{{:fieldName}}">{{:fieldLabel}}</H1>'
	});

	


function	addFields( parent_obj_id, fields){
	for ( var i=0; i<fields.length; i++) {
		var field = fields[i]; 
		//console.log( field.fieldType );
		addField( parent_obj_id, field);
	}
}

//
//	필드를 필드의 구성에 따라서 생성합니다. 
//
function	addField( parent_obj_id, field){
	
	console.log( "<<<<<<" + field.fieldType );
	  
	// 여기서 템플릿으로 렌더링 한다.
	if( !empty($.templates[field.fieldType]) ){
		htmlOutput = $.templates[field.fieldType].render(field);
		$(parent_obj_id).append(htmlOutput);
	}
		
	// 후 처리한다. 
	switch( field.fieldType ) {
	case "number":
		// $("#"+field.fieldName).append("<option>seoul</option>");
		var	number_attr = new Object();
		
		number_attr["verticalbuttons"] = true;
		
		if ( !empty(field.MaxValue))   number_attr["max"] = field.MaxValue;
		if ( !empty(field.MinValue))   number_attr["min"] = field.MinValue;
				
		$("#"+field.fieldName).TouchSpin( number_attr);		
		break;
	}
}


/**
	해당 변수가 아무값도 없는지를 점검하는 함수로 아주 유용함 
*/
function empty(data)
{
  if(typeof(data) == 'number' || typeof(data) == 'boolean')
  {
    return false;
  }
  if(typeof(data) == 'undefined' || data === null)
  {
    return true;
  }
  if(typeof(data.length) != 'undefined')
  {
    return data.length == 0;
  }
  var count = 0;
  for(var i in data)
  {
    if(data.hasOwnProperty(i))
    {
      count ++;
    }
  }
  return count == 0;
}

