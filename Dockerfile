FROM gradle:jdk11-alpine as build

WORKDIR /app

COPY --chown=gradle:gradle  . /app
RUN chmod +x gradlew
RUN chmod +x gradle

RUN gradle clean build --no-daemon

FROM openjdk:11-jre-slim
RUN apt-get update && apt-get install -y fontconfig libfreetype6

RUN apt-get update && apt-get -y install tzdata
ENV TZ="Asia/Ho_Chi_Minh"

EXPOSE 8081

RUN mkdir /app

COPY --from=build /src/build/libs/*.jar /app/trinh-vo-van-api.jar

# Here we can set config for Java, default is 64mb
ENTRYPOINT ["java","-jar","/app/trinh-vo-van-api.jar"]
