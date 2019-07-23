//define variables from backend
//var trackingStartAddresss = 'Space Needle';
//var trackingEndAddress = 'Pike Market, Washington';
//var trackingStartLocation = { lat: 37.777481, lon: -122.432467 };
//var trackingEndLocation = { lat: 37.783059, lon: -122.446494 };
//var trackingCurLocation = { lat: 37.779640, lon: -122.438740 };
//var vehicleType = 'UAV';// Robot or UAV
//var trackingNum = "SF100888";
var arvlDate = "Friday";
var orderStatus = "Shipped";

var trackingStartLocation = sessionStorage.getItem('startLoc');
var trackingEndLocation = sessionStorage.getItem('endLoc');
var trackingCurLocation = sessionStorage.getItem('curLoc');
var trackingNum = sessionStorage.getItem('trackingID');
var vehicleType = trackID.substr(-1);



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
        if (vehicleType == 'R'){      
            Microsoft.Maps.loadModule('Microsoft.Maps.Directions', function showTrack() {
                var directionsManager1 = new Microsoft.Maps.Directions.DirectionsManager(map);
                directionsManager1.setRequestOptions({ routeMode: Microsoft.Maps.Directions.RouteMode.walking });
                if (directionsManager1.getAllWaypoints().length < 2) {
                    var firstWaypoint = new Microsoft.Maps.Directions.Waypoint({ 
                                         location: new Microsoft.Maps.Location(trackingStartLocation.lat, trackingStartLocation.lon) });
                    directionsManager1.addWaypoint(firstWaypoint);
                    var middleWaypoint1 = new Microsoft.Maps.Directions.Waypoint({ 
                                          location: new Microsoft.Maps.Location(trackingCurLocation.lat, trackingCurLocation.lon) });
                    directionsManager1.addWaypoint(middleWaypoint1);
                }
                var directionsManager2 = new Microsoft.Maps.Directions.DirectionsManager(map);
                directionsManager2.setRequestOptions({ routeMode: Microsoft.Maps.Directions.RouteMode.walking });
                if (directionsManager2.getAllWaypoints().length < 2) {
                    var middleWaypoint2 = new Microsoft.Maps.Directions.Waypoint({
                                          location: new Microsoft.Maps.Location(trackingCurLocation.lat, trackingCurLocation.lon) });
                    directionsManager2.addWaypoint(middleWaypoint2);
                    var lastWaypoint = new Microsoft.Maps.Directions.Waypoint({
                                          location: new Microsoft.Maps.Location(trackingEndLocation.lat, trackingEndLocation.lon) });
                    directionsManager2.addWaypoint(lastWaypoint);
                }
    
                directionsManager1.setRenderOptions({
                    firstWaypointPushpinOptions: {
                        draggable: false,
                        color: 'green',
                        text: "S",
                        icon: 'https://www.bingmapsportal.com/Content/images/poi_custom.png',
                        title: ''
                    },                 
                    walkingPolylineOptions: {
                        strokeColor: 'green',
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
                        title: 'Robot',
                        icon: 'https://img.icons8.com/ultraviolet/40/000000/bot.png'
                    },                 
                    walkingPolylineOptions: {
                        strokeColor: 'orange',
                        strokeThickness: 6
                    },
                  lastWaypointPushpinOptions: {
                        draggable: false,
                        color: 'orange',
                        text: 'E',
                        icon: 'https://www.bingmapsportal.com/Content/images/poi_custom.png',
                        title: ''
                  }
                });
                directionsManager1.calculateDirections();
                directionsManager2.calculateDirections();
            });
        }//if it is robot
        else if(vehicleType == 'U'){
           Microsoft.Maps.loadModule('Microsoft.Maps.Directions', function showTrack() {
                var directionsManager1 = new Microsoft.Maps.Directions.DirectionsManager(map);
                directionsManager1.setRequestOptions({ routeMode: Microsoft.Maps.Directions.RouteMode.walking });
                if (directionsManager1.getAllWaypoints().length < 2) {
                    var firstWaypoint = new Microsoft.Maps.Directions.Waypoint({ 
                                          location: new Microsoft.Maps.Location(trackingStartLocation.lat, trackingStartLocation.lon) });
                    directionsManager1.addWaypoint(firstWaypoint);
                    var middleWaypoint1 = new Microsoft.Maps.Directions.Waypoint({ 
                                          location: new Microsoft.Maps.Location(trackingCurLocation.lat, trackingCurLocation.lon) });
                    directionsManager1.addWaypoint(middleWaypoint1);
                    //add airline
                    var polyline1 = new Microsoft.Maps.Polyline([firstWaypoint.getLocation(),
                                                                 middleWaypoint1.getLocation()],
                                                                 { strokeColor: 'green', strokeThickness: 6});
                    map.entities.push(polyline1);
                }
                var directionsManager2 = new Microsoft.Maps.Directions.DirectionsManager(map);
                directionsManager2.setRequestOptions({ routeMode: Microsoft.Maps.Directions.RouteMode.walking });
                if (directionsManager2.getAllWaypoints().length < 2) {
                    var middleWaypoint2 = new Microsoft.Maps.Directions.Waypoint({
                                          location: new Microsoft.Maps.Location(trackingCurLocation.lat, trackingCurLocation.lon) });
                    directionsManager2.addWaypoint(middleWaypoint2);
                    var lastWaypoint = new Microsoft.Maps.Directions.Waypoint({ 
                                          location: new Microsoft.Maps.Location(trackingEndLocation.lat, trackingEndLocation.lon) });
                    directionsManager2.addWaypoint(lastWaypoint);
                    //add airline
                    var polyline2 = new Microsoft.Maps.Polyline([middleWaypoint2.getLocation(),
                                                                 lastWaypoint.getLocation()],
                                                                 { strokeColor: 'orange', strokeThickness: 6});
                    map.entities.push(polyline2);
                }
    
                directionsManager1.setRenderOptions({
                    firstWaypointPushpinOptions: {
                        draggable: false,
                        color: 'green',
                        text: "S",
                        icon: 'https://www.bingmapsportal.com/Content/images/poi_custom.png',
                        title: ''
                    },                 
                    walkingPolylineOptions: {
                        visible: false
                    },
                  lastWaypointPushpinOptions: {
                        draggable: false,
                        visible: false
                  }
                  
                });
                directionsManager2.setRenderOptions({ 
                    firstWaypointPushpinOptions: {
                        draggable: false,
                        title: 'Drone',
                        icon: 'https://img.icons8.com/ultraviolet/40/000000/drone.png'
                    },                 
                    walkingPolylineOptions: {
                        visible:false
                    },
                  lastWaypointPushpinOptions: {
                        draggable: false,
                        color: 'orange',
                        text: 'E',
                        icon: 'https://www.bingmapsportal.com/Content/images/poi_custom.png',
                        title: ''
                  }
                });
                directionsManager1.calculateDirections();
                directionsManager2.calculateDirections();
            });
        }//if it is UAV
        else{
          console.log("Error:wrong vehicle type");
        } 
    }

document.getElementById("pckg").innerHTML = trackingNum;
document.getElementById("arvl-date").innerHTML = arvlDate;
document.getElementById("order").innerHTML = orderStatus;




