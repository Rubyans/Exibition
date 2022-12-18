var words = {
        en: [
           { id: "authorHello", text: "List of authors" },
           { id: "exitButton", text: "Exit" },
           { id: "updateButton", text: "Update" },
           { id: "addButton", text: "Adding" },
           { id: "deleteButton", text: "Delete" },
           { id: "roleBackButton", text: "RoleBack" },
           { id: "saveButton", text: "Save" },
           { id: "InputFirstNameAd", text: "Enter the author's last name" },
           { id: "InputLastNameAd", text: "Enter the author's name" },
           { id: "InputEmailAd", text: "Enter the author's mailbox" },
           { id: "addButtonAuthor", text: "Add" },
           { id: "InputDelAuthor", text: "Enter the author's mailbox" },
           { id: "delButtonAuthor", text: "Delete" },
           { id: "firstName", text: "Surname" },
           { id: "lastName", text: "Name" },
           { id: "email", text: "Email address" },
        ],

        ua: [
          { id: "authorHello", text: "Список авторів" },
          { id: "exitButton", text: "Вийти" },
          { id: "updateButton", text: "Оновити" },
          { id: "addButton", text: "Додавання" },
          { id: "deleteButton", text: "Видалення" },
          { id: "roleBackButton", text: "Відхилити" },
          { id: "saveButton", text: "Зберегти" },
          { id: "InputFirstNameAd", text: "Введіть прізвище автора" },
          { id: "InputLastNameAd", text: "Введіть ім`я автора" },
          { id: "InputEmailAd", text: "Введіть поштову скриньку автора" },
          { id: "addButtonAuthor", text: "Додати" },
          { id: "InputDelAuthor", text: "Введіть поштову скриньку автора" },
          { id: "delButtonAuthor", text: "Видалити" },
          { id: "firstName", text: "Прізвище" },
          { id: "lastName", text: "Ім`я" },
          { id: "email", text: "Електронна адреса" },
        ],
      };

function changeLanguage(lan) {
    words[lan].forEach((item) => {
        if(item.id=="InputFirstNameAd") {
            $("#InputFirstNameAd").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputLastNameAd") {
            $("#InputLastNameAd").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputEmailAd") {
            $("#InputEmailAd").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputDelAuthor") {
            $("#InputDelAuthor").attr("placeholder",item.text);
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
            document.getElementById("InputFirstNameAd").required = true;
            document.getElementById("InputLastNameAd").required = true;
            document.getElementById("InputEmailAd").required = true;
        }else {
            document.getElementById("addDiv").style.display='none';
            document.getElementById("InputFirstNameAd").required = false;
            document.getElementById("InputLastNameAd").required = false;
            document.getElementById("InputEmailAd").required = false;
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
            document.getElementById("InputDelAuthor").required = true;
        }
        else {
            document.getElementById("delDiv").style.display='none';
            document.getElementById("InputDelAuthor").required = false;
        }
    }
    else {
        document.getElementById("addDiv").style.display='block';
        addFunc();
        delFunc();
    }
};