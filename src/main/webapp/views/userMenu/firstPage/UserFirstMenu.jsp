<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Квиток</title>
        <meta charset="UTF-8"/>
        <link href="./style/styleUser/firstPage/FormUser.css" rel="stylesheet"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
    </head>
<body>

    <c:if test="${requestScope.TrueAdd}">
        <script>
                sweetAlert("Купівля", "Ви успішно придбали квиток, підтвердіть купівлю квитка!", "success");
        </script>
    </c:if>
    <c:if test="${requestScope.AddError}">
            <script>
                sweetAlert("Помилка", "Недостаньо коштів", "error");
            </script>
    </c:if>
    <c:if test="${requestScope.SaveCommitError}">
            <script>
                sweetAlert("Помилка", "Проблеми з базою даних", "error");
            </script>
    </c:if>
    <c:if test="${requestScope.SaveCommitTrue}">
            <script>
                sweetAlert("Купівля", "Ви успішно підтвердили купівлю квитків", "success");
            </script>
    </c:if>
    <c:if test="${requestScope.RoleBackCommitError}">
            <script>
                sweetAlert("Помилка", "Проблеми з базою даних", "error");
            </script>
    </c:if>
    <c:if test="${requestScope.RoleBackCommitTrue}">
            <script>
                sweetAlert("Купівля", "Ви успішно відхилили купівлю квитків", "success");
            </script>
    </c:if>
    <form action="user"  id="userForm" class="userForm" method="POST">
        <div class="nameExit">
             <h3 class="h3Add">Купівля квитка</p></h3>
            <button class="exitButton" name="exitButton">Вийти</button>
        </div>
        <div class="moneyUser">
            <h4 class="h4Text">Ваш бюджет</h4>
            <h4 class="h4Money">${Money.get(0).getAmount()} грн</h4>
        </div>
        <div class="buttonUser">
            <button class="updateButton" name="updateButton" >Оновити</button>
            <button class="addButton" id="one" name="addButton" onclick="addFunc()" type="Button">Квиток</button>
            <button class="saveButton" name="saveButton">Підтвердити</button>
            <button class="roleBackButton" name="roleBackButton">Відхилити</button>
        </div>

        <div id="addDiv" style="display: none;">
            <div class="wrapper">
                 <h6 class="h6Add">Оберіть виставку</h6>
                 <select class="SelectExhibition" name="nameExhibition" id="SelectExhibition" multiple>
                    <c:forEach var="name" items="${AddShow}">
                        <option value="${name.getNameExhibition()}">${name.getNameExhibition()}</option>
                    </c:forEach>
                 </select>
                <div class="buttonAdd">
                    <button class="addButtonServlet" name="addButtonTicket">Придбати квиток</button>
                </div>
            </div>
        </div>
    <table id="myTable">
            <tr>
                <th>Назва виставки</th>
                <th>Ціна білету</th>
                <th>Дата початку</th>
                <th>Дата кінця</th>
                <th>Придбані квитки</th>
            </tr>
            <c:if test="${not requestScope.Error}">
                        <c:forEach var="user" items="${FirstPageUser}">
                            <tr>
                                <td>${user.getNameExhibition()}</td>
                                <td>${user.getPrice()}</td>
                                <td>${user.getDateStart()}</td>
                                <td>${user.getDateEnd()}</td>
                                 <td>${user.getTicket()}</td>
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
                <a class="active"><input class="buttonPagination" type="submit" name="UserPagination" value="1"></a>
                <a><input class="buttonPagination" type="submit" name="UserExhibitionPagination" value="2"></a>
                <a><input class="buttonPagination" type="submit" name="UserExhibitionPagination" value="»"></a>
            </div>
    </form>
  <script src="./JS/userJS/firstPage/UserAutorized.js" type="text/javascript"></script>
</body>
</html>

