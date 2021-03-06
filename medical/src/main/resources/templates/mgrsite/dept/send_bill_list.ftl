<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>提交报销单</title>
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
				
				// 每一条被选中的时候 实时刷新数据
				$(".select-in").click(function(){
					var sumMoney = 0;
					var $bills = $(".table input[type=checkbox]");
					$.each($bills, function() {
						if($(this).prop("checked") == true){
							sumMoney = sumMoney + $(this).data("money")
						}
					});
					$("#sumMoney").html(sumMoney);
					$("#remainMoney").html(${medicalRoom.remainAmount?string('0.##')} - sumMoney);
				});
				
				// 确定提交数据
				$("#sendBills").click(function(){
					// 获取当前需要报销的账单
					var $bills = $(".table input[type=checkbox]");
					var billIds = new Array();
					var checkIndex = 0
					$.each($bills, function() {
						if($(this).prop("checked") == true){
							billIds[checkIndex++] = $(this).attr("value");
						}
					});
					$.ajax({
						type:"post",
						url:"${rc.contextPath}/passBills",
						async:true,
						data:{
							"billIds":billIds
						},
						success:function(data){
							if(data.success){
								$.dialog({
									title: '提示',
									content: "提交账单成功",
								});
								// 关闭模态框
//								window.location.reload();
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
					<!--引入左侧目录-->
					<#assign currentMenu="sendBillList"/>
					<#include "leftmenue-tpl.ftl"/>
				</div>
				<div class="col-xs-9">
					<div class="row">
						<div class="panel panel-default">
							<div class="panel-body">
								<form class="form-inline" id="select-form" method="post" action="${rc.contextPath}/billAuditListDept">
								  <div class="form-group">
								    <label for="bills-money">总计金额<span id="sumMoney">0.00</span>元</label>
								    <p class="form-control-static" id="bills-money" name="billMoney"></p>
								  </div>
								  <div class="form-group">
								    <label for="remain-money">剩余可用金额<span id="remainMoney">${medicalRoom.remainAmount?string('0.##')}</span>元</label>
								     <p class="form-control-static" id="remain-money" name="remainMoney"></p>
								  </div>
								  <button type="button" id="sendBills" class="btn btn-info pull-right">提交账单</button>
								</form>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="panel panel-default">
					  	<div class="panel-heading">
					    	<h3 class="panel-title">账单明细</h3>
					 	</div>
				  		<div class="panel-body">
				  			<table class="table table-hover">
								<tr>
									<th>全选<input id="select-all" type="checkbox"/> 全不选<input id="select-not-all" type="checkbox"/></th>
									<th>申请时间</th>
									<th>员工工号</th>
									<th>申请人姓名</th>
									<th>报销类型</th>
									<th>请求报销金额</th>
									<th>审批报销金额</th>
									<th>报销百分比</th>
								</tr>
								<#if (pageResult.list)?? && ((pageResult.list)?size > 0)>
								<#list pageResult.list as billAuditVO>
									<tr>
										<td><input class="select-in" type="checkbox"  value="${billAuditVO.bill.id}" data-money = "${billAuditVO.bill.auditMoney?string('0.##')}"/></td>
										<td>${billAuditVO.bill.applyDate?string('yyyy-MM-dd')}</td>
										<td>${billAuditVO.employee.jobId}</td>
										<td>${billAuditVO.employee.realname}</td>
										<td>${billAuditVO.bill.illnessTypeDisplay}</td>
										<td>${billAuditVO.bill.applyMoney?string('0.##')}</td>
										<td>${billAuditVO.bill.auditMoney?string('0.##')}</td>
										<td>${billAuditVO.billRate}</td>
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
		
	</body>

</html>