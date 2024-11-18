# Java Unit testing
## Best practice

### Test project overview

The project is the just usual Spring WEB application. So there are few endpoints and services and also it uses database and external API.

Our goal is cover all project functionality with test. Also we will compare some test frameworks to understand when and how to use them.

### Application
So. What this application does?!

*  The application send requests to external REST API (https://api.nasa.gov/api.html) and collect "Astronomy Picture of the Day" (https://apod.nasa.gov/apod/astropix.html).
*  Requests is send by scheduled task.
*  Collected pictures is stored in database.
*  Images is classified as galaxy, nebula, star, planet, spacecraft, Sun, Earth, Moon or marked as 'undefined'.
*  User is able to get images by API

The application uses simple Spring authorization:

*  Unauthorized user is able to get only one picture for current day.
*  Privileged user is able to get pictures history.

### Navigation

Test cases:

*  Simple unit test: ApodConverterTest
*  Internal REST API: ApodEndpointsTest
*  Integration with external REST API: NasaClientTest
*  Testing with stubs: ApodGrabberTest, NasaClientTest
*  Data driven testing: ApodTypeConverterTest, ApodTypeConverterTestNgTest

Frameworks:

*  Spring MockMVC: ApodEndpointsTest
*  WireMock: NasaClientTest
*  TestNG: ApodTypeConverterTestNgTest
*  AssertJ: ApodEndpointsTest
