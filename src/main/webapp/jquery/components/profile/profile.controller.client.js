(function(){

    var $usernameFld;
    var $emailFld,$roleFld,$phoneFld,$dateOfBirthFld;
    var $updateBtn, $logoutBtn;
    var currentUser;
    var userService = new AdminUserServiceClient();
    $(init);

    function init() {
        $usernameFld = $("#usernameFld");
        $emailFld = $("#emailFld");
        $roleFld = $("#roleFld");
        $phoneFld = $("#phoneFld");
        $dateOfBirthFld = $("#dateOfBirthFld");
        $updateBtn = $("#updateBtn").click(updateUser);
        $logoutBtn = $("#logoutBtn").click(logout);
        var username = getUrlVars()["username"];
        findUserByUsername(username);
    }

    function updateUser() {
        var user = new User();
        user.setUsername(currentUser.username);
        user.setPassword(currentUser.password);
        user.setFirstName(currentUser.firstName);
        user.setLastName(currentUser.lastName);
        user.setRole($roleFld.val());
        user.setPhone($phoneFld.val());
        user.setEmail($emailFld.val());
        user.setDateOfBirth($dateOfBirthFld.val());

        userService.updateProfile(user).then(showAlert);
    }

    function showAlert(response) {
        if(response === null) {
            alert('unable to update.');
        } else {
            $('#successAlert').show('fade');
            setTimeout(function() {
                $('#successAlert').hide('fade');
            }, 10000);
        }
    }


    function findUserByUsername(username) {
        userService
            .findUserByUsername(username)
            .then(renderUser);
    }

    function renderUser(user) {
        currentUser = user;
        $usernameFld.val(user.username);
        $emailFld.val(user.email);
        $roleFld.val(user.role);
        $phoneFld.val(user.phone);
        var dateOfBirth = user.dateOfBirth;
        if(dateOfBirth) {
            dateOfBirth = dateOfBirth.substring(0,dateOfBirth.indexOf("T"));
        }
        $dateOfBirthFld.val(dateOfBirth);
    }

    function getUrlVars()
    {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for(var i = 0; i < hashes.length; i++)
        {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    }

    function logout() {
        window.location.href = '/jquery/components/login/login.template.client.html';
    }
})();