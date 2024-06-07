window.addEventListener("load", iniciar);

function iniciar() {
    document.querySelector('#entrar').addEventListener('click', verificarNuevaCuenta, false);
    borrarError();
}

function verificarNuevaCuenta(e) {
    const usuario = document.querySelector('#nombre');
    const email = document.querySelector('#email');
    const direccion = document.querySelector('#direccion');
    const contraseña = document.querySelector('#contrasena');
    if (!usuario.checkValidity()) {
        if (usuario.validity.valueMissing) {
            error(usuario, 'Introduce un nombre de usuario');
        }
        e.preventDefault();
        return false;
    }
    if (!email.checkValidity()) {
        if (email.validity.valueMissing) {
            error(email, 'Introduce un correo electrónico');
        }
        e.preventDefault();
        return false;
    }
    if (!direccion.checkValidity()) {
        if (direccion.validity.valueMissing) {
            error(direccion, 'Introduce una dirección');
        }
        e.preventDefault();
        return false;
    }
    if (!contraseña.checkValidity()) {
        if (contraseña.validity.valueMissing) {
            error(contraseña, 'Introduce una contraseña');
            e.preventDefault();
            return false;
        }
        error(contraseña, 'La contraseña debe tener mínimo 4 caracteres');
        e.preventDefault();
        return false;
    }
    alert("Cuenta creada correctamente, inicie sesión para continuar");
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