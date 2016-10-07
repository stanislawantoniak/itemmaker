<%-- 
requires item and its itemComponents in metadata
--%>

<div class="panel panel-default">
	<div class="panel-heading">Edit components</div>
	<div class="panel-body">
		<c:if test="${item.isComposed == true}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="40%">Component</th>
						<th width="30%">Remarks</th>
						<th width="10%">Quantity</th>
						<th width="20%">Actions</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${itemComponents}" var="itemComponent">
						<c:url var="editComponentAction" value="/items/component/edit"></c:url>
						<c:url var="deleteComponentAction" value="/items/component/delete"></c:url>
						<tr>
							<td>${itemComponent.componentName}
								(#${itemComponent.componentId})</td>
							<td>${itemComponent.remarks}</td>
							<td>${itemComponent.quantity}</td>
							<td><div class="col-lg-6">
									<form
										action="${deleteComponentAction}/${item.id }-${itemComponent.id}">
										<button class="${buttonDefaultClasses }" type="submit">Delete</button>
									</form>

								</div>
								<div class="col-lg-6">
									<form action="${editComponentAction}/${itemComponent.id}">
										<button class="${buttonDefaultClasses }" type="submit">Edit</button>
									</form>
								</div></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</c:if>
		<c:url var="eddComponentAction"
			value="/items/component/add/${item.id}"></c:url>
		<form action="${eddComponentAction}">
			<button class="${buttonOutlineClasses }" type="submit">${__static__['editcomponents.button.add'] }</button>
		</form>

	</div>
</div>