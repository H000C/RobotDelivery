/**
 * javascript for newindex.html
 */
document.getElementById('submit').addEventListener('click', function (e) {  
       e.preventDefault();  
       var methodInfo = JSON.stringify({  
        trackingid: document.getElementById('trackingNumber').value  
       });  
         
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
    	   
       })  
         
       
       //self.location = "trackPackage";  
});
