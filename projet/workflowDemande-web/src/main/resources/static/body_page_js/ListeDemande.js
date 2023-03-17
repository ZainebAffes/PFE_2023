$(function () {
    drawBtnDemande();
    DrawTableDemande();
    $('#rafresh').unbind('click');
    $('#rafresh').bind('click', function (e) {
        showLoadingNotification();
        setTimeout(function () {
            DrawTableDemande();
            hideLoadingNotification();
        }, 50);
    });
    $('#btnMAJFam').unbind('click');
    $('#btnMAJFam').bind('click', function () {
        window.parent.$.loader.open();
        setTimeout(function () {
            submitMAJDemande();
            window.parent.$.loader.close();
        }, 100);
    });

    $('#btnFermer').unbind('click');
    $('#btnFermer').bind('click', function (e) {
        window.parent.$('ul.ui-tabs-nav .active .closeRad').click();
    });

    $('.filtreActif').unbind('click');
    $('.filtreActif').bind('click', function (e) {
        $('.filtreActif').find('i').removeClass('fa-check-circle');
        $('.filtreActif').find('i').removeClass('fa-search');
        $('.filtreActif').find('i').addClass('fa-search');
        $(this).find('i').eq(0).removeClass('fa-search');
        $(this).find('i').eq(0).addClass('fa-check-circle');
        window.parent.$.loader.open();
        setTimeout(function () {
            DrawTableDemande();
            window.parent.$.loader.close();
        }, 100);
    });
    
    $('#rafresh').unbind('click');
    $('#rafresh').bind('click', function (e) {
        showLoadingNotification();
        setTimeout(function () {
            DrawTableDemande();
            hideLoadingNotification();
        }, 50);
    });
    
    const item = document.querySelector('.item');

item.addEventListener('dragstart', dragStart);

function dragStart(e) {
    e.dataTransfer.setData('text/plain', e.target.id);
    setTimeout(() => {
        e.target.classList.add('hide');
    }, 0);
}


/* drop targets */
const boxes = document.querySelectorAll('.box');

boxes.forEach(box => {
    box.addEventListener('dragenter', dragEnter)
    box.addEventListener('dragover', dragOver);
    box.addEventListener('dragleave', dragLeave);
    box.addEventListener('drop', drop);
});


function dragEnter(e) {
    e.preventDefault();
    e.target.classList.add('drag-over');
}

function dragOver(e) {
    e.preventDefault();
    e.target.classList.add('drag-over');
}

function dragLeave(e) {
    e.target.classList.remove('drag-over');
}

function drop(e) {
    e.target.classList.remove('drag-over');

    // get the draggable element
    const id = e.dataTransfer.getData('text/plain');
    const draggable = document.getElementById(id);

    // add it to the drop target
    e.target.appendChild(draggable);

    // display the draggable element
    draggable.classList.remove('hide');
}
});
function allowDrop(ev) {
  ev.preventDefault();
}

function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
  ev.preventDefault();
  var data = ev.dataTransfer.getData("text");
  ev.target.appendChild(document.getElementById(data));
}