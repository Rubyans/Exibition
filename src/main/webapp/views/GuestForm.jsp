<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Виставки</title>
        <link href="./Style/styleGuest/guestForm.css" rel="stylesheet"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
    </head>
</head>
<body>
<form action="guest"  id="guestForm" method="POST">
<h3 class="text">Вітаємо шановного гостя</p><h3>
<div class="buttonAuto">
    <button class="updateButton" name="updateButton">Оновити</button>
    <button type="button" class="sortButton" name="sortButton" onclick="sortTable()">Сортувати</button>
    <button name="autoButton" class="autoButton" >Авторизація</button>
</div>
<table id="myTable">
  <tr>
    <th>Назва виставки</th>
    <th>Опис</th>
    <th>Експозиції</th>
    <th>Ціна</th>
    <th>Дата початку</th>
    <th>Дата кінця</th>
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

  <script src="./JS/checkGuest.js" type="text/javascript"></script>

</body>
</html>