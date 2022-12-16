import { Config } from "./config.js";

const d = document
const $productsCar = d.querySelector(".products-car");

export function loadCart() {
    const arrProductsId = JSON.parse(localStorage.getItem('productsId')) || [];

    if (arrProductsId.length == 0) {
        addEmptyCartText($productsCar);
    }
    if (arrProductsId.length >= 0) {
        addBtnGoCart($productsCar);
    }

    d.addEventListener('click', (e) => {
        if (e.target.matches('.btn-car') || e.target.matches('.car-count')) {
            $productsCar.classList.toggle("none-car-products");
        }
    })

}

export const getAddProducts = () => {
    const arrProductsId = JSON.parse(localStorage.getItem('productsId')) || [];
    const arrProducts = [];

    const $listaProductos = d.querySelector(".list-products-car");
    const $carCount = d.querySelector(".car-count");
    const $emptyCart = d.querySelector(".empty-cart");
    const $btnGoCart = d.querySelector(".btn-go-cart");
    let contentHTML = '';
    if (arrProductsId.length == 0) {
        if ($btnGoCart != null) {
            $btnGoCart.remove();
        }
    }
    if (arrProductsId.length > 0) {
        if ($emptyCart != null) {
            $emptyCart.remove();
        }
        $carCount.classList.remove("none-car-products"),
            arrProductsId.forEach(id => {
                fetch(`${location.origin}/api/producto/${id}`)
                    .then(res => res.json())
                    .then(res => {
                        const producto = res.data;
                        arrProducts.push(producto);
                        const urlProductoVista = `${location.origin}/producto/${producto.idProducto}`;

                        contentHTML += `
                    <a href="${urlProductoVista}">
                        <div class="card-car">
                            <div>
                                <img src="/${Config.folder}/${producto.uriImagen}" alt="${producto.nombre}">
                            </div>
                            <section class="card-car-info">
                                <p class="card-car-title">${producto.nombre}</p>
                                <p class="card-car-price">S/${producto.precio}</p>
                            </section>
                            </div>
                            </a>`;
                        $listaProductos.innerHTML = contentHTML;
                        localStorage.setItem("products",JSON.stringify(arrProducts));
                    })
                    .catch(err => console.log(err));
                });
                
                if(!$btnGoCart){
                    addBtnGoCart($productsCar);
                }
            }
            $carCount.innerHTML = arrProductsId.length;
}

const addBtnGoCart = (element) => {
    const $btnGoCart = d.createElement("a");
    $btnGoCart.classList.add("btn-go-cart");
    $btnGoCart.href = `${location.origin}/carrito`
    $btnGoCart.innerHTML = 'Ir al carrito';
    element.appendChild($btnGoCart);
}
const addEmptyCartText = (element) => {
    const $emptyCart = d.createElement("h4");
    $emptyCart.classList.add("empty-cart");
    $emptyCart.innerHTML = 'Tu carrito esta vacío';
    element.appendChild($emptyCart);
}