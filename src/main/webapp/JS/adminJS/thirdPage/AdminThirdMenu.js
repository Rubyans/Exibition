var words = {
        en: [
           { id: "addressHello", text: "List of addresses" },
           { id: "exitButton", text: "Exit" },
           { id: "updateButton", text: "Update" },
           { id: "addButton", text: "Adding" },
           { id: "deleteButton", text: "Delete" },
           { id: "roleBackButton", text: "RoleBack" },
           { id: "saveButton", text: "Save" },
           { id: "InputCountryAd", text: "Enter country name" },
           { id: "InputCityAd", text: "Enter the city name" },
           { id: "InputStreetAd", text: "Enter the name of the street/square" },
           { id: "InputHouseAd", text: "Enter house number" },
           { id: "addButtonAddress", text: "Add" },
           { id: "InputDelAddress", text: "Enter a unique address number" },
           { id: "delButtonAddress", text: "Delete" },
           { id: "keyUnique", text: "Unique number" },
           { id: "country", text: "Country" },
           { id: "city", text: "City" },
           { id: "street", text: "Street or square" },
           { id: "house", text: "House number" },
        ],

        ua: [
          { id: "addressHello", text: "Список адрес" },
          { id: "exitButton", text: "Вийти" },
          { id: "updateButton", text: "Оновити" },
          { id: "addButton", text: "Додавання" },
          { id: "deleteButton", text: "Видалення" },
          { id: "roleBackButton", text: "Відхилити" },
          { id: "saveButton", text: "Зберегти" },
          { id: "InputCountryAd", text: "Введіть назву країни" },
          { id: "InputCityAd", text: "Введіть назву міста" },
          { id: "InputStreetAd", text: "Введіть назву вулиці/площі" },
          { id: "InputHouseAd", text: "Введіть номер будинку" },
          { id: "addButtonAddress", text: "Додати" },
          { id: "InputDelAddress", text: "Введіть унікальний номер адреси" },
          { id: "delButtonAddress", text: "Видалити" },
          { id: "keyUnique", text: "Унікальний номер" },
          { id: "country", text: "Країна" },
          { id: "city", text: "Місто" },
          { id: "street", text: "Вулиця або площа" },
          { id: "house", text: "Номер будинку" },
        ],
      };

function changeLanguage(lan) {
    words[lan].forEach((item) => {
        if(item.id=="InputCountryAd") {
            $("#InputCountryAd").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputCityAd") {
            $("#InputCityAd").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputStreetAd") {
            $("#InputStreetAd").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputHouseAd") {
            $("#InputHouseAd").attr("placeholder",item.text);
            return;
        }
        if(item.id=="InputDelAddress") {
            $("#InputDelAddress").attr("placeholder",item.text);
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
            document.getElementById("InputCountryAd").required = true;
            document.getElementById("InputCityAd").required = true;
            document.getElementById("InputStreetAd").required = true;
            document.getElementById("InputHouseAd").required = true;
        }else {
            document.getElementById("addDiv").style.display='none';
            document.getElementById("InputCountryAd").required = false;
            document.getElementById("InputCityAd").required = false;
            document.getElementById("InputStreetAd").required = false;
            document.getElementById("InputHouseAd").required = false;
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
            document.getElementById("InputDelAddress").required = true;
        }
        else {
            document.getElementById("delDiv").style.display='none';
            document.getElementById("InputDelAddress").required = false;
        }
    }
    else {
        document.getElementById("addDiv").style.display='block';
        addFunc();
        delFunc();
    }
};