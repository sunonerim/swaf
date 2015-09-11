/**
 * @author zhixin wen <wenzhixin2010@gmail.com>
 * extensions: https://github.com/vitalets/x-editable
 */

!function ($) {

    'use strict';

    $.extend($.fn.bootstrapTable.defaults, {
        editable: true,
        onEditableInit: function () {
            return false;
        },
        onEditableSave: function (field, row, oldValue, $el) {
            return false;
        },
        onEditableShown: function (field, row, $el, editable) {
            return false;
        },
        onEditableHidden: function (field, row, $el) {
            return false;
        }
    });

    $.extend($.fn.bootstrapTable.Constructor.EVENTS, {
        'editable-init.bs.table': 'onEditableInit',
        'editable-save.bs.table': 'onEditableSave',
        'editable-shown.bs.table': 'onEditableShown',
        'editable-hidden.bs.table': 'onEditableHidden'
    });

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
        _initTable = BootstrapTable.prototype.initTable,
        _initBody = BootstrapTable.prototype.initBody;

    BootstrapTable.prototype.initTable = function () {
        var that = this;
        _initTable.apply(this, Array.prototype.slice.apply(arguments));

        if (!this.options.editable) {
            return;
        }

        console.log(this.columns);
        
        $.each(this.columns, function (i, column) {
            if (!column.editable) {
                return;
            }
            // console.log(column);
            
            var _formatter = column.formatter;
            column.formatter = function (value, row, index) {
                var result = _formatter ? _formatter(value, row, index) : value;
                
                if( column.checkEditable !== undefined ) {    
            		if ( !column.checkEditable(row, value)) return result;   		
            	}
                
                if(  column.type === undefined ) column.type = "text";
                
                return ['<a href="javascript:void(0)"',
                    ' data-name="' + column.field + '"',
                    ' data-type="' + column.type + '"',
                    ' data-pk="' + row[that.options.idField] + '"',
                    ' data-value="' + result + '"',
                    '>' + '</a>'
                ].join('');
            };
        });
    };

    BootstrapTable.prototype.initBody = function () {
        var that = this;
        _initBody.apply(this, Array.prototype.slice.apply(arguments));

        if (!this.options.editable) {
            return;
        }

        $.each(this.columns, function (i, column) {
            if (!column.editable) {
                return;
            }            

   
            // 아래는 오리지날
            
            that.$body.find('a[data-name="' + column.field + '"]').editable(column)  //.editable(column.editable) 을 수정함
                .off('save').on('save', function (e, params) {
                    var data = that.getData(),
                        index = $(this).parents('tr[data-index]').data('index'),
                        row = data[index],
                        oldValue = row[column.field];

                    row[column.field] = params.submitValue;
                    that.trigger('editable-save', column.field, row, oldValue, $(this));
                });
            that.$body.find('a[data-name="' + column.field + '"]').editable(column.editable)
                .off('shown').on('shown', function (e, editable) {
                    var data = that.getData(),
                        index = $(this).parents('tr[data-index]').data('index'),
                        row = data[index];
                    that.trigger('editable-shown', column.field, row, $(this));
                    
                    // 내가 추가 
                    if( column.inputwidth !== undefined )  editable.input.$input.css('width', column.inputwidth );
                });
            that.$body.find('a[data-name="' + column.field + '"]').editable(column.editable)
                .off('hidden').on('hidden', function (e, editable) {
                    var data = that.getData(),
                        index = $(this).parents('tr[data-index]').data('index'),
                        row = data[index];                    
                    that.trigger('editable-hidden', column.field, row, $(this), editable);
                });
            
        });
        this.trigger('editable-init');
    };

    /**
     * container_element  하위에 
     * @param container_element
     */
//    BootstrapTable.prototype.buildCreationForm = function( container_element ){
//    	console.log("...BootstrapTable.prototype.buildCreateForm");
//
//    	$.fn.editable.defaults.mode = "inline";
//    	
//    	// construct input form with table.
//    	var $table = $('<table id="new-command-form" class="table table-striped" ></table>');
//    	$.each(this.columns, function(index, column){
//    		var tr_tag = 
//        		['<tr>',
//        		 '<td style="width:160px;">' + column.field + '</td>',
//        		 '<td><a href="#" id="'  + column.field + '" class="swaf-auto-gen-field"></a></td>',
//        		 '</tr>'].join(' ');
//        		
//        		$table.append( tr_tag );    		
//    	});    	
//    	$(container_element).append( $table );
//    	
//    	
//    	// set editable() to each input field.
//    	$.each(this.columns, function(index, column){
//    		console.log(index, column);
//    		$( container_element + ' a#'  + column.field  ).editable(column);    		
//    	});
//    	
//    	
//    	$( container_element + ' a.swaf-auto-gen-field' ).on('hidden', function(e, reason) {
//    	    if(reason === 'save' || reason === 'cancel') {
//    	        //auto-open next editable
//    	        $(this).closest('tr').next().find('.swaf-auto-gen-field').editable('show');
//    	    } 
//    	});
//    	
//    	console.log("...BootstrapTable.prototype.buildCreateForm END");
//    };
    
    // to register method which i made.
//    $.fn.bootstrapTable.methods.push('buildCreationForm');
    
    
    /**
     * buildCreationForm 으로 생성된 양식에 사용된 
     * @param container_element
     * @returns
     */
    BootstrapTable.prototype.getCreationData = function( container_element ){
    	return $(container_element + ' a.swaf-auto-gen-field').editable('getValue');
    };    
    $.fn.bootstrapTable.methods.push('getCreationData');
    
    /**
     * buildCreationForm 으로 생성된 신규 레코드 양식폼의 입력된 정보를 서버로 전송한다.
     *  
     * @param container_element	신규 양식 폼을 생성한 HTML 요소
     * @param post_url POST		방식으로 등록을 요청시 사용되는 URL
     * @param success_callback	서버 요청 성공후 호출되는 메소드 
     * @param error_callback	서버 요청 실패시 호출되는 메소드
     */
    BootstrapTable.prototype.submitCreationData = function( container_element , post_url, success_callback, error_callback){
    	var	new_data = $(container_element + ' a.swaf-auto-gen-field').editable('getValue');
    	
    	$.ajax({
			method	: "POST",
			url		: post_url,
			data	: new_data
		}).done(function(msg){
			console.log("save it");
			
			$(container_element + ' a.swaf-auto-gen-field').editable('setValue', null)  	//clear values
	        .editable('option', 'pk', null)          			//clear pk
	        .removeClass('editable-unsaved');        			//remove bold css
			
			if ( success_callback !== undefined ) success_callback(msg);
			
		});
    };
    // to register method which i made.    
    $.fn.bootstrapTable.methods.push('submitCreationData');
    
    
    
    /**
     * 신규 등록을 위해서 자동으로 모달의 양식을 생성함
     *  
     * @param container_element
     */
    BootstrapTable.prototype.buildNewForm = function(   ){
    	var form_option = this.options. formOptions;
    	
    	var that = this;
    	 
    	var	modal_dlg_tag = 
    	[
		'<div class="modal fade" id="'+  form_option.formID +'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">',
		'  <div class="modal-dialog" role="document">',
		'    <div class="modal-content">',
		'      <div class="modal-header">',
		'        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>',
		'        <h4 class="modal-title">'+ form_option.title +'</h4>',
		'      </div>',
		'      <div class="modal-body"> <form class="form-horizontal"></form>',
		'      </div>',
		'      <div class="modal-footer">',
		'        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>',
		'        <button type="button" class="btn btn-primary" id="save-com-btn">Save changes</button>',
		'      </div>',
		'    </div>',
		'  </div>',
		'</div>'
    	 ].join(' ');
    	
    	$('body').append(modal_dlg_tag);
    	
    	
    	
    	var $table = $( '#' + form_option.formID + ' .modal-body form' );
    	
    	$.each(this.columns, function(index, column){
    		if ( !column.formInput ) return;
    		    		
    		var input_tag = '';
    		var inside_tag = '';
    		
    		switch( column.type ){
    			case 'select':
    				inside_tag = '<select  class="form-control" id="form-input-' + column.field + '>';//</select>');
    				
    				$.each(column.source, function(i, obj){
    					inside_tag +=  '<option value="' + obj.value + '">' + obj.text + '</option>';
    				});
    				    	    	    	    		
    	    		console.log( inside_tag );
    				break;
    				
    			case 'checklist':
//    				inside_tag = '<div class="radio">';
    				$.each(column.source, function(i, obj){
    					inside_tag +=  
    						[
    						'<div class="radio"><label>',
    						'    <input type="checkbox" name="' + column.field + '" id="optionsRadios1" value="' + obj.value + '" >',
    						obj.text,
    						'  </label></div>'
    						].join (' ');
    				});
    				//inside_tag += '</div>';
    	    		console.log( inside_tag );
    	    		break;
    	    	
    			case 'password':
    				inside_tag = '      <input type="password" class="form-control" name="' + column.field + '" id="form-input-' + column.field + '" placeholder="입력하세요">';
    				break;
    				
    			default:    				
    				inside_tag = '      <input type="text" class="form-control" name="' + column.field + '" id="form-input-' + column.field + '" placeholder="입력하세요">'; 
    				break;
    		}
    		
			input_tag = 
        		[
				'<div class="form-group">',
				'    <label for="form-input-' + column.field + '" class="col-sm-2 control-label">' + column.title + '</label>',
				'    <div class="col-sm-10">',
				inside_tag,
				'    </div>',
				'  </div>'
				].join(' ');
			
        	$table.append( input_tag );
    	});    	
    	
    	
    	$table.submit(function(event){
    		console.log('SUBMIT captured');
    		console.log( event );
    	});
    	
    	$('button#save-com-btn').click(function(){
    		// console.log( form_option.createAction, $table.serialize() );
    		if( form_option.createAction === undefined ) return ;    		
    		$.ajax( {
    			method	: "POST",
    			url 	: form_option.createAction,
    			data	: $table.serialize()
    		}).done(function(data){
    			that.refresh();
    			$('#' + form_option.formID ).modal('hide'); 
    		});
    	});
    	
    	console.log("...BootstrapTable.prototype.buildNewForm END");
    };
    
    // to register method which i made.
    $.fn.bootstrapTable.methods.push('buildNewForm');
    
    
    
    
    /**
     * jsonwriter에 맞춘 데이터 로드
     * @param container_element
     */
//    BootstrapTable.prototype.load2 = function(  ){
////    	console.log("...BootstrapTable.prototype.load2");
//    	var that 		= this;
//    	var form_option = this.options.formOptions;    	
//    	$.ajax( {
//    		url : that.options.url,		
//    	}).done(function(data){
//    		console.log("CALL before that.load BEGIN -------------------------->");    		
//    		that.options.sidePagination = "server";
//    		that.load(data);
//    		console.log("CALL after  that.load END   -------------------------->");
//    	});	
////    	console.log("...BootstrapTable.prototype.load2 END");
//    };
//    
//    // to register method which i made.
//    $.fn.bootstrapTable.methods.push('load2');
    
    /**
     * checked로 선택된 row에 처리할 함수 
     * 
     * do_handler 선택된 row별로 핸들링할 함수
     */
	BootstrapTable.prototype.doLoopForChecked = function( do_handler ){
		var	data = this.getSelections();				
		$.each( data, do_handler );		
	};

// to register method which i made.
$.fn.bootstrapTable.methods.push('doLoopForChecked');

    
    
}(jQuery);
