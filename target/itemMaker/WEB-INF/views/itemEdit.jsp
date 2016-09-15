<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
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
					<h1 class="page-header">Edit Item</h1>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<c:if test="${!empty item.name}">Edit Item #<spring:message
									text="${item.id}" />
							</c:if>
							<c:if test="${empty item.name}">Add Item
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
											<spring:message text="Name" />
										</form:label>
										<form:input class="form-control" path="name" />
									</div>
									<%--
										<div class="form-group">
											<form:label path="isComposed">
												<spring:message text="isComposed" />
											</form:label>
											<form:input class="form-control" path="isComposed" />
										</div>
										 --%>
									<div class="form-group">
										<form:label path="isComposed">
											<spring:message text="Is composed? " />
										</form:label>
										<c:if test="${item.isComposed}">
											<label class="radio-inline"> <input type="radio"
												value="yes" name="isComposed" path="isComposed" checked="">
												Yes
											</label>
											<label class="radio-inline"> <input type="radio"
												value="no" name="isComposed" path="isComposed"> No
											</label>
										</c:if>
										<c:if test="${!item.isComposed}">
											<label class="radio-inline"> <input type="radio"
												value="yes" name="isComposed" path="isComposed"> Yes
											</label>
											<label class="radio-inline"> <input type="radio"
												value="no" name="isComposed" path="isComposed" checked="">
												No
											</label>
										</c:if>
									</div>
									<button class="btn  btn-outline btn-primary" type="submit">
										<spring:message text="Save Item" />
									</button>
									<c:url var="goToItems" value="/items"></c:url>
									<a href="${goToItems}" class="btn btn-default btn-sm"> <spring:message
											text="Cancel" />
									</a>
								</form:form>
							</div>
						</div>
					</div>
				</div>

				<c:if test="${item.isComposed}">
					<div class="col-lg-6">
						<div class="panel panel-default">
							<div class="panel-heading">Edit components</div>
							<div class="panel-body">

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
								<c:url var="eddComponentAction"
									value="/item/addComponent/${item.id}"></c:url>
								<form action="${eddComponentAction}">
									<button class="btn btn-outline btn-primary btn-lg"
										type="submit">Add component</button>
								</form>

							</div>
						</div>

					</div>
				</c:if>
			</div>
		</div>

		<%@ include file="endingscripts.jsp"%>
</body>
</html>