/**
 * javascript for newindex.html
 */
document.getElementById('submit').addEventListener('click', function (e) {  
       e.preventDefault(); 
       window.sessionStorage.setItem("traceid", document.getElementById('trackingNumber').value);
       self.location = "trackPackage"; 

       
});
