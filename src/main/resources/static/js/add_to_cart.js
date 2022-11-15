$(document).ready(function () {
  $(".buttonAdd2Cart").on("click", function () {
	
    addToCart();
  });
  
  function addToCart() {
	quantity = $("#quantity" + productId).val();
	
	url = contextPath + "cart/add/" + productId + "/" + quantity;
	
	
		$.ajax({
		type: "POST",
		url: url,
		
		})
		.done(function(response) {
			if (name <= "") {
				alert("You must login to add to cart!");
			} else {
				if(quantity > 0 ) {
				alert("Added to cart!");
				} else {
					alert("You must select quantity more than 0!");
				}
			}
		})
		
	
	
	
	
	}
	
		
	
	
});
	
