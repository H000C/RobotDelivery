document.getElementById('shipPagePackage').addEventListener('submit', function (e) {
	e.preventDefault();
	
	var nodeDimension = document.getElementById('inputPackageDimension');
	
	var strDimension = nodeDimension.options[nodeDimension.selectedIndex].value;
		
	var packageInfo = JSON.stringify({
		size: strDimension,
		weight: document.getElementById('packageWeight').value,
	});
	
	var formPagePackage = document.getElementById("shipPagePackage");
	
	if (formPagePackage.checkValidity() === false) {
		formPagePackage.classList.add('was-validated');
	} else {
		fetch('/setOrder/setPackage', {
			method: 'POST',
			headers: { 
				'Content-Type': 'application/json'
	        },
			body: packageInfo
		}).then(function (response) {
			return response.json();
		}).then(function (myJson) {
			console.log(JSON.stringify(myJson));
			self.location = "shipMethod";
		}).catch(function (error){
			console.log(error);
		})		
	}
});