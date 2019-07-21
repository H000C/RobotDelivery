(function() {

    /**
     * Initialize
     */
    function init() {
        $('login-btn').addEventListener('click', login)

    }

    /**
     * Session
     */

    function onSessionInvalid() {
        var loginForm = $('login-form');
        showElement(loginForm);
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
            function() {
                window.location.href = '../index.html';//need specify
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
                callback();
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