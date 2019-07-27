document.getElementById('shipPageSender').addEventListener('submit', function (e) {
	e.preventDefault();
    e.stopPropagation();

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
	
	var formPageSender = document.getElementById("shipPageSender");
	
	if (formPageSender.checkValidity() === false) {
		formPageSender.classList.add('was-validated');
	} else {
		fetch('/setOrder/setSender', {
			method: 'POST',
			headers: { 
				'Content-Type': 'application/json'
	        },
			body: senderInfo
		}).then(function (response) {
			return response.json();
		}).then(function (myJson) {
			console.log(JSON.stringify(myJson));
			var obj = JSON.parse(JSON.stringify(myJson));
			if (obj.address === "invalid address") {
				document.getElementById("invalid-sender-address").style.display = "block";
			} else {
				document.getElementById("invalid-sender-address").style.display = "none";
				self.location = "shipReceiver";
			}
		}).catch(function (error){
			console.log(error);
		})   
	}
});