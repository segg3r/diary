<div class="ui green inverted menu">
	<div class="header item">
		<a href='<c:url value="/" />'>Main page</a>
	</div>
	<div class="item">
		<div class="ui left icon input">
			<input id="searchUsersInput" type="text"
				placeholder="Search users..."> <i class="users icon"></i>
		</div>
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

<script type="text/javascript">
	$('#searchUsersInput').on(
			'keyup',
			function(e) {
				if (e.which == 13) {
					window.location.href = "<c:url value='/user/search/' />"
							+ $(this).val();
				}
			});
</script>
