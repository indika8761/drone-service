# drone-service
Run run.sh or run.bat file inside the drone-service folder.

Note : need to install docker in your host run the application. 

Registering a drone API : /drone/register

#curl --location --request POST 'http://localhost:8081/drone/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "serialNumber":"qazwsx3edc4rfv",
    "model":"LIGHTWEIGHT",
    "weightLimit":500,
    "batteryCapacity":100,
    "state":"IDLE"
}'

# Loading a drone with medication items API : drone/{droneid}/load

curl --location --request POST 'http://localhost:8081/drone/1/load' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Medication1",
    "weight":500,
    "code":"MED_01",
    "image":"medication1.jpg"

}'

# Checking loaded medication items for a given drone API: drone/1/medication/loaded

curl --location --request GET 'http://localhost:8080/drone/1/medication/loaded'

# Checking available drones for loading API: drone/load/available

curl --location --request GET 'http://localhost:8081/drone/load/available'

# Check drone battery level for a given drone API : drone/1/batterylevel

curl --location --request GET 'http://localhost:8080/drone/1/batterylevel'