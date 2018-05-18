(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
    var $roleFld, $emailFld, $dateOfBirthFld, $phoneFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();

    $(main);

    function main() {
        $tbody = $('.wbdv-tbody');
        $userRowTemplate = $('.wbdv-template').clone().removeClass('wbdv-hidden');
        $createBtn = $('.wbdv-create');
        $createBtn.click(createUser);

        findAllUsers();
    }

    function createUser() {
        console.log('createUser');

        $usernameFld = $('#usernameFld').val();
        $passwordFld = $('#passwordFld').val();
        $firstNameFld = $('#firstNameFld').val();
        $lastNameFld = $('#lastNameFld').val();
        $roleFld = $('#roleFld').val();
        $emailFld = $('#emailFld').val();
        $phoneFld = $('#phoneFld').val();
        $dateOfBirthFld = $('#dateOfBirthFld').val();

        var user = new User(
            $usernameFld,$passwordFld,$firstNameFld,$lastNameFld,
            $emailFld,$phoneFld,$dateOfBirthFld,$roleFld);

        userService
            .createUser(user)
            .then(findAllUsers);
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    function deleteUser(event) {
        $removeBtn = $(event.currentTarget);

        var userId = $removeBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function selectUser() {

    }

    function updateUser(event) {
        $editBtn = $(event.currentTarget);

        var userId = $editBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        $usernameFld = $('#usernameFld').val();
        $passwordFld = $('#passwordFld').val();
        $firstNameFld = $('#firstNameFld').val();
        $lastNameFld = $('#lastNameFld').val();
        $roleFld = $('#roleFld').val();
        $emailFld = $('#emailFld').val();
        $phoneFld = $('#phoneFld').val();
        $dateOfBirthFld = $('#dateOfBirthFld').val();

        var updatedUser = new User(
            $usernameFld,$passwordFld,$firstNameFld,$lastNameFld,
            $emailFld,$phoneFld,$dateOfBirthFld,$roleFld);

        userService.updateUser(userId, updatedUser)
            .then(renderUsers);
    }

    function renderUser(user) {
        var clone = $userRowTemplate.clone();

        clone.attr('id', user.id);

        clone.find('.wbdv-remove').click(deleteUser);
        clone.find('.wbdv-edit').click(updateUser);

        clone.find('.wbdv-username')
            .html(user.username);
        clone.find('.wbdv-first-name')
            .html(user.firstName);
        clone.find('.wbdv-last-name')
            .html(user.lastName);
        clone.find('.wbdv-role')
            .html(user.role);
        $tbody.append(clone);
    }

    function renderUsers(users) {
        $tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            renderUser(user)
        }
    }
})();
