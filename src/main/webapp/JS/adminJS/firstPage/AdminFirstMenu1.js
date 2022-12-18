var words = {
        en: [
           { id: "exhibitionHello", text: "List of exhibitions" },
           { id: "exitButton", text: "Exit" },
           { id: "updateButton", text: "Update" },
           { id: "addButton", text: "Adding" },
           { id: "deleteButton", text: "Delete" },
           { id: "accessButton", text: "Access" },
           { id: "roleBackButton", text: "RoleBack" },
           { id: "saveButton", text: "Save" },
           { id: "InputNameEx", text: "Enter the name of the exhibition" },
           { id: "InputDescriptEx", text: "Enter a description of the exhibition" },
           { id: "InputPriceEx", text: "Enter the ticket price" },
           { id: "InputDatestartEx", text: "Enter the start date" },
           { id: "InputDateendEx", text: "Enter the end date" },
           { id: "hallChange", text: "Select hall(s)" },
           { id: "addressChange", text: "Select address(es)" },
           { id: "expositionChange", text: "Select exposure(s)" },
           { id: "addButtonServlet", text: "Add" },
           { id: "InputDelName", text: "Enter name of exhibition" },
           { id: "delButtonServlet", text: "Delete" },
           { id: "exhibitionChange", text: "Select access" },
           { id: "InputAccessName", text: "Enter name of exhibition" },
           { id: "accessButtonServlet", text: "Change" },
           { id: "nameExhibition", text: "Name of the exhibition" },
           { id: "description", text: "Description" },
           { id: "exposition", text: "Expositions" },
           { id: "price", text: "Price" },
           { id: "dateStart", text: "Start Date" },
           { id: "dateEnd", text: "End date" },
           { id: "hall", text: "Halls" },
           { id: "author", text: "Authors of expositions" },
           { id: "genre", text: "Genres" },
           { id: "address", text: "Addresses" },
        ],

        ua: [
          { id: "exhibitionHello", text: "Список виставок" },
          { id: "exitButton", text: "Вийти" },
          { id: "updateButton", text: "Оновити" },
          { id: "addButton", text: "Додавання" },
          { id: "deleteButton", text: "Видалення" },
          { id: "accessButton", text: "Доступ" },
          { id: "roleBackButton", text: "Відхилити" },
          { id: "saveButton", text: "Зберегти" },
          { id: "InputNameEx", text: "Введіть назву виставки" },
          { id: "InputDescriptEx", text: "Введіть опис виставки" },
          { id: "InputPriceEx", text: "Введіть ціну квитка" },
          { id: "InputDatestartEx", text: "Введіть дату початку" },
          { id: "InputDateendEx", text: "Введіть дату кінця" },
          { id: "hallChange", text: "Оберіть зал(и)" },
          { id: "addressChange", text: "Оберіть адресу(и)" },
          { id: "expositionChange", text: "Оберіть експозицію(ї)" },
          { id: "addButtonServlet", text: "Додати" },
          { id: "InputDelName", text: "Введіть назву виставки" },
          { id: "delButtonServlet", text: "Видалити" },
          { id: "exhibitionChange", text: "Оберіть доступ" },
          { id: "InputAccessName", text: "Введіть назву виставки" },
          { id: "accessButtonServlet", text: "Змінити" },
          { id: "nameExhibition", text: "Назва виставки" },
          { id: "description", text: "Опис" },
          { id: "exposition", text: "Експозиції" },
          { id: "price", text: "Ціна" },
          { id: "dateStart", text: "Дата початку" },
          { id: "dateEnd", text: "Дата кінця" },
          { id: "hall", text: "Зали" },
          { id: "author", text: "Автори експозицій" },
          { id: "genre", text: "Жанри" },
          { id: "address", text: "Адреси" },
        ],
      };

function changeLanguage(lan) {
    words[lan].forEach((item) => {
        if(item.id=="InputNameEx") {
            $("#InputNameEx").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputDescriptEx") {
            $("#InputDescriptEx").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputPriceEx") {
            $("#InputPriceEx").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputDatestartEx") {
            $("#InputDatestartEx").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputDateendEx") {
            $("#InputDateendEx").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputDelName") {
            $("#InputDelName").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputAccessName") {
            $("#InputAccessName").attr("placeholder",item.text);
            return;
        }
        document.getElementById(item.id).innerText = item.text;
    });
}
 function addFunc() {
    displayDel = document.getElementById("delDiv").style.display;
    displayAccess = document.getElementById("accessDiv").style.display;
    if(displayDel=='none'&&displayAccess=='none') {
        display = document.getElementById("addDiv").style.display;
        if(display=='none') {
            document.getElementById("addDiv").style.display='block';
            document.getElementById("InputNameEx").required = true;
            document.getElementById("InputDescriptEx").required = true;
            document.getElementById("InputPriceEx").required = true;
            document.getElementById("InputDatestartEx").required = true;

            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth() + 1;
            var yyyy = today.getFullYear();
            if(dd < 10){
              dd='0' + dd
            }
            if(mm < 10){
              mm='0' + mm
            }

            today = yyyy + '-' + mm + '-' + dd;
            document.getElementById("InputDatestartEx").setAttribute("min", today);
            document.getElementById("InputDatestartEx").setAttribute("value", today);

            document.getElementById("InputDateendEx").setAttribute("min", today);
            document.getElementById("InputDateendEx").setAttribute("value", today);

            document.getElementById("InputDateendEx").required = true;
            document.getElementById("SelectHall").required = true;
            document.getElementById("SelectAddress").required = true;
            document.getElementById("SelectArt").required = true;

        }else {
            document.getElementById("addDiv").style.display='none';
            document.getElementById("InputNameEx").required = false;
            document.getElementById("InputDescriptEx").required = false;
            document.getElementById("InputPriceEx").required = false;
            document.getElementById("InputDatestartEx").required = false;
            document.getElementById("InputDateendEx").required = false;
            document.getElementById("SelectHall").required = false;
            document.getElementById("SelectAddress").required = false;
            document.getElementById("SelectArt").required = false;
        }
    }
    else {
        if(displayDel!='none') {
            delFunc();
            addFunc();
        }
        if(displayAccess!='none') {
            accessFunc();
            addFunc();
        }
    }
 };
 function delFunc() {
    displayAdd = document.getElementById("addDiv").style.display;
    displayAccess = document.getElementById("accessDiv").style.display;
    if(displayAdd=='none'&&displayAccess=='none') {
        display = document.getElementById("delDiv").style.display;
        if(display=='none') {
            document.getElementById("delDiv").style.display='block';
            document.getElementById("InputDelName").required = true;
        }
        else {
            document.getElementById("delDiv").style.display='none';
            document.getElementById("InputDelName").required = false;
        }
    }
    else {
        if(displayAdd!='none') {
            addFunc();
            delFunc();
        }
        if(displayAccess!='none') {
            accessFunc();
            delFunc();
        }
    }
 };

 function accessFunc() {
    displayAdd = document.getElementById("addDiv").style.display;
    displayDel = document.getElementById("delDiv").style.display;
    if(displayAdd=='none'&&displayDel=='none') {
        display = document.getElementById("accessDiv").style.display;
        if(display=='none') {
            document.getElementById("accessDiv").style.display='block';
            document.getElementById("InputAccessName").required = true;
        }
        else {
            document.getElementById("accessDiv").style.display='none';
            document.getElementById("InputAccessName").required = false;
        }
    }
    else {
        if(displayAdd!='none') {
            addFunc();
            accessFunc();
        }
        if(displayDel!='none') {
            delFunc();
            accessFunc();
        }
    }

 };