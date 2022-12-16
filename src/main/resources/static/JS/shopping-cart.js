import { Config } from "./config.js";

const d = document;
d.addEventListener("DOMContentLoaded", () => {
    loadShoppingCart();
})
function loadShoppingCart() {

    const $cartCount = d.querySelector(".my-cart-count");
    const $listProducts = d.querySelector(".list-products-cart");
    const $totalPrice = d.querySelector(".total-price");
    const $productsContent = d.querySelector("main .content");
    const $emptyCart = d.querySelector(".empty-cart-list");

    //Traer los productos que se cargaron del carrito
    const arrProducts = JSON.parse(localStorage.getItem('products')) || [];

    if (arrProducts.length == 0) {
        $emptyCart.classList.remove("display-none");
        $productsContent.classList.add("display-none");
    } else {
        $productsContent.classList.remove("display-none");
        $emptyCart.classList.add("display-none");
    }


    $cartCount.innerHTML = arrProducts.length;

    let contentHTML = '';
    let totalPrice = 0;

    arrProducts.forEach(producto => {

        const urlProductoVista = `${location.origin}/producto/${producto.idProducto}`;
        totalPrice += parseInt(producto.precio);

        contentHTML +=
            `
            <li class="card-cart">
                <img src="/${Config.folder}/${producto.uriImagen}" alt="${producto.nombre}">
                <div class="card-cart-info">
                    <div>
                        <p class="card-cart-name">${producto.nombre}</p>
                        <p class="card-cart-desc">${producto.descripcion}</p>
                    </div>
                    
                </div>
                <span class="card-cart-price">
                    S/${producto.precio}
                </span>
                <span >
                    <button class="btn-delete-product-cart" data-id="${producto.idProducto}">Eliminar</button>
                </span>
            </li>
            `;

        $totalPrice.innerHTML = `${totalPrice}`;

        $listProducts.innerHTML = contentHTML;
    });
}


d.addEventListener("click", (e) => {
    if (e.target.matches(".btn-delete-product-cart")) {
        const id = parseInt(e.target.getAttribute("data-id"));
        deleteProductFromCart(id);
    }
})

function deleteProductFromCart(id) {
    console.log(id);
    const arrProductsId = JSON.parse(localStorage.getItem('productsId')) || [];
    const arrProducts = JSON.parse(localStorage.getItem('products')) || [];

    const arrID = arrProductsId.filter(pid => pid != id);
    const arr = arrProducts.filter(producto => producto.idProducto != id);

    localStorage.setItem("productsId", JSON.stringify(arrID));
    localStorage.setItem("products", JSON.stringify(arr));

    window.location.reload()
}

