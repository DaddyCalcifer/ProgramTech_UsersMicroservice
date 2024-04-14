FROM openjdk:21-oracle
ARG JAR_FILE=out/artifacts/users_jar/users.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
