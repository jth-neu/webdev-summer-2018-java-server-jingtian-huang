(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new AdminUserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $loginBtn = $('#loginBtn').click(login);
    }

    function login() {
        var usernameVal = $usernameFld.val();
        var passwordVal = $passwordFld.val();
        if(!usernameVal) {
            alert("The username can't be empty.");
        } else if(!passwordVal) {
            alert("The password can't be empty");
        } else {
            var user = new User();
            user.setUsername(usernameVal);
            user.setPassword(passwordVal);
            console.log(user);
            userService.login(user)
                .then(function(response) {
                    if(response.status === 200) {
                        window.location.href =
                            '/jquery/components/profile' +
                            '/profile.template.client.html?username=' + usernameVal;
                    } else if(response.status === 500) {
                        alert("No matching user found.");
                    } else {
                        alert("Something is wrong, but try again later");
                    }
                });
        }
    }
})();
