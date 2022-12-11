<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Виставки</title>
        <meta charset="UTF-8"/>
        <link href="./style/styleUser/secondPage/FormUserExhibition1.css" rel="stylesheet"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
    </head>
<body>

    <form action="userexhibition"  id="UserExhibitionForm" class="UserExhibitionForm" method="POST">
        <div class="nameExit">
            <h3 class="h3Add">Список виставок</p></h3>
            <button class="exitButton" name="exitButton">Вийти</button>
        </div>
        <div class="buttonUser">
            <button class="updateButton" name="updateButton" >Оновити</button>
        </div>

    <table id="myTable">
            <tr>
                <th>Назва виставки</th>
                <th>Опис</th>
                <th>Експозиції</th>
                <th>Ціна</th>
                <th>Дата початку</th>
                <th>Дата кінця</th>
                <th>Зали</th>
                <th>Автори експозицій</th>
                <th>Жанри</th>
                <th>Адреси</th>
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
                        sweetAlert("Помилка", "Проблеми з базою даних, спробуйте пізніше!", "error");
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
</body>
</html>

