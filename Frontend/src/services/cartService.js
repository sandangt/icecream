const createCart = () => {
    localStorage.setItem("cart", JSON.stringify([]));
}

export const addItemToCartService = (product) => {
    if (localStorage.getItem("cart") === null) {
        createCart();
    }
    const currentCart = JSON.parse(localStorage.getItem("cart"));

    // if (currentCart.length === 0) {
    //     currentCart.push({...product, quantity: 1});
    // }
    // else { 
    //     currentCart.some(value => {
    //         if (value.id === product.id) {
    //             value.quantity += 1;
    //             return true;
    //         }
    //         currentCart.push({...product, quantity: 1});
    //         return false;
    //     });
    // }
    currentCart.push(product);
    localStorage.setItem("cart", JSON.stringify(currentCart));   
}

export const removeItemFromCartService = (product) => {
    const currentCart = JSON.parse(localStorage.getItem("cart"));
    currentCart.pop(product);
    localStorage.setItem("cart", JSON.stringify(currentCart));   
}

export const countItemInCartService = () => {
    return localStorage.getItem("cart") === null ? 0 : JSON.parse(localStorage.getItem("cart")).length;
}

export const destroyCartService = () => {
    if (localStorage.getItem("cart") !== null) {
        localStorage.removeItem("cart");
    }
}