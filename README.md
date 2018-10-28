# Async Twitch New API Wrapper

[![Build Status](https://travis-ci.org/Mirimas/Java-Twitch-Api-Helix.svg?branch=master)](https://travis-ci.org/Mirimas/Java-Twitch-Api-Helix)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e9af5c225b034d139c652b7db7dae6be)](https://app.codacy.com/app/Mirimas/Java-Twitch-Api-New-Wrapper?utm_source=github.com&utm_medium=referral&utm_content=Mirimas/Java-Twitch-Api-New-Wrapper&utm_campaign=Badge_Grade_Dashboard)
[![Download](https://api.bintray.com/packages/mirimas/Mirimas/Java-Twitch-Api-Helix/images/download.svg?version=0.1)](https://github.com/Mirimas/Java-Twitch-Api-Helix/releases)

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

### Basic Example

```java
Twitch twitch = new Twitch();
twitch.setClientId(clientId);
twitch.auth().setAccessToken(accessToken);

twitch.users().get(new UsersResponseHandler() {
    @Override
    public void onSuccess(final Users users) {
        User user = users.getData().get(0);
        System.out.println("User: " + user.getLogin());
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
* [Jackson JSON Processor - Databind](https://github.com/FasterXML/jackson-databind/wiki) ver. 2.9.7
* [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/) ver. 3.7

## Install

This library and the 2 above mentioned dependencies are required.

## Thanks

Inspired by [Java-Twitch-Api-Wrapper](https://github.com/urgrue/Java-Twitch-Api-Wrapper).
