/**
 * javascript for newindex.html
 */
document.getElementById('submit').addEventListener('click', function (e) {  
       e.preventDefault();  
       var id = document.getElementById('trackingNumber').value;
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
    	   self.location = "trackPackage"; 
       })  
       
});
