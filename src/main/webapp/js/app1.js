document.addEventListener('DOMContentLoaded', function() {
    var divs = document.querySelectorAll('.clickable-div');
    var showAllButton = document.getElementById('showAll');

    // Oculta los demás divs al hacer clic en uno
    divs.forEach(function(div) {
        div.addEventListener('click', function() {
            divs.forEach(function(innerDiv) {
                if (innerDiv !== div) {
                    innerDiv.classList.add('hidden');
                }
            });
        });
    });

    // Muestra todos los divs al hacer clic en el botón
    showAllButton.addEventListener('click', function() {
        divs.forEach(function(div) {
            div.classList.remove('hidden');
        });
    });
});

document.addEventListener('DOMContentLoaded', function() {
    var divs1 = document.querySelectorAll('.clickable-div1');
    var showAllButton1 = document.getElementById('showAll1');

    // Oculta los demás divs al hacer clic en uno
    divs1.forEach(function(div1) {
        div1.addEventListener('click', function() {
            divs1.forEach(function(innerDiv1) {
                if (innerDiv1 !== div1) {
                    innerDiv1.classList.add('hidden');
                }
            });
        });
    });

    // Muestra todos los divs al hacer clic en el botón
    showAllButton1.addEventListener('click', function() {
        divs1.forEach(function(div1) {
            div1.classList.remove('hidden');
        });
    });
});
