/*
 * Lembrar de colocar prependId="false" no form
 * 
 * */

var map;
var geocoder;
var currentMarker = null;
var estado = 'rio de janeiro';
var latInicial = -22.9419;
var lngInicial = -43.4419;
var zoomPadrao = 10;
var markersArray = [];

function addListenerDragend(marker) {
	google.maps.event.addDomListener(marker, "dragend", function() {
		document.getElementById('latitude').value = marker.getPosition().lat();
		document.getElementById('longitude').value = marker.getPosition().lng();
		geocodeReverso(marker);
	});
}

function geocodeReverso(marker) {

	/* geocode reverso */
	geocoder
			.geocode(
					{
						'latLng' : marker.getPosition()
					},
					function(results, status) {
						if (status == google.maps.GeocoderStatus.OK) {
							if (results[0]) {
								endereco = results[0].formatted_address;

								document.getElementById("endereco").value = endereco;

							} else {
								console.log("No results found");
							}
						} else {
							console.log("Geocoder failed due to: " + status);
						}
					});
}

function addMarker(latLng) {
	map.setCenter(latLng);
	map.setZoom(17);

	var marker = new google.maps.Marker({
		map : map,
		position : latLng,
		draggable : true
	});

	addListenerDragend(marker);
	markersArray.push(marker);
	// geocodeReverso(marker);
	return marker;
}

function deleteOverlays() {

	if (markersArray) {
		for (i = 0; i < markersArray.length; i++) {
			markersArray[i].setMap(null);
		}
		markersArray.length = 0;
	}
}

//function initialize() {
//	geocoder = new google.maps.Geocoder();
//	map = PF('map').getMap();
//}

function initialize(map_canvas) {
	  var myOptions = {
	        zoom: zoomPadrao,
	        center: new google.maps.LatLng(latInicial, lngInicial),
	        mapTypeId: google.maps.MapTypeId.ROADMAP
	      };
	  if(!map){
		  geocoder = new google.maps.Geocoder();
		  map = new google.maps.Map(document.getElementById(map_canvas), myOptions);
	  }
}

function limparMapa() {
	deleteOverlays();
	map.setCenter(new google.maps.LatLng(latInicial, lngInicial));
	map.setZoom(zoomPadrao);
}

function marcar(latitude, longitude) {
	if (latitude == 0 && longitude == 0) {
		return;
	}
	initialize();
	limparMapa();
	var latLng = new google.maps.LatLng(latitude, longitude);
	marker = addMarker(latLng);
}
/*
function showAddress() {
	initialize();
	var address = document.getElementById("endereco").value;
	// deleteOverlays();
	limparMapa();
	geocoder
			.geocode(
					{
						'address' : address 
					},
					function(results, status) {
						if (status == google.maps.GeocoderStatus.OK) {
							marker = addMarker(results[0].geometry.location);
							geocodeReverso(marker);
							document.getElementById('latitude').value = marker.getPosition().lat();
							document.getElementById('longitude').value = marker.getPosition().lng();

						} else {
							alert("Geocode was not successful for the following reason: "
									+ status);
						}
					});
}
*/
function handlePointClick(event) {
	if (currentMarker == null) {
		document.getElementById('lat').value = event.latLng.lat();
		document.getElementById('long').value = event.latLng.lng();

		currentMarker = new google.maps.Marker({
			position : new google.maps.LatLng(event.latLng.lat(), event.latLng
					.lng())
		});

		map.addOverlay(currentMarker);

	}
}