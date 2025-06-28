# Use JDK 22 base image
FROM eclipse-temurin:22-jre

# Set work directory
WORKDIR /app

# Copy jar file into image
COPY target/Contest_Tracker-0.0.1-SNAPSHOT.jar app.jar

# Expose your Spring Boot port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
