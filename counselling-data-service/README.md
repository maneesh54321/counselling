# Counselling data service
Serves historical yearly counselling data of Indian engineering colleges based on various filters provided.

### Prepare database
Run following commands after installing postgres. Refer to https://www.digitalocean.com/community/tutorials/how-to-install-and-use-postgresql-on-ubuntu-20-04 for initial setup.
- Create the database
```shell
createdb counselling-db
```
- Execute the following commands to create tables and insert data into the table using the insert scripts under 'src/main/data/DDL&DMLs'
```shell
psql -U <POSTGRES_USERNAME> -d counselling-db -a -f '0-1.drop-tables.sql'
psql -U <POSTGRES_USERNAME> -d counselling-db -a -f '0-2.create_tables.sql'
psql -U <POSTGRES_USERNAME> -d counselling-db -a -f '0-3create_sequence.sql'
psql -U <POSTGRES_USERNAME> -d counselling-db -a -f '1.inserts-metadata.sql'
psql -U <POSTGRES_USERNAME> -d counselling-db -a -f '2.inserts-placements.sql'
psql -U <POSTGRES_USERNAME> -d counselling-db -a -f '3.inserts-rank.sql'
psql -U <POSTGRES_USERNAME> -d counselling-db -a -f '4.inserts-city&distance.sql'
psql -U <POSTGRES_USERNAME> -d counselling-db -a -f '5.inserts-summary.sql'
psql -U <POSTGRES_USERNAME> -d counselling-db -a -f 'inserts - partial.sql'
```

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
java -Dspring.profiles.active=prod -Dcors.allowedOrigins=<UI requests allowed origins> -Dlogging.file.name=<LOG_FILE_LOCATION>/counselling-data-service.log -jar <jarname>

e.g. "java -Dspring.profiles.active=prod -Dcors.allowedOrigins=http://localhost:4200 -Dlogging.file.name=/var/tmp/counselling-data-service.log -jar lib/counselling-data-service-1.0.0.jar" 
```
Note:
 - cors.allowedOrigins can be set like -Dcors.allowedOrigins=http://localhost:4200,http://xx.xx.xx.xx:xxxx for multiple allowedOrigins
 - If not passed, the service will allow all origins