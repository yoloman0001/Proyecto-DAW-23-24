window.addEventListener("load", iniciar);

function iniciar() {
    document.querySelector('#selectCat').addEventListener('change', changeCategoria, false);
    document.querySelector('#selectTem').addEventListener('change', changeTemporada, false);
}

function changeCategoria() {
    const select = document.querySelector("#selectCat");
    if (select.value == 0)
        window.location.href = "/productos";
    else window.location.href = "/productos/porCat/" + select.value;
}

function changeTemporada() {
    const select = document.querySelector("#selectTem");
    if (select.value == 0)
        window.location.href = "/productos";
    else window.location.href = "/productos/porTem/" + select.value;
}
