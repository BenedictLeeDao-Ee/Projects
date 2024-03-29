const menu_btn = document.querySelector(".menu-btn");
const search = document.querySelector(".search");
const sidebar_links = document.querySelectorAll(".sidebar-links a");
const active_tab = document.querySelector(".active-tab");
const shortcuts = document.querySelector(".sidebar-links h4");
const tooltip_elements = document.querySelectorAll(".tooltip-element");

let activeIndex;

menu_btn.addEventListener("click", () => {
  document.body.classList.toggle("shrink");
});

// search.addEventListener("click", () => {
//   document.body.classList.remove("shrink");
//   search.lastElementChild.focus();
// });

function moveActiveTab() {
  let topPosition = activeIndex * 58 + 2.5;

  if (activeIndex > 3) {
    topPosition += shortcuts.clientHeight;
  }

  active_tab.style.top = `${topPosition}px`;
}

function changeLink() {
  sidebar_links.forEach((sideLink) => sideLink.classList.remove("active"));
  this.classList.add("active");

  activeIndex = this.dataset.active;

  moveActiveTab();
}

sidebar_links.forEach((link) => link.addEventListener("click", changeLink));

// function showTooltip() {
//   let tooltip = this.parentNode.lastElementChild;
//   let spans = tooltip.children;
//   let tooltipIndex = this.dataset.tooltip;

//   Array.from(spans).forEach((sp) => sp.classList.remove("show"));
//   spans[tooltipIndex].classList.add("show");

//   tooltip.style.top = `${(100 / (spans.length * 2)) * (tooltipIndex * 2 + 1)}%`;
// }

// tooltip_elements.forEach((elem) => {
//   elem.addEventListener("mouseover", showTooltip);
// });
