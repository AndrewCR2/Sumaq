const d = document;

const $btnSearch = d.querySelector('.btn-search');
const $searchContainer = d.querySelector('.search_container');
const $inputSearch = d.querySelector('.input_search');
const $results = d.querySelector('#search_results ul');

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
})
d.addEventListener('submit', (e) => {
    e.preventDefault();

    const value = $inputSearch.value;

    fetch(location.origin + `/api/producto/buscar?q=${value}`)
        .then(res => res.json())
        .then(res => {
            const productosArr = res.listData;
            let resultsHtml = '';

            productosArr.forEach(producto => {
                resultsHtml += `
                <li>
                    <img src="/UploadsImg/${producto.uriImagen}" alt="${producto.nombre}">
                    <div>
                        <p class="result-title">${producto.nombre}</p>
                        <p class="result-desc">${producto.descripcion}</p>
                    </div>
                </li>`
            });
            $results.innerHTML = resultsHtml;
        })
        .catch(err => console.log(err));

})