# rest-api-spring-docs
demo worked with spring boot + spring rest docs

The project haven't libs to support other media type, it just to support `application/json`


One of the requirements is that the service return `415 - Unsupported Media Type` for both headers accept and content-type.
Fot that Spring method `handleHttpMediaTypeNotAcceptable` had to be overrided.

## Some examples
### Add new Note
POST http://localhost:8080/notes
Headers: 
Accept: application/json
Content-Type: application/json
RequestBody:
```json
{
"title":"my title",
"body":"my body"
}
```
Response Body:
```json
{
    "id": 1,
    "title": "my title",
    "body": "my body",
    "tags": []
}
```
###  Get Single Note
GET http://localhost:8080/notes/1
Headers:
Accept: application/json
Response Body:
```json
{
    "title": "my title",
    "body": "my body"
}
```

#### Put note id does not exists
GET http://localhost:8080/notes/5
Headers:
Accept: application/json

TIP: return my responseBean with a custom exception

Response Body:
```json
{
    "errorCode": "ERROR_BUSINESS_EXCEPTION",
    "httpStatus": "404",
    "errorMessage": "Not Found",
    "rootErrorMessage": "Not Found"
}
```
#### Error scenario

GET http://localhost:8080/notes/1
Headers:
Accept: **application/xml**

FOUND:

406 - Not acceptable

EXPECTED:
```json
{
    "errorCode": "ERROR_CLIENT_EXCEPTION",
    "httpStatus": "415",
    "errorMessage": "Unsupported Media Type. Please, use 'accept: application/json'",
    "rootErrorMessage": "Unsupported Media Type"
}
