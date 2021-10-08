# Pull java image
FROM openjdk:8-jdk-alpine
# Create directory
RUN mkdir -p /home/alexandr/app/
# Move to directory
WORKDIR /home/alexandr/app/
# Copy rest project (.) to /home/alexandr/app/
COPY . /home/alexandr/app/
# Share ports
EXPOSE 8888 8080
# Run command
ENTRYPOINT ["java", "-jar", "target/MyPostgresREST-1.0.0.jar"]