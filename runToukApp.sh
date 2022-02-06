#!/bin/sh

java -jar ./target/ToukApp-0.0.1-SNAPSHOT.jar

curl -X POST -H "Content-type: application/json" -d "{\"name\":\"John\",\"surname\":\"Smith\",\"screeningId\":1,\"chosenSeatIds\":[{\"key\":16,\"value\":1},{\"key\":16,\"value\":1},{\"key\":16,\"value\":1}]}" http://localhost:8080/api/reservation

