(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new AdminUserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $verifyPasswordFld = $('#verifyPasswordFld');
        $registerBtn = $('#registerBtn').click(register);
    }

    function register() {
        var passwordVal = $passwordFld.val();
        var verifyPasswordVal = $verifyPasswordFld.val();
        if(passwordVal === verifyPasswordVal) {
            var user = new User();
            user.setUsername($usernameFld.val());
            user.setPassword(passwordVal);
            console.log(user);
            userService.register(user);
        } else {
            alert("The verify password doesn't match.");
        }
    }
})();
