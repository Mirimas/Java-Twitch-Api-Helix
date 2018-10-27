# Async Twitch New API Wrapper

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e9af5c225b034d139c652b7db7dae6be)](https://app.codacy.com/app/Mirimas/Java-Twitch-Api-New-Wrapper?utm_source=github.com&utm_medium=referral&utm_content=Mirimas/Java-Twitch-Api-New-Wrapper&utm_campaign=Badge_Grade_Dashboard)

Async Twitch New API Wrapper is a asynchronous java wrapper for interaction with API of the [Twitch New API](https://dev.twitch.tv/docs/api/).

Currently support:
* users
* users follows
* streams
* games

Please feel free to report any issues or contribute code.

## Basics

Using the wrapper is as simple as instantiating the `Twitch` object and then calling the appropriate endpoint functions.

For example, a `GET /streams/featured` request would map to the `twitch.streams().getFeatured()` function; and `GET /users/mirrobot` would map to `twitch.users().get("mirrobot")`.

Responses are handled via callbacks passed via a handler with each function call. This process is outlined in the following examples.

#### Basic Example

```java
Twitch twitch = new Twitch();
twitch.setClientId(clientId);
Authenticator auth = twitch.auth();
auth.setAccessToken(accessToken);

twitch.users().get(new UsersResponseHandler() {
    @Override
    public void onSuccess(final Users users) {
        final User user = users.getData().get(0);
        System.out.println("Success received user: " + user.getLogin());

        // Additionally get user follows count
        twitch.usersFollows().get(user.getId(), 1, new UsersFollowsResponseHandler() {
            @Override
            public void onSuccess(UsersFollows usersFollows) {
                System.out.println("User: " + user.getLogin() + " has total follows:" + usersFollows.getTotal());
            }

            @Override
            public void onFailure(int statusCode, String statusMessage, String errorMessage) {
                /* Twitch API responded with an error message */
            }

            @Override
            public void onFailure(Throwable e) {
                /* Unable to access Twitch, or error parsing the response */
            }
        });
    }

    @Override
    public void onFailure(int statusCode, String statusMessage, String errorMessage) {
        /* Twitch API responded with an error message */
    }

    @Override
    public void onFailure(Throwable e) {
        /* Unable to access Twitch, or error parsing the response */
    }
});
```

### Explicitly Setting Access Token

If you already have an access token, you can explicitly set it. This _**should not**_ be done prior to an application being distributed as the access token is directly linked to a single Twitch account.

```java
twitch.auth().setAccessToken("my-access-token");
```

## Documentation
* The [Twitch API](https://dev.twitch.tv/docs/api/) documentation will best explain the functionality of each endpoint. 

## Dependencies

* [Java Async HTTP Client](https://github.com/urgrue/java-async-http/releases/tag/2.1.2) ver. 2.1.2 // Jar include to the project until it not in the maven repository
* [Jackson JSON Processor - Databind](http://wiki.fasterxml.com/JacksonHome) ver. 2.9.4

## Install

This library and the 2 above mentioned dependencies are required.

## Thanks

Inspired by [Java-Twitch-Api-Wrapper](https://github.com/urgrue/Java-Twitch-Api-Wrapper).