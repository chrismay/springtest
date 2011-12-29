<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h1>Routes</h1>
<p>${message}</p>
<ul id='routeList'>
<c:forEach var="route" items="${routes}">
<li>${route.name}</li>
</c:forEach>
</ul>
</body>
</html>