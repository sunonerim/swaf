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
function	NumberInt(num){
	return Number(num);	
}

var gProduct =  
{ 
	    "_id" : "54eec70a03645789d0bd4812", 
	    "userid" : "sunonerim", 
	    "category" : [
	        "ae1234"
	    ], 
	    "sku" : "277559824", 
	    "name" : "제이드 롱 점퍼 JJ0249", 
	    "price" : 98000, 
	    "imageSmall" : "010_small.jpg", 
	    "images" : [
	        "010_main_01.jpg", 
	        "010_main_02.jpg", 
	        "010_main_03.jpg", 
	        "010_main_04.jpg", 
	        "010_main_05.jpg"
	    ], 
	    "shippings" : [
	        {
	            "shipping" : "당일택배", 
	            "cost" : 4000
	        }, 
	        {
	            "shipping" : "일반택배", 
	            "cost" : 2500
	        }, 
	        {
	            "shipping" : "저렴택배", 
	            "cost" : NumberInt(1000)
	        }
	    ], 
	    "shippingPayMethod" : "주문시결제", 

	    "options" : {
	        "optionLabel" : "색상선택", 
	        "optionItmes" : [
	            {
	                "optionName" : "차콜", 
	                "optionImage" : "001_option_01.jpg", 
	                "subOptions" : {
	                    "optionLabel" : "사이즈선택", 
	                    "optionItems" : [
	                        {
	                            "optionName" : "S", 
	                            "optionPrice" : NumberInt(88000)
	                        }, 
	                        {
	                            "optionName" : "M", 
	                            "optionPrice" : NumberInt(95000)
	                        }, 
	                        {
	                            "optionName" : "L", 
	                            "optionPrice" : NumberInt(90000)
	                        }, 
	                        {
	                            "optionName" : "XL", 
	                            "optionPrice" : NumberInt(90000)
	                        }, 
	                        {
	                            "optionName" : "XXL", 
	                            "optionPrice" : NumberInt(90000)
	                        }
	                    ]
	                }
	            }, 
	            {
	                "optionName" : "블랙", 
	                "optionImage" : "001_option_02.jpg", 
	                "subOptions" : {
	                    "optionLabel" : "사이즈선택", 
	                    "optionItems" : [
	                        {
	                            "optionName" : "S", 
	                            "optionPrice" : NumberInt(88000)
	                        }, 
	                        {
	                            "optionName" : "M", 
	                            "optionPrice" : NumberInt(95000)
	                        }, 
	                        {
	                            "optionName" : "L", 
	                            "optionPrice" : NumberInt(90000)
	                        }, 
	                        {
	                            "optionName" : "XL", 
	                            "optionPrice" : NumberInt(90000)
	                        }, 
	                        {
	                            "optionName" : "XXL", 
	                            "optionPrice" : NumberInt(90000)
	                        }
	                    ]
	                }
	            }, 
	            {
	                "optionName" : "핑크", 
	                "optionImage" : "001_option_03.jpg", 
	                "subOptions" : {
	                    "optionLabel" : "사이즈선택", 
	                    "optionItems" : [
	                        {
	                            "optionName" : "S", 
	                            "optionPrice" : NumberInt(88000)
	                        }, 
	                        {
	                            "optionName" : "M", 
	                            "optionPrice" : NumberInt(95000)
	                        }, 
	                        {
	                            "optionName" : "L", 
	                            "optionPrice" : NumberInt(90000)
	                        }, 
	                        {
	                            "optionName" : "XL", 
	                            "optionPrice" : NumberInt(90000)
	                        }, 
	                        {
	                            "optionName" : "XXL", 
	                            "optionPrice" : NumberInt(90000)
	                        }
	                    ]
	                }
	            }, 
	            {
	                "optionName" : "블루", 
	                "optionImage" : "001_option_04.jpg", 
	                "subOptions" : {
	                    "optionLabel" : "사이즈선택", 
	                    "optionItems" : [
	                        {
	                            "optionName" : "S", 
	                            "optionPrice" : NumberInt(88000)
	                        }, 
	                        {
	                            "optionName" : "M", 
	                            "optionPrice" : NumberInt(95000)
	                        }, 
	                        {
	                            "optionName" : "L", 
	                            "optionPrice" : NumberInt(90000)
	                        }, 
	                        {
	                            "optionName" : "XL", 
	                            "optionPrice" : NumberInt(90000)
	                        }, 
	                        {
	                            "optionName" : "XXL", 
	                            "optionPrice" : NumberInt(90000)
	                        }
	                    ]
	                }
	            }
	        ]
	    }, 
	    
	    "manufacturer" : "드롱기", 
	    "brand" : "샛별", 
	    "productStatus" : "새상품", 
	    "madeCountry" : "중국", 
	    "receipt" : "신용카드전표, 현금영수증 발급", 
	    "as" : "012312330", 
	    "certificate" : "KS", 
	    "description" : "淘宝东南亚\nFind the best bargains and discounts online at Taobao.com.\n聚划算\n手机聚划算 · 登录免费注册 · 我的聚划算 · 购物车 · 我的淘宝 · 已买到 ...\n手机淘宝\n手机淘宝. 具有查看附近的生活优惠信息、商品搜索、浏览、购买、支付 ...\n淘宝达人\n淘宝达人是淘宝网官方第一导购资讯平台。通过具有专业背景、资深 ...\n注册\n账户注册. 1设置登录名; 2填写账户信息; 㑇注册成功 ... 需通过邮箱注 ...\n宝贝\n所有宝贝 · 天猫 · 二手 · 我的搜索. 共29298.77万 件宝贝 211627款 ...", 
	    "priceOriginal" : NumberInt(88000)
	};

$(document).ready(function(){
	$("#som-product-name").append( gProduct.name );
	
	for ( var i=0; i< gProduct.options.optionItmes.length; i++ ){
		var rrr = $("<option value='" + gProduct.options.optionItmes[i].optionName + "'>" + gProduct.options.optionItmes[i].optionName + "</option>");
		console.log(gProduct.options.optionItmes[i].subOptions);
		rrr.data(gProduct.options.optionItmes[i].subOptions);
		rrr.appendTo("#som-option-level1");
		
 //   	$("#som-option-level1").append("<option value='" + gProduct.options.optionItmes[i].optionName + "'>" + gProduct.options.optionItmes[i].optionName + "</option>");
	}
	
	$("#som-option-level1").change(function(){
		
		console.log( $("#som-option-level1 option:selected").data().optionLabel );
	});
	
});
</script>

<title>List </title>
</head>
<body>
<div id="som-product-name"></div>

<form class="form-horizontal">
	<div class="form-group">
		<label class="col-sm-2 control-label">Email</label>
		<div class="col-sm-10">
			<select class="form-control" id="som-option-level1">
				<option>선택하시요</option>  
			</select>
		</div>
	</div>
</form>


<select class="form-control" id="som-option-level2">
	<option>선택하시요</option>  
</select>

</body>
</html>