refreshCounter();
let itemsList = document.getElementById("productsList");
if (currentCart.content.length > 0) {
    itemsList.innerHTML = "";
    let table = document.createElement("table");
    let row0 = document.createElement("tr");
    let header1 = document.createElement("th");
    header1.innerHTML = "Product name";
    header1.className = "productName";
    let header2 = document.createElement("th");
    header2.innerHTML = "Amount";
    header2.className = "amountHeader";
    header2.colSpan = 3;
    row0.appendChild(header1);
    row0.appendChild(header2);
    table.appendChild(row0);

    for (let i = 0; i < currentCart.content.length; i++) {
        const item = currentCart.content[i];
        let row = document.createElement("tr");
        row.className = "item";
        row.id = "row" + i;
        let itemName = document.createElement("td");
        itemName.innerHTML = item.id;
        itemName.className = "productName";
        let decrease = document.createElement("button");
        decrease.innerHTML = "-";
        decrease.addEventListener("click", function() {
            if (removeItem(item.id)) {
                table.removeChild(row);
                if (currentCart.content.length == 0) {
                    itemsList.innerHTML = "No products in your cart yet!";
                }
            } else {
                amount.innerHTML = item.amount;
            }
        });
        let amount = document.createElement("td");
        amount.innerHTML = item.amount;
        let increase = document.createElement("button");
        increase.innerHTML = "+";
        increase.addEventListener("click", function() {
            addItem(item.id);
            amount.innerHTML = item.amount;
        })
        
        row.appendChild(itemName);
        let td = document.createElement("td");
        let div = document.createElement("div");
        div.appendChild(decrease);
        td.appendChild(div);
        row.appendChild(td);
        row.appendChild(amount);
        td = document.createElement("td");
        div = document.createElement("div");
        div.appendChild(increase);
        td.appendChild(div);
        row.appendChild(td);
        table.appendChild(row);
    }
    itemsList.appendChild(table);
}