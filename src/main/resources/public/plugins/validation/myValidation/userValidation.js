/**
 * Created by 贤名 on 2017/4/6.
 * 新增用户与编辑用户表单校验
 */
$("#myForm").validate({
rules:
    {
    username: {
        required:true,
        remote:{
            url:'/user/findUserByName',
            type:'post',
            dataType: "json",
            data:{
                username:function () {
                    return $("#username").val();
                },
                originalName:function(){
                    return $("#originalName").val();
                }
            }
        }
        },
        password: {
            required: true,
            minlength: 5,
            maxlength:8
        }
    },
messages:
    {
        username: {
            required:"请输入名称",
            remote:"此名称已经存在"
        },
        password: {
            required: "请输入密码",
            minlength: jQuery.format("密码不能小于{0}个字符"),
            maxlength:jQuery.format("密码不能大于{0}个字符"),
        }
    },submitHandler : function(form) {
    $("#myForm").submit();
}
});
