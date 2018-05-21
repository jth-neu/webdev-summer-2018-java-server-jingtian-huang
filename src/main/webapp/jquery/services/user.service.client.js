function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.register = register;
    this.login = login;
    this.url = 'http://localhost:8080/api/user';
    this.registerUrl = 'http://localhost:8080/api/register';
    this.loginUrl = 'http://localhost:8080/api/login';
    var self = this;

    function login(user,callback) {
        return fetch(self.loginUrl, {
            method:'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function register(user,callback) {
        return fetch(self.registerUrl, {
            method:'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function createUser(user,callback) {
         return fetch(self.url, {
            method:'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function updateUser(userId,user,callback) {
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


    function findAllUsers(callback) {
        return fetch(self.url)
            .then(function (response) {
            return response.json();
        });
    }

    function deleteUser(userId,callback) {
        return fetch(self.url + '/' + userId, {
            method:'delete'
        });
    }

    function findUserById(userId,callback) {
        return fetch(self.url + '/' + userId)
            .then(function(response) {
                return response.json();
            });
    }

}
