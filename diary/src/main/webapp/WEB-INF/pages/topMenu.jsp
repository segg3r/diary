<div class="ui fixed green inverted menu">
	<div class="right menu">
		<div class="item">
			Hi, <b><c:out value="${applicationUser.firstName }"></c:out> <c:out
					value="${applicationUser.lastName }"></c:out></b>
		</div>
		<div class="item">
			<a href='<c:url value="/user/logout" />'>Logout</a>
		</div>
	</div>
</div>
