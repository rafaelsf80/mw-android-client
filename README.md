# Mobility workshop Android application #
Android application for cases management that uses *Cloud Endpoints* and *Cloud Messaging*. 
Use case: case management application for a field engineer

## Usage

1) Launching the app automatically populates a list of cases coming from the backend (Cloud Endpoint)

2) Select a case from the list

3) Modify the closure date (only field which can be modified) and click on Update Case (this calls a Cloud Endpoint)

## Client libraries

For endpoints reference, refer to 
(https://apis-explorer.appspot.com/apis-explorer/?base=https://semobiletraining.appspot.com/_ah/api#p/)

Client libraries must be used for proper compilation. The following libraries must be included:

google-api-client-1.17.0-rc.jar

google-api-client-android-1.17.0-rc.jar

google-http-client-1.17.0-rc.jar

google-http-client-android-1.17.0-rc.jar

google-http-client-gson-1.17.0-rc.jar

google-oauth-client-1.17.0-rc.jar

gson-2.1.jar

jsr305-1.3.9.jar

volley.jar

gcm.jar

google-play-services.jar


## Android release

This apk uses Google Cloud Endpoints and Google Cloud Messaging technologies
Compiled with SDK 4.4 and tested on Nexus4, Nexus5, Nexus7


## Screenshots

Pending