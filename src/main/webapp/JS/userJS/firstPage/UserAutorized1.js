
function updateFunc() {
    if(document.getElementById("addDiv").style.display=='none') {
        display=document.getElementById("updateDiv").style.display;
        if(display=='none') {
            document.getElementById("updateDiv").style.display='block';
            document.getElementById("UpdateSort").required=true;
        }else {
            document.getElementById("updateDiv").style.display='none';
            document.getElementById("UpdateSort").required=false;
        }
    }
    else {
        addFunc();
        updateFunc();
    }
};

function addFunc() {
   if(document.getElementById("updateDiv").style.display=='none') {
       display=document.getElementById("addDiv").style.display;
       if(display=='none') {
            document.getElementById("addDiv").style.display='block';
            document.getElementById("SelectExhibition").required=true;
       }else {
            document.getElementById("addDiv").style.display='none';
            document.getElementById("SelectExhibition").required=false;
       }
   }
   else {
        updateFunc();
        addFunc();
   }
};