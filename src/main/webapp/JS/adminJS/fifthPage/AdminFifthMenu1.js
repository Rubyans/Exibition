function updateFunc() {
    displayDel=document.getElementById("delDiv").style.display;
    displayAdd=document.getElementById("addDiv").style.display;
    if(displayDel=='none' && displayAdd=='none') {
        display=document.getElementById("updateDiv").style.display;
        if(display=='none') {
            document.getElementById("updateDiv").style.display='block';
            document.getElementById("UpdateSort").required=true;
        }else {
            document.getElementById("updateDiv").style.display='none';
            document.getElementById("UpdateSort").required=false;
        }
    } else {
        if(displayDel!='none') {
            delFunc();
            updateFunc();
        }
        if(displayAdd!='none') {
            addFunc();
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
            document.getElementById("InputNameArt").required = true;
            document.getElementById("InputCreationArt").required = true;
            document.getElementById("InputPriceArt").required = true;
            document.getElementById("SelectAuthor").required = true;
            document.getElementById("SelectView").required = true;

        }else {
            document.getElementById("addDiv").style.display='none';
            document.getElementById("InputNameArt").required = false;
            document.getElementById("InputCreationArt").required = false;
            document.getElementById("InputPriceArt").required = false;
            document.getElementById("SelectAuthor").required = false;
            document.getElementById("SelectView").required = false;
        }
    }
    else {
        if(displayDel!='none') {
            delFunc();
            addFunc();
        }
        if(displayUpdate!='none') {
            updateFunc();
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
            document.getElementById("InputDelArt").required = true;
        }
        else {
            document.getElementById("delDiv").style.display='none';
            document.getElementById("InputDelArt").required = false;
        }
    }
    else {
        if(displayUpdate!='none') {
            updateFunc();
            delFunc();
        }
        if(displayAdd!='none') {
            addFunc();
            delFunc();
        }
    }
};