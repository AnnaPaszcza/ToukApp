curl -X GET http://localhost:8080/api/screening/"2022-03-01"/"10:00:00"
curl -X GET http://localhost:8080/api/screening/1
curl -X POST http://localhost:8080/api/makeReservation -H "Content-type: application/json" -d "{\"name\": \"John\", \"surname\": \"Smith\", \"screeningId\": 1, \"chosenSeatIds\": [{ \"key\": \"16\", \"value\": \"1\"}, { \"key\": \"16\", \"value\": \"1\"}, { \"key\": \"16\", \"value\": \"1\"}]}"