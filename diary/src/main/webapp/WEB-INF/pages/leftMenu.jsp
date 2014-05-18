<div class="ui vertical menu" style="width: 100%;">
	<div class="header item">
		<i class="browser icon"></i> Themes
	</div>
	<c:forEach items="${themes }" var="theme">
		<a class="item"
			onclick='window.location.href="<c:url value="/theme/${theme.id}" />"'><c:out
				value="${theme.name }" /></a>
	</c:forEach>
	<div class="item" style="position: relative;">
		<f:form modelAttribute="newTheme" method="POST"
			servletRelativeAction="/theme/add" cssClass="ui form">
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
</div>

<div class="ui vertical menu" style="width: 100%;">
	<div class="header item">
		<i class="align justify icon"></i>Publish Note
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
					<i class="align justify icon"></i> Publish
				</div>
			</div>

		</f:form>
	</div>
</div>