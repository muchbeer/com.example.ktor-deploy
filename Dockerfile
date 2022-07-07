FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/com.example.ktor-deploy-0.0.1-all.jar /app/com.example.ktor-deploy-0.0.1-all.jar
ENTRYPOINT ["java","-jar","/app/com.example.ktor-deploy-0.0.1-all.jar"]