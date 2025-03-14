let productsList = document.getElementById("productsList").children;
for (let index = 0; index < productsList.length; index++) {
    const item = document.getElementById("item" + index);
    const cart = document.getElementById("cart" + index);
    item.addEventListener("mouseover", function() {
        cart.className = "addToCart";
    });
    item.addEventListener("mouseout", function() {
        cart.className = "hidden";
    });
    // cart.addEventListener("click", function() {
    //     let counter = document.getElementById("cartCounter");
    //     counter.innerHTML = parseInt(counter.innerHTML) + 1;
    // });
}
