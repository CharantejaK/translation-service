# Steps to execute the service.

1. execute command "gradle build" using gradle 6.4 or above
2. run the jar using java 14 (alternatively run with Docker follow steps 3 and 4)
3. docker build --tag translation-service:1.0 .
4. docker run --publish 9091:9091 --detach --name bb translation-service:1.0

# Access the service at.
1. SaveTransaltion 
http://localhost:9091/savetranslation (HTTP Post Request )

    eg request :
    {
      "language":"en",
      "translationKey": "buy-chips",
      "translationValue": "Buy Chips"
    }
2. Get Translation 
http://localhost:9091/gettranslation?language=en&translationKey=buy-chips  (Get Request)

