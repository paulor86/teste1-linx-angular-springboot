FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} /linx-teste.jar
ENTRYPOINT ["java","-jar","/linx-teste.jar"]