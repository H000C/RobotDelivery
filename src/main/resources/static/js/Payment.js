document.getElementById("estPrice").innerHTML = window.sessionStorage.getItem("estPrice");
document.getElementById("estTime").innerHTML = window.sessionStorage.getItem("estTime");
var infoLoc = window.sessionStorage.getItem('infoLoc');  
var obj = JSON.parse(infoLoc);
console.log(infoLoc);
document.getElementById("sAddress").innerHTML = obj[0].pickupLocation;
document.getElementById("fAddress").innerHTML = obj[0].dropoffLoaction;
var money = window.sessionStorage.getItem("estPrice");
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
        		var myMethod = window.sessionStorage.getItem("myMethod");
        	    var id = sessionStorage.getItem('orderid');
        	    var trackid = id + (myMethod?  "R" : "U");
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
        	    }) 
        	    
        	    document.getElementById("notpayed").style.display = "none";
        	    document.getElementById("havepayed").style.display = "block";
        	    document.getElementById("textalert").innerHTML = "Your Tracking Id Is: " + trackid;
        		}
        );
      }
    }).render('#paypal-button-container');
document.getElementById("notpayed").style.display = "block";
document.getElementById("havepayed").style.display = "none";
