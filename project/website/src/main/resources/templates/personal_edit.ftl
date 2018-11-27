<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>个人中心</title>
		<!--导入confirm的css-->
		<link rel="stylesheet" href="css/plugins/jquery-confirm.min.css" />	
		<!--引入模板连接文件-->
		<#include "common/links-tpl.ftl"/>
		<!-- 导入jqueryFrom 使用AJAX提交数据 -->
		<script src="js/plugins/jquery-validation-1.14.0/lib/jquery.form.js"></script>
		<!--导入confirem插件的js-->
		<script type="text/javascript" src="js/plugins/jquery-confirm.min.js"></script>
		<style>
			.head-img img{
				width: 150px;
				height: 210px;
			}
			.form-control{margin-bottom: 5px;}
			
			
		</style>
		<script type="text/javascript">
			$(function(){
				
				$("#com").change(function(){
					// 当选中公司名称后，添加到部门发送ajax请求
					var comId = $(this).val();
					$.ajax({
						type:"post",
						url:"${rc.contextPath}/listDeptByParentComId",
						data:{
							"comId":comId
						},
						async:true,
						success:function(data){
							// 返回成功并接受到了部门 并进行操作
							appendDept(data);
						}
					});
					
				});
				
				function appendDept (depts) {
					// 将json depts 转换成js的json对象
					depts = JSON.parse(depts);
					// 进行覆盖
					var $dept = $("#dept");
					$dept.empty();
					$dept.append("<option value='-1'>---请选择---</option>");
					// 遍历追加元素
					$(depts).each(function(i,n){
						$dept.append("<option value="+ n.id +">"+ n.comDeptName +"</option>");
					});
				}
				
				// 用于提交记录
				$(".save-btn").click(function(){
					// 使用AJAX提交数据
					$("#employee-form").ajaxSubmit({
						dataType:"json",
						type:"POST",
						url:"${rc.contextPath}/saveEmployee",
						success:function(data){
							if(data.success){
								$.dialog({
									title: '提示',
									content: "提交数据成功",
								});
								window.location.href="${rc.contextPath}/personal";
							}else{
								$.dialog({
									title: '新增失败',
									content: data.msg
								});
							}
						}
					});
					// 提交数据完成
				});
				
				
			})
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
							<form id="employee-form" class="form-horizontal" method="post" action="${rc.contextPath}/saveEmployee">
								<div class="row">
									<div class="col-xs-5">
											<label for="realname" class="col-sm-4 control-label">真实姓名</label>
											<div class="col-sm-8">
												<input class="form-control" id="realname" type="text" name="realname" />
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
											<label for="idNumber" class="col-sm-4 control-label">身份证号码</label>
											<div class="col-sm-8">
												<input class="form-control" id="idNumber" type="text" name="idNumber" />
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-5">
											<label for="bankNumber" class="col-sm-4 control-label">银行卡号</label>
											<div class="col-sm-8">
												<input class="form-control" id="bankNumber" type="text" name="bankNumber" />
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-5">
											<label for="phoneNumber" class="col-sm-4 control-label">联系电话</label>
											<div class="col-sm-8">
												<input class="form-control" id="phoneNumber" type="text" name="phoneNumber" />
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-5">
											<label for="com" class="col-sm-4 control-label">分公司</label>
											<div class="col-sm-8">
												<select class="form-control" id="com" name="com">
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
											<label for="dept" class="col-sm-6 control-label">所属部门</label>
											<div class="col-sm-6">
												<select class="form-control" id="dept" name="dept">
													<option value="-1">---请选择---</option>
												</select>
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-5">
										<label for="jobId" class="col-sm-4 control-label">工号</label>
										<div class="col-sm-8">
											<input class="form-control" id="jobId" type="text" name="jobId" />
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
						    <a class="center-block btn btn-default" href="javascript:;">上传照片</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>