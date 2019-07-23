document.getElementById('shipPagePackage').addEventListener('submit', function (e) {
	e.preventDefault();
	var packageInfo = JSON.stringify({
		size: document.getElementById('packageSize').value,
		weight: document.getElementById('packageWeight').value,
	});
	
	fetch('/setOrder/setPackage', {
		method: 'POST',
		headers: { 
			'Content-Type': 'application/json'
        },
		body: packageInfo
	}).then(function (req) {
		return req.json();
	}).then(function (err) {
		console.log(err);
	})
	
	self.location = "shipMethod";
});