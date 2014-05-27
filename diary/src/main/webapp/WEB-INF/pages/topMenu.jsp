<div class="ui green inverted menu">
	<div class="header item">
		<a href='<c:url value="/" />'>Main page</a>
	</div>
	<div class="right menu">
		<div class="item">
			Hi, <b><a href='<c:url value="/user/${applicationUser.id }"/>'>${applicationUser.fullName }</a></b>
		</div>
		<div class="item">
			<a href='<c:url value="/user/logout" />'>Logout</a>
		</div>
	</div>
</div>
