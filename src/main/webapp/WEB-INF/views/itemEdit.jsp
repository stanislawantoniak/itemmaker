<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Edit item</title>
<%@ include file="cssinheader.jsp"%>
</head>
<body>
	<div id="wrapper">
		<%@ include file="navi.jsp"%>
		<div id="page-wrapper" style="min-height: 618px;">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						<c:if test="${item.id != 0}">
						${__static__['edititem.heading.edititem'] }
						</c:if>
						<c:if test="${item.id == 0}">
						${__static__['edititem.heading.additem'] }
						</c:if>
					</h1>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<c:if test="${item.id != 0}">
						${__static__['edititem.heading.edititem'] }
						</c:if>
							<c:if test="${item.id == 0}">
						${__static__['edititem.heading.additem'] }
						</c:if>
						</div>
						<div class="panel-body">
							<div class="col-lg-12">
								<c:url var="updateAction" value="/item/update"></c:url>
								<form:form role="form" action="${updateAction}"
									modelAttribute="item">
									<form:hidden path="id" />
									<div class="form-group">
										<form:label path="name">
											${__static__['edititem.label.itemname'] }
										</form:label>            
										<form:input class="form-control" path="name" />
										<form:errors path="name" />
									</div>
									<button class="btn  btn-outline btn-primary" type="submit">
										${__static__['edititem.button.save'] }</button>
									<c:url var="goToItems" value="/items"></c:url>
									<a href="${goToItems}" class="btn btn-default btn-sm">
										${__static__['edititem.button.cancel'] } </a>
								</form:form>
							</div>
						</div>
					</div>
				</div>

				<c:if test="${item.id != 0}">
					<div class="col-lg-6">
						<div class="panel panel-default">
							<div class="panel-heading">Edit components</div>
							<div class="panel-body">
								<c:if test="${item.isComposed == true}">
									<table class="table table-hover">
										<thead>
											<tr>
												<th width="50%">Component</th>
												<th width="20%">Quantity</th>
												<th width="39%">Actions</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${itemComponents}" var="itemComponent">
												<c:url var="editComponentAction" value="/item/editComponent"></c:url>
												<c:url var="deleteComponentAction"
													value="/item/deleteComponent"></c:url>
												<tr>
													<td>${itemComponent.componentName}
														(#${itemComponent.componentId})</td>
													<td>${itemComponent.quantity}</td>
													<td><div class="col-lg-6">
															<form
																action="${deleteComponentAction}/${itemComponent.id}">
																<button class="btn btn-primary" type="submit">Delete</button>
															</form>

														</div>
														<div class="col-lg-6">
															<form action="${editComponentAction}/${itemComponent.id}">
																<button class="btn btn-primary" type="submit">Edit</button>
															</form>
														</div></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>
								<c:url var="eddComponentAction"
									value="/item/addComponent/${item.id}"></c:url>
								<form action="${eddComponentAction}">
									<button class="btn btn-outline btn-primary btn-lg"
										type="submit">${__static__['editcomponents.button.add'] }</button>
								</form>

							</div>
						</div>

					</div>
				</c:if>
			</div>
		</div>
	</div>

	<%@ include file="endingscripts.jsp"%>
</body>
</html>