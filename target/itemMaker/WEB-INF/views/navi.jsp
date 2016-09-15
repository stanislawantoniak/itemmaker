<nav class="navbar navbar-default navbar-static-top"
	style="margin-bottom: 0" role="navigation">
	<div class="navbar-header">
		<button class="navbar-toggle" data-target=".navbar-collapse"
			data-toggle="collapse" type="button">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<c:url value="/" var="main"/>
		<a class="navbar-brand" href="${main}">Item Maker 1.0</a>
	</div>
	<%--<%@ include file="navbar-right.jsp"%>--%>
	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul id="side-menu" class="nav in">
				<li class="sidebar-search">
					<div class="input-group custom-search-form">
						<input class="form-control" type="text" placeholder="Search...">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</li>
				<c:url value="/items" var="items"/>
				<li><a class="active" href="${items}"> <i
						class="fa fa-dashboard fa-fw"></i> Manage Items
				</a></li>
			</ul>
		</div>
	</div>
</nav>

