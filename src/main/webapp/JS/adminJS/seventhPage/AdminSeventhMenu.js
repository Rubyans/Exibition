function addUser() {
    document.getElementById("addDiv").style.display='none';
    display = document.getElementById("addDivUser").style.display;
    if(display=='none') {
        document.getElementById("addDivUser").style.display='block';
    }
    else {
        document.getElementById("addDivUser").style.display='none';
    }
};
function addMoney() {
    document.getElementById("addDiv").style.display='none';
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
};

function addFunc() {
    displayMoney=document.getElementById("addDivMoney").style.display;
    displayAddUser=document.getElementById("addDivUser").style.display;
    displayDel=document.getElementById("delDiv").style.display;
    if(displayMoney=='block') {
        addMoney();
    }
    if(displayAddUser=='block') {
        addUser();
    }
    if(displayDel=='none') {
        display = document.getElementById("addDiv").style.display;
        if(display=='none') {
            document.getElementById("addDiv").style.display='block';
        }
        else {
            document.getElementById("addDiv").style.display='none';
        }
    }
    else {
        document.getElementById("delDiv").style.display='block';
        delFunc();
        addFunc();
    }
};
function delFunc() {
    displayMoney=document.getElementById("addDivMoney").style.display;
    displayAddUser=document.getElementById("addDivUser").style.display;
    displayAdd= document.getElementById("addDiv").style.display;
    if(displayMoney=='block') {
        addMoney();
    }
    if(displayAddUser=='block') {
        addUser();
    }
    if(displayAdd=='none') {
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
        document.getElementById("addDiv").style.display='block';
        addFunc();
        delFunc();
    }
};