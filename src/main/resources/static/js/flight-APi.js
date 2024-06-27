   //Flight Location API

//Origin Code
    console.log('welcome')

    const selectElement1 = document.getElementById('selectElement1');

    fetch('https://airportdb.io/api/v1/airport/KJFK?apiToken=ec55a8ab43b97c69f06c580dcfeb283f7e4f791862bc58496c8efe09e998b6cad1b901374b4f7e30efc9a9bab4410dc4')
        .then(res => res.json())
        .then(data => {

            if (data && data.freqs && Array.isArray(data.freqs)) {

                selectElement1.innerHTML = '';


                data.freqs.forEach(freq => {
                    const option = document.createElement('option');
                    option.value = freq.id;
                    option.textContent = `${freq.type}: ${freq.description}`;
                    selectElement1.appendChild(option);
                });
            } else {
                console.warn('Unexpected API response format:', data);
            }
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });



    const selectElement2 = document.getElementById('selectElement2');

    fetch('https://airportdb.io/api/v1/airport/KJFK?apiToken=ec55a8ab43b97c69f06c580dcfeb283f7e4f791862bc58496c8efe09e998b6cad1b901374b4f7e30efc9a9bab4410dc4')
        .then(res => res.json())
        .then(data => {

            if (data && data.freqs && Array.isArray(data.freqs)) {

                selectElement2.innerHTML = '';


                data.freqs.forEach(freq => {
                    const option = document.createElement('option');
                    option.value = freq.id;
                    option.textContent = `${freq.type}: ${freq.description}`;
                    selectElement2.appendChild(option);
                });
            } else {
                console.warn('Unexpected API response format:', data);
            }
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
