var words = {
        en: [
          { id: "ticketHello", text: "Buying a ticket" },
          { id: "exitButton", text: "Exit" },
          { id: "amount", text: "Your money" },
          { id: "updateButton", text: "Update" },
          { id: "addButton", text: "Ticket" },
          { id: "saveButton", text: "Confirm" },
          { id: "roleBackButton", text: "RoleBack" },
          { id: "nameEx", text: "Choose an exhibition" },
          { id: "addButtonTicket", text: "Buy a ticket" },
          { id: "nameExhibition", text: "Name of exhibition" },
          { id: "ticketPrice", text: "Ticket price" },
          { id: "dateStart", text: "Start date" },
          { id: "dateEnd", text: "End date" },
          { id: "boughtTicket", text: "Purchased tickets" },
        ],

        ua: [
          { id: "ticketHello", text: "Купівля квитка" },
          { id: "exitButton", text: "Вийти" },
          { id: "amount", text: "Ваш бюджет" },
          { id: "updateButton", text: "Оновити" },
          { id: "addButton", text: "Квиток" },
          { id: "saveButton", text: "Підтвердити" },
          { id: "roleBackButton", text: "Відхилити" },
          { id: "nameEx", text: "Оберіть виставку" },
          { id: "addButtonTicket", text: "Придбати квиток" },
          { id: "nameExhibition", text: "Назва виставки" },
          { id: "ticketPrice", text: "Ціна білету" },
          { id: "dateStart", text: "Дата початку" },
          { id: "dateEnd", text: "Дата кінця" },
          { id: "boughtTicket", text: "Придбані квитки" },
        ],
      };

function changeLanguage(lan) {
    words[lan].forEach((item) => {
        document.getElementById(item.id).innerText = item.text;
    });
}

function addFunc() {
   display=document.getElementById("addDiv").style.display;
   if(display=='none') {
        document.getElementById("addDiv").style.display='block';
        document.getElementById("SelectExhibition").required=true;
   }else {
        document.getElementById("addDiv").style.display='none';
        document.getElementById("SelectExhibition").required=false;
   }
};