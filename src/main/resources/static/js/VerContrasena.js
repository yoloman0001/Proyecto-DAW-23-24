window.addEventListener("load", iniciar);

function iniciar() {
    document.querySelector('.toggle-contrasena').addEventListener('click', mostrar, false);
}

function mostrar() {
    let contraseña = document.querySelector("#contrasena");
    if (contraseña.className == 'contrasenaFalse') {
        contraseña.className = 'contrasenaTrue';
    }
    else {
        contraseña.className = 'contrasenaFalse';
    }
}