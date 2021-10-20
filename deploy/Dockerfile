FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
COPY settings.xml .

RUN --mount=type=cache,target=/root/.m2 ./mvnw --settings settings.xml install -DskipTests

FROM openjdk:8-jdk-alpine
ARG DS_URL
ARG DB_USER_NAME
ARG DB_PWD
ARG MAIN_DS_URL

ENV DS_URL=$DS_URL
ENV DB_USER_NAME=$DB_USER_NAME
ENV DB_PWD=$DB_PWD
ENV MAIN_DS_URL=$MAIN_DS_URL

RUN addgroup -S starcoin && adduser -S starcoin -G starcoin
VOLUME /tmp
USER starcoin
ARG DEPENDENCY=/workspace/app/target
COPY --from=build ${DEPENDENCY}/swap-stat-api-1.0-SNAPSHOT.jar /app/lib/swap-stat-api-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-noverify","-XX:TieredStopAtLevel=1","-jar","app/lib/swap-stat-api-1.0-SNAPSHOT.jar","-Dspring.main.lazy-initialization=true", \
            "DS_URL=$DS_URL", \
            "DB_USER_NAME=$DB_USER_NAME", \
            "DB_PWD=$DB_PWD", \
            "MAIN_DS_URL=$MAIN_DS_URL" \
            ]