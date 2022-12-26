function addFunc() {
   display=document.getElementById("addDiv").style.display;
   if(display=='none') {
        document.getElementById("addDiv").style.display='block';
        document.getElementById("SelectExhibition").required=true;
   }else {
        document.getElementById("addDiv").style.display='none';
        document.getElementById("SelectExhibition").required=false;
   }
};