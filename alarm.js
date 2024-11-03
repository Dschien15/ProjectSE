const body = document.querySelector("body"),
  nav = document.querySelector("nav"),
  modeToggle = document.querySelector(".dark-light"),
  searchToggle = document.querySelector(".searchToggle");

// js code search box
searchToggle.addEventListener("click", () => {
  searchToggle.classList.toggle("active");
});
