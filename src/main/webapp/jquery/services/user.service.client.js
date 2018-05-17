function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.login = login;
    this.url =
        'http://localhost:8080/api/user';
    this.loginUrl =
        'http://localhost:8080/api/user';
    var self = this;

    function login(username, password) {
        return fetch(self.url, {
            method:'post',
            body: JSON.stringify({username:username,password:password}),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function createUser(user) {
         return fetch(self.url, {
            method:'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function updateUser(userId,user) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(function(response) {
            const contentType = response.headers.get("content-type");
            if (contentType && contentType.indexOf("application/json") !== -1) {
                return response.json();
            } else {
                return null;
            }
        });
    }


    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
            return response.json();
        });
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method:'delete'
        });
    }

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function(response) {
                return response.json();
            });
    }

}
