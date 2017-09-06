<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <body>
        <b>ALL PRODUCTS</b>
        <ul>
            <c:forEach var="product" items="${productList}">
                <li>
                    <a href="./product.do?id=${product.id}">${product.name}</a>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>