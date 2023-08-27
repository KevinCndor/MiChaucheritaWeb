const openIngreso = document.getElementById('openIngreso');
const modal_container_Ingreso = document.getElementById('modal_container_Ingreso');
const closeIngreso = document.getElementById('closeIngreso');

openIngreso.addEventListener('click', () => {
    modal_container_Ingreso.classList.add('show');
});

closeIngreso.addEventListener('click', () => {
    modal_container_Ingreso.classList.remove('show');
});

const openEgreso = document.getElementById('openEgreso');
const modal_container_Egreso= document.getElementById('modal_container_Egreso');
const closeEgreso = document.getElementById('closeEgreso');

openEgreso.addEventListener('click', () => {
    modal_container_Egreso.classList.add('show');
});

closeEgreso.addEventListener('click', () => {
    modal_container_Egreso.classList.remove('show');
});

const openAgregarCuenta = document.getElementById('openAgregarCuenta');
const modal_container_AgregarCuenta= document.getElementById('modal_container_AgregarCuenta');
const closeAgregarCuenta = document.getElementById('closeAgregarCuenta');

openAgregarCuenta.addEventListener('click', () => {
    modal_container_AgregarCuenta.classList.add('show');
});

closeAgregarCuenta.addEventListener('click', () => {
    modal_container_AgregarCuenta.classList.remove('show');
});

const openEliminarCuenta = document.getElementById('openEliminarCuenta');
const modal_container_EliminarCuenta= document.getElementById('modal_container_EliminarCuenta');
const closeEliminarCuenta= document.getElementById('closeEliminarCuenta');

openEliminarCuenta.addEventListener('click', () => {
    modal_container_EliminarCuenta.classList.add('show');
});

closeEliminarCuenta.addEventListener('click', () => {
    modal_container_EliminarCuenta.classList.remove('show');
});





