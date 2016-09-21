<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Edit component</title>
<%@ include file="cssinheader.jsp"%>
</head>
<body>
	<div id="wrapper">
		<%@ include file="navi.jsp"%>
		<div id="page-wrapper" style="min-height: 618px;">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Edit Component</h1>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Component of Item: ${item.name}
							(#${item.id})</div>
						<div class="panel-body">

							<c:url var="updateAction" value="/itemComponent/update"></c:url>
							<form:form id="component" role="form" action="${updateAction}"
								modelAttribute="itemComponent">
								<form:hidden path="id" />
								<form:hidden path="parent.id" />
								<div class="form-group">
									<form:label path="component">
										<spring:message text="Component name" />
									</form:label>
									<c:if test="${empty componentItem}">
										<form:select class="form-control" path="component">
											<option value="">--Select Item--</option>
											<c:forEach items="${allItems}" var="i">
												<c:choose>
													<c:when test="${i.key eq component.id}">
														<option value="${i.key}" selected="true">${i.value}</option>
													</c:when>
													<c:otherwise>
														<option value="${i.key}">${i.value}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>

										</form:select>
									</c:if>
									<form:errors path="component" />
								</div>
								<div class="form-group">
									<form:label path="quantity">
										<spring:message text="Quantity" />
									</form:label>
									<form:input class="form-control" path="quantity" />
									<form:errors path="quantity" />
								</div>
								<button class="btn  btn-outline btn-primary" type="submit">
									<spring:message text="Save component" />
								</button>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="endingscripts.jsp"%>
</body>
</html>