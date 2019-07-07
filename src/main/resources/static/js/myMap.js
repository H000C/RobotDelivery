     var trackingStartAddresss = 'Space Needle';
     var trackingEndAddress = 'Pike Market, Washington';
     var trackingCurLocation = { lat: 47.617593, lon: -122.351712 };

    function GetMap()
    {
        
        var map = new Microsoft.Maps.Map('#myMap', {
            credentials: 'Your Key'
        });
        Microsoft.Maps.loadModule('Microsoft.Maps.Directions', function addWaypoint() {
	    var directionsManager1 = new Microsoft.Maps.Directions.DirectionsManager(map);
	    directionsManager1.setRequestOptions({ routeMode: Microsoft.Maps.Directions.RouteMode.walking });
    	    if (directionsManager1.getAllWaypoints().length < 2) {
        	var firstWaypoint = new Microsoft.Maps.Directions.Waypoint({ address: trackingStartAddresss });
        	directionsManager1.addWaypoint(firstWaypoint);
        	var middleWaypoint = new Microsoft.Maps.Directions.Waypoint({ location: new Microsoft.Maps.Location(trackingCurLocation.lat, trackingCurLocation.lon) });
        	directionsManager1.addWaypoint(middleWaypoint);
	    }
    	    var directionsManager2 = new Microsoft.Maps.Directions.DirectionsManager(map);
    	    directionsManager2.setRequestOptions({ routeMode: Microsoft.Maps.Directions.RouteMode.walking });
    	    if (directionsManager2.getAllWaypoints().length < 2) {
		var middleWaypoint = new Microsoft.Maps.Directions.Waypoint({ location: new Microsoft.Maps.Location(trackingCurLocation.lat, trackingCurLocation.lon) });
        	directionsManager2.addWaypoint(middleWaypoint);
        	var lastWayPoint = new Microsoft.Maps.Directions.Waypoint({ address: trackingEndAddress });
        	directionsManager2.addWaypoint(lastWayPoint);
    	    }
    
    	    directionsManager1.setRenderOptions({
		firstWaypointPushpinOptions: {
		    draggable: false,
		    visible: false
		},                 
	    	walkingPolylineOptions: {
                    strokeColor: 'green',
                    strokeThickness: 6
                }});
    	    directionsManager2.setRenderOptions({ 
		firstWaypointPushpinOptions: {
		    draggable: false,
		    text: 'Robot'
		},                 
	    	walkingPolylineOptions: {
                    strokeColor: 'yellow',
                    strokeThickness: 6
                },
		lastWaypointPushpinOptions: {
		    draggable: false,
		    visible: false
		}});
    	    directionsManager1.calculateDirections();
    	    directionsManager2.calculateDirections();
	});
    }