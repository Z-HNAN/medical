<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>医务室管理</title>
		<!--导入confirm的css-->
		<link rel="stylesheet" href="css/plugins/jquery-confirm.min.css" />	
		<!--引入模板连接文件-->
		<#include "/mgrsite/common/links-tpl.ftl"/>
		<!-- 导入jqueryFrom 使用AJAX提交数据 -->
		<script src="js/plugins/jquery-validation-1.14.0/lib/jquery.form.js"></script>
		<!--导入confirem插件的js-->
		<script type="text/javascript" src="js/plugins/jquery-confirm.min.js"></script>
		<style>
			
		</style>
		<script type="text/javascript">
			$(function(){
				// 新增大病类型
				$("#add-rom").click(function(){
					// 清空之前数据
					$("#medicalRoomModelForm")[0].reset();
					// 展示模态框
					$("#medicalRoomModal").modal("show");
				});
				
				// 保存提交数据
				$(".medical-save").click(function(){
					$("#medicalRoomModelForm").ajaxSubmit({
						dataType:"json",
						type:"POST",
						url:"${rc.contextPath}/addMedical",
						success:function(data){
							if(data.success){
								$.dialog({
									title: '提示',
									content: "新增成功",
								});
								// 关闭模态框
								window.location.href = "${rc.contextPath}/medicalList";
							}else{
								$.dialog({
									title: '新增失败',
									content: data.msg
								});
							}
						}
					});
				});
				
			});
		</script>
	</head>

	<body>
		<div class="container">
			<!--引入模板的头部-->
			<#include "/mgrsite/common/head-tpl.ftl"/>
			<div class="row">
				<div class="col-xs-3">
					<!--引入左侧目录-->
					<#assign currentMenu="medicalList"/>
					<#include "leftmenue-tpl.ftl"/>
				</div>
				<div class="col-xs-9">
					<div class="row">
						<div class="panel panel-default">
							<div class="panel-body">
								<form class="form-inline" method="post" action="${rc.contextPath}/medicalList">
								  <div class="form-group">
								    <label for="medical-dept">选择医务室</label>
								    <select class="form-control" id="medical-dept" name="medicalDept">
								    	<option value="-1">---请选择---</option>
								    	<#if medicalRooms?? && (medicalRooms?size > 0)>
							    		<#list medicalRooms as room>
							    			<option value="${room.id}">${room.com.comDeptName}</option>
							    		</#list>
								    	</#if>
								    </select>
								  </div>
								  <button type="submit" class="btn btn-default">查询医务室</button>
								  <button type="button" id="add-rom" class="btn btn-info pull-right">新增医务室</button>
								</form>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="panel panel-default">
					  	<div class="panel-heading">
					    	<h3 class="panel-title">医务室详情
					    	<p class="pull-right">负责人: ${(medicalRoom.principal.username)!}</p>
					    	</h3>
					 	</div>
				  		<div class="panel-body">
				  			<form id="" class="form-horizontal" method="post" action="">
								<div class="row">
									<div class="col-xs-6">
										<label for="comName" class="col-sm-4 control-label">医务室名称</label>
										<div class="col-sm-8">
											<p name="comName" id="comName" class="form-control-static"> ${(medicalRoom.com.comDeptName)!}</p>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6">
										<label for="budgetAmount" class="col-sm-4 control-label">预算总金额</label>
										<div class="col-sm-8">
											<p name="budgetAmount" id="budgetAmount" class="form-control-static">${(medicalRoom.budgetAmount)!}</p>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6">
										<label for="useAmount" class="col-sm-4 control-label">已使用金额</label>
										<div class="col-sm-8">
											<p name="useAmount" id="useAmount" class="form-control-static">${(medicalRoom.useAmount)!}</p>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6">
										<label for="remainAmount" class="col-sm-4 control-label">剩余可用金额:</label>
										<div class="col-sm-8">
											<p name="remainAmount" id="remainAmount" class="form-control-static">${(medicalRoom.remainAmount)!}</p>
										</div>
									</div>
								</div>
							</form>
				  		</div>
					</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 新增模态框 -->
		<div class="modal fade" id="medicalRoomModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">新增医务室信息</h4>
		      </div>
		      <div class="modal-body">
		        <form id="medicalRoomModelForm" class="form-horizontal">
				  <div class="form-group">
				    <label for="comModelValue" class="col-sm-3 control-label">对应公司</label>
				    <div class="col-sm-9">
				    	<select class="form-control" id="comModelValue" name="comId">
							<option value="-1">---请选择---</option>
							<#if coms?? && (coms?size > 0)>
							<#list coms as com>
								<option value="${com.id}">${com.comDeptName}</option>
							</#list>
							</#if>
				    	</select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="usernameModelValue" class="col-sm-3 control-label">医务室账号</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="usernameModelValue" name="username">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="passwordModelValue" class="col-sm-3 control-label">医务室密码</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="passwordModelValue" name="password">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="budgetAmountModelValue" class="col-sm-3 control-label">初始总预算金额</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="budgetAmountModelValue" name="budgetAmount">
				    </div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary medical-save">保存</button>
		      </div>
		    </div>
		  </div>
		</div>
	</body>

</html>