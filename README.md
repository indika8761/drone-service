# drone-service
Run run.sh or run.bat file inside the drone-service folder.

Note : need to install docker in your host run the application. 

Assumptions : 
- The Drone can be loaded multiple medication items at a time.
- The Drone take 2 seconds to load the 5gr of medication.
- The Drone take 0.01% battery capacity to load the 5gr of medication.
- The Drone can load if the drone states are IDLE, LOADED, DELIVERED, RETURNING

######  Registering a drone API : /drone/register

curl --location --request POST 'http://localhost:8081/drone/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "serialNumber":"qazwsx3edc4rfv",
    "model":"LIGHTWEIGHT",
    "weightLimit":500,
    "batteryCapacity":100,
    "state":"IDLE"
}'

######  Loading a drone with medication items API : drone/{droneid}/load

curl --location --request POST 'http://localhost:8081/drone/1/load' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
    "name":"123med1",
    "weight":250,
    "code":"ABC_091",
    "image":"123med1.jpg"

},
{
    "name":"123med2",
    "weight":250,
    "code":"ABC_092",
    "image":"123med2.jpg"

}
]'

######  Checking loaded medication items for a given drone API: drone/1/medication/loaded

curl --location --request GET 'http://localhost:8081/drone/1/medication/loaded'

######  Checking available drones for loading API: drone/load/available

curl --location --request GET 'http://localhost:8081/drone/load/available'

######  Check drone battery level for a given drone API : drone/1/batterylevel

curl --location --request GET 'http://localhost:8081/drone/1/batterylevel'