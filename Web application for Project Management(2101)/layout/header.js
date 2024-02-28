const headerClassList = "bg-white py-1 d-flex align-items-stretch border-bottom border-primary".split(" ")
const header = document.createElement("header");
header.innerHTML = 
`
<div class="container-fluid d-flex align-items-center">
    <div class="ms-auto d-flex align-items-center">
        <div class="ms-left d-flex flex-column ">
        <div class="text-end">
            Kamalahshunee
        <img src="./images/account.png" width="38" height="38" class="mx-3 rounded-circle border border-dark">
    </div>
</div>
`
header.classList.add(...headerClassList);
document.querySelector("main").prepend(header);
