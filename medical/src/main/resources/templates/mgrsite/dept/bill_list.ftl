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
				$(".bill-detail").click(function(){
					// 清空之前数据
					$("#auditModelForm")[0].reset();
					var json = $(this).data("jsonstring");
					$("#idModelValue").val(json.id);
					$("#jodIdModelValue").html(json.jobId);
					$("#realnameModelValue").html(json.realname);
					$("#illnessTypeModelValue").html(json.illnessType);
					$("#seriousTypeModelValue").html(json.seriousType);
					$("#hospitalModelValue").html(json.hospital);
					$("#applyMoneyModelValue").html(json.applyMoney);
					$("#auditMoneyModelValue").attr("max",json.applyMoney)
					$("#descriptionModelValue").html(json.description);
					 
					// 展示模态框
					$("#auditModal").modal("show");
				});
				
				// 保存提交数据
				$(".medical-save").click(function(){
					$("#auditModelForm").ajaxSubmit({
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
				
				// 进行具体的查询
				$("#select-employee").click(function(){
					$("#select-form").submit();
				});
				
				// 审核结果操作
				$(".audit-result").click(function(){
					var state = $(this).data("state");
					$("#stateModelValue").val(state);
					// 提交数据到后台
					$("#auditModelForm").ajaxSubmit({
						dataType:"json",
						type:"POST",
						url:"${rc.contextPath}/auditBill",
						success:function(data){
							if(data.success){
								$.dialog({
									title: '提示',
									content: "审核成功",
								});
								// 关闭模态框
								window.location.href = "${rc.contextPath}/billAuditListDept";
							}else{
								$.dialog({
									title: '审核失败',
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
					<#assign currentMenu="billAuditListDept"/>
					<#include "leftmenue-tpl.ftl"/>
				</div>
				<div class="col-xs-9">
					<div class="row">
						<div class="panel panel-default">
							<div class="panel-body">
								<form class="form-inline" id="select-form" method="post" action="${rc.contextPath}/billAuditListDept">
								  <div class="form-group">
								    <label for="job-id">员工工号</label>
								    <input class="form-control" id="job-id" type="text" name="jobId" value=""/>
								  </div>
								  <div class="form-group">
								    <label for="realname">员工姓名</label>
								    <input class="form-control" id="realname" type="text" name="realname"/>
								  </div>
								  <button type="button" id="select-employee" class="btn btn-info pull-right">查询员工</button>
								</form>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="panel panel-default">
					  	<div class="panel-heading">
					    	<h3 class="panel-title">审批报销</h3>
					 	</div>
				  		<div class="panel-body">
				  			<table class="table table-hover">
								<tr>
									<th>申请时间</th>
									<th>员工工号</th>
									<th>申请人姓名</th>
									<th>报销类型</th>
									<th>请求报销金额</th>
									<th>操作</th>
								</tr>
								<#if (pageResult.list)?? && ((pageResult.list)?size > 0)>
								<#list pageResult.list as billAuditVO>
									<tr>
										<td>${billAuditVO.bill.applyDate?string('yyyy-MM-dd')}</td>
										<td>${billAuditVO.employee.jobId}</td>
										<td>${billAuditVO.employee.realname}</td>
										<td>${billAuditVO.bill.illnessTypeDisplay}</td>
										<td>${billAuditVO.bill.applyMoney?string('0.##')}</td>
										<td><a class="bill-detail" href="javascript:;" data-jsonstring = ${billAuditVO.jsonString}>审核</a></td>
									</tr>
									</#list>
								<#else>
									<tr>
										<th colspan="6">暂未插询到任何数据</th>
									</tr>
								</#if>
							</table>
							<!--引入分页标签-->
				  		</div>
					</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 新增模态框 -->
		<div class="modal fade" id="auditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">报销审核</h4>
		      </div>
		      <div class="modal-body">
		        <form id="auditModelForm" class="form-horizontal" action="${rc.contextPath}/auditBill">
		          <input id="idModelValue" type="hidden" name="id" value="" />
		          <input id="stateModelValue" type="hidden" name="state" value="" />
				  <div class="row">
				  	<div class="col-xs-6">
				  		 <div class="form-group">
						    <label for="jodIdModelValue" class="col-sm-4 control-label">员工工号</label>
						    <div class="col-sm-8">
						    	<p class="form-control-static" id="jodIdModelValue" name="jodId"></p>
						    </div>
					  	</div>
				  	</div>
				  	<div class="col-xs-6">
				  		 <div class="form-group">
						    <label for="realnameModelValue" class="col-sm-4 control-label">员工姓名</label>
						    <div class="col-sm-8">
						      <p class="form-control-static" id="realnameModelValue" name="realname"></p>
						    </div>
					  	</div>    
				  	</div>
				  </div>
				  <div class="row">
				  	<div class="col-xs-6">
				  		 <div class="form-group">
						    <label for="illnessTypeModelValue" class="col-sm-4 control-label">报销类型</label>
						    <div class="col-sm-8">
						    	<p class="form-control-static" id="illnessTypeModelValue" name="illnessType"></p>
						    </div>
					  	</div>
				  	</div>
				  	<div class="col-xs-6">
				  		 <div class="form-group">
						    <label for="seriousTypeModelValue" class="col-sm-4 control-label">大病类型</label>
						    <div class="col-sm-8">
						      <p class="form-control-static" id="seriousTypeModelValue" name="seriousType"></p>
						    </div>
					  	</div>
				  	</div>
				  </div>
				  <div class="row">
				  	<div class="col-xs-6">
				  		 <div class="form-group">
						    <label for="hospitalModelValue" class="col-sm-4 control-label">就诊医院</label>
						    <div class="col-sm-8">
						    	<p class="form-control-static" id="hospitalModelValue" name="hospital"></p>
						    </div>
					  	</div>
				  	</div>
				  	<div class="col-xs-6">
				  		 <div class="form-group">
						    <label for="applyMoneyModelValue" class="col-sm-4 control-label">报销金额</label>
						    <div class="col-sm-8">
						      <p class="form-control-static" id="applyMoneyModelValue" name="applyMoney"></p>
						    </div>
					  	</div>
				  	</div>
				  </div>
				  <div class="row">
				  	<div class="col-xs-offset-6 col-xs-6">
				  		 <div class="form-group">
						    <label for="auditMoneyModelValue" class="col-sm-4 control-label">审批金额</label>
						    <div class="col-sm-8">
						    	<input class="form-control" id="auditMoneyModelValue" type="number" max="" name="auditMoney" />
						    </div>
					  	</div>
				  	</div>
				  </div>
				  <div class="row">
				  	<div class="col-xs-6">
				  		 <div class="form-group">
				  		 	<div class="row">
				  		 		<label for="descriptionModelValue" class="col-sm-4 control-label">描述信息</label>
				  		 	</div>
				  		 	<div class="row">
				  		 		<div class="col-sm-offset-2 col-sm-9">
				  		 			<p class="form-control-static" id="descriptionModelValue" name="description"></p>
				  		 		</div> 
				  		 	</div>
					  	</div>
				  	</div>
				  	<div class="col-xs-6">
				  		 <div class="form-group">
				  		 	<div class="row">
				  		 		<label for="noteModelValue" class="col-sm-4 control-label">审核意见</label>
				  		 	</div>
						    <div class="row">
						    	<div class="col-sm-offset-1 col-sm-9 ">
					    	 		<textarea class="form-control" id="noteModelValue" name="note"></textarea>
						    	</div> 		
						    </div>
					  	</div>
				  	</div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary audit-result" data-state="3">审核通过</button>
		        <button type="button" class="btn btn-warning audit-result" data-state="1">审核拒绝</button>
		      </div>
		    </div>
		  </div>
		</div>
	</body>

</html>