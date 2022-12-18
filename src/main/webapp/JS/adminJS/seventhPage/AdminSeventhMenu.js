var words = {
        en: [
           { id: "authorizedHello", text: "List of authorized users" },
           { id: "exitButton", text: "Exit" },
           { id: "updateButton", text: "Update" },
           { id: "addButton", text: "Adding" },
           { id: "deleteButton", text: "Delete" },
           { id: "accessButton", text: "Access" },
           { id: "roleBackButton", text: "RoleBack" },
           { id: "saveButton", text: "Save" },
           { id: "addH6", text: "Choose the method of addition" },
           { id: "addButtonUser", text: "Add User" },
           { id: "addButtonMoney", text: "Change price" },
           { id: "roleChange", text: "Choose a role" },
           { id: "InputFirstNameAuto", text: "Enter the user's last name" },
           { id: "InputLastNameAuto", text: "Enter username" },
           { id: "InputLoginAuto", text: "Enter user login" },
           { id: "InputPasswordAuto", text: "Enter user password" },
           { id: "InputEmailAuto", text: "Enter email address" },
           { id: "InputAmountAuto", text: "Enter amount of money" },
           { id: "addButtonAuto", text: "Add" },
           { id: "InputEmailUserAuto", text: "Enter email address" },
           { id: "InputAmountUserAuto", text: "Enter amount of money" },
           { id: "ButtonMoney", text: "Change" },
           { id: "InputDelAuto", text: "Enter the user's mail" },
           { id: "delButtonAuto", text: "Delete" },
           { id: "userChange", text: "Select access" },
           { id: "InputAccessEmail", text: "Enter your mailbox" },
           { id: "accessButtonServlet", text: "Change" },
           { id: "firstName", text: "Username" },
           { id: "lastName", text: "Username" },
           { id: "login", text: "User login" },
           { id: "password", text: "User password" },
           { id: "email", text: "E-mail address" },
           { id: "amount", text: "Amount of money" },
           { id: "role", text: "Role" },
        ],

        ua: [
          { id: "authorizedHello", text: "Список користувачів" },
          { id: "exitButton", text: "Вийти" },
          { id: "updateButton", text: "Оновити" },
          { id: "addButton", text: "Додавання" },
          { id: "deleteButton", text: "Видалення" },
          { id: "accessButton", text: "Змінити" },
          { id: "roleBackButton", text: "Відхилити" },
          { id: "saveButton", text: "Зберегти" },
          { id: "addH6", text: "Оберіть спосіб додавання" },
          { id: "addButtonUser", text: "Додати користувача" },
          { id: "addButtonMoney", text: "Змінити ціну" },
          { id: "roleChange", text: "Оберіть роль" },
          { id: "InputFirstNameAuto", text: "Введіть прізвище користувача" },
          { id: "InputLastNameAuto", text: "Введіть ім`я користувача" },
          { id: "InputLoginAuto", text: "Введіть логін користувача" },
          { id: "InputPasswordAuto", text: "Введіть пароль користувача" },
          { id: "InputEmailAuto", text: "Введіть електронну адресу" },
          { id: "InputAmountAuto", text: "Введіть кількість грошей" },
          { id: "addButtonAuto", text: "Додати" },
          { id: "InputEmailUserAuto", text: "Введіть електронну адресу" },
          { id: "InputAmountUserAuto", text: "Введіть кількість грошей" },
          { id: "ButtonMoney", text: "Змінити" },
          { id: "InputDelAuto", text: "Введіть пошту користувача" },
          { id: "delButtonAuto", text: "Видалити" },
          { id: "userChange", text: "Оберіть доступ" },
          { id: "InputAccessEmail", text: "Введіть поштову адресу" },
          { id: "accessButtonServlet", text: "Змінити" },
          { id: "firstName", text: "Прізвище користувача" },
          { id: "lastName", text: "Ім`я користувача" },
          { id: "login", text: "Логін користувача" },
          { id: "password", text: "Пароль користувача" },
          { id: "email", text: "Електрона адреса" },
          { id: "amount", text: "Кількість грошей" },
          { id: "role", text: "Роль" },
        ],
      };

