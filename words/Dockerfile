FROM ubuntu
RUN apt-get update
RUN apt-get install maven openjdk-8-jdk -y
COPY . .
RUN mvn verify
FROM ubuntu
RUN apt-get update
RUN apt-get install openjdk-8-jre -y
COPY --from=0 target .
CMD ["java", "-Xmx8m", "-Xms8m", "-jar", "words.jar"]

