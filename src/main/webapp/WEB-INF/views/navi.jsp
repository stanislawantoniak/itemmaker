<nav class="navbar navbar-default navbar-static-top"
	style="margin-bottom: 0" role="navigation">
	<div class="navbar-header">
		<button class="navbar-toggle" data-target=".navbar-collapse"
			data-toggle="collapse" type="button">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<c:url value="/" var="main" />
		<a class="navbar-brand" href="${main}">${__static__['app.name']}</a>
	</div>
	
	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown"><a class="dropdown-toggle" href="#"
			data-toggle="dropdown" aria-expanded="true"> <img src="<c:url value="/resources/${user.languageSelected.flag}"/>"/> <i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<c:forEach items="${languages}" var="language">
				<c:url value="/selectLanguage" var="selectLanguage" />
					<li><a href="${selectLanguage}/${language.acronym}"> <img src="<c:url value="/resources/${language.flag}"/>"/>
							${language.name}
					</a></li>
				</c:forEach>
			</ul></li>
	</ul>
							
	<%--<%@ include file="navbar-right.jsp"%>--%>
	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul id="side-menu" class="nav in">
				<li class="sidebar-search">
					<div class="input-group custom-search-form">
						<input class="form-control" type="text" placeholder="${__static__['search.widget.text']}">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</li>
				<c:url value="/items" var="items" />
				<li><a class="active" href="${items}"> <i
						class="fa fa-dashboard fa-fw"></i> ${__static__['leftpanel.menu.manageitems']}
				</a></li>
			</ul>
		</div>
	</div>
</nav>

