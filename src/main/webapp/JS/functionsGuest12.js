
function updateFunc() {
    if(document.getElementById("sortDiv").style.display=='none') {
        display=document.getElementById("updateDiv").style.display;
        if(display=='none') {
            document.getElementById("updateDiv").style.display='block';
            document.getElementById("UpdateSort").required=true;
        }else {
            document.getElementById("updateDiv").style.display='none';
            document.getElementById("UpdateSort").required=false;
        }
    } else {
        sortFunc();
        updateFunc();
   }
};


function sortFunc() {
    if(document.getElementById("updateDiv").style.display=='none') {
        display=document.getElementById("sortDiv").style.display;
        if(display=='none') {
            document.getElementById("sortDiv").style.display='block';
            document.getElementById("SelectSort").required=true;
        }else {
            document.getElementById("sortDiv").style.display='none';
            document.getElementById("SelectSort").required=false;
        }
    } else {
        updateFunc();
        sortFunc();
    }
};