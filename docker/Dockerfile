FROM amazoncorretto:21

WORKDIR /petlog

COPY build/libs/*SNAPSHOT.jar /petlog.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/petlog.jar"]