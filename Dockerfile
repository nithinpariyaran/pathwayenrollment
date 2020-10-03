FROM java:8-jdk-alpine

COPY ./target/PathwayEnrollmentProgram-1.0.0.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch PathwayEnrollmentProgram-1.0.0.jar'

ENTRYPOINT ["java","-jar","PathwayEnrollmentProgram-1.0.0.jar"]