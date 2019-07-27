var arvlDate = "Friday";
var orderStatus = "Shipped";

var id = window.sessionStorage.getItem("traceid");
var methodInfo = JSON.stringify({  
 trackingid: id     
});         
window.sessionStorage.setItem('myID', id);

fetch('/trackOrder/currentLocation', {  
 method: 'POST',  
 headers: {   
  'Content-Type': 'application/json'  
       },  
 body: methodInfo  
}).then(function (resp) {  
 return resp.json();  
}).then(function (myJSON) {  
	   console.log(JSON.stringify(myJSON));
	   window.sessionStorage.setItem('myLoc',JSON.stringify(myJSON));  
}) 

var myLoc = window.sessionStorage.getItem('myLoc');
var myID = window.sessionStorage.getItem('myID');
var obj1 = JSON.parse(myLoc);
var trackingStartLocation = {lat:obj1.start[0], lon: obj1.start[1]};
var trackingEndLocation = {lat:obj1.finish[0], lon: obj1.finish[1]};
var trackingCurLocation = {lat:obj1.current[0], lon: obj1.current[1]};
var trackingNum = myID;
var vehicleType = trackingNum.substr(-1);
var shipStatus = obj1.phase;

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
//document.getElementById("arvl-date").innerHTML = arvlDate;
document.getElementById("order").innerHTML = shipStatus;




