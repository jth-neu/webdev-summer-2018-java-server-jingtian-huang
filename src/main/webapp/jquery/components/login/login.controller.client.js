(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new AdminUserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#login-username');
        $passwordFld = $('#login-password');
        $loginBtn = $('#login-btn').click(login);
    }

    function login() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        if(!username && !password) {
            alert("Please input the username and password.");
        } else {
            userService.login(username,password);
        }
    }
})();
