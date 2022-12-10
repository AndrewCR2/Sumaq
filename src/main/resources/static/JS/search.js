const d = document;

const $btnSearch = d.querySelector('.btn-search');
const $searchContainer = d.querySelector('.search_container');

d.addEventListener('click', (e)=>{
    if(e.target.matches('.btn-search')){
        $searchContainer.classList.toggle('none-modal-search');
    }
    if(e.target.matches('.search_container')){
        $searchContainer.classList.toggle('none-modal-search');
    }
    if(e.target.matches('.btn-modal-close')){
        e.preventDefault();
        $searchContainer.classList.toggle('none-modal-search');
    }
})