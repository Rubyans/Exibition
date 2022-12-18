<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Адміністратор</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleAdmin/thirdPage/FormAddress.css"%> </style>
    </head>
    <body>

        <c:if test="${requestScope.TrueAdd}">
            <script>
                sweetAlert("Addition", "You have successfully added data. Save the changes!", "success");
            </script>
        </c:if>

        <c:if test="${requestScope.TrueDel}">
            <script>
                sweetAlert("Removal", "You have successfully deleted the data. Save the changes!", "success");
            </script>
        </c:if>

        <c:if test="${requestScope.AddError}">
            <script>
                sweetAlert("Addition", "This address already exists, please enter another name!", "error");
            </script>
        </c:if>

        <c:if test="${requestScope.DelError}">
            <script>
                sweetAlert("Removal", "There is no data for this unique number!", "error");
            </script>
        </c:if>

        <form action="adminaddress"  id="AddressForm" class="AddressForm" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <div class="nameExit">
                <h3 class="h3Add" id="addressHello">Список адрес</p></h3>
                <button class="exitButton" id="exitButton" name="exitButton">Вийти</button>
            </div>
            <div class="buttonAddress">
                <button class="updateButton" id="updateButton" name="updateButton" >Оновити</button>
                <button class="addButton" id="addButton" name="addButton" onclick="addFunc()" type="Button">Додавання</button>
                <button class="deleteButton" id="deleteButton" name="deleteButton" onclick="delFunc()" type="Button" >Видалення</button>
                <button class="saveButton" id="roleBackButton" name="roleBackButton">Відхилити</button>
                <button class="saveButton" id="saveButton" name="saveButton">Зберегти</button>
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
                        <button class="addButtonServlet" id="addButtonAddress" name="addButtonAddress">Додати</button>
                    </div>
                </div>
            </div>

            <div class="delDiv" id="delDiv" style="display: none;">
                    <div class="InputDelDiv">
                        <input class="inputDel" type="number" id="InputDelAddress" placeholder="Введіть унікальний номер адреси" name="addressDel">
                    </div>
                    <button class="delButtonServlet" id="delButtonAddress" name="delButtonAddress">Видалити</button>
            </div>

            <table id="myTable">
                <tr>
                    <th id="keyUnique">Унікальний номер</th>
                    <th id="country">Країна</th>
                    <th id="city">Місто</th>
                    <th id="street">Вулиця або площа</th>
                    <th id="house">Номер будинку</th>
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
                        sweetAlert("Error", "Database problems, try again later!", "error");
                    </script>
                </c:if>
            </table>
            <div class="pagination">
                <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="«"></a>
                <a><input class="buttonPagination" type="submit" name="AdminMainPagination" value="1"></a>
                <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="2"></a>
                <a class="active"><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="3"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="4"></a>
                <a><input class="buttonPagination" type="submit" name="AdminArtPagination" value="5"></a>
                <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="6"></a>
                <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="7"></a>
                <a><input class="buttonPagination" type="submit" name="AdminStatisticsExhibition" value="8"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="»"></a>
            </div>
        </form>
    <script src="./JS/adminJS/thirdPage/AdminThirdMenu.js" type="text/javascript"></script>
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