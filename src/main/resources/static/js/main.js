const toggleBtn = document.querySelector('.navbar__toggleBtn');
const menu = document.querySelector('.navbar__menu');
const links = document.querySelector('.navbar__links');
const navbar = document.querySelector('.navbar');
let hamburgMenuShow = false;
const loading_page = document.getElementById("load");

window.onload = function () {
    setTimeout(function () {
        loading_page.style.display = 'none';
    }, 500);
}

toggleBtn.addEventListener('click', () => {
    menu.classList.toggle('active');
    links.classList.toggle('active');
    if (menu.classList.contains('active')) {
        hamburgMenuShow = true;
        navbar.style.background = "#63c404";
    } else {
        hamburgMenuShow = false;
        navbar.removeAttribute('style');
    }
})

window.onresize = function () {
    const width = window.innerWidth;
    const height = window.innerHeight;

    if (width > 736) {
        menu.classList.remove('active');
        links.classList.remove('active');
        navbar.removeAttribute('style');
    } else if (width <= 736 && hamburgMenuShow) {
        menu.classList.add('active');
        links.classList.add('active');
        navbar.style.background = "#63c404";
    }
}

$('.slider').slick({
    autoplay: true,
    autoplaySpeed: 2000,
    dots: true,
    infinite: true,
    speed: 500,
    fade: true,
    cssEase: 'linear',
    arrows: false,
    adaptiveHeight: true,
});

$(function () {
//     이미지 클릭시 해당 이미지 모달
    $("p img").click(function () {
        let img = new Image();
        let address = $(this).attr("src");
        img.src = $(this).attr("src").replace("profile.png", "stand.png")
        img.width = img.width * 0.5;
        img.height = img.height * 0.5;
        $('.modalBox').html(img);
        $(".modal").show();
    });
// 모달 클릭할때 이미지 닫음
    $(".modal").click(function (e) {
        $(".modal").toggle();
    });
});


