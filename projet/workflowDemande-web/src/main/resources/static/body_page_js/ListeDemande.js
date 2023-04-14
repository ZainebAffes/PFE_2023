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
    $('#btnMAJDemande').unbind('click');
    $('#btnMAJDemande').bind('click', function () {
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


    $('#rafresh').unbind('click');
    $('#rafresh').bind('click', function (e) {
        showLoadingNotification();
        setTimeout(function () {
            DrawTableDemande();
            hideLoadingNotification();
        }, 50);
    });
    ////select
    // Récupérer la liste déroulante par ID
    var selectList = document.getElementById("codeTypeDemande");

// Envoyer une requête AJAX à l'API pour récupérer les données de la base de données
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:9011/workflowDemande-core/api/typedemandes/filter", true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Convertir la réponse JSON en objet JavaScript
            var data = JSON.parse(xhr.responseText);

            // Parcourir les données et créer des options pour la liste déroulante
            for (var i = 0; i < data.length; i++) {
                var option = document.createElement("option");
                option.value = data[i].codeTypeDemande;
                option.text = data[i].description;
                selectList.appendChild(option);
            }
        }
    };
    xhr.send();



    ///////////// drag and drop 
    const tags = document.querySelectorAll('.tag');
    const dropzones = document.querySelector('.dropzones');
    let draggedTag = null;
