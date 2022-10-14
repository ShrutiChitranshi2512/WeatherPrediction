FROM openjdk:17
EXPOSE 8070
ADD target/weather-api.jar weather-api.jar
ENTRYPOINT ["java", "-jar", "/weather-api.jar"]