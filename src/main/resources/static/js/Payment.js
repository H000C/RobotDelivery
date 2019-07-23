var money = 40.00;
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
        	window.location.href = "http://localhost:8080/paypal-transaction-complete"
        );
      }
    }).render('#paypal-button-container');