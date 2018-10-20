FROM gradle:4.10.2-jdk11 as builder
WORKDIR /home/gradle
COPY . /home/gradle
CMD  gradle && gradle bootJar


FROM gradle:4.10.2-jdk11-slim
COPY --from=builder /home/gradle/build/libs/settings*.jar ./
ENV profile production
CMD java  -Dspring.profiles.active=${profile} -jar settings-0.0.1-SNAPSHOT.jar
EXPOSE 8080