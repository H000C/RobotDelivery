/**
 * javascript for newindex.html
 */
document.getElementById('trackingWindow').addEventListener('submit', function (e) {  
       e.preventDefault();  
       var methodInfo = JSON.stringify({  
        trackingid: document.getElementById('trackingNumber').value  
       });  
         
       fetch('/setOrder/setPackage', {  
        method: 'POST',  
        headers: {   
         'Content-Type': 'application/json'  
              },  
        body: methodInfo  
       }).then(function (resp) {  
        return resp.json();  
       }).then(function (myJSON) {  
    	   JSON.stringify(myJSON);
       })  
         
       self.location = "tracking";  
});