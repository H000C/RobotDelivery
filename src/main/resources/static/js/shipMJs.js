//set Location spots
//var startLoc = { lat: 37.777481, lon: -122.432467 };
//var endLoc = { lat: 37.783059, lon: -122.446494 };
//var startStation = { lat: 37.771687, lon: -122.431694 };
//var endStation = { lat: 37.787833, lon: -122.447744 };

var estPrice1= "10.6";
var estPrice2= "15.3";

var startLoc = sessionStorage.getItem('startLoc');
var endLoc = sessionStorage.getItem('endLoc');
var startStation = sessionStorage.getItem('station1');
var endStation = sessionStorage.getItem('station2');

//draw the map
function GetMap()
    {    
        var map = new Microsoft.Maps.Map(document.getElementById('myMap'), {
              credentials: 'AqDMxwwiD2P7-8TCvVyHmOnvrktdAqn7TXFYXDAYuLPhesaPHXin836RNWKAY6a_'
        });
        
        //set the appearance of map
        map.setView({
            mapTypeId: Microsoft.Maps.MapTypeId.road,
            zoom: 13,
        });
           
        //decide the type of vehicle. polylines for robots/straight lines for UAVs
        if (vehicleType == 'robot'){      
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
                                          location: new Microsoft.Maps.Location(endLoc.lat, endLoc.lon) });
                    directionsManager2.addWaypoint(lastWaypoint1);
                    //add airline
                    var polyline2 = new Microsoft.Maps.Polyline([firstWaypoint2.getLocation(),
                                                                 lastWaypoint1.getLocation()],
                                                                 { strokeColor: color1, strokeThickness: 6});
                    map.entities.push(polyline2);
                }
              
               var directionsManager3 = new Microsoft.Maps.Directions.DirectionsManager(map);
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
                        title:'Pickup',
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
                 directionsManager3.setRenderOptions({
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
                   
                });  
                directionsManager1.calculateDirections();
                directionsManager2.calculateDirections();
                directionsManager3.calculateDirections();
                
            });
        }else{
          console.log("Error:wrong vehicle type");
        } 
    }
document.getElementById("estPrice").innerHTML = estPrice1;
function est1(){
  document.getElementById("estPrice").innerHTML = estPrice1;
}
function est2(){
  document.getElementById("estPrice").innerHTML = estPrice2;
}




