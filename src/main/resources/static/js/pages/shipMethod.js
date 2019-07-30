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
    }).then(function (response) {
        return response.json();
    }).then(function (myJson) {
        console.log(JSON.stringify(myJson));
        self.location = "shipSuccess";
    }).catch(function (error){
        console.log(error);
    })
});