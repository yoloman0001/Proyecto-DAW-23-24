window.addEventListener("load", iniciar);
// EL METODO A LO MEJOR NO NECESITA UN ARCHIVO PARA EL SOLO
function iniciar() {
    document.querySelector('.boton-compra').addEventListener('click', notificarCompra, false);
}

function notificarCompra() {
    alert("Pago realizado correctamente");
}