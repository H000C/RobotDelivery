document.getElementById('shipPageReceiver').addEventListener('submit', function (e) {
	e.preventDefault();
	
	var receiverFullAddress;
	
	if (document.getElementById('receiverAddress2').value !== '') {
		receiverFullAddress = document.getElementById('receiverAddress').value + ', ' 
		+ document.getElementById('receiverAddress2').value + ', '
		+ document.getElementById('receiverCity').value + ', '
		+ document.getElementById('receiverState').value;
	} else {
		receiverFullAddress = document.getElementById('receiverAddress').value + ', '
		+ document.getElementById('receiverCity').value + ', '
		+ document.getElementById('receiverState').value;	
	}
	
	var receiverInfo = JSON.stringify({
		orderid: window.sessionStorage.getItem('orderid'),
		firstname: document.getElementById('receiverFn').value,
		lastname: document.getElementById('receiverLn').value,
		address: receiverFullAddress,
		zipcode: document.getElementById('receiverZip').value,
		email: document.getElementById('receiverEmail').value,
		phone: document.getElementById('receiverPhone').value
	});
	
	fetch('/setOrder/setRecipient', {
		method: 'POST',
		headers: { 
			'Content-Type': 'application/json'
        },
		body: receiverInfo
	}).then(function (response) {
		return response.json();
	}).then(function (myJson) {
		console.log(JSON.stringify(myJson));
		var obj = JSON.parse(JSON.stringify(myJson));
		window.sessionStorage.setItem("orderid", obj.orderid);
		if (obj.address === "invalid address") {
			document.getElementById("invalid-receiver-address").style.display = "block";
		} else {
			document.getElementById("invalid-receiver-address").style.display = "none";
			self.location = "shipPackage";
		}
	}).catch(function (error){
		console.log(error);
	})
});