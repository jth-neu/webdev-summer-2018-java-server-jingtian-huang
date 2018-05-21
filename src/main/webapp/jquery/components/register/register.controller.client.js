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
        var usernameVal = $usernameFld.val();
        var passwordVal = $passwordFld.val();
        var verifyPasswordVal = $verifyPasswordFld.val();
        if(!usernameVal) {
            alert("The username can't be empty.");
        } else if(passwordVal !== verifyPasswordVal) {
            alert("The verify password doesn't match.");
        } else {
            var user = new User();
            user.setUsername(usernameVal);
            user.setPassword(passwordVal);
            console.log(user);
            userService.register(user)
                .then(function(response) {
                    if(response.status === 200) {
                        alert("Success!");
                    } else if(response.status === 500) {
                        alert("Username already exist.");
                    } else {
                        alert("Something is wrong, but try again later");
                    }
                });
        }
    }
})();
