window.addEventListener("load", iniciar);

function iniciar() {
    document.querySelector('#lupa').addEventListener('click', buscar, false);
}

function buscar() {
    const texto = document.querySelector('#buscador').value;
    if (texto != "") {
        window.location.href = "/productos/buscar/" + texto;
    }
}
