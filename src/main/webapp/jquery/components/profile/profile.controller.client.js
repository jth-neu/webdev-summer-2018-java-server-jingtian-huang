(function(){

    var $usernameFld;
    var $emailFld,$roleFld,$phoneFld,$dateOfBirthFld;
    var $updateBtn;
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
        findUserById(702);
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

        userService.updateUser(702,user).then(success);
    }

    function success(response) {
        if(response === null) {
            alert('unable to update.');
        } else {
            alert('success');
        }
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
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
})();