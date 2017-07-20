$(document).ready(function(){
	$(".navbar-default ul li").click(function() {
		$(".navbar-default ul li").removeClass("active");
		$(this).addClass("active");
	});
});

/*Function to change active class when scrolling in portfolio page. */
/*$(document).ready(function(){       
   var scrollStart = 0;

   var about = $(".about-content");
   var whyTech = $(".why-tech");
   var whyCagi = $(".why-capgemini");
   var mySkills = $(".my-skills");

   var aboutOffset = about.offset();
   var whyTechOffset = whyTech.offset();
   var whyCagiOffset = whyCagi.offset();
   var mySkillsOffset = mySkills.offset();
 
   $(document).scroll(function() {

    
      scrollStart = $(this).scrollTop();
    
      if (scrollStart < aboutOffset.top-100) {
        $(".navbar-default ul li").removeClass("active");
      }

      if(scrollStart > aboutOffset.top-100 ) {
        $(".navbar-default ul li").removeClass("active");
        $(".navbar-default ul li #about-btn").addClass("active");
        

       } 

      if(scrollStart > whyTechOffset.top-100) {
        $(".navbar-default ul li").removeClass("active");
        $("#why-tech-btn").addClass("active");
        

       }

       if(scrollStart > whyCagiOffset.top-100) {
        $(".navbar-default ul li").removeClass("active");
        $(".#why-capgemini-btn").addClass("active");
        

       }

       if(scrollStart > mySkillsOffset.top-100) {
        $(".navbar-default ul li").removeClass("active");
        $("#my-skills-btn").addClass("active");


       }   
   });
}); */
 
function deleteAllActive() {
    
    $("li #why-tech-btn").removeClass("active");
    $("li #why-capgemini-btn").removeClass("active");
    $("li #my-skills-btn").removeClass("active");
    $("navbar .navbar-right li").removeClass("active"); 



}

$(document).ready(function(){       
   var scrollStart = 0;
   

   
   var whyTech = $("#why-tech");
   var whyCapgi = $("#why-capgemini");
   var mySkills = $("#my-skills");

   
   var whyTechOffset = whyTech.offset();
   var whyCapgiOffset = whyCapgi.offset();
   var mySkillsOffset = mySkills.offset();

   $(document).scroll(function() {
   	
      scrollStart = $(this).scrollTop();
      
      if(scrollStart > (whyTechOffset.top)-100) {
          deleteAllActive();
          $(".navbar-default").css("background-color", "#757575");

          
          if(scrollStart > whyTechOffset.top-100) {
            deleteAllActive();
            $(".navbar-right li #why-tech-btn").addClass("active");
          }

          if(scrollStart > whyCapgiOffset.top-100) {
            deleteAllActive();
            $(".navbar-right #why-capgemini-btn").addClass("active");
          }

          if(scrollStart > mySkillsOffset.top-100) {
            deleteAllActive();
            $(".navbar-right #my-skills-btn").addClass("active");
          }           
       } else {
          deleteAllActive();
          $(".navbar-default").css("background-color", "#1A237E");

       }
       
   });
});


$(document).ready(function() {


  $(".my-button").click(function( e ){
    if(this.hash !== "") {
      e.preventDefault();

      //Store hash
      var hash = this.hash;
      
      var newPos = $("#why-tech").offset().top;
      $('html, body').stop().animate({scrollTop: newPos}, 800, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
      
    }
    
  });

    $("#why-tech-btn").click(function( e ){
    if(this.hash !== "") {
      e.preventDefault();

      //Store hash
      var hash = this.hash;
      
      var newPos = $("#why-tech").offset().top;
      $('html, body').stop().animate({scrollTop: newPos}, 800, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
      
    }
    
  });

    $("#about-to-home").click(function( e ){
    if(this.hash !== "") {
      e.preventDefault();

      //Store hash
      var hash = this.hash;
      
      var newPos = $("#home-image").offset().top;
      $('html, body').stop().animate({scrollTop: newPos}, 800, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
      
    }
    
  });  

  $("#about-to-why-tech").click(function( e ){
    if(this.hash !== "") {
      e.preventDefault();

      //Store hash
      var hash = this.hash;
      
      var newPos = $("#why-tech").offset().top;
      $('html, body').stop().animate({scrollTop: newPos}, 800, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
      
    }
    
  });  

    $("#why-capgemini-btn").click(function( e ){
    if(this.hash !== "") {
      e.preventDefault();

      //Store hash
      var hash = this.hash;
      
      var newPos = $("#why-capgemini").offset().top;
      $('html, body').stop().animate({scrollTop: newPos}, 800, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
      
    }
    
  });

  $("#why-tech-to-about").click(function( e ){
    if(this.hash !== "") {
      e.preventDefault();

      //Store hash
      var hash = this.hash;
      
      var newPos = $("#about").offset().top;
      $('html, body').stop().animate({scrollTop: newPos}, 800, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
      
    }
    
  });
  $("#why-tech-to-why-capgemini").click(function( e ){
    if(this.hash !== "") {
      e.preventDefault();

      //Store hash
      var hash = this.hash;
      
      var newPos = $("#why-capgemini").offset().top;
      $('html, body').stop().animate({scrollTop: newPos}, 800, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
      
    }
    
  });  

    $("#my-skills-btn").click(function( e ){
    if(this.hash !== "") {
      e.preventDefault();

      //Store hash
      var hash = this.hash;
      
      var newPos = $("#my-skills").offset().top;
      $('html, body').stop().animate({scrollTop: newPos}, 800, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
      
    }
    
  }); 

  $("#why-capgemini-to-why-tech").click(function( e ){
    if(this.hash !== "") {
      e.preventDefault();

      //Store hash
      var hash = this.hash;
      
      var newPos = $("#why-tech").offset().top;
      $('html, body').stop().animate({scrollTop: newPos}, 800, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
      
    }
    
  });
  
  $("#why-capgemini-to-my-skills").click(function( e ){
    if(this.hash !== "") {
      e.preventDefault();

      //Store hash
      var hash = this.hash;
      
      var newPos = $("#my-skills").offset().top;
      $('html, body').stop().animate({scrollTop: newPos}, 800, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
      
    }
    
  });

  $("#my-skills-to-why-capgemini").click(function( e ){
    if(this.hash !== "") {
      e.preventDefault();

      //Store hash
      var hash = this.hash;
      
      var newPos = $("#why-capgemini").offset().top;
      $('html, body').stop().animate({scrollTop: newPos}, 800, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
      
    }
    
  });

    $("#my-skills-to-home").click(function( e ){
    if(this.hash !== "") {
      e.preventDefault();

      //Store hash
      var hash = this.hash;
      
      var newPos = $("#home-image").offset().top;
      $('html, body').stop().animate({scrollTop: newPos}, 800, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
      
    }
    
  });

    
});