function changeLanguage(lan) {
    words[lan].forEach((item) => {
        if(item.id=="InputFirstNameAuto") {
            $("#InputFirstNameAuto").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputLastNameAuto") {
            $("#InputLastNameAuto").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputLoginAuto") {
            $("#InputLoginAuto").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputPasswordAuto") {
            $("#InputPasswordAuto").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputEmailAuto") {
            $("#InputEmailAuto").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputAmountAuto") {
            $("#InputAmountAuto").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputEmailUserAuto") {
            $("#InputEmailUserAuto").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputAmountUserAuto") {
            $("#InputAmountUserAuto").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputDelAuto") {
            $("#InputDelAuto").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputAccessEmail") {
            $("#InputAccessEmail").attr("placeholder",item.text);
            return;
        }
        document.getElementById(item.id).innerText = item.text;
    });
}
function addUser() {
    document.getElementById("addDiv").style.display='none';
    display = document.getElementById("addDivUser").style.display;
    if(display=='none') {
        document.getElementById("addDivUser").style.display='block';
    }
    else {
        document.getElementById("addDivUser").style.display='none';
    }
};
function addMoney() {
    document.getElementById("addDiv").style.display='none';
    display = document.getElementById("addDivMoney").style.display;
    if(display=='none') {
        document.getElementById("addDivMoney").style.display='block';
        document.getElementById("InputAmountUserAuto").required = true;
        document.getElementById("InputEmailUserAuto").required = true;
    }
    else {
        document.getElementById("addDivMoney").style.display='none';
        document.getElementById("InputAmountUserAuto").required = false;
        document.getElementById("InputEmailUserAuto").required = false;
    }
};

function addFunc() {
    displayMoney = document.getElementById("addDivMoney").style.display;
    displayAddUser = document.getElementById("addDivUser").style.display;
    displayDel = document.getElementById("delDiv").style.display;
    displayAccess = document.getElementById("accessDiv").style.display;
    if(displayMoney=='block') {
        addMoney();
    }
    if(displayAddUser=='block') {
        addUser();
    }
    if(displayDel=='none' && displayAccess=='none') {
        display = document.getElementById("addDiv").style.display;
        if(display=='none') {
            document.getElementById("addDiv").style.display='block';
        }
        else {
            document.getElementById("addDiv").style.display='none';
        }
    }
    else {
        if(displayDel!='none') {
            document.getElementById("delDiv").style.display='block';
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
    displayMoney = document.getElementById("addDivMoney").style.display;
    displayAddUser = document.getElementById("addDivUser").style.display;
    displayAdd = document.getElementById("addDiv").style.display;
    displayAccess = document.getElementById("accessDiv").style.display;
    if(displayMoney=='block') {
        addMoney();
    }
    if(displayAddUser=='block') {
        addUser();
    }
    if(displayAdd=='none' && displayAccess=='none') {
        display = document.getElementById("delDiv").style.display;
        if(display=='none') {
            document.getElementById("delDiv").style.display='block';
            document.getElementById("InputDelAuto").required = true;
        }
        else {
            document.getElementById("delDiv").style.display='none';
            document.getElementById("InputDelAuto").required = false;
        }
    }
    else {
        if(displayAdd!='none') {
           document.getElementById("addDiv").style.display='block';
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
    displayMoney = document.getElementById("addDivMoney").style.display;
    displayAddUser = document.getElementById("addDivUser").style.display;
    if(displayMoney=='block') {
        addMoney();
    }
    if(displayAddUser=='block') {
         addUser();
    }
    if(displayAdd=='none'&&displayDel=='none') {
        display = document.getElementById("accessDiv").style.display;
        if(display=='none') {
            document.getElementById("accessDiv").style.display='block';
            document.getElementById("InputAccessEmail").required = true;
        }
        else {
            document.getElementById("accessDiv").style.display='none';
            document.getElementById("InputAccessEmail").required = false;
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