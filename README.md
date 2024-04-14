# Student Registration Service

Student Registration Service

## Jasypt

Encrypt credentials that are wrapped with `DEC(value)` in application.properties file.

```sh
mvn jasypt:encrypt -Djasypt.encryptor.password=encryptionPassword
```

Decrypt credentials that are wrapped with `ENC(value)` in application.properties file.

```shell
mvn jasypt:decrypt -Djasypt.encryptor.password=encryptionPassword
```

Run the Spring Boot application by passing the private key password as VM arguments in the command prompt like this:

```shell
java -Djasypt.encryptor.password=encryptionPassword -jar target/student-registration-service-0.0.1-SNAPSHOT.jar
```

Can provide the encryption password in application's configuration file (`application.properties` or `application.yml`):

```shell
jasypt.encryptor.password=encryptionPassword
```

## Swagger

- http://localhost:8080/v2/api-docs
- http://localhost:8080/swagger-ui/
