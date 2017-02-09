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