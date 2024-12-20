/**
 * Contains all Javascript methods utilized throughout the website.
 */

// Utilized in headerFilters.jsp
function submitForm() {
    document.getElementById("dropdownFilters").submit();
}

// Utilized in ItemDetails.jsp
function showPopup() {
  var popup = document.getElementById('popup');
  popup.style.display = 'block';

  // Hide the popup after 2 seconds
  setTimeout(function() {
    popup.style.display = 'none';
  }, 2000);
}