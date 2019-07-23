document.getElementById('shipPageSender').addEventListener('submit', function (e) {
	e.preventDefault();
	
	var senderFullAddress;
	
	if (document.getElementById('senderAddress2').value !== '') {
		senderFullAddress = document.getElementById('senderAddress').value + ', ' 
		+ document.getElementById('senderAddress2').value + ', '
		+ document.getElementById('senderCity').value + ', '
		+ document.getElementById('senderState').value;
	} else {
		senderFullAddress = document.getElementById('senderAddress').value + ', '
		+ document.getElementById('senderCity').value + ', '
		+ document.getElementById('senderState').value;	
	}
	
	var senderInfo = JSON.stringify({
		firstname: document.getElementById('senderFn').value,
		lastname: document.getElementById('senderLn').value,
		address: senderFullAddress,
		zipcode: document.getElementById('senderZip').value,
		email: document.getElementById('senderEmail').value,
		phone: document.getElementById('senderPhone').value
	});
	
	fetch('/setOrder/setSender', {
		method: 'POST',
		headers: { 
			'Content-Type': 'application/json'
        },
		body: senderInfo
	}).then(function (req) {
		return req.json();
	}).then(function (err) {
		console.log(err);
	})
	
	self.location = "shipReceiver";
});