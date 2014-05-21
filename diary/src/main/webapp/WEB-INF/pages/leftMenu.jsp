<div class="ui vertical menu" style="width: 100%;">
	<div class="header item">
		<i class="browser icon"></i> Themes
	</div>
	<c:forEach items="${themes }" var="theme">
		<c:choose>
			<c:when test="${theme eq activeTheme }">
				<a class="active red item"
					onclick='window.location.href="<c:url value="/theme/${theme.id}" />"'><c:out
						value="${theme.name }" /></a>
			</c:when>
			<c:otherwise>
				<a class="item"
					onclick='window.location.href="<c:url value="/theme/${theme.id}" />"'><c:out
						value="${theme.name }" /></a>
			</c:otherwise>
		</c:choose>

	</c:forEach>
	<div class="item" style="position: relative;">
		<f:form modelAttribute="newTheme" method="POST"
			servletRelativeAction="/theme/add" cssClass="ui error form">
			<f:errors path="*" element="div" cssClass="ui error message" />
			<f:hidden path="user" value="${newTheme.user.id }" />
			<div class="fields">
				<div class="twelve wide field" style="margin-bottom: 0px;">
					<f:input path="name" cssClass="ui input" />
				</div>
				<div class="four wide field" style="margin-bottom: 0px;">
					<input type="submit" class="ui button" value="Add"
						style="width: 100%; padding-left: 0px; padding-right: 0px;" />
				</div>
			</div>
		</f:form>
	</div>
	<div class="header item">
		<i class="comment outline icon"></i> Subscriptions
	</div>
	<c:forEach items="${subscriptions }" var="subscription">
		<c:choose>
			<c:when test="${subscription.theme eq activeTheme }">
				<a class="active red item"
					onclick='window.location.href="<c:url value="/theme/${subscription.theme.id}" />"'><c:out
						value="${subscription.theme.name }" /> </a>
			</c:when>
			<c:otherwise>
				<a class="item"
					onclick='window.location.href="<c:url value="/theme/${subscription.theme.id}" />"'><c:out
						value="${subscription.theme.name }" /> <c:if
						test="${subscription.unread > 0 }">
						<div class="ui label">${subscription.unread }</div>
					</c:if> </a>
			</c:otherwise>
		</c:choose>

	</c:forEach>
	<div class="item">
		<div class="ui left icon input">
			<input id="searchThemesInput" type="text"
				placeholder="Search themes..."> <i class="list layout icon"></i>
		</div>
	</div>
</div>
<c:if test="${not empty themes }">
	<div class="ui vertical menu" style="width: 100%;">
		<div class="header item">
			<i class="edit icon"></i>Publish Note
		</div>
		<div class="item">

			<f:form modelAttribute="note" method="POST" id="noteForm"
				servletRelativeAction="/note/add" cssClass="ui attached error form">
				<f:errors path="*" element="div" cssClass="ui error message"></f:errors>
				<f:hidden path="user" value="${note.user.id }" />
				<div class="field">
					<label>Title</label>
					<div class="ui input">
						<f:input path="name" />
					</div>

				</div>
				<div class="field">
					<label>Text</label>
					<f:textarea path="description" />
				</div>
				<div class="ui two fields">
					<div class="field">
						<label>Theme</label>
						<f:select path="theme" cssClass="ui selection dropdown">
							<f:options items="${themes}" itemValue="id" itemLabel="name" />
						</f:select>
					</div>
					<div class="field">
						<label>Priority</label>
						<f:select path="priority" cssClass="ui selection dropdown">
							<f:options items="${priorities}" itemValue="id" itemLabel="name" />
						</f:select>
					</div>
				</div>

				<div class="field">
					<div class="ui right labeled icon button"
						onclick="document.getElementById('noteForm').submit();">
						<i class="edit icon"></i> Publish
					</div>
				</div>

			</f:form>

		</div>
	</div>
</c:if>

<script type="text/javascript">
	$('#searchThemesInput').on(
			'keyup',
			function(e) {
				if (e.which == 13) {
					window.location.href = "<c:url value='/theme/search/' />"
							+ $(this).val();
				}
			});
</script>