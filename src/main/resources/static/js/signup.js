(function() {

    /**
     * Initialize
     */
    function init() {
        // Register event listeners
        onSessionInvalid();
        $('signup-btn').addEventListener('click',signup);
    }

    /**
     * Session
     */


    function onSessionValid() {
        var signupForm = $('signup-form');
        var signupSuccess = $('signup-success');
        showElement(signupSuccess);
        hideElement(signupForm);
    }

    function onSessionInvalid() {
        var signupForm = $('signup-form');
        var signupSuccess = $('signup-success');
        showElement(signupForm);
        hideElement(signupSuccess);
    }




    // -----------------------------------
    // Sign up
    // -----------------------------------

    function signup() {
        var username = $('username').value;
        var password = $('password').value;
        var reenterPassword = $('re-enter-password').value;

        if (username === '' || password === '' || reenterPassword === '') {
            showSignupError('Please fill all blanks!');
            return;
        }
        if (password !== reenterPassword) {
            showSignupError('Passwords not match!');
            return;
        }
        password = md5(username + md5(password));

        // The request parameters
        var url = '../signup';
        var req = JSON.stringify({
            username : username,
            password : password,
        })

        console.log('url in js: ');
        ajax('POST', url, req,
            // successful callback
            function(res) {
                onSessionValid();
            },

            // error
            function(message) {
                showSignupError(message);
            });
    }


    function showSignupError(message) {
        $('signup-error').innerHTML = message;
    }

    function clearSignupError() {
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
                errorHandler(xhr.body);
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