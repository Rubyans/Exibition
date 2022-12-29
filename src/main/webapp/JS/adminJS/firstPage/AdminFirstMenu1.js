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
            document.getElementById("InputDateendEx").setAttribute("min", today);

            document.getElementById("InputDatestartEx").setAttribute("value", today);
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