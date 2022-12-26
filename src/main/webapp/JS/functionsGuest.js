function sortFunc() {
   display=document.getElementById("sortDiv").style.display;
   if(display=='none') {
        document.getElementById("sortDiv").style.display='block';
        document.getElementById("SelectSort").required=true;
   }else {
        document.getElementById("sortDiv").style.display='none';
        document.getElementById("SelectSort").required=false;
   }
};