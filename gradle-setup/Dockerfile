# Amazon JDK image https://hub.docker.com/_/amazoncorretto
FROM amazoncorretto:23

WORKDIR /app

# Miniconda needs xargs to be installed
RUN yum -y install findutils

COPY requirements.txt .
COPY *.py .
COPY demo demo
COPY Makefile .
COPY pyproject.toml .
COPY gradle-setup/gradle gradle-setup/gradle/
COPY gradle-setup/build.gradle.kts gradle-setup/
COPY gradle-setup/settings.gradle.kts gradle-setup/
COPY gradle-setup/gradle.properties gradle-setup/
COPY gradle-setup/gradlew gradle-setup/

WORKDIR /app/gradle-setup

ENTRYPOINT ["./gradlew"]
CMD ["help"]
