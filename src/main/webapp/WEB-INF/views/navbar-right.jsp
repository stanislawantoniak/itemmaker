<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul class="nav navbar-top-links navbar-right">
	<c:if test="${languageSelectorClass == null}">
		<c:set var="languageSelectorClass" value="" />
	</c:if>
	<li class="dropdown"><a
		class="dropdown-toggle  ${languageSelectorClass}" href="#"
		data-toggle="dropdown" aria-expanded="true"> <img
			src="<c:url value="/resources/${user.languageSelected.flag}"/>" /> <i
			class="fa fa-caret-down"></i>
	</a>
		<ul class="dropdown-menu dropdown-alerts">
			<c:forEach items="${languages}" var="language">
				<c:url value="/selectLanguage" var="selectLanguage" />
				<li><a href="${selectLanguage}/${language.acronym}"> <img
						src="<c:url value="/resources/${language.flag}"/>" />
						${language.name}
				</a></li>
			</c:forEach>
		</ul></li>

	<li class="dropdown"><a class="dropdown-toggle active" href="#"
		data-toggle="dropdown" aria-expanded="true"> ${user.name} <i
			class="fa fa-caret-down"></i>
	</a> <c:url value="/logout" var="logout" />
		<ul class="dropdown-menu dropdown-user">
			<li><a href="${logout}"><i class="fa fa-sign-out fa-fw"></i> ${__static__['navbar.menu.action.logout']}</a></li>
		</ul></li>
</ul>

