<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>个人中心</title>
		<!--引入模板连接文件-->
		<#include "common/links-tpl.ftl"/>
		<style>
			.head-img img{
				width: 150px;
				height: 210px;
			}
		</style>
		<script type="text/javascript">
		</script>
	</head>

	<body>
		<div class="container">
			<!--引入模板的头部-->
			<#include "common/head-tpl.ftl"/>
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="${rc.contextPath}/personal" class="btn btn-info panel-title">个人信息</a>
						<a href="${rc.contextPath}/bill" class="btn btn-default panel-title">我要报销</a>
						<a href="${rc.contextPath}/billHistory" class="btn btn-default panel-title">报销历史</a>
					</div>
					<div class="panel-body">
						<div class="col-xs-9">
							<form id="" class="form-horizontal" method="post" action="">
								<div class="row">
									<div class="col-xs-5">
											<label for="realname" class="col-sm-4 control-label">姓名</label>
											<div class="col-sm-8">
												<p name="realname" id="realname" class="form-control-static">${(employee.realname)!}</p>
											</div>
									</div>
									<div class="col-xs-4">
											<label for="gender" class="col-sm-6 control-label">性别</label>
											<div class="col-sm-6">
												<p name="gender" id="gender" class="form-control-static">${employee.genderDisplay}</p>
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-5">
											<label for="idNumber" class="col-sm-4 control-label">身份证号码</label>
											<div class="col-sm-8">
												<p name="idNumber" id="idNumber" class="form-control-static">${employee.idNumber}</p>
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-5">
											<label for="bankNumber" class="col-sm-4 control-label">银行卡号</label>
											<div class="col-sm-8">
												<p name="bankNumber" id="bankNumber" class="form-control-static">${employee.bankNumber}</p>
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-5">
											<label for="phoneNumber" class="col-sm-4 control-label">联系电话</label>
											<div class="col-sm-8">
												<p name="phoneNumber" id="phoneNumber" class="form-control-static">${employee.phoneNumber}</p>
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-5">
											<label for="com" class="col-sm-4 control-label">分公司</label>
											<div class="col-sm-8">
												<p name="com" id="com" class="form-control-static">${comname}</p>
											</div>
									</div>
									<div class="col-xs-4">
											<label for="dept" class="col-sm-6 control-label">所属部门</label>
											<div class="col-sm-6">
												<p name="dept" id="dept" class="form-control-static">${deptname}</p>
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-5">
											<label for="jobId" class="col-sm-4 control-label">工号</label>
											<div class="col-sm-8">
												<p name="jobId" id="jobId" class="form-control-static">${employee.jobId}</p>
											</div>
									</div>
								</div>
							</form>
						</div>
						<div class="col-xs-3 head-img">
							<a href="javascript:;" class="thumbnail">
						      <img src="" alt="本人照片">
						    </a>
							<h6 class="text-center">照片</h6>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>