FROM openjdk
RUN mkdir -p /xmls
COPY ./src/main/resources/static/files /xmls
ADD ./target/ms-spring-batch-0.0.1-SNAPSHOT.jar msspringbatch.jar
ENTRYPOINT ["java", "-jar", "msspringbatch.jar"]