document.getElementById('shipPageMethod').addEventListener('submit', function (e) {
	e.preventDefault();
	var methodInfo = JSON.stringify({
		DeliveryOption: (document.getElementById('radioBot').value === 'on')  ? 'bot' : 'drone'
	});
	
	fetch('/setOrder/setPackage', {
		method: 'POST',
		headers: { 
			'Content-Type': 'application/json'
        },
		body: methodInfo
	}).then(function (req) {
		return req.json();
	}).then(function (err) {
		console.log(err);
	})
	
	self.location = "shipSuccess";
});