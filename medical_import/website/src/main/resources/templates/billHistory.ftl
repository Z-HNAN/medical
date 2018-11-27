<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>报销历史</title>
		<!--引入模板连接文件-->
		<#include "common/links-tpl.ftl"/>
		<!--引入分页css-->
		<link rel="stylesheet" href="css/plugins/pagination.css" />
		<style>
			#selectSubmit{margin-right: 20px;}
			
			.bill-history-qo{margin-bottom: 5px;}
			/*
			.bill-history .bill-history-qo {
				margin-bottom: 20px;
			}
			
			.bill-history .bill-history-qo input {
				margin: 0 10px;
			}
			.bill-history-qo .form-group{margin: 12px;}*/
		</style>
		<!--加入分页插件-->
		<script type="text/javascript" src="js/plugins/jquery.pagination.js"></script>
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
						 $(".bill-history-qo input[name='currentPage']").val(currentPage);
						 $(".bill-history-qo").submit();
				    }
				});
				
				// 高级查询提交功能 默认从第一页开始查看
				$("#selectSubmit").click(function(){
					$(".bill-history-qo input[name='currentPage']").val(1);
					$(".bill-history-qo").submit();
				});
				
				// 查看报销详情页
				$(".bill-detail").click(function(){
					// 清空之前数据
					$("#billModelForm")[0].reset();
					var json = $(this).data("jsonstring");
					$("#myModalLabel").html(json.billState);
					$("#idModelValue").val(json.id);
					$("#illnessTypeModelValue").html(json.illnessType);
					$("#seriousTypeModelValue").html(json.seriousType);
					$("#hospitalModelValue").html(json.hospital);
					$("#applyMoneyModelValue").html(json.applyMoney);
					$("#auditMoneyModelValue").html(json.auditMoney)
					$("#descriptionModelValue").html(json.description);
					$("#noteModelValue").html(json.note);
					// 展示模态框
					$("#billModal").modal("show");
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
						<a href="${rc.contextPath}/personal" class="btn btn-default panbill-title">个人信息</a>
						<a href="${rc.contextPath}/bill" class="btn btn-default panbill-title">我要报销</a>
						<a href="${rc.contextPath}/billHistory" class="btn btn-info panbill-title">报销历史</a>
					</div>
					<div class="panel-body">
						<div class="row bill-history-qo">
							<form class="form-inline" method="post" action="${rc.contextPath}/billHistory">
								<div class="col-sm-8">
									<input type="hidden" name="currentPage" value="${(pageResult.currentPage)!}"/>
									<div class="form-group col-xs-4">
										<label for="beginDateInput">起始日期</label>
										<input type="date" name="beginDate" class="form-control" id="beginDateInput" value="${(qo.beginDate?string('yyyy-MM-dd'))!}">
									</div>
									<div class="form-group col-xs-4">
										<label for="endDateInput">截止日期</label>
										<input type="date" name="endDate" class="form-control" id="endDateInput" value="${(qo.endDate?string('yyyy-MM-dd'))!}">
									</div>
								</div>
								<div class="col-xs-4">
									<button type="submit" class="btn btn-primary pull-right" id="selectSubmit">开始查询</button>
								</div>
							</form>
						</div>
						<div id="row">
							<table class="table table-hover">
								<tr>
									<th>申请时间</th>
									<th>报销类型</th>
									<th>请求报销金额</th>
									<th>实际报销金额</th>
									<th>报销状态</th>
									<th>操作</th>
								</tr>
								<#if (pageResult.list)?? && ((pageResult.list)?size > 0)>
								<#list pageResult.list as bill>
									<tr>
										<td>${bill.applyDate?string('yyyy-MM-dd')}</td>
										<td>${bill.illnessTypeDisplay}</td>
										<td>${bill.applyMoney?string('0.##')}</td>
										<td>${(bill.auditMoney?string('0.##'))!}</td>
										<td>${bill.billStateDisplay}</td>
										<td><a class="bill-detail" href="javascript:;" data-jsonstring='${bill.jsonString}'>查看</a></td>
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
		
		<!--查看审核详情模态框-->
		<div class="modal fade" id="billModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel"></h4>
		      </div>
		      <div class="modal-body">
		        <form id="billModelForm" class="form-horizontal" action="">
		          <input id="idModelValue" type="hidden" name="id" value="" />
		          <input id="stateModelValue" type="hidden" name="state" value="" />
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
				  	<div class="col-xs-12">
				  		 <div class="form-group">
						    <label for="hospitalModelValue" class="col-sm-2 control-label">就诊医院</label>
						    <div class="col-sm-10">
						    	<p class="form-control-static" id="hospitalModelValue" name="hospital"></p>
						    </div>
					  	</div>
				  	</div>
				  </div>
				  <div class="row">
				  	<div class="col-xs-6">
				  		 <div class="form-group">
						    <label for="applyMoneyModelValue" class="col-sm-4 control-label">报销金额</label>
						    <div class="col-sm-8">
						      <p class="form-control-static" id="applyMoneyModelValue" name="applyMoney"></p>
						    </div>
					  	</div>
				  	</div>
				  	<div class="col-xs-6">
				  		 <div class="form-group">
						    <label for="auditMoneyModelValue" class="col-sm-4 control-label">审批金额</label>
						    <div class="col-sm-8">
						    	 <p class="form-control-static" id="auditMoneyModelValue" name="auditMoney"></p>
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
					    	 		<p class="form-control-static" id="noteModelValue" name="note"></p>
						    	</div> 		
						    </div>
					  	</div>
				  	</div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary">确定</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		
	</body>

</html>