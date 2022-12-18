var words = {
        en: [
           { id: "genreHello", text: "List of genres" },
           { id: "exitButton", text: "Exit" },
           { id: "updateButton", text: "Update" },
           { id: "addButton", text: "Adding" },
           { id: "deleteButton", text: "Delete" },
           { id: "roleBackButton", text: "RoleBack" },
           { id: "saveButton", text: "Save" },
           { id: "InputNameView", text: "Enter genre name" },
           { id: "InputDelView", text: "Enter genre name" },
           { id: "delButtonView", text: "Delete" },
           { id: "uniqueNumber", text: "Unique Number" },
           { id: "nameGenre", text: "Genre name" },
        ],

        ua: [
          { id: "genreHello", text: "Список жанрів" },
          { id: "exitButton", text: "Вийти" },
          { id: "updateButton", text: "Оновити" },
          { id: "addButton", text: "Додавання" },
          { id: "deleteButton", text: "Видалення" },
          { id: "roleBackButton", text: "Відхилити" },
          { id: "saveButton", text: "Зберегти" },
          { id: "InputNameView", text: "Введіть назву жанра" },
          { id: "InputDelView", text: "Введіть назву жанра" },
          { id: "delButtonView", text: "Видалити" },
          { id: "uniqueNumber", text: "Унікальний номер" },
          { id: "nameGenre", text: "Назва жанру" },
        ],
      };

function changeLanguage(lan) {
    words[lan].forEach((item) => {
        if(item.id=="InputNameView") {
            $("#InputNameView").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputDelView") {
            $("#InputDelView").attr("placeholder",item.text);
            return;
        }
        document.getElementById(item.id).innerText = item.text;
    });
}

function addFunc() {
    displayAnother= document.getElementById("delDiv").style.display;
    if(displayAnother=='none') {
        display = document.getElementById("addDiv").style.display;
        if(display=='none') {
            document.getElementById("addDiv").style.display='block';
            document.getElementById("InputNameView").required = true;
        }else {
            document.getElementById("addDiv").style.display='none';
            document.getElementById("InputNameView").required = false;
        }
    }else {
        document.getElementById("delDiv").style.display='block';
        delFunc();
        addFunc();
    }
};
function delFunc() {
    displayAnother= document.getElementById("addDiv").style.display;
    if(displayAnother=='none') {
        display = document.getElementById("delDiv").style.display;
        if(display=='none') {
            document.getElementById("delDiv").style.display='block';
            document.getElementById("InputDelView").required = true;
        }
        else {
            document.getElementById("delDiv").style.display='none';
            document.getElementById("InputDelView").required = false;
        }
    }
    else {
        document.getElementById("addDiv").style.display='block';
        addFunc();
        delFunc();
    }
};