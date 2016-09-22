<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>${__static__['itemslist.title']}</title>
<%@ include file="cssinheader.jsp"%>
</head>
<body>
	<div id="wrapper">
		<%@ include file="navi.jsp"%>
		<div id="page-wrapper" style="min-height: 618px;">

			<div class="row">
				<div class="col-lg-6">
					<h1 class="page-header">${__static__['itemslist.heading']}</h1>

					<c:url var="addAction" value="/item/edit/0"></c:url>
					<form action="${addAction}">
						<button class="btn  btn-outline btn-primary" type="submit">
							${__static__['itemslist.heading.button.additem']}</button>
					</form>

				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="table-responsive">
							<c:if test="${!empty itemsList}">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th width="40">${__static__['itemslist.itemstable.heading.id']}</th>
											<th width="160">${__static__['itemslist.itemstable.heading.name']}</th>
											<th width="40">${__static__['itemslist.itemstable.heading.iscomposed']}</th>
											<th width="40">${__static__['itemslist.itemstable.heading.actions']}</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${itemsList}" var="item">
											<tr>
												<td width="8%">${item.id}</td>
												<td width="50%">${item.name}</td>
												<td width="17">${item.isComposed}</td>
												<td width="25"><c:url var="editAction"
														value="/item/edit/${item.id}"></c:url>
													<div class="col-lg-6">
														<form action="${editAction}">
															<button class="btn btn-primary" type="submit">${__static__['itemslist.itemstable.editbutton']}</button>
														</form>
													</div> <c:url var="deleteAction" value="/remove/${item.id}"></c:url>
													<div class="col-lg-6">
														<form action="${deleteAction}">
															<button class="btn btn-primary" type="submit">${__static__['itemslist.itemstable.deletebutton']}</button>
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
		<%@ include file="endingscripts.jsp"%>
</body>
</html>