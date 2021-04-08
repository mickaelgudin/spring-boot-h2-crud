# spring-boot-h2-crud

Back end for the project archi web, this projet is configure with CI CD, automatic deployment is done to heroku when merging or pushing in the main branch.

Please refer to this repo for the front end made with VueJs : https://github.com/jdoujet/archiweb/tree/julien-config-leaflet

**Features**

Descriptions of all routes of our api : 

Journeys | Stations
------------ | -------------
get journeys between two stations | get all stations
get tendancy of prices for journeys between two stations | add a new station
get accessibles stations from a station, with the average price | update an existing station
//  | delete an existing station

see the swagger interface for more detail : https://projet-web-trains.herokuapp.com/

**this repo also have the following features :**
- Check input of user requesting our api (custom errors if a given station don't exist)
- Multilanguage custom errors (especially useful for the front end part)

**Stack**
- Framework Spring boot
- H2 Database
- JPA (hibernate implementation)

**Versions**
- JDK 1.8
- Apache Maven 3.6.3
