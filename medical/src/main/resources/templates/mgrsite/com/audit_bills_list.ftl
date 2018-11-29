<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>管理员用户</title>
		<!--引入分页css-->
		<link rel="stylesheet" href="css/plugins/pagination.css" />
		<!--导入confirm的css-->
		<link rel="stylesheet" href="css/plugins/jquery-confirm.min.css" />	
		<!--引入模板连接文件-->
		<#include "/mgrsite/common/links-tpl.ftl"/>
		<!-- 导入jqueryFrom 使用AJAX提交数据 -->
		<script src="js/plugins/jquery-validation-1.14.0/lib/jquery.form.js"></script>
		<!--导入confirem插件的js-->
		<script type="text/javascript" src="js/plugins/jquery-confirm.min.js"></script>
		<!--加入分页插件-->
		<script type="text/javascript" src="js/plugins/jquery.pagination.js"></script>
		<style>
		</style>
		
		<script type="text/javascript">
			$(function(){
				// 配置分页
				$(".page-pagination").pagination({
				 	pageCount: ${(pageResult.totalPage)!},
				 	totalData: ${(pageResult.count)!},
				 	showData:  ${(pageResult.pageSize)!},
					current: ${(pageResult.currentPage)!},
					coping: true,	// 显示首页末页
					jump: true,
					jumpBtnCls:'btn-primary',
					callback: function (api) {
						var currentPage = api.getCurrent();
						 $(".bill-detail-qo input[name='currentPage']").val(currentPage);
						 $(".bill-detail-qo").submit();
				    }
				});
				
				
				// 确认当前公司的放款
				$("#pass-money").click(function(){
					// 发送放款确认请求
					$.ajax({
						type:"post",
						url:"${rc.contextPath}/passMoney",
						async:true,
						data:{
							"medicalDept":${(medicalDept)!},
							"currentAmount":${(currentAmount)!}
						},
						success:function(data){
							if(data.success){
								$.dialog({
									title: '提示',
									content: "提交账单成功",
								});
								window.location.reload();
							}else{
								$.dialog({
									title: '提交失败',
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
					<#include "leftmenue-tpl.ftl"/>
				</div>
				<div class="col-xs-9">
					<div class="row">
						<div class="panel panel-default">
							<div class="panel-body">
								<form class="form-inline bill-detail-qo" method="post" action="${rc.contextPath}/billAuditListCom">
									<input type="hidden" name="currentPage" value="${(pageResult.currentPage)!}"/>
									<div class="form-group">
										<label for="medical-dept">选择医务室</label>
										<select class="form-control" id="medical-dept" name="medicalDept">
											<option value="-1">---请选择---</option>
											<#if medicalRooms?? && (medicalRooms?size> 0)>
												<#list medicalRooms as room>
													<!--默认勾选 判断是否该有checked-->
													<#if medicalDept??  && room.id == medicalDept>
														<#assign isChecked="checked"/>
													</#if>
													<option value="${room.id}" ${(isChecked)!}>${room.com.comDeptName}</option>
												</#list>
											</#if>
										</select>
									</div>
									<button type="submit" class="btn btn-default">查询医务室</button>
									<button type="button" id="pass-money" class="btn btn-info pull-right">确认放款</button>
								</form>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									医务室名称：<span class="label label-primary bill-info">${(medicalRoom.com.comDeptName)!}</span>
									本次报销金额：<span class="label label-success bill-info">${(currentAmount)!}</span>
									目前可用总金额：<span class="label label-warning bill-info">${(medicalRoom.remainAmount)!}</span>
					    		<p class="pull-right">负责人: ${(medicalRoom.principal.username)!}</p>
					    	</h3>
							</div>
							<div class="panel-body">
								<div id="row">
									<table class="table table-hover">
										<tr>
											<th>申请时间</th>
											<th>员工工号</th>
											<th>申请人姓名</th>
											<th>报销类型</th>
											<th>请求报销金额</th>
											<th>实际报销金额</th>
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
												<td>${billAuditVO.bill.auditMoney?string('0.##')}</td>
												<td><a class="bill-detail" href="javascript:;" data-jsonstring = ${billAuditVO.jsonString}>查看</a></td>
											</tr>
											</#list>
										<#else>
											<tr>
												<th colspan="6">暂未插询到任何数据</th>
											</tr>
										</#if>
									</table>
								</div>
								<div class="row">
									<div class="page-pagination"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</body>

</html>