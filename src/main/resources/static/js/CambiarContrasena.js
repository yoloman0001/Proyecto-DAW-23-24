window.addEventListener("load", iniciar);

function iniciar() {
    document.querySelector('#entrar').addEventListener('click', cambiarContraseña, false);
    borrarError();
}

function cambiarContraseña(e) {
    const nuevaContrasena = document.querySelector("#contrasena");
    const contrasenaRepetida = document.querySelector("#repeat-contrasena");

    if (nuevaContrasena.value == '' || contrasenaRepetida.value == '') {
        error(nuevaContrasena, "Completa todos los campos");
        e.preventDefault();
        return false;
    }
    if (nuevaContrasena.value != contrasenaRepetida.value) {
        error(nuevaContrasena, "Las contraseñas no coinciden");
        e.preventDefault();
        return false;
    }
    alert("Has cambiado tu contraseña");
    return true;
}

function error(elemento, mensaje) {
    document.querySelector('#mensajeError').innerHTML = mensaje;
    elemento.focus();
}

function borrarError() {
    const mensajeError = document.querySelector('#mensajeError');
    mensajeError.innerHTML = '';
}