//    // DRAG
    tags.forEach(tag => {
        tag.addEventListener('dragstart', () => {
            draggedTag = tag.cloneNode(true);
            tag.classList.add('dragged');
        });

        tag.addEventListener('dragend', () => {
            draggedTag = null;
            tag.classList.remove('dragged');
            draggedType = tag.getAttribute('data-type');
        });
    });

    function dragEnter(e) {
        e.preventDefault();
        e.target.classList.add('drag-over');
    }
    // DROP
    dropzones.addEventListener('dragover', e => {
        e.preventDefault();
        const afterElement = getDragAfterElement(e.clientY);

        const draggable = document.querySelector('.dragged');
        if (afterElement === null) {
            if (draggedTag) {
                dropzones.appendChild(draggedTag);
            }
        } else {
            if (draggedTag) {
                dropzones.insertBefore(draggedTag, afterElement);

            }
        }
    });
    dropzones.addEventListener('drop', e => {
        e.preventDefault();
        if (draggedTag) {

//            const tagType = draggedTag.querySelector('input').getAttribute('type');
//           if (tagType) {

            //input: le nom du champs
            const newInput = document.createElement('input');
            //newInput.classList.add('tag');
            newInput.setAttribute('type', 'text');
            newInput.setAttribute('placeholder', 'Saisir le nom du champ');
            newInput.classList.add('dropped-tag');
            newInput.setAttribute('id', 'nom');
            newInput?.setAttribute('id', 'box');
          
            draggedTag.appendChild(newInput);
            //dropzones.insertBefore(newInput, draggedTag);


            //input description
            const newInput1 = document.createElement('input');
            //newInput.classList.add('tag');
            newInput1.setAttribute('type', 'text');
            newInput1.classList.add('dropped-tag');
            newInput1.setAttribute('id', 'input1');

            draggedTag.appendChild(newInput1);

            const requiredLabel1 = document.createElement('label1');
            requiredLabel1.innerHTML = 'description ';
            requiredLabel1.setAttribute('for', 'input1');


            draggedTag.appendChild(requiredLabel1);
            draggedTag.appendChild(newInput1);
            //selon type 
            const newInput2 = document.createElement('input');
            newInput2.classList.add('dropped-tag');
            const draggedType = draggedTag.getAttribute('data-type');

            if (draggedType === 'textt') {
                newInput2.setAttribute('type', 'text');
                newInput2.setAttribute('placeholder', 'text');
            } else if (draggedType === 'date') {
                newInput2.setAttribute('type', 'date');
                newInput2.setAttribute('class', ' form-control datepicker input-xs');
                newInput2.setAttribute('data-mask-clearifnotmatch', 'true');
            } else if (draggedType === 'temps') {
                newInput2.setAttribute('type', 'time');
            } else if (draggedType === 'nombre') {
                newInput2.setAttribute('type', 'number');
                newInput2.setAttribute('placeholder', 'Saisir une valeur numérique');
            }
            //case a cocher
            let i = 1;

if (draggedType === 'caseCocher') {
    const requiredLabel2 = document.createElement('label');
    requiredLabel2.innerHTML = "Option";
    requiredLabel2.setAttribute('for', 'Checkbox-' + i);

    const checkboxWrapper = document.createElement('div');
    checkboxWrapper.classList.add('checkbox-wrapper');

    const checkboxInput = document.createElement('input');
    checkboxInput.setAttribute('type', 'checkbox');
    checkboxInput.setAttribute('id', 'Checkbox-' + i);

    const optionInput = document.createElement('input');
    optionInput.setAttribute('type', 'text');
    optionInput.setAttribute('placeholder', 'option');

    checkboxWrapper.appendChild(checkboxInput);
    checkboxWrapper.appendChild(optionInput);

    draggedTag.appendChild(requiredLabel2);
    draggedTag.appendChild(checkboxWrapper);

    const addButton = document.createElement('button');
    addButton.innerHTML = '+';
    addButton.addEventListener('click', () => {
        i++;

        const newCheckboxWrapper = document.createElement('div');
        newCheckboxWrapper.classList.add('checkbox-wrapper');

        const newCheckboxInput = document.createElement('input');
        newCheckboxInput.setAttribute('type', 'checkbox');
        newCheckboxInput.setAttribute('id', 'Checkbox-' + i);

        const newOptionInput = document.createElement('input');
        newOptionInput.setAttribute('type', 'text');
        newOptionInput.setAttribute('placeholder', 'option');

        newCheckboxWrapper.appendChild(newCheckboxInput);
        newCheckboxWrapper.appendChild(newOptionInput);

        draggedTag.appendChild(newCheckboxWrapper);
    });

    draggedTag.appendChild(addButton);
}



            draggedTag.appendChild(newInput2);

            //btn supprimer
            const deleteButton = document.createElement('button');
            deleteButton.innerHTML = 'Supprimer';
            deleteButton.classList.add('delete-tag');
            draggedTag.appendChild(deleteButton);

            deleteButton.addEventListener('click', (e) => {
                const parentTag = e.target.parentElement;
                const grandParentTag = parentTag.parentElement;
                //grandParentTag.removeChild(newInput);
                grandParentTag.removeChild(parentTag);

            });

            // Add checkbox to make field required
            const requiredCheckbox = document.createElement('input');
            requiredCheckbox.setAttribute('type', 'checkbox');
            requiredCheckbox.setAttribute('id', 'requiredCheckbox');


            const requiredLabel = document.createElement('label');
            requiredLabel.innerHTML = 'Champ obligatoire';
            requiredLabel.setAttribute('for', 'requiredCheckbox');
            draggedTag.appendChild(requiredCheckbox);
            draggedTag.appendChild(requiredLabel);



            //message d'alert
            newInput.addEventListener('blur', (e) => {
                if (requiredCheckbox.checked && !e.target.value) {
                    alert('Veuillez remplir ce champ.');
                    e.target.focus();
                }
            });
            // }
        }
    });

    function getDragAfterElement(y) {

        const draggableElements = [...dropzones.querySelectorAll('.tag:not(.dragged)')];

        return draggableElements.reduce((closest, child) => {
            const box = child.getBoundingClientRect();
            const offset = y - box.top - box.height / 2;
            if (offset < 0 && offset > closest.offset) {
                return {
                    offset: offset,
                    element: child

                };
            } else {
                return closest;
            }
        }, {offset: Number.NEGATIVE_INFINITY}).element;
    }
});


