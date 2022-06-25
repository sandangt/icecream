const createCart = () => {
    localStorage.setItem("cart", JSON.stringify([]));
}

export const addItemToCartService = (product) => {
    if (localStorage.getItem("cart") === null) {
        createCart();
    }
    const currentCart = JSON.parse(localStorage.getItem("cart"));
    const ifExist = (currentCart.some(value => {
        if (value.id === product.id) {
            value.quantity += 1;
            return true;
        }
        return false;
    }));
    if (currentCart.length === 0 || !ifExist) {
        currentCart.push({...product, quantity: 1});
    }   

    localStorage.setItem("cart", JSON.stringify(currentCart));
}

export const setNewCartService = (newCart) => {
    localStorage.setItem("cart", JSON.stringify(newCart));
}

export const removeItemFromCartService = (product) => {
    let currentCart = JSON.parse(localStorage.getItem("cart"));
    currentCart = currentCart.filter(value => value.id !== product.id)
    localStorage.setItem("cart", JSON.stringify(currentCart));   
}

export const countItemInCartService = () => {
    return (localStorage.getItem("cart") === null || JSON.parse(localStorage.getItem("cart")).length === 0) ? 0 : 
    JSON.parse(localStorage.getItem("cart")).map(value=>(value.quantity )).reduce( (accumulator, currentVal) => {
        return accumulator + currentVal;
    });
}

export const costOfCartService = () => {
    return (localStorage.getItem("cart") === null || JSON.parse(localStorage.getItem("cart")).length === 0) ? 0 : 
    JSON.parse(localStorage.getItem("cart")).map(value=>(value.quantity * value.price)).reduce( (accumulator, currentVal) => {
        return accumulator + currentVal;
    }).toFixed(3);
}

export const destroyCartService = () => {
    if (localStorage.getItem("cart") !== null) {
        localStorage.removeItem("cart");
    }
}