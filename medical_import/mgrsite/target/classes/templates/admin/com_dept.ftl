<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>公司管理</title>
		<!--导入confirm的css-->
		<link rel="stylesheet" href="css/plugins/jquery-confirm.min.css" />
		<!--引入分页css-->
		<link rel="stylesheet" href="css/plugins/pagination.css" />
		<!--引入模板连接文件-->
		<#include "/common/links-tpl.ftl"/>
		<!-- 导入jqueryFrom 使用AJAX提交数据 -->
		<script src="js/plugins/jquery-validation-1.14.0/lib/jquery.form.js"></script>
		<!--导入confirem插件的js-->
		<script type="text/javascript" src="js/plugins/jquery-confirm.min.js"></script>
		<style>
			
		</style>
		<script type="text/javascript">
			$(function(){
				// 新增大病类型
				$(".add-comDept").click(function(){
					// 清空之前数据
					$("#comModelForm")[0].reset();
					// 填充提示
					var json = $(this).data("tips");
					$("#myModalLabel").html(json.title);
					$("#comDeptLabel").html(json.label);
					$("#parentComDeptIdModelValue").val(json.parentId);
					$("#typedModelValue").val(json.type);
					// 展示模态框
					$("#comDeptModal").modal("show");
				});
				
				// 保存提交数据
				$(".comDept-save").click(function(){
					$("#comModelForm").ajaxSubmit({
						dataType:"json",
						type:"POST",
						url:"/${rc.contextPath}/addComDept",
						success:function(data){
							if(data.success){
								$.dialog({
									title: '提示',
									content: "新增成功",
								});
								window.location.reload();
							}else{
								$.dialog({
									title: '新增失败',
									content: data.msg
								});
							}
						}
					});
				});
				
				
			})
		</script>
	</head>

	<body>
		<div class="container">
			<!--引入模板的头部-->
			<#include "/common/head-tpl.ftl"/>
			<div class="row">
				<div class="col-xs-3">
					<!--引入左侧目录-->
					<#assign currentMenu="comDept"/>
					<#include "leftmenue-tpl.ftl"/>
				</div>
				<div class="col-xs-9">
					<div class="panel panel-default">
					  	<div class="panel-heading">
					    	<h3 class="panel-title">公司管理
					    		<button class="btn btn-warning add-comDept" id="add-com" data-tips='{"title":"新增分公司","label":"分公司名称","type":"0","parentId":""}'>新增分公司</button>
					    	</h3>
					 	</div>
				  		<div class="panel-body">
				  			<div class="row">
				  				<#if (comDepts)?? && ((comDepts)?size > 0)>
				  				<#list comDepts as fullComDeptVO>
					  				<div class="col-xs-6">
					  					<div class="panel panel-info">
					  						<div class="panel-heading">
					  							<h3 class="panel-title">${fullComDeptVO.com.comDeptName}
										    		<button class="btn btn-warning  add-comDept" id="add-com" data-tips='${fullComDeptVO.tips}'>添加下属部门</button>
										    	</h3>
					  						</div>
					  						<div class="panel-body">
					  							<ul class="list-group">
					  								<#list fullComDeptVO.depts as dept>
												  		<li class="list-group-item">${dept.comDeptName}<a class="pull-right" href="#">删除此部门</a></li>
												  	</#list>	
												</ul>
					  						</div>
					  					</div>
					  				</div>
				  				</#list>
				  				</#if>
				  			</div>
				  		</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 新增模态框 -->
		<div class="modal fade" id="comDeptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel"></h4>
		      </div>
		      <div class="modal-body">
		        <form id="comModelForm" class="form-horizontal" >
	        	  <input id="parentComDeptIdModelValue" type="hidden" name="parentComDeptId" />
	        	  <input id="typedModelValue" type="hidden" name="type" />
				  <div class="form-group">
				    <label class="col-sm-3 control-label" id="comDeptLabel" for="comDeptNameModelValue" ></label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="comDeptNameModelValue" name="comDeptName">
				    </div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary comDept-save">保存</button>
		      </div>
		    </div>
		  </div>
		</div>
	</body>

</html>