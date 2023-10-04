FROM eclipse-temurin:17-jdk
VOLUME /tmp
COPY ./build/libs/map-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
