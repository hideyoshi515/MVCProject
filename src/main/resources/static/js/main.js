const toggleBtn = document.querySelector('.navbar__toggleBtn');
const menu = document.querySelector('.navbar__menu');
const links = document.querySelector('.navbar__links');
const navbar = document.querySelector('.navbar');
const navbarlogo = navbar.querySelector('.navbar__logo');
let hamburgMenuShow = false;
const loading_page = document.getElementById("load");

document.addEventListener("DOMContentLoaded", () => {
    history.replaceState({}, null, location.pathname);
});

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
        navbar.style.background = "#2a5dfa";
        navbarlogo.style.visibility  = 'hidden';
        //navbar.style.background = "#63c404";
    } else {
        hamburgMenuShow = false;
        navbar.removeAttribute('style');
        navbarlogo.style.visibility  = 'visible';
    }
})

window.onresize = function () {
    const width = window.innerWidth;
    const height = window.innerHeight;

    if (width > 736) {
        menu.classList.remove('active');
        links.classList.remove('active');
        navbar.removeAttribute('style');
        navbarlogo.style.visibility  = 'visible';
    } else if (width <= 736 && hamburgMenuShow) {
        menu.classList.add('active');
        links.classList.add('active');
        navbar.style.background = "#63c404";
        navbarlogo.style.visibility  = 'hidden';
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
    /*$("p img").click(function () {
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
    });*/
});


document.getElementById('skillmodal').addEventListener('click', function () {
    $("#skillmodal").removeClass("show");
    $(".modal-backdrop").remove();
    $('body').removeClass('modal-open');
    $('body').css('padding-right', '');
    $('body').css('overflow', '');
    $("#skillmodal").hide();
});



function skillModalById(skillnum, lang) {
    let skillmodal = new bootstrap.Modal(document.getElementById('skillmodal'), {
        backdrop: false,
        focus: true
    });
    let skillname = document.getElementById("skillname");
    let skilldesc = document.getElementById("skilldesc");
    let skillcondition = document.getElementById("skillcondition");

    let skillname_ko = document.getElementById("kor_name"+skillnum).innerText.trim();
    let skillname_jp = document.getElementById("jpn_name"+skillnum).innerText.trim();
    let skilleffect_ko = document.getElementById("kor_effect"+skillnum).innerText.trim();
    let skilleffect_jp = document.getElementById("jpn_effect"+skillnum).innerText.trim();
    let img = document.getElementById("img"+skillnum).src;
    let prec1 = document.getElementById("prec1_"+skillnum).innerText.trim();
    let c1 = document.getElementById("c1_"+skillnum).innerText.trim();
    let prec2 = document.getElementById("prec2_"+skillnum).innerText.trim();
    let c2 = document.getElementById("c2_"+skillnum).innerText.trim();

    console.log(":"+prec1+":");

    const urlParams = new URL(window.location.href).searchParams;
    //let lang = urlParams.get('lang');
    let conditions = "";
    let condition = "";
    let precondition = "";
    let or_string = "";
    if (lang == null || lang === undefined || !lang || lang == "ko") {
        skillname.innerHTML = "<img src='"+img+"' style='width: auto; height: 2em; padding-right:1rem;'></img>" + skillname_ko;
        skilldesc.innerHTML = skilleffect_ko.replaceAll("<", "<br><").replaceAll("＜", "<br>＜");
        condition = "발동조건<br>";
        precondition = "전제조건<br>";
        or_string = "<br>또는<br><br>";
    } else if (lang == "ja") {
        skillname.innerHTML = "<img src='"+img+"' style='width: auto; height: 2em; padding-right:1rem;'></img>" + skillname_jp;
        skilldesc.innerHTML = skilleffect_jp.replaceAll("<", "<br><").replaceAll("＜", "<br>＜");
        condition = "発動条件<br>";
        precondition = "前提条件<br>";
        or_string = "<br>または<br><br>";
    } else if (lang == "en") {
        skillname.innerHTML = "<img src='"+img+"' style='width: auto; height: 2em; padding-right:1rem;'></img>" + skillname_jp;
        skilldesc.innerHTML = skilleffect_jp.replaceAll("<", "<br><").replaceAll("＜", "<br>＜");
        conditions= "Conditions<br>";
        precondition = "Pre-Conditions<br>";
        or_string = "<br>or<br><br>";
    }
    if (prec1 != "") {
        conditions += precondition;
        prec1 = prec1.replaceAll("&","<br>");
        conditions += prec1 + "<br>";
    }
    if (c1 != "") {
        conditions += condition;
        c1 = c1.replaceAll("&","<br>");
        conditions += c1 + "<br>";
    }
    if (prec2 != "" || c2 != ""){
        conditions += or_string;
    }
    if (prec2 != "") {
        conditions += precondition;
        prec2 = prec2.replaceAll("&","<br>");
        conditions += prec2 + "<br>";
    }
    if (c2 != "") {
        conditions += condition;
        c2 = c2.replaceAll("&","<br>");
        conditions += c2 + "<br>";
    }

    skillcondition.innerHTML = conditions.replaceAll("@"," @ ");
    skillmodal.show();
}

function skillsearch() {
    let input, filter, skillList, skillBoxes, i, j, txtValue;
    input = document.getElementById("skill-search");
    filter = input.value.toUpperCase();
    skillList = document.getElementById("skillList");
    skillBoxes = skillList.getElementsByClassName("skillbox");

    for (i = 0; i < skillBoxes.length; i++) {
        let cardBodies = skillBoxes[i].getElementsByClassName("card-body");
        for (j = 0; j < cardBodies.length; j++) {
            let searchWords = cardBodies[j].getElementsByClassName("search-word");
            for (let k = 0; k < searchWords.length; k++) {
                txtValue = searchWords[k].innerText || searchWords[k].textContent;
                if (txtValue.toUpperCase().indexOf(filter) > -1 && filter !== "") {
                    skillBoxes[i].style.display = "";
                } else if (filter == "") {
                    skillBoxes[i].style.display = "";
                } else {
                    skillBoxes[i].style.display = "none";
                }
            }
        }
    }
}
