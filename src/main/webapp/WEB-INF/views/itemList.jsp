<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Item Page</title>
<%@ include file="cssinheader.jsp"%>
</head>
<body>
	<div id="wrapper">
		<%@ include file="navi.jsp"%>
		<div id="page-wrapper" style="min-height: 618px;">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Manage items</h1>
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
								<c:url var="addAction" value="/item/add"></c:url>
								<form:form role="form" action="${addAction}"
									modelAttribute="item">
									<c:if test="${!empty item.name}">
										<form:hidden path="id" />
									</c:if>
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
										<spring:message text="Add Item" />
									</button>
								</form:form>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Items List</div>
						<div class="panel-body">
							<div class="table-responsive">
								<c:if test="${!empty itemsList}">
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th width="40">Item ID</th>
												<th width="160">Item Name</th>
												<th width="40">Is composed</th>
												<th width="40">Actions</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${itemsList}" var="item">
												<tr>
													<td width="8%">${item.id}</td>
													<td width="50%">${item.name}</td>
													<td width="17">${item.isComposed}</td>
													<td width="25"><c:url var="editAction"
															value="/edit/${item.id}"></c:url>
														<div class="col-lg-6">
															<form action="${editAction}">
																<button class="btn btn-primary" type="submit">Edit</button>
															</form>
														</div> <c:url var="deleteAction" value="/remove/${item.id}"></c:url>
														<div class="col-lg-6">
															<form action="${deleteAction}">
																<button class="btn btn-primary" type="submit">Delete</button>
															</form>
														</div></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<%@ include file="endingscripts.jsp"%>
</body>
</html>