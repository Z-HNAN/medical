<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>我要报销</title>
		<!--导入confirm的css-->
		<link rel="stylesheet" href="css/plugins/jquery-confirm.min.css" />	
		<!--引入模板连接文件-->
		<#include "common/links-tpl.ftl"/>
		<!-- 导入jqueryFrom 使用AJAX提交数据 -->
		<script src="js/plugins/jquery-validation-1.14.0/lib/jquery.form.js"></script>
		<!--导入confirem插件的js-->
		<script type="text/javascript" src="js/plugins/jquery-confirm.min.js"></script>
		<style>
			#bill-form>.row {
				margin-bottom: 10px;
				width: 85%;
			}
			
			#biiImgBox{
				width: 100%;
				height: 150px;
			}
			#biiImgBox+button{
				width: 100px;
				margin-top: 10px;	
			}
		</style>
		<script type="text/javascript">
			$(function(){
				
				// 提交表单
				$(".create-bill").click(function(){
					$("#bill-form").ajaxSubmit({
						dataType:"json",
						type:"POST",
						url:"${rc.contextPath}/createBill",
						success:function(data){
							if(data.success){
								$.dialog({
									title: '提示',
									content: "新增成功"
								});
								window.location.href="${rc.contextPath}/billHistory";
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
			<#include "common/head-tpl.ftl"/>
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="${rc.contextPath}/personal" class="btn btn-default panel-title">个人信息</a>
						<a href="${rc.contextPath}/bill" class="btn btn-info panel-title">我要报销</a>
						<a href="${rc.contextPath}/billHistory" class="btn btn-default panel-title">报销历史</a>
					</div>
					<div class="panel-body">
						<div class="row">
							<form id="bill-form" class="form-horizontal" method="post" action="${rc.contextPath}/createBill">
								<div class="row">
									<div class="col-xs-6">
											<label for="illnessType" class="col-sm-4 control-label">报销类型</label>
											<div class="col-sm-8">
												<select id="illnessType" name="illnessType" class="form-control">
													<option value="-1">--请选择--</option>
													<option value="0">门诊</option>
													<option value="1">普通疾病</option>
													<option value="2">重大疾病</option>
												</select>
											</div>
									</div>
									<div class="col-xs-6">
											<label for="seriousType" class="col-sm-4 control-label">大病类型</label>
											<div class="col-sm-8">
												<select id="seriousType" name="seriousType.id" class="form-control">
													<option>--请选择--</option>
														<#if (seriousList)?? && ((seriousList)?size > 0)>
															<#list seriousList as serious>
																<option value="${serious.id}">${serious.title}</option>
															</#list>
														</#if>
												</select>
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6">
											<label for="hospital" class="col-sm-4 control-label">就诊医院</label>
											<div class="col-sm-8">
												<input name="hospital" id="hospital" class="form-control" type="text"/>
											</div>
									</div>
									<div class="col-xs-6">
											<label for="applyMoney" class="col-sm-4 control-label">报销金额</label>
											<div class="col-sm-8 input-group">
												<input name="applyMoney" id="applyMoney" class="form-control" type="text"/>
												<div class="input-group-addon">元</div>
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6">
											<label for="inHospitalDate" class="col-sm-4 control-label">住院时间</label>
											<div class="col-sm-8">
												<input name="inHospitalDate" id="inHospitalDate" class="form-control" type="date"/>
											</div>
									</div>
									<div class="col-xs-6">
											<label for="outHospitalDate" class="col-sm-4 control-label">出院时间</label>
											<div class="col-sm-8">
												<input name="outHospitalDate" id="outHospitalDate" class="form-control" type="date"/>
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6">
											<div class="col-sm-12">
												<div class="biiImgBox img-thumbnail" id="biiImgBox">
													<img src="" title="发票图片"/>													
												</div>
												<button class="btn btn-default center-block" id="billImg" name="billImg">上传图片</button>
											</div>
									</div>
									<div class="col-xs-6">
											<label for="description" class="col-sm-4 control-label">简要说明</label>
											<div class="col-sm-8">
												<textarea class="form-control" id="description" name="description"  rows="6"></textarea>
											</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-offset-6">
										<button type="button" class="btn btn-success create-bill">我要报销</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>