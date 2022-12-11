<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Адміністратор</title>
        <meta charset="UTF-8"/>
        <link href="./style/styleAdmin/firstPage/FormExhibition.css" rel="stylesheet"/>
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
                sweetAlert("Помилка", "Дана назва виставки вже існує, введіть іншу назву!", "error");
            </script>
    </c:if>
    <c:if test="${requestScope.DelError}">
        <script>
            sweetAlert("Помилка", "Даних за даною назвою не існує!", "error");
        </script>
    </c:if>

    <form action="adminmain"  id="adminForm" class="adminForm" method="POST">
        <div class="nameExit">
             <h3 class="h3Add">Список виставок</p></h3>
            <button class="exitButton" name="exitButton">Вийти</button>
        </div>
        <div class="buttonAdmin">
            <button class="updateButton" name="updateButton" >Оновити</button>
            <button class="addButton" id="one"  name="addButton"   onclick="addFunc()" type="Button">Додавання</button>
            <button class="deleteButton" name="deleteButton" onclick="delFunc()" type="Button" >Видалення</button>
            <button class="roleBackButton" name="roleBackButton">Відхилити</button>
            <button class="saveButton" name="saveButton">Зберегти</button>
        </div>

        <div id="addDiv" style="display: none;">
            <div class="wrapper">
                <div class="left_block">
                    <input type="text" id="InputNameEx" placeholder="Введіть назву виставки" name="nameExibition" >
                    <br><input type="text" id="InputDescriptEx" placeholder="Введіть опис виставки" name="description" >
                    <br><input type="number" id="InputPriceEx" placeholder="Введіть ціну квитка" step="any" name="price" >
                    <br><input type="date" id="InputDatestartEx" placeholder="Введіть дату початку" name="start" >
                    <br><input type="date" id="InputDateendEx" placeholder="Введіть дату кінця" name="end" >
                </div>
                <div class="right_block">
                    <c:forEach var="addFirstPage" items="${AddShow}">
                        <h6 class="h6Add">Оберіть зал(и)</h6>
                        <select class="SelectAdd" name="hall" id="SelectHall" multiple>
                            <c:forEach var="hall" items="${addFirstPage.getHall()}">
                                <option value="${hall}">${hall}</option>
                                 </c:forEach>
                        </select>
                        <h6 class="h6Add">Оберіть адресу(и)</h6>
                        <select class="SelectAdd" name="address" id="SelectAddress" multiple>
                            <c:forEach var="address" items="${addFirstPage.getAddress()}">
                                <option value="${address}">${address}</option>
                            </c:forEach>
                        </select>
                            <h6 class="h6Add">Оберіть експозицію(ї)</h6>
                        <select class="SelectAdd" name="workArt" id="SelectArt" multiple>
                            <c:forEach var="art" items="${addFirstPage.getArt()}">
                                <option value="${art}">${art}</option>
                            </c:forEach>
                        </select>

                    </c:forEach>
                </div>
                <div class="buttonAdd">
                    <button class="addButtonServlet" name="addButtonServlet">Додати</button>
                </div>
            </div>
        </div>

        <div class="delDiv" id="delDiv" style="display: none;">
                <div class="InputDelDiv">
                    <input class="inputDel" type="text" id="InputDelName" placeholder="Введіть назву виставки" name="nameExhibitionDel">
                </div>
                <button class="delButtonServlet" name="delButtonServlet">Видалити</button>
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
                        <c:forEach var="admin" items="${FirstPage}">
                            <tr>
                                <td>${admin.getNameExhibition()}</td>
                                <td>${admin.getDescriptionExibition()}</td>
                                <td>
                                    <c:forEach var="exposition" items="${admin.getExpositionName()}">
                                     ${exposition}<br>
                                     </c:forEach>
                                 </td>
                                <td>${admin.getPrice()}</td>
                                <td>${admin.getDateStart()}</td>
                                <td>${admin.getDateEnd()}</td>
                                <td>
                                    <c:forEach var="hell" items="${admin.getNameHall()}">
                                        ${hell}<br>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:forEach var="nameAuthor" items="${admin.getNameAuthor()}">
                                        ${nameAuthor}<br>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:forEach var="nameView" items="${admin.getNameview()}">
                                         ${nameView}<br>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:forEach var="address" items="${admin.getAddressExibition()}">
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
                <a><input class="buttonPagination" type="submit" name="AdminMainPagination" value="«"></a>
                <a class="active"><input class="buttonPagination" type="submit" name="AdminMainPagination" value="1"></a>
                <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="2"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="3"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="4"></a>
                <a><input class="buttonPagination" type="submit" name="AdminArtPagination" value="5"></a>
                <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="6"></a>
                <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="7"></a>
                <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="»"></a>
            </div>
    </form>
    <script src="./JS/adminJS/firstPage/AdminFirstMenu.js" type="text/javascript"></script>
</body>
</html>

