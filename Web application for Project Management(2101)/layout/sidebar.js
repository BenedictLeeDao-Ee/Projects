const sidebar = document.createElement("nav");
sidebar.innerHTML = 
`
<a href="#" class="active menu-btn">
    <div class="sidebar-icon">
    <i class='bx bx-menu bx-lg'></i>
    <i class='bx bxs-menu bx-lg'></i>
    </div>
</a>
<div class="sidebar-top">
    <img src="./images/logo.png" class="logo" alt="">
    <h3 class="hide my-0">Navigation</h3>
</div>

<br></br>
<!-- hard coded links -->
<div class="sidebar-links">
    <ul>
    <div class="active-tab"></div>
    <li class="tooltip-element" data-tooltip="0">
        <a href="./taskview.html" class="active" data-active="0">
        <div class="sidebar-icon">
            <i class='bx bx-folder'></i>
            <i class='bx bxs-folder'></i>
        </div>
        <span class="link hide">Product Backlog</span>
        </a>
    </li>
    <li class="tooltip-element" data-tooltip="1">
        <a href="./sprintlist.html" data-active="1">
        <div class="sidebar-icon">
            <i class='bx bx-bar-chart-square'></i>
            <i class='bx bxs-bar-chart-square'></i>
        </div>
        <span class="link hide">Sprint List</span>
        </a>
    </li>
    <li class="tooltip-element" data-tooltip="2">
        <a href="./team-board.html" data-active="2">
        <div class="sidebar-icon">
            <i class='bx bx-tachometer'></i>
            <i class='bx bxs-tachometer'></i>
        </div>
        <span class="link hide">Team Members</span>
        </a>
    </li>
    </ul>

</div>
`
document.querySelector("body").prepend(sidebar);