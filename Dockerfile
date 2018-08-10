FROM gradle:4.9.0-jdk10 as builder
WORKDIR /home/gradle
COPY . /home/gradle
CMD  gradle && gradle bootJar


FROM openjdk:10.0.2-13-jdk-sid
COPY --from=builder /home/gradle/build/libs/settings*.jar ./
ENV profile production
CMD java  -Dspring.profiles.active=${profile} -jar settings-0.0.1-SNAPSHOT.jar
EXPOSE 8080