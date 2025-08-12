document.addEventListener("DOMContentLoaded", function () {
    var swiper = new Swiper(".mySwiper", {
        loop: true, // Infinite loop
        autoplay: {
            delay: 4000, // Change slide every 2s
            disableOnInteraction: true, // Keeps autoplay active even after manual interaction
        },
        slidesPerView: 2, // Show 1 slide at a time
        spaceBetween: 200, // Space between slides
        pagination: {
            el: ".swipers-pagination",
            clickable: true, // Allow user to click on dots
        },

    });
});
