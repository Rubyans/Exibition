var words = {
        en: [
          { id: "guestHello", text: "Welcome, dear guest" },
          { id: "updateButton", text: "Update" },
          { id: "sortButton", text: "Sort" },
          { id: "autoButton", text: "Authorization" },
          { id: "nameExhibition", text: "Name of exhibition" },
          { id: "description", text: "Description" },
          { id: "art", text: "Exposures" },
          { id: "price", text: "Price" },
          { id: "dataStart", text: "Start date" },
          { id: "dataEnd", text: "End date" },

        ],

        ua: [
          { id: "guestHello", text: "Вітаємо шановного гостя" },
          { id: "updateButton", text: "Оновити" },
          { id: "sortButton", text: "Сортувати" },
          { id: "autoButton", text: "Авторизація" },
          { id: "nameExhibition", text: "Назва виставки" },
          { id: "description", text: "Опис" },
          { id: "art", text: "Експозиції" },
          { id: "price", text: "Ціна" },
          { id: "dataStart", text: "Дата початку" },
          { id: "dataEnd", text: "Дата кінця" },
        ],
      };

function changeLanguage(lan) {
    words[lan].forEach((item) => {
        document.getElementById(item.id).innerText = item.text;
    });
}


function sortTable() {
  var table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("myTable");
  switching = true;

  while (switching) { //make a loop that will continue until no switching has been done
    switching = false;
    rows = table.getElementsByTagName("TR");
    for (i = 1; i < (rows.length - 1); i++) { //loop through all table rows (except the first, which contains table headers):
      shouldSwitch = false;
      x = rows[i].getElementsByTagName("TD")[4];
      y = rows[i + 1].getElementsByTagName("TD")[4];
      if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
        shouldSwitch = true;
        break;
      }
    }
    if (shouldSwitch) { //if a switch has been marked, make the switchand mark that a switch has been done
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
}