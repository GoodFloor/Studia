refreshCounter();
let productsList = document.getElementById("productsList");
if (currentCategoryData.products.length > 0) {
    productsList.innerHTML = "";
}
for (let i = 0; i < currentCategoryData.products.length; i++) {
    let item = document.createElement("div");
    item.className = "item";
    let figure = document.createElement("figure");
    let image = document.createElement("img");
    image.src = "images/" + currentCategoryData.products[i].image;
    image.className = "itemImage";
    let cart = document.createElement("img");
    cart.src = "images/cart.png";
    cart.id = "addToCart" + i;
    cart.className = "hidden";
    let amount = document.createElement("div");
    amount.id = "amount" + i;
    if (getAmount(currentCategoryData.products[i].name) > 0) {
        amount.innerHTML = getAmount(currentCategoryData.products[i].name);
        amount.className = "amount";
    } else {
        amount.className = "hidden";
    }
    figure.appendChild(image);
    figure.appendChild(cart);
    figure.appendChild(amount);
    let name = document.createElement("p");
    name.innerHTML = currentCategoryData.products[i].name;
    name.className = "itemName";
    let cat = document.createElement("p");
    cat.innerHTML = currentCategoryName;
    cat.className = "itemCategory";

    item.addEventListener("mouseover", function() {
        cart.className = "addToCart";
    });
    item.addEventListener("mouseout", function() {
        cart.className = "hidden";
    });
    cart.addEventListener("click", function() {
        addItem(currentCategoryData.products[i].name);
        document.getElementById("amount" + i).className = "amount";
        document.getElementById("amount" + i).innerHTML = getAmount(currentCategoryData.products[i].name);
    });
    
    item.appendChild(figure);
    item.appendChild(name);
    item.appendChild(cat);
    productsList.appendChild(item);
}
