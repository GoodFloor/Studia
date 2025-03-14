refreshCounter();
let itemsList = document.getElementById("productsList");
if (currentCart.content.length > 0) {
    itemsList.innerHTML = "";
    let content = document.createElement("div");
    content.className = "cartContent";
    
    let cell = document.createElement("p");
    cell.className = "productName header";
    cell.innerHTML = "Product name";
    content.appendChild(cell);

    cell = document.createElement("p");
    cell.className = "amountHeader header";
    cell.innerHTML = "Amount";
    content.appendChild(cell);

    for (let i = 0; i < currentCart.content.length; i++) {
        const item = currentCart.content[i];

        let nameCell = document.createElement("p");
        nameCell.className = "productName item" + i;
        nameCell.innerHTML = item.id;
        content.appendChild(nameCell);

        let decreaseBtn = document.createElement("button");
        decreaseBtn.className = "decrease item" + i;
        decreaseBtn.innerHTML = "-";
        decreaseBtn.addEventListener("click", function() {
            if (removeItem(item.id)) {
                content.removeChild(nameCell);
                content.removeChild(decreaseBtn);
                content.removeChild(amountCell);
                content.removeChild(increaseBtn);
                if (currentCart.content.length == 0) {
                    itemsList.innerHTML = "No products in your cart yet!";
                }
            } else {
                amountCell.innerHTML = item.amount;
            }
        });
        content.appendChild(decreaseBtn);

        let amountCell = document.createElement("p");
        amountCell.className = "amount item" + i;
        amountCell.innerHTML = item.amount;
        content.appendChild(amountCell);

        let increaseBtn = document.createElement("button");
        increaseBtn.className = "increase item" + i;
        increaseBtn.innerHTML = "+";
        increaseBtn.addEventListener("click", function() {
            addItem(item.id);
            amountCell.innerHTML = item.amount;
        });
        content.appendChild(increaseBtn);
    }
    itemsList.appendChild(content);
}