document.getElementById('shipPagePackage').addEventListener('submit', function (e) {
	e.preventDefault();
	
	var nodeDimension = document.getElementById('inputPackageDimension');
	
	var strDimension = nodeDimension.options[nodeDimension.selectedIndex].value;
		
	var packageInfo = JSON.stringify({
		size: strDimension,
		weight: document.getElementById('packageWeight').value,
	});
	
	fetch('/setOrder/setPackage', {
		method: 'POST',
		headers: { 
			'Content-Type': 'application/json'
        },
		body: packageInfo
	}).then(res => res.json())
	.then(response => console.log('Success:', JSON.stringify(response)))
	.catch(error => console.error('Error:', error));
	
	if (strDimension === 'PackageDimensionChoose') {
		document.getElementById("invalid-package-dimension").style.display = "block";
	} else {
		document.getElementById("invalid-package-dimension").style.display = "none";
	    self.location = "shipMethod";
	}
	
});
