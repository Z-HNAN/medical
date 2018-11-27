<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>疾病管理</title>
		<!--导入confirm的css-->
		<link rel="stylesheet" href="css/plugins/jquery-confirm.min.css" />	
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
				$("#add-serious").click(function(){
					// 清空之前数据
					$("#seriousModelForm")[0].reset();
					// 展示模态框
					$("#sreiousModal").modal("show");
				});
				
				// 保存提交数据
				$(".serious-save").click(function(){
					$("#seriousModelForm").ajaxSubmit({
						dataType:"json",
						type:"POST",
						url:"${rc.contextPath}/addSerious",
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
				
				
				// 逻辑删除
				$(".serious-remove").click(function(){
					var id = $(this).data("seriousid");
					var title = $(this).data("serioustitle");
					$.confirm({
					    title: '确认删除此条信息',
					    content: '大病类型为: ' + title,
					    type: 'red',
				    	icon: 'glyphicon glyphicon-remove',
						buttons: {
							ok: {
								text: '确认',
								btnClass: 'btn-primary',
								action: function() {
									$.ajax({
										type:"post",
										url:"${rc.contextPath}/removeSerious",
										data:{
											"id":id
										},
										async:true,
										success:function(data){
											if(data.success){
												window.location.reload();
											}
										}
									});
								}
							},
							cancel: {
								text: '取消',
								btnClass: 'btn-error'
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
					<#assign currentMenu="seriousList"/>
					<#include "leftmenue-tpl.ftl"/>
				</div>
				<div class="col-xs-9">
					<div class="panel panel-default">
					  	<div class="panel-heading">
					    	<h3 class="panel-title">大病类型维护
					    		<button class="btn btn-warning" id="add-serious">新增大病类型</button>
					    	</h3>
					 	</div>
				  		<div class="panel-body">
				    		<table class="table table-hover">
							  	<tr>
							  		<th>名称</th>
								  	<th>序号</th>
								  	<th>描述</th>
								  	<th></th>
							  	</tr>
							  	<#if (pageResult.list)?? && ((pageResult.list)?size > 0)>
									<#list pageResult.list as serious>
										<tr>
											<td>${serious.title}</td>
											<td>${serious.sequence}</td>
											<td>${serious.description}</td>
											<td><a class="serious-remove" href="javascript:;" data-seriousid="${serious.id}" data-serioustitle="${serious.title}">删除</a></td>
										</tr>
									</#list>
								<#else>
									<tr>
										<th colspan="4">暂未插询到任何数据</th>
									</tr>
								</#if>
							</table>
				  		</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 新增模态框 -->
		<div class="modal fade" id="sreiousModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">新增大病类型</h4>
		      </div>
		      <div class="modal-body">
		        <form id="seriousModelForm" class="form-horizontal">
				  <div class="form-group">
				    <label for="titleModelValue" class="col-sm-2 control-label">大病类型</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="titleModelValue" name="title">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="descModelValue" class="col-sm-2 control-label">描述信息</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="descModelValue" name="description">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="descModelValue" class="col-sm-2 control-label">检索顺序</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="descModelValue" name="sequence">
				    </div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary serious-save">保存</button>
		      </div>
		    </div>
		  </div>
		</div>
	</body>

</html>