<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>管理员用户</title>
		<!--引入模板连接文件-->
		<#include "/mgrsite/common/links-tpl.ftl"/>
		<style>
			
		</style>
		<script type="text/javascript">
			
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
		
	</body>

</html>