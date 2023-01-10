
function updateFunc() {
    displayAdd=document.getElementById("addDiv").style.display;
    displayDel=document.getElementById("delDiv").style.display;
    if(displayAdd=='none' && displayDel=='none') {
        display=document.getElementById("updateDiv").style.display;
        if(display=='none') {
            document.getElementById("updateDiv").style.display='block';
            document.getElementById("UpdateSort").required=true;
        }else {
            document.getElementById("updateDiv").style.display='none';
            document.getElementById("UpdateSort").required=false;
        }
    } else {
        if(displayAdd!='none') {
            addFunc();
            updateFunc();
        }
        if(displayDel!='none') {
            delFunc();
            updateFunc();
        }
   }
};

function addFunc() {
    displayUpdate=document.getElementById("updateDiv").style.display;
    displayDel=document.getElementById("delDiv").style.display;
    if(displayUpdate=='none' && displayDel=='none') {
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
        if(displayUpdate!='none') {
            updateFunc();
            addFunc();
        }
        if(displayDel!='none') {
            delFunc();
            addFunc();
        }
    }
};
function delFunc() {
    displayUpdate=document.getElementById("updateDiv").style.display;
    displayAdd=document.getElementById("addDiv").style.display;
    if(displayUpdate=='none' && displayAdd=='none') {
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
        if(displayAdd!='none') {
            addFunc();
            delFunc();
        }
        if(displayUpdate!='none') {
            updateFunc();
            delFunc();
        }
    }
};