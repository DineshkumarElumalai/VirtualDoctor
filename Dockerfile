FROM openjdk:8
# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8000

# The application's jar file
ARG JAR_FILE=/build/libs/virtualdoctor-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} virtualdoctor-0.0.1-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/virtualdoctor-0.0.1-SNAPSHOT.jar"]