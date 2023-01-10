<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>${languageChange.get(0)}</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleUser/secondPage/FormUserExhibition.css"%> </style>
    </head>
    <body>
        <form action="exhibition?command=userexhibition"  id="UserExhibitionForm" class="UserExhibitionForm" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <div class="nameExit">
                <h3 class="h3Add" id="exHello">${languageChange.get(1)}</p></h3>
                <button class="exitButton" name="exitButton" id="exitButton">${languageChange.get(2)}</button>
            </div>
            <div class="buttonUser">
                <button class="updateButton" name="updateButton" id="updateButton" onclick="updateFunc()" type="Button">${languageChange.get(3)}</button>
            </div>

            <div id="updateDiv" style="display: none;">
                <div class="wrapper">
                    <h6 class="h6Add" id="updateTxt">${languageChange.get(4)}</h6>
                    <select class="UpdateSort" name="UpdateSort" id="UpdateSort">
                        <option value="3">3</option>
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="all">${languageChange.get(5)}</option>
                    </select>
                    <div class="buttonAdd">
                        <button class="updateButtonServlet" id="updateButton" name="updateButton">${languageChange.get(6)}</button>
                    </div>
                </div>
            </div>

            <table id="myTable">
                <tr>
                    <th id="nameExhibition">${languageChange.get(7)}</th>
                    <th id="description">${languageChange.get(8)}</th>
                    <th id="art">${languageChange.get(9)}</th>
                    <th id="price">${languageChange.get(10)}</th>
                    <th id="dateStart">${languageChange.get(11)}</th>
                    <th id="dateEnd">${languageChange.get(12)}</th>
                    <th id="hours">${languageChange.get(13)}</th>
                    <th id="hall">${languageChange.get(14)}</th>
                    <th id="author">${languageChange.get(15)}</th>
                    <th id="genre">${languageChange.get(16)}</th>
                    <th id="address">${languageChange.get(17)}</th>
                </tr>
                <c:if test="${not requestScope.Error}">
                    <c:forEach var="user" items="${SecondPage}">
                        <tr>
                            <td>${user.getNameExhibition()}</td>
                            <td>${user.getDescriptionExibition()}</td>
                            <td>
                                <c:forEach var="exposition" items="${user.getExpositionName()}">
                                    ${exposition}<br>
                                </c:forEach>
                            </td>
                            <td>${user.getPrice()}</td>
                            <td>${user.getDateStart()}</td>
                            <td>${user.getDateEnd()}</td>
                            <td>${user.getHours()}</td>
                            <td>
                                <c:forEach var="hell" items="${user.getNameHall()}">
                                    ${hell}<br>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="nameAuthor" items="${user.getNameAuthor()}">
                                    ${nameAuthor}<br>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="nameView" items="${user.getNameview()}">
                                    ${nameView}<br>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="address" items="${user.getAddressExibition()}">
                                    ${address}<br>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${requestScope.Error}">
                    <script>
                        sweetAlert("${languageChange.get(18)}", "${languageChange.get(19)}", "error");
                    </script>
                </c:if>
            </table>
            <div class="pagination">
                <a><input class="buttonPagination" type="submit" name="UserPagination" value="«"></a>
                <a><input class="buttonPagination" type="submit" name="UserPagination" value="1"></a>
                <a class="active"><input class="buttonPagination" type="submit" name="UserExhibitionPagination" value="2"></a>
                <a><input class="buttonPagination" type="submit" name="UserExhibitionPagination" value="»"></a>
            </div>
        </form>
        <script src="./JS/userJS/secondPage/UserExhibition1.js" type="text/javascript"></script>
    </body>
</html>

