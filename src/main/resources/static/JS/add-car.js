
const arrProductsId = JSON.parse(localStorage.getItem('productsId')) || [];
const $btnAddCar = document.querySelector('#btn-add-car');

$btnAddCar.addEventListener('click',()=>{

    const id = $btnAddCar.getAttribute('data-id');
    console.log(id);
    arrProductsId.push(id);
    localStorage.setItem('productsId',JSON.stringify(arrProductsId));
})

