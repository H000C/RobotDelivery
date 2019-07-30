/**
 *
 */

function TrackingWindow() {
    var x = document.getElementById("shippingWindow").style.display;
    if (x.style.display === "block") {
        x.style.display = "none";
    }
}

function ShippingWindow() {
    var x = document.getElementById("trackingWindow").style.display;
    if (x.style.display === "block") {
        x.style.display = "none";
    }
}