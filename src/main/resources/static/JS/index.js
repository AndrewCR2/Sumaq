import { Config } from "./config.js";
import { getAddProducts, loadCart } from "./btn-car.js";
import {search } from "./search.js";

document.addEventListener('DOMContentLoaded', ()=>{
    showSomeProducts();
    search();
    loadCart();
    getAddProducts();
})

function showSomeProducts(){
    const $listProducts = document.querySelector(".list-products");
    let contentHTML = '';
    
    fetch(location.origin + `/api/producto/limit?limit=3`)
        .then(res => res.json())
        .then( res => {
            const productosArr = res.listData;

            productosArr.forEach( producto => {
                const urlProductoVista = `${location.origin}/producto/${producto.idProducto}`;

                contentHTML+=`
                <div class="card-product" >
                <a href="${urlProductoVista}">
                            <img src="/${Config.folder}/${producto.uriImagen}" alt="${producto.nombre}">
                            </a>
                            <p class="product-title">${producto.nombre}</p>
                            <div>
                                <span class="product-price">S/${producto.precio}</span>
                                <button class="btn-add-car" data-id="${producto.idProducto}" >Agregar al carrito</button>
                            </div>
                        </div>
                `;
            });

            $listProducts.innerHTML = contentHTML;
        })
        .catch(err => err);
}
