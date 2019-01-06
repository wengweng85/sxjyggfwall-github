/**
 * 
 * @use 专业树
 */
 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var path = window.location.pathname;
contextPath = path.substring(0, path.substr(1).indexOf('/') + 1);
var requesturl=contextPath+'/comm/majorquery';
$(function() {
	// 将ul.tabs区域设定为选项卡，用来控制div.panes区域中最近一层的各div区域
    // 注意tabs和panes与html中class的对应关系
    $("ul.tabs").tabs("div.panes > div",{history: true}); 
	$.fn.zTree.init($("#tree"), setting);
	$("#makesure").click(function() {
		if($("#nodeid").val()==null || $("#nodeid").val() == "" ){
			alert("请选择专业!");
		} else {
			//parent.window.returnValue = $("#nodeid").val()+","+$("#nodename").val();
			//window.close();
			parent.$('#aac183').val($("#nodeid").val());
			parent.$('#aac183_name').val($("#nodename").val());
			parent.$('#aac183_name').blur();
		    parent.layer.close(index);
		}
	});
	$("#clear").click(function() {
		//parent.window.returnValue =""+","+"";
		//window.close();
		parent.$('#aac183').val("");
		parent.$('#aac183_name').val("");
		parent.$('#aac183_name').blur();
		parent.layer.close(index);
	});
});
var setting = {
	view : {
		dblClickExpand : false,
		showLine : false,
		expandSpeed:"normal"
	},
	async : {
		enable : true,
		url : requesturl,
		autoParam : ["id=parentid"]
	},
	data:{
		simpleData: {
		   enable: true,
		   pIdKey: "pid"
	    }
	},
	callback : {
		beforeExpand : beforeExpand,
		onExpand : onExpand,
		onClick : onClick
	}
};
var curExpandNode = null;
function beforeExpand(treeId, treeNode) {
	var pNode = curExpandNode ? curExpandNode.getParentNode() : null;
	var treeNodeP = treeNode.parentTId ? treeNode.getParentNode() : null;
	var zTree = $.fn.zTree.getZTreeObj("tree");
	for (var i = 0, l = !treeNodeP ? 0 : treeNodeP.children.length; i < l; i++) {
		if (treeNode !== treeNodeP.children[i]) {
			zTree.expandNode(treeNodeP.children[i], false);
		}
	}
	while (pNode) {
		if (pNode === treeNode) {
			break;
		}
		pNode = pNode.getParentNode();
	}
	if (!pNode) {
		singlePath(treeNode);
	}

}
function singlePath(newNode) {
	if (newNode === curExpandNode)
		return;
	if (curExpandNode && curExpandNode.open == true) {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		if (newNode.parentTId === curExpandNode.parentTId) {
			zTree.expandNode(curExpandNode, false);
		} else {
			var newParents = [];
			while (newNode) {
				newNode = newNode.getParentNode();
				if (newNode === curExpandNode) {
					newParents = null;
					break;
				} else if (newNode) {
					newParents.push(newNode);
				}
			}
			if (newParents != null) {
				var oldNode = curExpandNode;
				var oldParents = [];
				while (oldNode) {
					oldNode = oldNode.getParentNode();
					if (oldNode) {
						oldParents.push(oldNode);
					}
				}
				if (newParents.length > 0) {
					for (var i = Math.min(newParents.length, oldParents.length)
							- 1; i >= 0; i--) {
						if (newParents[i] !== oldParents[i]) {
							zTree.expandNode(oldParents[i], false);
							break;
						}
					}
				} else {
					zTree.expandNode(oldParents[oldParents.length - 1], false);
				}
			}
		}
	}
	curExpandNode = newNode;
}

function onExpand(event, treeId, treeNode) {
	curExpandNode = treeNode;
}
function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	if (treeNode.isParent) {
		zTree.expandNode(treeNode, null, null, null, true);
	} else {
		setValue(treeNode);
	}
}
function setValue(result){
	$('#nodeid').val(result.id);
	$('#nodename').val(result.name);
}
