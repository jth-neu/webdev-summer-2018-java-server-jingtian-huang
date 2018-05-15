(function () {

    jQuery(main);

    function main() {
        var h1 = jQuery('#title');
        h1.css('color','red');
        h1.html('User Administration!');

        var users = [
            {username:'bob'},
            {username:'charlie'}
        ];

        var tbody = $('tbody');
        var tr = $('.template');

        for(var i = 0; i < users.length; i++) {
            var user = users[i];
            console.log(user);
            var clone = tr.clone();
            clone.find('.username').html(user.username);
            tbody.append(clone);
        }
    }
})();