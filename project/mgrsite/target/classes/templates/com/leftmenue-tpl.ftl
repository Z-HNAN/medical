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
			<li name="comDept">
				<a href="${rc.contextPath}/billAuditListCom"><span>审批报销</span></a>
			</li>
			<li name="comDept">
				<a href="javascript:;"><span>历史报销</span></a>
			</li>
			<li name="comDept">
				<a href="javascript:;"><span>审核放款</span></a>
			</li>
		</ul>
	</li>
	<li class="list-group-item">
		<a href="javascript:;" class="el-menu-title"><span>医务室管理</span></a>
		<ul class="in">
			<li name="medicalList">
				<a href="${rc.contextPath}/medicalList"><span>医务室管理</span></a>
			</li>
		</ul>
	</li>
	<li class="list-group-item">
		<a href="javascript:;" class="el-menu-title"><span>财务状况查看</span></a>
		<ul class="in">
			<li name="accountFlow_list">
				<a href="#"><span>账户流水</span></a>
			</li>
		</ul>
	</li>
</ul>

<#if currentMenu??>
	<script type="text/javascript">
		$(".list-group-item li[name=${currentMenu}]").addClass("active");		
	</script>
</#if>