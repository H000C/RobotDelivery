(function() {

    /**
     * Initialize
     */
    function init() {
        $('login-btn').addEventListener('click', login)
        onSessionInvalid();
    }

    /**
     * Session
     */

    function onSessionInvalid() {
        var loginForm = $('login-form');
        var logout = $('logout-link');
        showElement(loginForm);
        hideElement(logout);
    }

    function onSessionValid(result) {
        var username = result.username;
        var welcomeMsg = $('welcome-msg');
        var registerLogin = $('register-login');
        var logout = $('logout-link');
        welcomeMsg.innerHTML = 'Welcome, ' + username;
        showElement(welcomeMsg);
        showElement(logout);
        // hideElement(registerLogin);
    }


    // -----------------------------------
    // Login
    // -----------------------------------

    function login() {
        var username = $('username').value;
        var password = $('password').value;
        password = md5(username + md5(password));

        // The request parameters
        var url = '../login';
        var req = JSON.stringify({
            username : username,
            password : password,
        })

        ajax('POST', url, req,
            // successful callback
            function(res) {
                var result = JSON.parse(res);
                sessionStorage.setItem("username", result.username);
                onSessionValid(result);
                console.log('in login function');
                window.location.href = '../';
            },

            // error
            function(message) {
                showLoginError(message);
            });
    }


    function showLoginError(message) {
        $('login-error').innerHTML = message;
    }

    function clearloginError() {
        $('signup-error').innerHTML = '';
    }

    // -----------------------------------
    // Helper Functions
    // -----------------------------------



    /**
     * A helper function that creates a DOM element <tag options...>
     *
     * @param tag
     * @param options
     * @returns
     */
    function $(tag, options) {
        if (!options) {
            return document.getElementById(tag);
        }

        var element = document.createElement(tag);

        for (var option in options) {
            if (options.hasOwnProperty(option)) {
                element[option] = options[option];
            }
        }

        return element;
    }

    function hideElement(element) {
        element.style.display = 'none';
    }

    function showElement(element, style) {
        var displayStyle = style ? style : 'block';
        element.style.display = displayStyle;
    }

    /**
     * AJAX helper
     *
     * @param method -
     *            GET|POST|PUT|DELETE
     * @param url -
     *            API end point
     * @param callback -
     *            This the successful callback
     * @param errorHandler -
     *            This is the failed callback
     */
    function ajax(method, url, data, callback, errorHandler) {
        var xhr = new XMLHttpRequest();

        xhr.open(method, url, true);

        xhr.onload = function() {
            if (xhr.status === 200) {

                callback(xhr.responseText);
            } else if (xhr.status === 403) {
                onSessionInvalid();
            } else {
                errorHandler(xhr.responseText);
            }

        };

        xhr.onerror = function() {
            console.error("The request couldn't be completed.");
            errorHandler();
        };

        if (data === null) {
            xhr.send();
        } else {
            xhr.setRequestHeader("Content-Type",
                "application/json;charset=utf-8");
            xhr.send(data);
        }
    }

    init();

})()