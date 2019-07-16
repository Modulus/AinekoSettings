FROM gradle:4.10.2-jdk11-slim as builder
WORKDIR /home/gradle
COPY . /home/gradle
CMD  gradle && gradle bootJar


FROM gradle:4.10.2-jdk11-slim
COPY --from=builder /home/gradle/build/libs/settings*.jar ./settings.jar
ENV profile production
CMD java  -Dspring.profiles.active=${profile} -jar settings.jar
EXPOSE 8080
