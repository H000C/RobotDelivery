//station location
const station1 = {lat: 37.73107, lon: -122.40907};
const station2 = {lat: 37.754452,lon: -122.477165};
const station3 = {lat: 37.782928,lon: -122.418996};
//fetch location info for map
//assign location info to variables
var infoLoc = window.sessionStorage.getItem('infoLoc');
var obj = JSON.parse(infoLoc);
console.log(obj);
var startLoc = {lat:obj[0].pickupLatLon[0], lon:obj[0].pickupLatLon[1]};
var endLoc = {lat:obj[0].dropoffLatLon[0], lon:obj[0].dropoffLatLon[1]};
var startStation;
if (obj[0].startStation == 1){
    startStation = station1;
}else if (obj[0].startStation == 2){
    startStation = station2;
}else if (obj[0].startStation == 3){
    startStation = station3;
}else{
    console.log('wrong station lat&lon');
}

//Calculate price for both two shipping method
var drone_dist = obj[1].initialDistance + obj[1].deliveryDistance + obj[1].returnDistance;
var bot_dist = obj[0].initialDistance + obj[0].deliveryDistance + obj[0].returnDistance;
var estPrice2= Math.round((drone_dist * 1)*100)/100;
var estPrice1= Math.round((bot_dist * 0.6)*100)/100;
var estTime2= Math.round(drone_dist/40*10)/10;
var estTime1= Math.round(bot_dist/15*10)/10;
var myMethod = true;
//draw the map
function GetMap(){
    var map = new Microsoft.Maps.Map(document.getElementById('myMap'), {
        credentials: 'AqDMxwwiD2P7-8TCvVyHmOnvrktdAqn7TXFYXDAYuLPhesaPHXin836RNWKAY6a_'
    });

    //set the appearance of map
    map.setView({
        mapTypeId: Microsoft.Maps.MapTypeId.road,
        zoom: 13,
    });

    //decide the type of vehicle. polylines for robots/straight lines for UAVs
    Microsoft.Maps.loadModule('Microsoft.Maps.Directions', function showTrack() {
        var color1 = Microsoft.Maps.Color.fromHex('#ff9e7b');
        var color2 = Microsoft.Maps.Color.fromHex('#62a8fa');

        var directionsManager1 = new Microsoft.Maps.Directions.DirectionsManager(map);
        directionsManager1.setRequestOptions({ routeMode: Microsoft.Maps.Directions.RouteMode.walking });
        if (directionsManager1.getAllWaypoints().length < 2) {
            var startS = new Microsoft.Maps.Directions.Waypoint({
                location: new Microsoft.Maps.Location(startStation.lat, startStation.lon) });
            directionsManager1.addWaypoint(startS);
            var firstWaypoint1 = new Microsoft.Maps.Directions.Waypoint({
                location: new Microsoft.Maps.Location(startLoc.lat, startLoc.lon) });
            directionsManager1.addWaypoint(firstWaypoint1);
            //add airline
            var polyline1 = new Microsoft.Maps.Polyline([startS.getLocation(),
                    firstWaypoint1.getLocation()],
                { strokeColor: color1, strokeThickness: 6});
            map.entities.push(polyline1);
        }


        var directionsManager2 = new Microsoft.Maps.Directions.DirectionsManager(map);
        directionsManager2.setRequestOptions({ routeMode: Microsoft.Maps.Directions.RouteMode.walking });
        if (directionsManager2.getAllWaypoints().length < 2) {
            var firstWaypoint2 = new Microsoft.Maps.Directions.Waypoint({
                location: new Microsoft.Maps.Location(startLoc.lat, startLoc.lon) });
            directionsManager2.addWaypoint(firstWaypoint2);
            var lastWaypoint1 = new Microsoft.Maps.Directions.Waypoint({
                location: new Microsoft.Maps.Location(endLoc.lat, endLoc.lon)});
            directionsManager2.addWaypoint(lastWaypoint1);
            //add airline
            var polyline2 = new Microsoft.Maps.Polyline([firstWaypoint2.getLocation(),
                    lastWaypoint1.getLocation()],
                { strokeColor: color1, strokeThickness: 6});
            map.entities.push(polyline2);
        }

        /*var directionsManager3 = new Microsoft.Maps.Directions.DirectionsManager(map);
         directionsManager3.setRequestOptions({ routeMode: Microsoft.Maps.Directions.RouteMode.walking });
         if (directionsManager3.getAllWaypoints().length < 2) {
             var lastWaypoint2 = new Microsoft.Maps.Directions.Waypoint({
                                   location: new Microsoft.Maps.Location(endLoc.lat, endLoc.lon) });
             directionsManager3.addWaypoint(lastWaypoint2);
             var endS = new Microsoft.Maps.Directions.Waypoint({
                                   location: new Microsoft.Maps.Location(endStation.lat, endStation.lon) });
             directionsManager3.addWaypoint(endS);
             //add airline
             var polyline3 = new Microsoft.Maps.Polyline([lastWaypoint2.getLocation(),
                                                          endS.getLocation()],
                                                          { strokeColor: color1, strokeThickness: 6});
             map.entities.push(polyline3);
         }
       */

        directionsManager1.setRenderOptions({
            firstWaypointPushpinOptions: {
                draggable: false,
                //visible: false,
                icon:'https://www.bingmapsportal.com/Content/images/poi_custom.png',
                title:'Station',
                text: ''
            },
            walkingPolylineOptions: {
                strokeColor: color2,
                strokeThickness: 6
            },
            lastWaypointPushpinOptions: {
                draggable: false,
                visible: false
            }
        });
        directionsManager2.setRenderOptions({
            firstWaypointPushpinOptions: {
                draggable: false,
                icon:'https://www.bingmapsportal.com/Content/images/poi_custom.png',
                title:'Pickup Package',
                text: ''
            },
            walkingPolylineOptions: {
                strokeColor: color2,
                strokeThickness: 6
            },
            lastWaypointPushpinOptions: {
                draggable: false,
                icon:'https://www.bingmapsportal.com/Content/images/poi_custom.png',
                title:'Destination',
                text: ''
            }
        });
        /*directionsManager3.setRenderOptions({
           firstWaypointPushpinOptions: {
               draggable: false,
               icon:'https://www.bingmapsportal.com/Content/images/poi_custom.png',
               title:'Dropoff',
               text: ''
           },
           walkingPolylineOptions: {
               strokeColor: color2,
               strokeThickness: 6
           },
          lastWaypointPushpinOptions: {
               draggable: false,
               icon:'https://www.bingmapsportal.com/Content/images/poi_custom.png',
               title:'Station',
               text: ''
         }
       });  */
        directionsManager1.calculateDirections();
        directionsManager2.calculateDirections();
        //directionsManager3.calculateDirections();
    });
}
//display price
document.getElementById("estPrice").innerHTML = estPrice1;
document.getElementById("estTime").innerHTML = estTime1;
window.sessionStorage.setItem("estPrice", estPrice1);
window.sessionStorage.setItem("estTime", estTime1);
function est1(){
    document.getElementById("estPrice").innerHTML = estPrice1;
    document.getElementById("estTime").innerHTML = estTime1;
    window.sessionStorage.setItem("estPrice", estPrice1);
    window.sessionStorage.setItem("estTime", estTime1);
    myMethod = true;
}
function est2(){
    document.getElementById("estPrice").innerHTML = estPrice2;
    document.getElementById("estTime").innerHTML = estTime2;
    window.sessionStorage.setItem("estPrice", estPrice2);
    window.sessionStorage.setItem("estTime", estTime2);
    myMethod = false;
}
document.getElementById('submit').addEventListener('click', function (e) {
    e.preventDefault();
    window.sessionStorage.setItem("myMethod", myMethod);
    console.log(myMethod);
    self.location = "paypal-transaction-complete";

});



