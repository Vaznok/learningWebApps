<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <body>
        <b>PRODUCT PAGE</b>
        <br/>id: ${product.id}
        <br/>name: ${product.name}
        <h4><a href="./productAddToBucket.do?id=${product.id}">ADD TO BUCKET</a></h4>

        <h3>PRODUCTS IN BUCKET</h3>
        <ul>
            <c:forEach var="productInBucket" items="${productsInBucket}">
                <li>
                    <a href="./product.do?id=${productInBucket.key.id}">${productInBucket.key.name} = ${productInBucket.value}</a>
                    <a href="./productRemoveFromBucket.do?id=${productInBucket.key.id}">(X)</a>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>