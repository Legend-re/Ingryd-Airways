//window.addEventListener('load', () =>{
//    const params = (new URL(document.location)).searchParams;
//    const flightNumber = params.get('flightNumber');
//    const destinationCode = params.get('destinationCode');
//
//    console.log('Flight Number:', flightNumber);
//        console.log('Destination Code:', destinationCode);
//
//        // Set values to the input fields with IDs 'flight-Number' and 'destination-Code'
//        document.getElementById('flight-Number').value = flightNumber;
//        document.getElementById('destination-Code').value = destinationCode;
//
//})

//document.addEventListener('DOMContentLoaded', () => {
//    const params = (new URL(document.location)).searchParams;
//    const flightNumber = params.get('flightNumber');
//    const destinationCode = params.get('destinationCode');
//
//    console.log('Flight Number:', flightNumber);
//    console.log('Destination Code:', destinationCode);
//
//    // Set values to the input fields with IDs 'flight-Number' and 'destination-Code'
//    const flightNumberInput = document.getElementById('flight-Number');
//    const destinationCodeInput = document.getElementById('destination-Code');
//
//    if (flightNumberInput && destinationCodeInput) {
//        flightNumberInput.value = flightNumber;
//        destinationCodeInput.value = destinationCode;
//    } else {
//        console.error("One or both input fields not found in the DOM.");
//    }
//});





//function getDataFromGETRequest() {
//  const xhttp = new XMLHttpRequest();
//  xhttp.open('GET', '/your-endpoint', true);
//  xhttp.onload = function() {
//    if (xhttp.status === 200) {
//      const data = JSON.parse(xhttp.responseText);
//      return data;
//    } else {
//      console.error('Error:', xhttp.statusText);
//    }
//  };
//  xhttp.send();
//}
//
//
//const templateForm = document.getElementById('template-form');
//
//// Assume the GET request generates an array of data, e.g. [{name: 'John', email: 'john@example.com'}, ...]
//const data = getDataFromGETRequest(); // implement this function to retrieve the data from the server
//
//data.forEach((item, index) => {
//  const form = templateForm.cloneNode(true);
//  form.innerHTML = `
//    <input name="name" value="${item.name}">
//    <input name="email" value="${item.email}">
//    <button onclick="submitForm(event)">Go to Page B</button>
//  `;
//  document.body.appendChild(form); // append the form to the document body
//});
