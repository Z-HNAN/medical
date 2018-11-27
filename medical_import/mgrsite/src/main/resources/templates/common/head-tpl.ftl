<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
				<a class="navbar-brand" href="#">XXX公司</a>
				<p class="navbar-text">企业医疗报销管理平台</p>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="javascript:;">首页</a>
					</li>
					<#if !logininfo??>
						<!--用户未登录-->
						<li>
							<a href="javascript:;">登录</a>
						</li>
					<#else>
						<!--用户登录-->
						<li>
							<a href="javascript:;">${logininfo.username}</a>
						</li>
					</#if>
					<li>
						<a href="javascript:;">帮助</a>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
</div>