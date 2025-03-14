const express = require('express');
const session = require('express-session');
const data = require('../data/mydata');
const router = express.Router();

router.get('/', (req, res) => {
    res.status(404);
});

router.get('/getAll', (req, res) => {
    let productsList = data.categories;
    let cartContent = [];
    let cartContentCounter = 0;
    if (req.session.cart != undefined) {
        req.session.cart.forEach(element => {
            cartContent.push({
                "id": element.category + "_" + element.item,
                "name": productsList[element.category].products[element.item].name,
                "amount": element.count
            });
            cartContentCounter += element.count;
        });
    }

    res.render('cart', {
        title: "Your cart",
        currentCategoryName: "Cart",
        cart: cartContent,
        cartCounter: cartContentCounter
    });
});

router.get('/add/:id([0-9]{1,2}_[0-9]{1,9})', (req, res) => {
    let complexId = req.params.id.split('_');
    let categoryId = complexId[0];
    let productId = complexId[1];
    for (let index = 0; index < req.session.cart.length; index++) {
        const element = req.session.cart[index];
        if (element.category == categoryId && element.item == productId) {
            element.count++;
            break;
        }
    }

    res.redirect('/cart/getAll');
});

router.get('/remove/:id([0-9]{1,2}_[0-9]{1,9})', (req, res) => {
    let complexId = req.params.id.split('_');
    let categoryId = complexId[0];
    let productId = complexId[1];
    for (let index = 0; index < req.session.cart.length; index++) {
        const element = req.session.cart[index];
        if (element.category == categoryId && element.item == productId) {
            element.count--;
            if (element.count <= 0) {
                req.session.cart.splice(index, 1);
            }
            break;
        }
    }

    res.redirect('/cart/getAll');
});

module.exports = router;