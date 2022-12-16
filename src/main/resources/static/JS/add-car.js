import { getAddProducts } from "./btn-car.js";


const arrProductsId = JSON.parse(localStorage.getItem('productsId')) || [];

document.addEventListener('click',(e)=>{
    
    if (e.target.matches('.btn-add-car')) {
        
        const $btnAddCar = e.target;
        const id = $btnAddCar.getAttribute('data-id');

        // validar si el productos ya esta agregado
        if(arrProductsId.includes(id)) return;

        arrProductsId.push(id);
        localStorage.setItem('productsId',JSON.stringify(arrProductsId));
        getAddProducts();
    }

})

