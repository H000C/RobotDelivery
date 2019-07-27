document.getElementById("estPrice").innerHTML = window.sessionStorage.getItem("estPrice");
document.getElementById("estTime").innerHTML = window.sessionStorage.getItem("estTime");
var infoLoc = window.sessionStorage.getItem('infoLoc');  
var obj = JSON.parse(infoLoc);
console.log(infoLoc);
document.getElementById("sAddress").innerHTML = obj[0].pickupLocation;
document.getElementById("fAddress").innerHTML = obj[0].dropoffLoaction;
var money = window.sessionStorage.getItem("estPrice");
var myMethod = window.sessionStorage.getItem("myMethod");
var id = sessionStorage.getItem('orderid');
var trackid = id;
console.log(myMethod);
if (myMethod == "true"){
	trackid = trackid + "R";
}
else {
	trackid = trackid + "U";
}

console.log(trackid);
paypal.Buttons({
    createOrder: function(data, actions) {
      return actions.order.create({
        purchase_units: [{
          amount: {
            value: money
          }
        }]
      });
    },
    onApprove: function(data, actions) {
        // Capture the funds from the transaction
        return actions.order.capture().then(
          // Show a success message to your buyer
        		function() {
        			alert("Paypal transaction completed, Please wait")
        	    var methodInfo = JSON.stringify({  
        	     trackingid:  trackid
        	    });         
        	    window.sessionStorage.setItem('trackingid', trackid);
        	    
        	    fetch('/setOrder/setOptions', {  
        	     method: 'POST',  
        	     headers: {   
        	      'Content-Type': 'application/json'  
        	           },  
        	     body: methodInfo  
        	    }).then(function (resp) {  
        	     return resp.json();  
        	    }).then(function (myJSON) {  
        	 	   console.log(JSON.stringify(myJSON));
        	 	  window.sessionStorage.setItem("myJSONelement",JSON.stringify(myJSON));
        	 	   setup();
        	    }) 
        		}
        );
      }
    }).render('#paypal-button-container');
document.getElementById("notpayed").style.display = "block";
document.getElementById("havepayed").style.display = "none";
function setup(){
		window.sessionStorage.setItem("paypalPayed", true);
	    document.getElementById("notpayed").style.display = "none";
	    document.getElementById("havepayed").style.display = "block";
	    document.getElementById("textalert").innerHTML = "Your Tracking Id Is: " + trackid;
	    document.getElementById("havepayed").innerHTML = "Your Package Will Arrive On: " + JSON.parse(window.sessionStorage.getItem("myJSONelement")).arrivingTime;
}
if (window.sessionStorage.getItem("paypalPayed")){
	setup();
}
