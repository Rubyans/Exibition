var words = {
        en: [
            { id: "hallHello", text: "List of halls" },
            { id: "exitButton", text: "Exit" },
            { id: "updateButton", text: "Update" },
            { id: "addButton", text: "Adding" },
            { id: "deleteButton", text: "Delete" },
            { id: "roleBackButton", text: "RoleBack" },
            { id: "saveButton", text: "Save" },
            { id: "InputNameHL", text: "Enter the name of the hall" },
            { id: "InputSquareHL", text: "Enter the area of the hall" },
            { id: "addButtonHall", text: "Add" },
            { id: "InputDelName", text: "Enter the name of the hall" },
            { id: "delButtonHall", text: "Delete" },
            { id: "nameHall", text: "Hall name" },
            { id: "squareHall", text: "Hall area m²" },
        ],
        ua: [
            { id: "hallHello", text: "Список залів" },
            { id: "exitButton", text: "Вийти" },
            { id: "updateButton", text: "Оновити" },
            { id: "addButton", text: "Додавання" },
            { id: "deleteButton", text: "Видалення" },
            { id: "roleBackButton", text: "Відхилити" },
            { id: "saveButton", text: "Зберегти" },
            { id: "InputNameHL", text: "Введіть назву залу" },
            { id: "InputSquareHL", text: "Введіть площу залу" },
            { id: "addButtonHall", text: "Додати" },
            { id: "InputDelName", text: "Введіть назву залу" },
            { id: "delButtonHall", text: "Видалити" },
            { id: "nameHall", text: "Назва залу" },
            { id: "squareHall", text: "Площа залу м²" },
        ],
      };

function changeLanguage(lan) {
    words[lan].forEach((item) => {
        if(item.id=="InputNameHL") {
            $("#InputNameHL").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputSquareHL") {
            $("#InputSquareHL").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputDelName") {
            $("#InputDelName").attr("placeholder",item.text);
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
            document.getElementById("InputNameHL").required = true;
            document.getElementById("InputSquareHL").required = true;
        }else {
            document.getElementById("addDiv").style.display='none';
            document.getElementById("InputNameHL").required = false;
            document.getElementById("InputSquareHL").required = false;
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
            document.getElementById("InputDelName").required = true;
        }
        else {
            document.getElementById("delDiv").style.display='none';
            document.getElementById("InputDelName").required = false;
        }
    }
    else {
        document.getElementById("addDiv").style.display='block';
        addFunc();
        delFunc();
    }
};