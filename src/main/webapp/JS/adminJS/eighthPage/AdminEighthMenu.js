var words = {
        en: [
           { id: "exhibitionHello", text: "Statistics of visits users" },
           { id: "exitButton", text: "Exit" },
           { id: "updateButton", text: "Update" },
           { id: "nameExhibition", text: "Name of exhibition" },
           { id: "ticketExhibition", text: "Number of tickets" },
        ],

        ua: [
          { id: "exhibitionHello", text: "Статистика відвідувань" },
          { id: "exitButton", text: "Вийти" },
          { id: "updateButton", text: "Оновити" },
          { id: "nameExhibition", text: "Назва виставки" },
          { id: "ticketExhibition", text: "Кількість білетів" },
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