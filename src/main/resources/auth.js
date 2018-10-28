var thisPage = location.origin + location.pathname; // Path to this file
var failurePage = location.origin + "/authorization-failure.html";

var hashValues = {
    accessToken: null,
    scope: ""
};

/**
 * Twitch Auth API sends the access token and scope through the Hash of the URL. This
 * method will extract them and put them into the hashValues object.
 */
function extractAccessToken() {
    var hash = document.location.hash;
    var params = hash.slice(1).split("&");
    for (var i = 0; i < params.length; i++) {
        var param = params[i].split("=");
        if (param[0] === "access_token") {
            hashValues.accessToken = param[1]; // access token found
        } else if (param[0] === "scope") {
            hashValues.scope = param[1];
        }
    }
}

function getAccessTokenFromHash() {
    extractAccessToken();
    if (hashValues.accessToken != null && hashValues.accessToken.length > 0) {
        // Send request to server for extraction of access token
        document.location.replace(thisPage + "?access_token=" + hashValues.accessToken + "&scope=" + hashValues.scope);
    } else {
        // No access token found
        document.location.replace(failurePage + "?error=access_denied&error_description=no_access_token_found");
    }
}

function process() {
    if (document.location.search.length > 0) {
        // GET param exists
    } else {
        // Attempt to extract access token from hash
        getAccessTokenFromHash();
    }
}

window.onload = process;