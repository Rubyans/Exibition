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
            document.getElementById("InputNameView").required = true;
        }else {
            document.getElementById("addDiv").style.display='none';
            document.getElementById("InputNameView").required = false;
        }
    }else {
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
            document.getElementById("InputDelView").required = true;
        }
        else {
            document.getElementById("delDiv").style.display='none';
            document.getElementById("InputDelView").required = false;
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