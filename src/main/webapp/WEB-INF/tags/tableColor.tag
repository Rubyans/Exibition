<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="name" required="true" rtexprvalue="true" type="java.lang.String"%>
<c:choose>
    <c:when test="${name.equals('2')}">
       <td style="background: #EB1D36;"></td>
    </c:when>
    <c:when test="${name.equals('1')}">
       <td style="background: #38E54D;"></td>
    </c:when>
</c:choose>
