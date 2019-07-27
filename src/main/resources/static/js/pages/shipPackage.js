document.getElementById('shipPagePackage').addEventListener('submit', function (e) {
	e.preventDefault();
	
	var nodeDimension = document.getElementById('inputPackageDimension');
	
	var strDimension = nodeDimension.options[nodeDimension.selectedIndex].value;
		
	var packageInfo = JSON.stringify({
		orderid: window.sessionStorage.getItem('orderid'),
		size: strDimension,
		weight: document.getElementById('packageWeight').value,
	});

	var formPagePackage = document.getElementById("shipPagePackage");
	
	if (formPagePackage.checkValidity() === false) {
		formPagePackage.classList.add('was-validated');
	} else if (strDimension === 'PackageDimensionChoose') {
		document.getElementById("invalid-package-dimension").style.display = "block";
	} else {
		document.getElementById("invalid-package-dimension").style.display = "none";
		
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
      window.sessionStorage.setItem("orderid", JSON.parse(JSON.stringify(response)).orderid);
			self.location = "shipMethod";
		}).catch(function (error){
			console.log(error);
		})
	}
});