# Use official OpenJDK 22 image
FROM eclipse-temurin:22-jdk

# Create a folder inside the Docker container
WORKDIR /app

# Copy all your project files into that folder
COPY . .

# Give permission to run Maven wrapper
RUN chmod +x mvnw

# Build your Spring Boot JAR file
RUN ./mvnw clean package -DskipTests

# Expose Spring Boot's default port
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "target/Contest_Tracker-0.0.1-SNAPSHOT.jar"]
