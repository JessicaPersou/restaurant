FROM ubuntu:latest

WORKDIR /app

LABEL authors="Jessica Persou"

EXPOSE 8080

ENTRYPOINT ["top", "-b"]
