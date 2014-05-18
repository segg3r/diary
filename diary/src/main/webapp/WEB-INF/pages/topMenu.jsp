<div class="ui fixed green inverted menu">
	<div class="item">
		<div class="ui left icon input">
			<input type="text" placeholder="Search themes..."> <i
				class="list layout icon"></i>
		</div>
	</div>
	<div class="right menu">
		<div class="item">
			Hi, <b><c:out value="${applicationUser.firstName }"></c:out> <c:out
					value="${applicationUser.lastName }"></c:out></b>
		</div>
	</div>
</div>

<script type="text/javascript">
	$('input[type=text]').on('keyup', function(e) {
		if (e.which == 13) {
			window.location.href="<c:url value='/theme/search/' />" + this.value;
		}
	});
</script>