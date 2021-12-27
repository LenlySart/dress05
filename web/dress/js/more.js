$(function () {
    findMore();
})
function findMore() {
    $.ajax({
        url: "/more",
        type:'get',
        data: '',
        async: false,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            setCompany(data.data.companys);
        }
    });
}
// 公司信息
function setCompany(data) {
    $("#addres").html("地址:  "+data.address);
    $("#phones").html("电话:  "+data.phone);
    $("#emails").html("邮箱:  "+data.email);
    $("#qq").html("邮箱:  "+data.qq);
}

function submit() {
    let name=$("#visitorName").val();
    let email=$("#visitorEmail").val();
    let message=$("#message").val();
   let data = {name,email,message};
   if (data.name!='' && data.email!=''){
       $.ajax({
           url: "/back/message/add",
           type:'post',
           data: data,
           dataType: 'json',
           success: function (data) {
               console.log(data);
               if (data.msg=="success"){
                   alert("留言成功！");
               }else {
                   alert("留言失败！");
               }
           }
       });
   }
    alert("请输入姓名和者邮箱！");



}

