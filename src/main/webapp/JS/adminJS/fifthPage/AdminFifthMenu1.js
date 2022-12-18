var words = {
        en: [
          { id: "artHello", text: "List of artworks exhibition" },
          { id: "exitButton", text: "Exit" },
          { id: "updateButton", text: "Update" },
          { id: "addButton", text: "Adding" },
          { id: "deleteButton", text: "Delete" },
          { id: "roleBackButton", text: "RoleBack" },
          { id: "saveButton", text: "Save" },
          { id: "InputNameArt", text: "Enter the name of the art" },
          { id: "InputCreationArt", text: "Enter the year of creation" },
          { id: "InputPriceArt", text: "Enter the price of the product" },
          { id: "authorChange", text: "Choose an author" },
          { id: "genreChange", text: "Choose a genre" },
          { id: "addButtonArt", text: "Add" },
          { id: "InputDelArt", text: "Enter the name of the artwork" },
          { id: "delButtonArt", text: "Delete" },
          { id: "art", text: "Work of art" },
          { id: "year", text: "Year of creation" },
          { id: "price", text: "Price" },
          { id: "genreArt", text: "Genre" },
          { id: "authorArt", text: "Author" },
        ],

        ua: [
          { id: "artHello", text: "Список витворів мистецтва" },
          { id: "exitButton", text: "Вийти" },
          { id: "updateButton", text: "Оновити" },
          { id: "addButton", text: "Додавання" },
          { id: "deleteButton", text: "Видалення" },
          { id: "roleBackButton", text: "Відхилити" },
          { id: "saveButton", text: "Зберегти" },
          { id: "InputNameArt", text: "Введіть назву мистецтва" },
          { id: "InputCreationArt", text: "Введіть рік створення" },
          { id: "InputPriceArt", text: "Введіть ціну витвору" },
          { id: "authorChange", text: "Оберіть автора" },
          { id: "genreChange", text: "Оберіть жанр" },
          { id: "addButtonArt", text: "Додати" },
          { id: "InputDelArt", text: "Введіть назву витвору мистецтва" },
          { id: "delButtonArt", text: "Видалити" },
          { id: "art", text: "Витвір мистецтва" },
          { id: "year", text: "Рік створення" },
          { id: "price", text: "Ціна" },
          { id: "genreArt", text: "Жанр" },
          { id: "authorArt", text: "Автор" },
        ],
      };

function changeLanguage(lan) {
    words[lan].forEach((item) => {
        if(item.id=="InputNameArt") {
            $("#InputNameArt").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputCreationArt") {
            $("#InputCreationArt").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputPriceArt") {
            $("#InputPriceArt").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputDelArt") {
            $("#InputDelArt").attr("placeholder",item.text);
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
            document.getElementById("InputNameArt").required = true;
            document.getElementById("InputCreationArt").required = true;
            document.getElementById("InputPriceArt").required = true;
            document.getElementById("SelectAuthor").required = true;
            document.getElementById("SelectView").required = true;

        }else {
            document.getElementById("addDiv").style.display='none';
            document.getElementById("InputNameArt").required = false;
            document.getElementById("InputCreationArt").required = false;
            document.getElementById("InputPriceArt").required = false;
            document.getElementById("SelectAuthor").required = false;
            document.getElementById("SelectView").required = false;
        }
    }
    else {
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
            document.getElementById("InputDelArt").required = true;
        }
        else {
            document.getElementById("delDiv").style.display='none';
            document.getElementById("InputDelArt").required = false;
        }
    }
    else {
        document.getElementById("addDiv").style.display='block';
        addFunc();
        delFunc();
    }
};