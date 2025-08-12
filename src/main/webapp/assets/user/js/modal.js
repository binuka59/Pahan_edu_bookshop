// Get modal element and buttons
const modal = document.getElementById("exampleModal");
const launchModalButton = document.getElementById("launchModalButton");
const closeModalButtons = document.querySelectorAll(".btn-close");

// Show the modal
launchModalButton.addEventListener("click", () => {
  modal.style.display = "block";  // Show the modal
});

// Close the modal when clicking on close button
closeModalButtons.forEach(button => {
  button.addEventListener("click", () => {
    modal.style.display = "none";  // Hide the modal
  });
});

// Close the modal if clicked outside of the modal content
window.addEventListener("click", (event) => {
  if (event.target === modal) {
    modal.style.display = "none";  // Hide the modal
  }
});
