/**
 * zj.main.js
 * 
 * @author zdwx
 * @date 2012-5-16
 * @modify date 2012-11-28
 * @use 职介网模块业务主要js
 */
var contextPath;// 工程路径

var zj = {
	/**浏览器是否是ie6
	isie6 : $.browser.msie && ($.browser.version == "6.0")&& !$.support.style,*/
	
	
	
	/**
	 * ajax请求
	 * 
	 * @param {} url 请求地址
	 * @param {} reqParams 请求参数
	 * @param {} successFun 成功回调函数
	 * @param {} failureFun 失败回调函数
	 */
	Ajax : function(url, reqParams, successFun, failureFun) {
		$.ajax({
			type : 'post',
			url : url,
			data : reqParams || {},
			beforeSend : function(xhr) {
			},
			success : function(responseText, textStatus) {
				try {
					response = eval('(' + responseText + ')');
				} catch (e) {
					zj.openErrorWindow();
					//alert("服务器返回的数据的错误！" + e);
					throw e;
				}
				if (response.messageCode) {
					if (response.messageCode == '0') {
						if (successFun) {// 有成功回调函数
							successFun(response);
						}
					} else {
						if (failureFun) {// 有失败回调函数
							failureFun(response);
						} else {
							var errmsg = response.mainMessage;
							if (response.detailMessage != "") {
								errmsg = errmsg + "\n详细信息:"+ response.detailMessage;
							}
							zj.openErrorWindow();
							//alert(errmsg);
						}
					}
				} else {
					if (successFun) {// 有成功回调函数
						successFun(response);
					}
					if (failureFun) {// 有失败回调函数
						failureFun(response);
					}
				}
			},
			complete : function(xhr, textStatus) {
			},
			error : function(xhr, textStatus, errorThrown) {
				zj.openErrorWindow();
				//alert("与服务器连接断开,请重新登录！");
			}
		})
	},
	/**
	 * ajax请求
	 * 
	 * @param {} url 请求地址
	 * @param {} reqParams 请求参数
	 * @param {} successFun 成功回调函数
	 * @param {} failureFun 失败回调函数
	 */
	AjaxParams : function(url, reqParams, successFun, failureFun) {
		$.ajax({
			type : 'post',
			url : url,
			data : reqParams || {},
			beforeSend : function(xhr) {
			},
			success : function(responseText, textStatus) {
				try {
					response = eval('(' + responseText + ')');
				} catch (e) {
					zj.openErrorWindow();
					//alert("服务器返回的数据的错误！" + e);
					throw e;
				}
				if (response.messageCode) {
					if (response.messageCode == '0') {
						if (successFun) {// 有成功回调函数
							successFun(response);
						}
					} else {
						if (failureFun) {// 有失败回调函数
							failureFun(response);
						} else {
							var errmsg = response.mainMessage;
							if (response.detailMessage != "") {
								errmsg = errmsg + "\n详细信息:"+ response.detailMessage;
							}
							zj.openErrorWindow();
							//alert(errmsg);
						}
					}
				} else {
					if (successFun) {// 有成功回调函数
						successFun(response);
					}
					if (failureFun) {// 有失败回调函数
						failureFun(response);
					}
				}
			},
			complete : function(xhr, textStatus) {
			},
			error : function(xhr, textStatus, errorThrown) {
				zj.openErrorWindow();
				//alert("与服务器连接断开,请重新登录！");
			}
		})
	},
	/**
	 * jquery 原生ajax
	 * @param {} options
	 * @param {} maskdom
	 */
	$_ajax:function(url,appenddom_selector,maskdom_selector){
		var options={};
		options.url=url;
		options.beforeSend=function(xhr) {
			if(maskdom_selector){
				$(maskdom_selector).html("<img src='"+contextPath+"/images/loading.gif' style='vertical-align:middle;'>&nbsp;正在加载中,请稍等...").show();
			}
		},
		options.success=function(data){
			$(appenddom_selector).find('table').append($(data));
			if(maskdom_selector){
				$(maskdom_selector).hide();
			}
		},
		options.error=function(xhr, textStatus, errorThrown) {
			if(maskdom_selector){
			   $(maskdom_selector).html("<font style='color:red'>"+(textStatus=='timeout'?'请求超时!':'请求出错!')+"</font>" +
			   		"<a href=\"javascript:void(0)\" onclick=\"zj.$_ajax('"+url+"','"+appenddom_selector+"','"+maskdom_selector+"')\">点击重试</a>");
			}
		};
		options.dataType='html',
		options.timeout=600*1000,//超时20秒
		options.global=false;
		$.ajax(options);
	},
	/**
	 * 查询成功,赋值
	 * 
	 * @param {}
	 *            response
	 */
	evaluation : function(response) {
		var inputs = $(":input");
		inputs.each(function(i, dom) {
			var type = dom.type;
			var name = dom.name;
			if (name) {
				eval('var res=response.data.' + name);
				if (res) {
					if (type == 'text'||type=='hidden') {
						$(dom).val(res);
					} else if (type == 'checkbox') {
						var checkboxvalues = res.split(',');
						for (var i = 0; i < checkboxvalues.length; i++) {
							if ($(dom).val() === checkboxvalues[i]) {
								$(dom).attr('checked', 'checked');
							}
						}
					} else if (type == 'radio') {
						if ($(dom).val() === res) {
							$(dom).attr('checked', 'checked');
						}
					} else if (type == 'select-one') {
						var options = $(dom).children();
						options.each(function(i, d) {
									if ($(d).val() === res) {
										$(d).attr('selected','selected');
									}
								})
					} else if (type == 'select-multiple') {
						var a = new Array();
						for (var i = 0; i < selectvalues.length; i++) {
							a.push(selectvalues[i]);
						}
						$(dom).val(a);
					}
				}
			}
		});
	}
}





