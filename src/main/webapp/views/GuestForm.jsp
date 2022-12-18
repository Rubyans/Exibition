<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Гость</title>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleGuest/FormGuest.css"%> </style>
    </head>
    <body>
        <form action="guest"  id="guestForm" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <h3 class="text"><p id="guestHello">Вітаємо шановного гостя</p><h3>
            <div class="buttonAuto">
                <button class="updateButton" name="updateButton" id="updateButton">Оновити</button>
                <button type="button" class="sortButton" name="sortButton" id="sortButton" onclick="sortTable()">Сортувати</button>
                <button name="autoButton" class="autoButton" id="autoButton">Авторизація</button>
            </div>
            <table id="myTable">
              <tr>
                <th id="nameExhibition">Назва виставки</th>
                <th id="description">Опис</th>
                <th id="art">Експозиції</th>
                <th id="price">Ціна</th>
                <th id="dataStart">Дата початку</th>
                <th id="dataEnd">Дата кінця</th>
              </tr>
                <c:if test="${not requestScope.Error}">
                        <c:forEach var="guest" items="${GuestList}">
                            <tr>
                                <td>${guest.getNameExhibition()}</td>
                                <td>${guest.getDescriptionExibition()}</td>
                                <td>
                                    <c:forEach var="exposition" items="${guest.getExpositionName()}">
                                     ${exposition}<br>
                                     </c:forEach>
                                 </td>
                            <td>${guest.getPrice()}</td>
                            <td>${guest.getDateStart()}</td>
                            <td>${guest.getDateEnd()}</td>
                            </tr>
                         </c:forEach>
                </c:if>
                <c:if test="${requestScope.Error}">
                    <script>
                        sweetAlert("Помилка", "Проблеми з базою даних, спробуйте пізніше!", "error");
                    </script>
                </c:if>
            </table>
        </form>
        <script src="./JS/functionsGuest.js" type="text/javascript"></script>
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