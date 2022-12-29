<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="language" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="role" required="true" rtexprvalue="true" type="java.lang.String"%>
<c:choose>
    <c:when test="${language.equals('Ukraine')}">
       <c:if test="${role.equals('2')}">
            <td>Адміністратор</td>
       </c:if>
       <c:if test="${role.equals('1')}">
             <td>Користувач</td>
       </c:if>
    </c:when>
    <c:when test="${language.equals('English')}">
       <c:if test="${role.equals('2')}">
            <td>Administrator</td>
        </c:if>
        <c:if test="${role.equals('1')}">
            <td>User</td>
        </c:if>
    </c:when>
</c:choose>
