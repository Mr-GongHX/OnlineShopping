/**
 * 登录
 * @returns
 */
function login() {
    if (document.getElementById("username").value === '') {
        alert("请输入用户名！");
        return false;
    } else if (document.getElementById("password").value === '') {
        alert("请输入密码！");
        return false;
    } else {
        return document.getElementById("loginForm").submit();
    }
}

