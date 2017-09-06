<html>
    <body>
        <b>MVC MOCK VIEW</b>
        <br/>requestAttribute.str = ${requestAttribute.str}
        <br/>requestAttribute.map = ${requestAttribute.map['key-0']}
        <br/>requestAttribute.demoEntityB.str = ${requestAttribute.demoEntityB.str}
        <br/>sessionAttribute.array[1] = ${sessionAttribute.array[1]}
        <br/>servletContextAttribute.list[0] = ${servletContextAttribute.list[0]}
        <hr/>
        <jsp:useBean id="pageBean" scope="page" class="com.vetallWebapp.entity.DemoEntityB"></jsp:useBean>
        <br/>pageBean.str = ${pageBean.str}
        <hr/>
        <br/>(pageBean.intValue0 gt -10) and (pageBean.intValue1 lt 10) =
            ${(pageBean.intValue0 gt -10) and (pageBean.intValue1 lt 10)}
        <hr/>
        <br/>test = ${test}
    </body>
</html>