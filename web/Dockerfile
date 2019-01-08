FROM ubuntu
RUN apt-get update
RUN apt-get install golang-go -y
COPY . .
RUN go build dispatcher.go
FROM ubuntu
COPY . .
COPY --from=0 ./dispatcher ./dispatcher
CMD ["./dispatcher"]

