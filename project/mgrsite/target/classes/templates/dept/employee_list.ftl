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
		<#include "/common/links-tpl.ftl"/>
		<!-- 导入jqueryFrom 使用AJAX提交数据 -->
		<script src="js/plugins/jquery-validation-1.14.0/lib/jquery.form.js"></script>
		<!--导入confirem插件的js-->
		<script type="text/javascript" src="js/plugins/jquery-confirm.min.js"></script>
		<style>
			
		</style>
		<script type="text/javascript">
			$(function(){
				
				// 新增员工 
				$("#add-employee").click(function(){
					// 清空之前数据
					$("#employeeAddModelForm")[0].reset();
					var json = $(this).data("jsonstring");
					
					
					// 展示模态框
					$("#employeeAddModal").modal("show");
				});
				
				
				// 查看员工 详情并修改
				$(".employee-detail").click(function(){
					// 清空之前数据
					$("#employeeDetailModelForm")[0].reset();
					var json = $(this).data("jsonstring");
					
					// TODO 目前三个框  查询框  新增框   修改框
					// 目前三个框  查询框  新增框   修改框
					
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
					$("#employeeDetailModal").modal("show");
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
			<#include "/common/head-tpl.ftl"/>
			<div class="row">
				<div class="col-xs-3">
					<!--引入左侧目录-->
					<#assign currentMenu="employeeList"/>
					<#include "leftmenue-tpl.ftl"/>
				</div>
				<div class="col-xs-9">
					<div class="row">
						<div class="panel panel-default">
							<div class="panel-body">
								<form class="form-inline" id="select-form" method="post" action="${rc.contextPath}/">
								  <div class="form-group">
								    <label for="job-id">员工工号</label>
								    <input class="form-control" id="job-id" type="text" name="jobId" value=""/>
								  </div>
								  <div class="form-group">
								    <label for="realname">员工姓名</label>
								    <input class="form-control" id="realname" type="text" name="realname"/>
								  </div>
								  <button type="button" id="select-employee" class="btn btn-info">查询员工</button>
								  <button type="button" id="add-employee" class="btn btn-info pull-right">新增员工</button>
								</form>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="panel panel-default">
					  	<div class="panel-heading">
					    	<h3 class="panel-title">员工列表</h3>
					 	</div>
				  		<div class="panel-body">
				  			<table class="table table-hover">
								<tr>
									<th>员工工号</th>
									<th>员工姓名</th>
									<th>性别</th>
									<th>所属公司</th>
									<th>所在部门</th>
									<th>操作</th>
								</tr>
								<#if (pageResult.list)?? && ((pageResult.list)?size > 0)>
								<#list pageResult.list as employee>
									<tr>
										<td>${employee.jobId}</td>
										<td>${employee.realname}</td>
										<td>${employee.genderDisplay}</td>
										<td>${employee.comDept.comDeptName}</td>
										<td>${employee.comDept.parentComDept.comDeptName}</td>
										<td><a class="employee-detail" href="javascript:;" data-jsonstring = ${employee.jsonString}>操作</a></td>
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
		
		
		<!-- 新增员工框 -->
		<div class="modal fade" id="employeeAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">新增员工</h4>
		      </div>
		      <div class="modal-body">
		        <form id="employeeAddModelForm" class="form-horizontal" action="${rc.contextPath}/">
				  	<div class="row">
					  	<div class="col-xs-12">
					  		 <div class="form-group">
							    <label for="usernameModelValue" class="col-sm-4 control-label">员工账号</label>
							    <div class="col-sm-8">
							    	<input class="form-control" id="usernameModelValue" type="text" name="username"></input>
							    </div>
						  	</div>
					  	</div>
				  	</div>
				  	<div class="row">
				  	<div class="col-xs-6">
				  		 <div class="form-group">
						    <label for="passwordModelValue" class="col-sm-4 control-label">员工密码</label>
						    <div class="col-sm-8">
						      <input class="form-control" id="passwordModelValue" type="text" name="password"></input>
						    </div>
					  	</div>    
				  	</div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary btn-add-employee">新增员工</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
		
		<!-- 员工详情 -->
		<div class="modal fade" id="employeeDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">修改员工信息</h4>
		      </div>
		      <div class="modal-body">
				<div class="col-xs-9">
					<form id="employeeDetailModelForm" class="form-horizontal" method="post" action="${rc.contextPath}/">
						<input id="idModelValue" type="hidden" name="id" value="" />
						<div class="row">
							<div class="col-xs-5">
									<label for="realnameModalValue" class="col-sm-4 control-label">真实姓名</label>
									<div class="col-sm-8">
										<input class="form-control" id="realnameModalValue" type="text" name="realname" />
									</div>
							</div>
							<div class="col-xs-4">
								<div class="col-sm-3">
									<label class="control-label">性别</label>
								</div>
								<div class="col-sm-6">
									<label class="radio-inline">
									  	<input type="radio" name="gender" value="0"> 男
									</label>
									<label class="radio-inline">
									  	<input type="radio" name="gender" value="1"> 女
									</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-5">
									<label for="idNumberModalValue" class="col-sm-4 control-label">身份证号码</label>
									<div class="col-sm-8">
										<input class="form-control" id="idNumberModalValue" type="text" name="idNumber" />
									</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-5">
									<label for="bankNumberModalValue" class="col-sm-4 control-label">银行卡号</label>
									<div class="col-sm-8">
										<input class="form-control" id="bankNumberModalValue" type="text" name="bankNumber" />
									</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-5">
									<label for="phoneNumberModalValue" class="col-sm-4 control-label">联系电话</label>
									<div class="col-sm-8">
										<input class="form-control" id="phoneNumberModalValue" type="text" name="phoneNumber" />
									</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-5">
									<label for="comModalValue" class="col-sm-4 control-label">分公司</label>
									<div class="col-sm-8">
										<select class="form-control" id="comModalValue" name="com">
											<option value="-1">---请选择---</option>
											<#if (coms)?? && ((coms)?size > 0) >
											<#list coms as com>
												<option value="${com.id}">${com.comDeptName}</option>
											</#list>
											</#if>
										</select>
									</div>
							</div>
							<div class="col-xs-5">
									<label for="deptModalValue" class="col-sm-6 control-label">所属部门</label>
									<div class="col-sm-6">
										<select class="form-control" id="deptModalValue" name="dept">
											<option value="-1">---请选择---</option>
										</select>
									</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-5">
								<label for="jobIdModalValue" class="col-sm-4 control-label">工号</label>
								<div class="col-sm-8">
									<input class="form-control" id="jobIdModalValue" type="text" name="jobId" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-offset-5 col-sm-2">
								<button type="button" class="btn btn-primary save-btn">保存信息</button>
							</div>										
						</div>
					</form>
				</div>
				<div class="col-xs-3 head-img">
					<a href="javascript:;" class="thumbnail">
				      <img src="" alt="本人照片">
				    </a>
				</div>
				
				
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary btn-detail-employee">确认修改</button>
		      </div>
		    </div>
		  </div>
		</div>
	</body>

</html>