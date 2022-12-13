const d = document;

const $btnSearch = d.querySelector('.btn-search');
const $searchContainer = d.querySelector('.search_container');
const $inputSearch = d.querySelector('.input_search');
const $results = d.querySelector('#search_results ul');
const $formSearch = d.querySelector('#form-search');

d.addEventListener('click', (e) => {
    if (e.target.matches('.btn-search')) {
        $searchContainer.classList.toggle('none-modal-search');
        $inputSearch.focus();
    }
    if (e.target.matches('.search_container')) {
        $searchContainer.classList.toggle('none-modal-search');
    }
    if (e.target.matches('.btn-modal-close')) {
        e.preventDefault();
        $searchContainer.classList.toggle('none-modal-search');
    }
    if (e.target.matches('.btn-car')) {
        getAddProducts();
    }
})
$formSearch.addEventListener('submit', (e) => {
    e.preventDefault();

    const value = $inputSearch.value;

    fetch(location.origin + `/api/producto/buscar?q=${value}`)
        .then(res => res.json())
        .then(res => {
            const productosArr = res.listData;
            let resultsHtml = '';

            productosArr.forEach(producto => {

                const urlProductoVista = `${location.origin}/producto/${producto.idProducto}`;

                // <img src="/ProductoImg/${producto.uriImagen}" alt="${producto.nombre}">
                resultsHtml += `
                <li>
                    <a href="${urlProductoVista}">
                        <img src="/UploadsImg/${producto.uriImagen}" alt="${producto.nombre}">
                        <div>
                            <p class="result-title">${producto.nombre}</p>
                            <p class="result-desc">${producto.descripcion}</p>
                        </div>
                    </a>
                </li>`
            });
            $results.innerHTML = resultsHtml;
        })
        .catch(err => console.log(err));
})

const getAddProducts = () => {
    const arrProductsId = JSON.parse(localStorage.getItem('productsId'));

    arrProductsId.forEach(id => {
        fetch(`${location.origin}/api/producto/${id}`)
        .then(res => res.json())
        .then(res => {
            const producto = res.data;
            console.log(producto);
        })
        .catch(err => console.log(arr));
    })
}