<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html><body>
<H1>Add Route</H1>
<form:form commandName="route" >
<form:errors path="name"/>
Name:<form:input type="text" path="name"/>
<p/>
<input type="submit"/>
</form:form >
</body></html>