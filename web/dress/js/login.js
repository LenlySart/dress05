// function $(param) {
//     if (arguments[1] == true) {
//         return document.querySelectorAll(param);
//     } else {
//         return document.querySelector(param);
//     }
// }
function ani() {
    $(".popOut").className = "popOut ani";
}
function ClickLogin(){
    $(".popOut").style.display = "block";
    ani();
    $(".popOutBg").style.display = "block";
}
function closeButton() {
    $(".popOut").style.display = "none";
    $(".popOutBg").style.display = "none";
};
$(".popOutBg").onclick = function() {
    $(".popOut").style.display = "none";
    $(".popOutBg").style.display = "none";
};