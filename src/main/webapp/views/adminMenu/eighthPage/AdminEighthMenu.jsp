<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>Адміністратор</title>
        <meta charset="UTF-8"/>
        <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style> <%@include file="/style/styleAdmin/eighthPage/FormStatisticsExhibition.css"%> </style>
    </head>
    <body>
        <form action="adminstatistics"  id="AdminStatistics" class="AdminStatistics" method="POST">
            <div class="languageDiv">
                <button class="languageButton" name="englishButton"><img src="image/flagUK.png" alt="English" width="100%" height="100%"></button>
                <button class="languageButton" name="ukraineButton"><img src="image/flagUA.png" alt="Ukraine" width="100%" height="100%"></button>
            </div>
            <div class="nameExit">
                 <h3 class="h3Add" id="exhibitionHello">Статистика відвідувань</p></h3>
                <button class="exitButton" id="exitButton" name="exitButton">Вийти</button>
            </div>
            <div class="buttonStatistics">
                <button class="updateButton" name="updateButton" id="updateButton">Оновити</button>
            </div>
        <table id="myTable">
            <tr>
                <th id="nameExhibition">Назва виставки</th>
                <th id="ticketExhibition">Кількість білетів</th>
            </tr>
            <c:if test="${not requestScope.Error}">
                <c:forEach var="statistics" items="${EighthPageShow}">
                    <tr>
                        <td>${statistics.getNameExhibition()}</td>
                        <td>${statistics.getTicketCount()}</td>
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
            <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="«"></a>
            <a ><input class="buttonPagination" type="submit" name="AdminMainPagination" value="1"></a>
            <a><input class="buttonPagination" type="submit" name="AdminHallPagination" value="2"></a>
            <a><input class="buttonPagination" type="submit" name="AdminAddressPagination" value="3"></a>
            <a><input class="buttonPagination" type="submit" name="AdminAuthorPagination" value="4"></a>
            <a><input class="buttonPagination" type="submit" name="AdminArtPagination" value="5"></a>
            <a><input class="buttonPagination" type="submit" name="AdminViewPagination" value="6"></a>
            <a><input class="buttonPagination" type="submit" name="UserAutorizedPagination" value="7"></a>
            <a class="active"><input class="buttonPagination" type="submit" name="AdminStatisticsExhibition" value="8"></a>
            <a><input class="buttonPagination" type="submit" name="AdminStatisticsExhibition" value="»"></a>
        </div>
    </form>
    <script src="./JS/adminJS/eighthPage/AdminEighthMenu.js" type="text/javascript"></script>
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

