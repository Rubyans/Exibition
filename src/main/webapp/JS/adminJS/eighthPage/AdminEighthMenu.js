function updateFunc() {
    display=document.getElementById("updateDiv").style.display;
    if(display=='none') {
        document.getElementById("updateDiv").style.display='block';
        document.getElementById("UpdateSort").required=true;
    }else {
        document.getElementById("updateDiv").style.display='none';
        document.getElementById("UpdateSort").required=false;
    }
};

