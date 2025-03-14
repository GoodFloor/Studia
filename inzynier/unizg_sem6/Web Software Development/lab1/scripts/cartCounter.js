function refreshCounter() {
    let counter = document.getElementById("cartCounter");
    if (currentCart.content.length > 0) {
        let sum = 0;
        for (let i = 0; i < currentCart.content.length; i++) {
            const item = currentCart.content[i];
            sum += item.amount;
        }
        counter.innerHTML = sum;
    } else {
        counter.innerHTML = "";
    }
}