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
	}).then(function (req) {
		return req.json();
	}).then(function (err) {
		console.log(err);
	})
	
	self.location = "shipPackage";
});