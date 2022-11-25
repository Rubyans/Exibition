<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Адміністратор</title>
        <meta charset="UTF-8"/>
        <link href="./style/styleAdmin/thirdPage/FormAddress.css" rel="stylesheet"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
    </head>
<body>

    <c:if test="${requestScope.TrueAdd}">
        <script>
            sweetAlert("Додавання", "Ви успішно додали дані. Збережіть зміни!", "success");
        </script>
    </c:if>

    <c:if test="${requestScope.TrueDel}">
        <script>
            sweetAlert("Видалення", "Ви успішно видалили дані. Збережіть зміни!", "success");
        </script>
    </c:if>

    <c:if test="${requestScope.AddError}">
        <script>
            sweetAlert("Помилка", "Дана адреса вже існує, введіть іншу назву!", "error");
        </script>
    </c:if>

    <c:if test="${requestScope.DelError}">
        <script>
            sweetAlert("Помилка", "Даних за даним унікальним номером не існує!", "error");
        </script>
    </c:if>

    <form action="adminaddress"  id="AddressForm" class="AddressForm" method="POST">
        <h3 class="text">Список адрес</p><h3>
        <div class="buttonAddress">
            <button class="updateButton" name="updateButton" >Оновити</button>
            <button class="addButton" id="one" name="addButton"   onclick="addFunc()" type="Button">Додавання</button>
            <button class="deleteButton" name="deleteButton" onclick="delFunc()" type="Button" >Видалення</button>
            <button class="saveButton" name="roleBackButton">Відхилити</button>
            <button class="saveButton" name="saveButton">Зберегти</button>
        </div>

        <div id="addDiv" style="display: none;">
            <div class="wrapper">
                <div class="left_block">
                    <input type="text" id="InputCountryAd" placeholder="Введіть назву країни" name="countryAddress" >
                    <br><input type="text" id="InputCityAd" placeholder="Введіть назву міста" name="cityAddress" >
                    <br><input type="text" id="InputStreetAd" placeholder="Введіть назву вулиці/площі" name="streetAddress" >
                    <br><input type="number" id="InputHouseAd" placeholder="Введіть номер будинку" name="houseAddress" >
                </div>
                <div class="buttonAdd">
                    <button class="addButtonServlet" name="addButtonAddress">Додати</button>
                </div>
            </div>
        </div>

        <div class="delDiv" id="delDiv" style="display: none;">
                <div class="InputDelDiv">
                    <input class="inputDel" type="number" id="InputDelAddress" placeholder="Введіть унікальний номер адреси" name="addressDel">
                </div>
                <button class="delButtonServlet" name="delButtonAddress">Видалити</button>
        </div>

    <table id="myTable">
            <tr>
                <th>Унікальний номер</th>
                <th>Країна</th>
                <th>Місто</th>
                <th>Вулиця або площа</th>
                <th>Номер будинку</th>
            </tr>

            <c:if test="${not requestScope.Error}">
                        <c:forEach var="address" items="${ThirdPageShow}">
                            <tr>
                                <td>${address.getUnumber()}</td>
                                <td>${address.getCountry()}</td>
                                <td>${address.getCity()}</td>
                                <td>${address.getStreet()}</td>
                                <td>${address.getNumberHouse()}</td>
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

              <a href="/exhibition/adminhall">«</a>
              <a href="/exhibition/adminmain">1</a>
              <a href="/exhibition/adminhall">2</a>
              <a class="active" href="/exhibition/adminaddress">3</a>
              <a href="/exhibition/adminauthor">4</a>
              <a href="#">5</a>
              <a href="#">6</a>
              <a href="/exhibition/adminauthor">»</a>
            </div>
    </form>

    <script>
           function addFunc(){

                displayAnother= document.getElementById("delDiv").style.display;
                if(displayAnother=='none')
                {
                    display = document.getElementById("addDiv").style.display;
                    if(display=='none'){
                        document.getElementById("addDiv").style.display='block';
                        document.getElementById("InputCountryAd").required = true;
                        document.getElementById("InputCityAd").required = true;
                        document.getElementById("InputStreetAd").required = true;
                        document.getElementById("InputHouseAd").required = true;


                    }else{
                        document.getElementById("addDiv").style.display='none';
                        document.getElementById("InputCountryAd").required = false;
                        document.getElementById("InputCityAd").required = false;
                        document.getElementById("InputStreetAd").required = false;
                        document.getElementById("InputHouseAd").required = false;
                    }
                }
                else
                {
                    document.getElementById("delDiv").style.display='block';
                    delFunc();
                    addFunc();
                }
           };

           function delFunc(){
                displayAnother= document.getElementById("addDiv").style.display;
                if(displayAnother=='none'){
                    display = document.getElementById("delDiv").style.display;
                    if(display=='none'){
                        document.getElementById("delDiv").style.display='block';
                        document.getElementById("InputDelAddress").required = true;
                    }
                    else{
                        document.getElementById("delDiv").style.display='none';
                        document.getElementById("InputDelAddress").required = false;
                    }
                }
                else{
                    document.getElementById("addDiv").style.display='block';
                    addFunc();
                    delFunc();
                }
           };
    </script>
</body>
</html>