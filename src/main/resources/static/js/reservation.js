
 //show passenger tab
    const addPsg = document.querySelector('.addPsg');

    addPsg.onclick = function(){
        listDrop = document.querySelector('.col-lg-c');
        listDrop.classList.toggle('active');
    }

    //addPassenger
    const addBtn = document.querySelector(".addBtn");

    function removePassenger(){
        this.parentElement.remove();
    }

    //optional
    function addPassenger(){
        const nil = document.querySelector('.more');

        const flex = document.createElement('div');
        flex.className = "flex";
        const box1 = document.createElement('div');
        box1.className= "box1"
        const box2 = document.createElement('div');
        const box3 = document.createElement('div');

        const in1 = document.createElement('input');
        in1.placeholder='First Name:';
        in1.setAttribute('name', 'firstName');
        const in2 = document.createElement('input');
        in2.placeholder='Last Name:';
        in2.setAttribute('name', 'lastName');
        const in3 = document.createElement('input');
        in3.placeholder='Email:';
        in3.setAttribute('name', 'email');

        const gender = document.createElement('select');
        gender.setAttribute('name', 'gender')
        gender.innerHTML='<option value="empty">Gender:</option><option value="male">Male</option><option value="female">Female</option>';
        gender.style.color='grey';
        const addr = document.createElement('input');
        addr.placeholder='Address:';
        addr.setAttribute('name', 'address');
        const dob = document.createElement('input');
        dob.type = "date";
        dob.setAttribute('name', 'dob');
        dob.style.color='grey';

        const btn = document.createElement("a");
        btn.className = "delete";
        btn.innerHTML = "&times";

        nil.appendChild(flex);
        flex.appendChild(box1);

        box1.appendChild(box2);
            box2.appendChild(in1);
            box2.appendChild(in2);
            box2.appendChild(in3);
        box1.appendChild(box3);
            box3.appendChild(gender);
            box3.appendChild(addr);
            box3.appendChild(dob);

        flex.appendChild(btn);
        btn.addEventListener("click", removePassenger);



    }
    //addBtn.addEventListener("click", addPassenger);