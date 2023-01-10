package app.service.sorting;

import app.DAO.entities.UserGuest;
import app.DAO.sqlFunctions.UserGuestDB;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SortGuest {
    private static String valueRows;
    public static List<UserGuest> sortAscending() {

        List<UserGuest> listGuest = UserGuestDB.authorizationUser(valueRows);
        List<UserGuest> sortGuestAscending = listGuest.stream().sorted((s1, s2) -> {
            LocalDate s1DateTime = LocalDate.parse(s1.getDateStart().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate s2DateTime = LocalDate.parse(s2.getDateStart().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return s1DateTime.compareTo(s2DateTime);
        }).collect(Collectors.toList());
        listGuest.removeIf(i -> sortGuestAscending.contains(i));

        return sortGuestAscending;
    }

    public static List<UserGuest> sortDescending() {

        List<UserGuest> listGuest = UserGuestDB.authorizationUser(valueRows);
        List<UserGuest> sortGuestDescending = listGuest.stream().sorted((s2, s1) -> {
            LocalDate s1DateTime = LocalDate.parse(s1.getDateStart().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate s2DateTime = LocalDate.parse(s2.getDateStart().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return s1DateTime.compareTo(s2DateTime);
        }).collect(Collectors.toList());
        listGuest.removeIf(i -> sortGuestDescending.contains(i));

        return sortGuestDescending;
    }

    public static void setValueRows(String valueRows) {
        SortGuest.valueRows = valueRows;
    }

    public static String getValueRows() {
        return SortGuest.valueRows;
    }
}
