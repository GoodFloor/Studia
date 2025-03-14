let currentCart = localStorage.cart;
if (currentCart == null) {
    currentCart = {content: []};
} else {
    currentCart = JSON.parse(currentCart);
}

function addItem(itemId) {
    for (let i = 0; i < currentCart.content.length; i++) {
        const item = currentCart.content[i];
        if (item.id == itemId) {
            item.amount++;
            localStorage.cart = JSON.stringify(currentCart);
            refreshCounter();
            return;            
        }
    }
    currentCart.content.push({id: itemId, amount: 1});
    localStorage.cart = JSON.stringify(currentCart);
    refreshCounter();
}

function removeItem(itemId) {
    for (let i = 0; i < currentCart.content.length; i++) {
        const item = currentCart.content[i];
        if (item.id == itemId) {
            item.amount--;
            if (item.amount <= 0) {
                currentCart.content.splice(i, 1);
                localStorage.cart = JSON.stringify(currentCart);
                refreshCounter();
                return true;
            }
            localStorage.cart = JSON.stringify(currentCart);
            refreshCounter();
            return false;            
        }
    }
    console.error("Element " + itemId + " not found in cart");
}

function getAmount(itemId) {
    for (let i = 0; i < currentCart.content.length; i++) {
        const item = currentCart.content[i];
        if (item.id == itemId) {
            return item.amount;            
        }
    }
    return 0;
}
