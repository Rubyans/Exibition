<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>${languageChange.get(0)}</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleAdmin/fourthPage/FormAuthor.css"%> </style>
    </head>
    <body>
        <c:if test="${requestScope.TrueAdd}">
            <script>
                sweetAlert("${languageChange.get(1)}", "${languageChange.get(3)}", "success");
            </script>
        </c:if>

        <c:if test="${requestScope.TrueDel}">
            <script>
                sweetAlert("${languageChange.get(2)}", "${languageChange.get(4)}", "success");
            </script>
        </c:if>

        <c:if test="${requestScope.AddError}">
            <script>
                sweetAlert("${languageChange.get(1)}", "${languageChange.get(5)}", "error");
            </script>
        </c:if>

        <c:if test="${requestScope.DelError}">
            <script>
                sweetAlert("${languageChange.get(2)}", "${languageChange.get(6)}", "error");
            </script>
        </c:if>

        <form action="adminauthor"  id="AuthorForm" class="AuthorForm" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <div class="nameExit">
                <h3 class="h3Add" id="authorHello">${languageChange.get(7)}</p></h3>
                <button class="exitButton" id="exitButton" name="exitButton">${languageChange.get(8)}</button>
            </div>
            <div class="buttonAuthor">
                <button class="updateButton" id="updateButton" name="updateButton" >${languageChange.get(9)}</button>
                <button class="addButton" id="addButton" name="addButton" onclick="addFunc()" type="Button">${languageChange.get(10)}</button>
                <button class="deleteButton" id="deleteButton" name="deleteButton" onclick="delFunc()" type="Button" >${languageChange.get(11)}</button>
                <button class="saveButton" id="roleBackButton" name="roleBackButton">${languageChange.get(12)}</button>
                <button class="saveButton" id="saveButton" name="saveButton">${languageChange.get(13)}</button>
            </div>

            <div id="addDiv" style="display: none;">
                <div class="wrapper">
                    <div class="left_block">
                        <input type="text" id="InputFirstNameAd" placeholder="${languageChange.get(14)}" name="firstNameAuthor" >
                        <br><input type="text" id="InputLastNameAd" placeholder="${languageChange.get(15)}" name="lastNameAuthor" >
                        <br><input type="email" id="InputEmailAd" placeholder="${languageChange.get(16)}" name="emailAuthor" >
                    </div>
                    <div class="buttonAdd">
                        <button class="addButtonServlet" id="addButtonAuthor" name="addButtonAuthor">${languageChange.get(17)}</button>
                    </div>
                </div>
            </div>

            <div class="delDiv" id="delDiv" style="display: none;">
                    <div class="InputDelDiv">
                        <input class="inputDel" type="email" id="InputDelAuthor" placeholder="${languageChange.get(16)}" name="authorDel">
                    </div>
                    <button class="delButtonServlet" id="delButtonAuthor" name="delButtonAuthor">${languageChange.get(18)}</button>
            </div>

            <table id="myTable">
                <tr>
                    <th id="firstName">${languageChange.get(19)}</th>
                    <th id="lastName">${languageChange.get(20)}</th>
                    <th id="email">${languageChange.get(21)}</th>
                </tr>
                <c:if test="${not requestScope.Error}">
                            <c:forEach var="author" items="${FouthPageShow}">
                                <tr>
                                    <td>${author.getFirstName()}</td>
                                    <td>${author.getLastName()}</td>
                                    <td>${author.getEmail()}</td>
                                </tr>
                            </c:forEach>
                </c:if>
                <c:if test="${requestScope.Error}">
                    <script>
                        sweetAlert("${languageChange.get(22)}", "${languageChange.get(23)}", "error");
                    </script>
                </c:if>
            </table>
            <div class="pagination">
                <a><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="«"></a>
                <a><input class="buttonPagination" type="submit" name="AdminMainPagination" value="1"></a>
                <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="2"></a>
                <a><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="3"></a>
                <a class="active"><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="4"></a>
                <a><input class="buttonPagination" type="submit" name="AdminArtPagination" value="5"></a>
                <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="6"></a>
                <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="7"></a>
                <a><input class="buttonPagination" type="submit" name="AdminStatisticsExhibition" value="8"></a>
                <a><input class="buttonPagination" type="submit" name="AdminArtPagination" value="»"></a>
            </div>
        </form>
    <script src="./JS/adminJS/fourthPage/AdminFourthMenu.js" type="text/javascript"></script>
    </body>
</html>