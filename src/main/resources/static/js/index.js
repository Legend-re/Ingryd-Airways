    // signup form
    const toggle = document.querySelectorAll('.log-btn');

    toggle[1].onclick = function(){
        adminLoginPage = document.querySelector('.sec-four');
        adminLoginPage.classList.toggle('active');
    }



    //collapse date
    const toggleDate = document.querySelector('.inp-hl');
    toggleDate.onclick = function(){
        listDate = document.querySelector('.toggle-date')
        listDate.classList.toggle('active');
    }



    // side nav-bar
    const hamb = document.querySelector('.hamburger');
    const hamb2 = document.querySelector('.hamb2');
    hamb.onclick = function(){
        navBar = document.querySelector('.side-bar');
        navBar.classList.toggle('active');
    }

  //collapse date
    const toggleDate2 = document.querySelector('.inp-hl2');
    toggleDate2.onclick = function(){
        listDate2 = document.querySelector('.toggle-date2')
        listDate2.classList.toggle('active');
    }


    //date1
        const currentDate = new Date();
        const year = currentDate.getFullYear();
        let month = currentDate.getMonth() +1;
        let day = currentDate.getDate();

        month = month < 10 ? "0" + month : month;
        day = day < 10 ? "0" + day : day;

        document.querySelector("#date1").min = `${year}-${month}-${day}`;





//    //date2
    const currentDate2 = new Date();
    const year2 = currentDate2.getFullYear();
    let month2 = currentDate2.getMonth() +1;
    let day2 = currentDate2.getDate();

    month2 = month2 < 10 ? "0" + month2 : month2;
    day2 = day2 < 10 ? "0" + day2 : day2;

    document.querySelector("#date2").min = `${year2}-${month2}-${day2}`;






    //jqDatePicker
//    $( function() {
//        var dateFormat = "mm/dd/yy",
//          from = $( "#date1" )
//            .datepicker({
//              defaultDate: "+1w",
//              changeMonth: true,
//              numberOfMonths: 3
//            })
//            .on( "change", function() {
//              to.datepicker( "option", "minDate", getDate( this ) );
//            }),
//          to = $( "#date2" ).datepicker({
//            defaultDate: "+1w",
//            changeMonth: true,
//            numberOfMonths: 3
//          })
//          .on( "change", function() {
//            from.datepicker( "option", "maxDate", getDate( this ) );
//          });
//
//        function getDate( element ) {
//          var date;
//          try {
//            date = $.datepicker.parseDate( dateFormat, element.value );
//          } catch( error ) {
//            date = null;
//          }
//
//          return date;
//        }
//      } );








