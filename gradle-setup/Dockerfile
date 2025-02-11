# Amazon JDK image https://hub.docker.com/_/amazoncorretto
FROM amazoncorretto:23

WORKDIR /app

# Miniconda needs additional packages to be installed
RUN yum -y install findutils cmake gcc gcc-c++ make git
# Get Rust; NOTE: using sh for better compatibility with other base images
RUN curl https://sh.rustup.rs -sSf | sh -s -- -y

# Add .cargo/bin to PATH
ENV PATH="/root/.cargo/bin:${PATH}"

COPY requirements.txt .
COPY *.py .
COPY demo demo
COPY janus janus
COPY Makefile .
COPY pyproject.toml .
COPY gradle-setup/gradle gradle-setup/gradle/
COPY gradle-setup/gradlew gradle-setup/
COPY gradle-setup/gradle.properties gradle-setup/
COPY gradle-setup/settings.gradle.kts gradle-setup/
COPY gradle-setup/build.gradle.kts gradle-setup/

WORKDIR /app/gradle-setup

RUN ls -al /app/janus

ENTRYPOINT ["./gradlew"]
CMD ["help"]
