<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Адміністратор</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleAdmin/firstPage/FormExhibition.css"%> </style>
    </head>
    <body>
        <c:if test="${requestScope.TrueAdd}">
            <script>
                    sweetAlert("Addition", "You have successfully changed access. Save the changes!", "success");
            </script>
        </c:if>
        <c:if test="${requestScope.TrueChange}">
            <script>
                sweetAlert("Access", "You have successfully added data. Save the changes!", "success");
            </script>
        </c:if>
        <c:if test="${requestScope.TrueDel}">
            <script>
                sweetAlert("Removal", "You have successfully deleted the data. Save the changes!", "success");
            </script>
        </c:if>
        <c:if test="${requestScope.AddError}">
                <script>
                    sweetAlert("Addition", "This exhibition name already exists, please enter another name!", "error");
                </script>
        </c:if>
        <c:if test="${requestScope.DelError}">
            <script>
                sweetAlert("Removal", "There is no data for this name!", "error");
            </script>
        </c:if>
        <c:if test="${requestScope.ChangeError}">
            <script>
                sweetAlert("Access", "There is no data for this exhibition!", "error");
            </script>
        </c:if>
        <form action="adminmain"  id="adminForm" class="adminForm" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <div class="nameExit">
                 <h3 class="h3Add" id="exhibitionHello">Список виставок</p></h3>
                <button class="exitButton" id="exitButton" name="exitButton">Вийти</button>
            </div>
            <div class="buttonAdmin">
                <button class="updateButton" name="updateButton" id="updateButton">Оновити</button>
                <button class="addButton" name="addButton" id="addButton" onclick="addFunc()" type="Button">Додавання</button>
                <button class="deleteButton" name="deleteButton" id="deleteButton" onclick="delFunc()" type="Button" >Видалення</button>
                <button class="accessButton" name="accessButton" id="accessButton" onclick="accessFunc()" type="Button">Доступ</button>
                <button class="roleBackButton" name="roleBackButton" id="roleBackButton">Відхилити</button>
                <button class="saveButton" name="saveButton" id="saveButton">Зберегти</button>
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
                            <h6 class="h6Add" id="hallChange">Оберіть зал(и)</h6>
                            <select class="SelectAdd" name="hall" id="SelectHall" multiple>
                                <c:forEach var="hall" items="${addFirstPage.getHall()}">
                                    <option value="${hall}">${hall}</option>
                                     </c:forEach>
                            </select>
                            <h6 class="h6Add" id="addressChange">Оберіть адресу(и)</h6>
                            <select class="SelectAdd" name="address" id="SelectAddress" multiple>
                                <c:forEach var="address" items="${addFirstPage.getAddress()}">
                                    <option value="${address}">${address}</option>
                                </c:forEach>
                            </select>
                                <h6 class="h6Add" id="expositionChange">Оберіть експозицію(ї)</h6>
                            <select class="SelectAdd" name="workArt" id="SelectArt" multiple>
                                <c:forEach var="art" items="${addFirstPage.getArt()}">
                                    <option value="${art}">${art}</option>
                                </c:forEach>
                            </select>
                        </c:forEach>
                    </div>
                    <div class="buttonAdd">
                        <button class="addButtonServlet" name="addButtonServlet" id="addButtonServlet">Додати</button>
                    </div>
                </div>
            </div>
            <div class="delDiv" id="delDiv" style="display: none;">
                <div class="InputDelDiv">
                    <input class="inputDel" type="text" id="InputDelName" placeholder="Введіть назву виставки" name="nameExhibitionDel">
                </div>
                <button class="delButtonServlet" id="delButtonServlet" name="delButtonServlet">Видалити</button>
            </div>
            <div class="accessDiv" id="accessDiv" style="display: none;">
                  <div class="checkAccess">
                    <h6 class="h6Acc" id="exhibitionChange">Оберіть доступ</h6>
                    <select class="SelectAccess" name="access" id="SelectAccess">
                            <option value="1">Дозволити</option>
                            <option value="2">Заборонити</option>
                    </select>
                  </div>
                 <div class="InputAccessDiv">
                    <input class="inputAccess" type="text" id="InputAccessName" placeholder="Введіть назву виставки" name="nameExhibitionAccess">
                 </div>
                 <button class="accessButtonServlet" id="accessButtonServlet" name="accessButtonServlet">Змінити</button>
            </div>

        <table id="myTable">
            <tr>
                <th id="nameExhibition">Назва виставки</th>
                <th id="description">Опис</th>
                <th id="exposition">Експозиції</th>
                <th id="price">Ціна</th>
                <th id="dateStart">Дата початку</th>
                <th id="dateEnd">Дата кінця</th>
                <th id="hall">Зали</th>
                <th id="author">Автори експозицій</th>
                <th id="genre">Жанри</th>
                <th id="address">Адреси</th>
                <th id="access">Доступ</th>
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
                        <c:if test="${admin.getAccessExhibition().equals('Заборонено')}">
                            <td style="background: #EB1D36;">${admin.getAccessExhibition()}</td>
                        </c:if>
                        <c:if test="${admin.getAccessExhibition().equals('Дозволено')}">
                            <td>${admin.getAccessExhibition()}</td>
                        </c:if>
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
            <a><input class="buttonPagination" type="submit" name="AdminMainPagination" value="«"></a>
            <a class="active"><input class="buttonPagination" type="submit" name="AdminMainPagination" value="1"></a>
            <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="2"></a>
            <a><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="3"></a>
            <a><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="4"></a>
            <a><input class="buttonPagination" type="submit" name="AdminArtPagination" value="5"></a>
            <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="6"></a>
            <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="7"></a>
            <a><input class="buttonPagination" type="submit" name="AdminStatisticsExhibition" value="8"></a>
            <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="»"></a>
        </div>
    </form>
    <script src="./JS/adminJS/firstPage/AdminFirstMenu1.js" type="text/javascript"></script>
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

