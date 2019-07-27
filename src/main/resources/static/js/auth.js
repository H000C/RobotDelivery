(function() {

    /**
     * Initialize
     */
    function init() {
        // $('login-btn').addEventListener('click', login);
        $('logout-link').addEventListener('click', logout);
        validateSession();
    }

    /**
     * Session
     */

    function onSessionInvalid() {
        var logout = $('logout-link');
        var welcomeMsg = $('welcome-msg');
        var registerLogin = $('register-login');
        sessionStorage.removeItem('username');
        showElement(registerLogin);
        hideElement(welcomeMsg);
        hideElement(logout);

    }

    function onSessionValid() {
        var username = sessionStorage.getItem('username');
        var welcomeMsg = $('welcome-msg');
        var registerLogin = $('register-login');
        var logout = $('logout-link');
        welcomeMsg.innerHTML = 'Welcome, ' + username;
        showElement(welcomeMsg);
        showElement(logout);
        hideElement(registerLogin);
    }


    // -----------------------------------
    // Login
    // -----------------------------------

    function validateSession() {

        // The request parameters
        var url = '../verifyLogin';
        var req = JSON.stringify({
            username : sessionStorage.getItem('username')
        });

        ajax('POST', url, req,
            // successful callback
            function(res) {
                onSessionValid();
            },

            // error
            function(message) {
                showLoginError(message);
            });
    }

    function showLoginError(message) {
        // $('login-error').innerHTML = message;
    }

    function clearloginError() {
        $('signup-error').innerHTML = '';
    }

    // -----------------------------------
    // Logout
    // -----------------------------------

    function logout() {
        var url = '../logout';
        var req = JSON.stringify({});

        ajax('POST', url, req,
            //sucessful callback
            function (res) {
                onSessionInvalid();
                window.location.href = '../';
            },
            function (message) {
                showLogoutError(message);
            });
    }

    function showLogoutError(message) {
        // $('login-error').innerHTML = message;
    }

    function clearlogoutError() {
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
                onSessionInvalid();
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