FROM alpine/git as clone
ARG url
WORKDIR /app
RUN git clone ${url}

FROM openjdk:8-jre-alpine
ARG artifactid
ARG version
ENV artifact ${artifactid}-${version}.jar
WORKDIR /app
COPY --from=build /app/target/${artifact} /app

FROM maven:3.6.3 as build
ARG project
WORKDIR /app
COPY --from=clone /app/${project} /app
RUN mvn clean verify