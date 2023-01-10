
function updateFunc() {
    displayDel=document.getElementById("delDiv").style.display;
    displayAccess = document.getElementById("accessDiv").style.display;
    displayMoney = document.getElementById("addDivMoney").style.display;

    if(displayDel=='none' && displayAccess=='none' && displayMoney=='none') {
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
        if(displayAccess!='none') {
            accessFunc();
            updateFunc();
        }
        if(displayMoney!='none') {
            addMoney();
            updateFunc();
        }
   }
};

function addMoney() {
    displayDel=document.getElementById("delDiv").style.display;
    displayAccess = document.getElementById("accessDiv").style.display;
    displayUpdate = document.getElementById("updateDiv").style.display;

    if(displayDel=='none' && displayAccess=='none' && displayUpdate=='none') {
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
    }
    else {
        if(displayDel!='none') {
            delFunc();
            addMoney();
        }
        if(displayAccess!='none') {
            accessFunc();
            addMoney();
        }
        if(displayUpdate!='none') {
            updateFunc();
            addMoney();
        }
    }
};

function delFunc() {
    displayMoney = document.getElementById("addDivMoney").style.display;
    displayAccess = document.getElementById("accessDiv").style.display;
    displayUpdate = document.getElementById("updateDiv").style.display;

    if(displayAccess=='none' && displayUpdate=='none' && displayMoney=='none') {
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
        if(displayMoney!='none') {
            addMoney();
            delFunc();
        }

        if(displayAccess!='none') {
            accessFunc();
            delFunc();
        }
        if(displayUpdate!='none') {
            updateFunc();
            delFunc();
        }
    }
};
function accessFunc() {
    displayDel = document.getElementById("delDiv").style.display;
    displayMoney = document.getElementById("addDivMoney").style.display;
    displayUpdate = document.getElementById("updateDiv").style.display;

    if(displayMoney=='none' && displayDel=='none' && displayUpdate=='none') {
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
        if(displayMoney!='none') {
            addMoney();
            accessFunc();
        }
        if(displayDel!='none') {
            delFunc();
            accessFunc();
        }
        if(displayUpdate!='none') {
            updateFunc();
            accessFunc();
        }
    }
};