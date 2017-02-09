# Discount Backend

## How to start

- You will need Maven to easily compile the application and execute the tests.
Please go to maven homepage and install the maven as instructed there. It is a trivial effort.
- When Maven is ready please unzip the source code to a folder then use the console to run
following commands in the folder:
    - `mvn clean package` - this should compile the code, execute the tests and create the
    JAR package which you can run. The package will be located in the `target` subfolder
    in the directory where you currently extracted the project (next to POM file)
    - `java -jar target\discount-1.0.0.jar` - that is how you start the application.
    Pay attention that you are currently located in the folder where i extracted the source code
    (at the same level where the POM file of the project is so i see the target folder directly).

## Configuration notes

- There is a configuration file `application.properties`. You will find it in `src/main/resources` folder. Use this file
to make the following changes in configuration of the application:
    - Logging level - You can set the log level for the application. Example: 
    **logging.level.com.xteam.discount=DEBUG** This way you can see in the console very nice log messages of outgoing
    REST calls and the responses that we are getting.
    - Configure the URLs to connect to the purchases-master application which is used to obtain the data. By default
    i have always started the purchase-master without arguments and it is available at 127.0.0.1:8000
    If you change the URLs pay attention to the placeholders in the urls (i.e. `{username}` and `{productId}`). They
    need to remain exactly in that form as they are parsed in the code.
- If you change the configuration make sure to package the application again and restart it for the configuration
to take effect.

## API Reference

### GET /api/recent_purchases/{username}

- params:
  - username (string)

- response (json):

```
[
   {
      "id": (int),
      "face": (string),
      "price": (int),
      "size": (int),
      "recent": (array)
   }
]
```

- response example
```
[
   {
      "id":704268,
      "face":"ॱ॰⋆(˶ॢ‾᷄﹃‾᷅˵ॢ)",
      "price":1055,
      "size":27,
      "recent":[
         "Monte33",
         "Gino.VonRueden",
         "Carey.Beer5",
         "Hudson.Keebler52"
      ]
   }
]
```

## Notes for reviewer

- I have made the app configurable (application.properties)
- I also made a few simple integration tests
- I taught about package structure. Our domain package is `com.xteam.discount`. Everything which is in subpackage 
`purchase` is a part which works with the external purchase-master interface. I think here there could be a bit 
different structure.
- I taught about using different frameworks for REST and even taught about "plain-java" application without frameworks.
But then i decided that this could be done with the Spring Boot as for the basic things i needed it was enough.
- I taught about error handling. If you are making a request from REST client you will get JSON formatted error
response which is almost a valid solution. Only thing not nice is that it will even include too much technical
info for the consumer. If you are making requests form a web client then you will get default spring error page.
Of course error details are in the log. I did not want to "overcomit" and invest in a better error handling.
- Also the question is how should our app work if the external interface is down (purchase-master). First i implemented
it that it gives us the same message like in the case when the username is not found. But then in the end i decided
not to "catch" the error on our side and rather propagate the error to the consumer as JSON.
Even though the change/implementation is trivial it is a question if the first version would be better. Depends how and
where the application is used.