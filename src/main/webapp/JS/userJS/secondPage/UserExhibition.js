var words = {
        en: [
          { id: "exHello", text: "List of exhibitions" },
          { id: "exitButton", text: "Exit" },
          { id: "updateButton", text: "Update" },
          { id: "nameExhibition", text: "Name of exhibition" },
          { id: "description", text: "Description" },
          { id: "art", text: "Exposures" },
          { id: "price", text: "Price" },
          { id: "dateStart", text: "Start date" },
          { id: "dateEnd", text: "End date" },
          { id: "hall", text: "Halls" },
          { id: "author", text: "Authors of expositions" },
          { id: "genre", text: "Genres" },
          { id: "address", text: "Addresses" },
        ],

        ua: [
          { id: "exHello", text: "Список виставок" },
          { id: "exitButton", text: "Вийти" },
          { id: "updateButton", text: "Оновити" },
          { id: "nameExhibition", text: "Назва виставки" },
          { id: "description", text: "Опис" },
          { id: "art", text: "Експозиції" },
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
        document.getElementById(item.id).innerText = item.text;
    });
}

