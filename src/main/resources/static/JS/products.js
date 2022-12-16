import { Config } from "./config.js";

const d = document;

d.addEventListener("DOMContentLoaded", ()=>{
    loadChange();
})

function loadChange(){
    const $listProducts = d.querySelector(".list-all-products");
    let contentHTML = '';

    fetch(`${location.origin}/api/producto/listar`)
        .then(res => res.json())
        .then(res => {
            const arrProducts = res.listData;
            arrProducts.forEach( producto => {
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
                $listProducts.innerHTML = contentHTML;
            });
        })
        .catch(err => console.log(err));

}