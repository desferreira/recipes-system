FROM maven:3.6.3-jdk-11 as builder

ENV APP_DIR /build/

WORKDIR $APP_DIR

COPY pom.xml $APP_DIR

RUN echo "--> Download dependecies" && \
    mvn -B dependency:resolve dependency:resolve-plugins

COPY . $APP_DIR

RUN echo "--> Build application" && \
    mvn package -DskipTests
    
# App image

FROM openjdk:13-alpine
LABEL maintainer="Diego"
ENV TZ Brazil/East
RUN mkdir /opt/app
RUN apk add --no-cache tzdata \
   && ln -sf /usr/share/zoneinfo/Brazil/East /etc/localtime \
   && echo "Brazil/East" > /etc/timezone \
   && rm -rf /var/cache/apk/* \
   && rm -rf /tmp/* \
   && rm -rf /var/tmp/*
   
COPY --from=builder /build/target/recipes.jar /opt/app
CMD ["java", "-jar", "/opt/app/recipes.jar"]
