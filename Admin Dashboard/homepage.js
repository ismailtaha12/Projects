
function openHTMLFileNutrietion() {
  window.open('nutritionplans.html', '_self');
}
function openHTMLFileaccount() {
  window.open('account.html', '_self');
}
function openHTMLFilepayment() {
  window.open('payment & Billing.html', '_self');
}


const menubtn = document.querySelector("#menu-btn"); 
const navlinks = document.querySelector(".navlinks");
const menubtnicon = menubtn.querySelector("i");

menubtn.addEventListener("click", (e) => {
    navlinks.classList.toggle("open");
  
    const isOpen= navlinks.classList.contains("open");
    menubtnicon.setAttribute("class",isOpen ? "ri-close-line":"ri-menu-line");
});

navlinks.addEventListener("click", (e)=> {
    navlinks.classList.remove("open");
    menubtnicon.setAttribute("class","ri-menu-line");
});

//for videos
const btns= document.querySelectorAll(".nav-btn");
const slides= document.querySelectorAll(".video-slide");
const contents= document.querySelectorAll(".content");

var slidernav = function(manual){
  btns.forEach((btn) =>{
    btn.classList.remove("active");
  })
  slides.forEach((slide) =>{
    slide.classList.remove("active");
  })
  contents.forEach((content) =>{
    content.classList.remove("active");
  })

    btns[manual].classList.add("active");
    slides[manual].classList.add("active");
    contents[manual].classList.add("active");

}

btns.forEach((btn,i) => {
    btn.addEventListener("click",()=> {
        slidernav(i);
    })
})

