# Counselling data service
Serves historical yearly counselling data of Indian engineering colleges based on various filters provided.

### Build project
```shell
gradlew clean build -PprojectVersion=<PROJECT_VERSION>
```

### Start the service
- LOCAL
```shell
java -Dspring.profiles.active=local -jar <jarname>
```
- PROD
```shell
java -Dspring.profiles.active=prod -Dlogging.file.name=<LOG_FILE_LOCATION>/counselling-data-service.log -jar <jarname>
```