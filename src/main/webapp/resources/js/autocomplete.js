var autocomplete;

function initAutocomplete() {
  autocomplete = new google.maps.places.Autocomplete(
      (document.getElementById('endereco')),
      {types: ['geocode']});

  autocomplete.addListener('place_changed', marcarAutocomplete);
}

function marcarAutocomplete(){
	document.getElementById('latitude').value = autocomplete.getPlace().geometry.location.lat();
	document.getElementById('longitude').value = autocomplete.getPlace().geometry.location.lng();
	marcar(autocomplete.getPlace().geometry.location.lat(),autocomplete.getPlace().geometry.location.lng());
}

