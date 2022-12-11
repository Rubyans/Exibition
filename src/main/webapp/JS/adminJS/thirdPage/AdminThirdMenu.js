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