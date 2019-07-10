//define variables from backend
var trackingStartAddresss = 'Space Needle';
var trackingEndAddress = 'Pike Market, Washington';
var trackingCurLocation = { lat: 47.617593, lon: -122.351712 };
var vehicleType = 'UAV';// Robot or UAV
var trackingNum = "SF100888";
var arvlDate = "Friday";
var orderStatus = "Shipped"

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
                var directionsManager1 = new Microsoft.Maps.Directions.DirectionsManager(map);
                directionsManager1.setRequestOptions({ routeMode: Microsoft.Maps.Directions.RouteMode.walking });
                if (directionsManager1.getAllWaypoints().length < 2) {
                    var firstWaypoint = new Microsoft.Maps.Directions.Waypoint({ address: trackingStartAddresss });
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
                    var lastWayPoint = new Microsoft.Maps.Directions.Waypoint({ address: trackingEndAddress });
                    directionsManager2.addWaypoint(lastWayPoint);
                }
    
                directionsManager1.setRenderOptions({
                    firstWaypointPushpinOptions: {
                        draggable: false,
                        color: 'green',
                        text: 'S'
                    },                 
                    walkingPolylineOptions: {
                        strokeColor: 'green',
                        strokeThickness: 6
                    }
                });
                directionsManager2.setRenderOptions({ 
                    firstWaypointPushpinOptions: {
                        draggable: false,
                        //icon: '',
                        text: 'Robot'
                    },                 
                    walkingPolylineOptions: {
                        strokeColor: 'orange',
                        strokeThickness: 6
                    },
                  lastWaypointPushpinOptions: {
                        draggable: false,
                        color: 'orange',
                        text: 'E'
                  }
                });
                directionsManager1.calculateDirections();
                directionsManager2.calculateDirections();
            });
        }//if it is robot
        else if(vehicleType == 'UAV'){
          Microsoft.Maps.loadModule('Microsoft.Maps.Directions', function showTrack() {
                var directionsManager1 = new Microsoft.Maps.Directions.DirectionsManager(map);
                directionsManager1.setRequestOptions({ routeMode: Microsoft.Maps.Directions.RouteMode.walking });
                if (directionsManager1.getAllWaypoints().length < 2) {
                    var firstWaypoint = new Microsoft.Maps.Directions.Waypoint({ address: trackingStartAddresss });
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
                    var lastWayPoint = new Microsoft.Maps.Directions.Waypoint({ address: trackingEndAddress });
                    directionsManager2.addWaypoint(lastWayPoint);
                }
            
          
                directionsManager1.setRenderOptions({
                    firstWaypointPushpinOptions: {
                        draggable: false,
                        color: 'green',
                        text: 'S'
                    },                 
                    walkingPolylineOptions: {
                        visible: false
                    }
                });
            
               directionsManager2.setRenderOptions({ 
                    firstWaypointPushpinOptions: {
                        draggable: false,
                        //icon: '',
                        text: 'UAV'
                    },                 
                    lastWaypointPushpinOptions: {
                        draggable: false,
                        color: 'orange',
                        text: 'E'
                    }, 
                    walkingPolylineOptions: {
                        visible: false
                    }
                });
           
                directionsManager1.calculateDirections();
                directionsManager2.calculateDirections();
            });       
          var coords1 = [firstWaypoint.getLocation(), middleWaypoint.getLocation()];
          var polyline1 = new Microsoft.Maps.Polyline(coords1,{
                                                      strokeColor: 'green',
                                                      strokeThickness: 6});
          map.entities.push(polyline1); 
          var coords2 = [middleWaypoint2.getLocation(), lastWayPoint.getLocation()];
          var polyline2 = new Microsoft.Maps.Polyline(coords2,{
                                                      strokeColor: 'orange',
                                                      strokeThickness: 6});
          map.entities.push(polyline2);     
        }//if it is UAV
        else{
          console.log("Error:wrong vehicle type");
        }
      
     /* var center = map.getCenter();
      var polyline = new Microsoft.Maps.Polyline([new Microsoft.Maps.Location(trackingCurLocation.lat, trackingCurLocation.lon),
                     new Microsoft.Maps.Location(trackingCurLocation.lat,trackingCurLocation.lon)], null);
      map.entities.push(polyline);*/
      
       
    }

document.getElementById("pckg").innerHTML = trackingNum;
document.getElementById("arvl-date").innerHTML = arvlDate;
document.getElementById("order").innerHTML = orderStatus;


