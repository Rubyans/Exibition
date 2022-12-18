<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Виставки</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleUser/secondPage/FormUserExhibition.css"%> </style>
    </head>
    <body>
        <form action="userexhibition"  id="UserExhibitionForm" class="UserExhibitionForm" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <div class="nameExit">
                <h3 class="h3Add" id="exHello">Список виставок</p></h3>
                <button class="exitButton" name="exitButton" id="exitButton">Вийти</button>
            </div>
            <div class="buttonUser">
                <button class="updateButton" name="updateButton" id="updateButton">Оновити</button>
            </div>
            <table id="myTable">
                <tr>
                    <th id="nameExhibition">Назва виставки</th>
                    <th id="description">Опис</th>
                    <th id="art">Експозиції</th>
                    <th id="price">Ціна</th>
                    <th id="dateStart">Дата початку</th>
                    <th id="dateEnd">Дата кінця</th>
                    <th id="hall">Зали</th>
                    <th id="author">Автори експозицій</th>
                    <th id="genre">Жанри</th>
                    <th id="address">Адреси</th>
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
                        sweetAlert("Error", "Database problems, try again later!", "error");
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
        <script src="./JS/userJS/secondPage/UserExhibition.js" type="text/javascript"></script>
        <c:if test="${requestScope.languageEnglish}">
            <script>
                changeLanguage('en');
            </script>
        </c:if>
        <c:if test="${requestScope.languageUkraine}">
            <script>
                changeLanguage('ua');
            </script>
        </c:if>
    </body>
</html>

