FROM gradle:4.9.0-jdk10 as builder
WORKDIR /home/gradle
COPY . /home/gradle
CMD gradle &&  gradle bootJar


FROM openjdk:11-jre-slim
COPY --from=builder /home/gradle/build/libs/settings*.jar ./
ENV profile production
CMD java  -Dspring.profiles.active=${profile} -jar settings-0.0.1-SNAPSHOT.jar
