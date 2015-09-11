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
                
                if(  column.type  === undefined ) column.type = "text";
                
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
            // 내가 붙인 부분 시작

            
//            if( column.type == "select") {
//            	console.log( " select column >>", column );
//            	that.$body.find('a[data-name="' + column.field + '"]').editable({
//            		prepend:'Not Selected'
//            		,value: 2    
//            		,source: [
//            		          {value: 1, text: 'Male'},
//            		          {value: 2, text: 'Female'}
//            		    ]  
//            	});
//            }
            
//
//            $( 'a[data-name="' + column.field + '"]' ).each(function(){
//            	            	
//            	var data = that.getData(),
//            	index = $(this).parents('tr[data-index]').data('index'),
//            	row = data[index],
//            	oldValue = row[column.field];
//            	
//            	if( column.checkEditable !== undefined ) {
//            		var can_edit = column.checkEditable(row);
//            		if ( can_edit ) $(this).editable(column);
//            		else {
//            			console.log('NOPE');
//            		}
//            		
//            	}
//            	// console.log('editable-save', column.field, row);
//            });
            // 내가 붙인 부분 끝            

            // 아래는 오리지날
            
            that.$body.find('a[data-name="' + column.field + '"]').editable(column)
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
                    	console.log("shown ---> ", editable);
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

}(jQuery);
