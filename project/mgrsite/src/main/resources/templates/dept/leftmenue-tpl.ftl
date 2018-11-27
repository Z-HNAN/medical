<style>

/*清楚列表默认样式*/

#menu .list-group-item{padding: 0;}

ul,li {
	list-style: none
}

/*制作动态选定效果*/
.in .active{background: #337aba;}
.in .active a{color: #FFFFFF;}


#menu .list-group-item ul{padding: 0;}
#menu .list-group-item ul li{display:block;padding: 5px 0 5px 40px;font-size:15px;}
#menu .list-group-item a:hover{text-decoration: none;}
#menu .list-group-item ul li:hover{background: #337aba;}
#menu .list-group-item ul li:hover a{color: #FFFFFF;}
/*menu列标题*/
#menu .list-group-item .el-menu-title {
	border-bottom: 1px solid #dddddd;
	height: 34px;
	margin: 0;
	padding: 0;
	background: #f9f9f8;
	display: block;
}
#menu .list-group-item .el-menu-title span{padding-left: 14px;font-size: 18px; line-height: 34px;}
#menu .list-group-item ul li span{margin-top: 20px;}	
	
</style>

<ul id="menu" class="list-group">
	<li class="list-group-item">
		<a href="javascript:;" class="el-menu-title"><span>医疗报销</span></a>
		<ul>
			<li name="billAuditListDept">
				<a href="${rc.contextPath}/billAuditListDept"><span>审批报销</span></a>
			</li>
			<li name="sendBillList">
				<a href="${rc.contextPath}/sendBillList"><span>提交报销单</span></a>
			</li>
			<li name="comDept">
				<a href="javascript:;"><span>历史报销</span></a>
			</li>
			<li name="comDept">
				<a href="javascript:;"><span>新增报销</span></a>
			</li>
		</ul>
	</li>
	<li class="list-group-item">
		<a href="javascript:;" class="el-menu-title"><span>员工管理</span></a>
		<ul class="in">
			<li name="employeeList">
				<a href="${rc.contextPath}/employeeList"><span>员工管理</span></a>
			</li>
		</ul>
	</li>
	<li class="list-group-item">
		<a href="javascript:;" class="el-menu-title"><span>医务室维护</span></a>
		<ul class="in">
			<li name="medicalRoomInfo">
				<a href="${rc.contextPath}/medicalRoomInfo"><span>医务室信息</span></a>
			</li>
		</ul>
	</li>
</ul>

<#if currentMenu??>
	<script type="text/javascript">
		$(".list-group-item li[name=${currentMenu}]").addClass("active");		
	</script>
</#if>