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
      ////select les etats 
    // Récupérer la liste déroulante par ID
    var selectList = document.getElementById("code");
// Envoyer une requête AJAX à l'API pour récupérer les données de la base de données
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:9011/workflowDemande-core/api/etats", true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Convertir la réponse JSON en objet JavaScript
            var data = JSON.parse(xhr.responseText);
            // Parcourir les données et créer des options pour la liste déroulante
            for (var i = 0; i < data.length; i++) {
                var option = document.createElement("option");
                option.value = data[i].code;
                option.text = data[i].designation;
                selectList.appendChild(option);
            }
        }
    };
    xhr.send();
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
            draggedTag.classList.add('dragging');
            //input: le nom du champs
            const newInput = document.createElement('input');
            newInput.setAttribute('type', 'text');
            newInput.setAttribute('placeholder', 'Saisir le nom du champ');
            newInput.classList.add('dropped-nom');
            newInput.classList.add('nom');
            newInput.setAttribute('id', 'nom');

            draggedTag.appendChild(newInput);
            draggedTag.classList.remove('tag');
            if (event.preventDefault)
                event.preventDefault();


            //selon type 
            const newInput2 = document.createElement('input');
            newInput2.classList.add('dropped-tag');
            const draggedType = draggedTag.getAttribute('data-type');

            if (draggedType === 'text') {
                newInput2.setAttribute('type', 'text');
                newInput2.setAttribute('id', 'text');
                newInput2.setAttribute('placeholder', 'text');
                newInput2.setAttribute('for', 'input');
            } else if (draggedType === 'date') {
                newInput2.setAttribute('type', 'date');
                 newInput2.setAttribute('id', 'date');
            } else if (draggedType === 'time') {
                newInput2.setAttribute('type', 'time');
                newInput2.setAttribute('id', 'time');
            } else if (draggedType === 'nombre') {
                newInput2.setAttribute('type', 'number');
                newInput2.setAttribute('id', 'number');
                newInput2.setAttribute('placeholder', 'Saisir une valeur numérique');
                const minValue = 0;
                const maxValue = 100;
                newInput2.setAttribute('min', minValue);
                newInput2.setAttribute('max', maxValue);
                newInput2.addEventListener('change', () => {
                    const value = parseInt(newInput2.value);
                    if (isNaN(value) || value < minValue || value > maxValue) {
                        newInput2.value = '';
                    }
                });
                //checkbox
            } else if (draggedType === 'checkbox') {

                const addOptionBtn = document.createElement('button');
                addOptionBtn.innerHTML = 'Case à cocher';
                addOptionBtn.classList.add('add-option-btn');
                draggedTag.appendChild(addOptionBtn);

                const optionsContainer = document.createElement('div');
                optionsContainer.classList.add('options-container');
                draggedTag.appendChild(optionsContainer);

                let optionCount = 1;

                addOptionBtn.addEventListener('click', () => {
                    const newOption = document.createElement('div');
                    newOption.classList.add('option');

                    const newCheckbox = document.createElement('input');
                    newCheckbox.setAttribute('type', 'checkbox');
                    newCheckbox.setAttribute('id', 'checkbox');                   
                   // newCheckbox.setAttribute('id', `option${optionCount}`);
                   
                    newOption.appendChild(newCheckbox);


                    const newInputOption = document.createElement('input');
                    newInputOption.setAttribute('type', 'text');
                    newInputOption.setAttribute('placeholder', `Option ${optionCount}`);
                    newInputOption.addEventListener('input', () => {

                    });
                    newOption.appendChild(newInputOption);

                    optionsContainer.appendChild(newOption);

                    optionCount++;
                });
                //Liste deroulante
            } else if (draggedType === 'listeDeroulante') {

                const optionsContainer = document.createElement('div');
                optionsContainer.classList.add('options-container');
                draggedTag.appendChild(optionsContainer);

                const selectContainer = document.createElement('div');
                selectContainer.classList.add('select-container');
                optionsContainer.appendChild(selectContainer);

                const select = document.createElement('select');
                selectContainer.appendChild(select);

                const addOptionBtnContainer = document.createElement('div');
                addOptionBtnContainer.classList.add('add-option-btn-container');
                optionsContainer.appendChild(addOptionBtnContainer);

                const addOptionInput = document.createElement('input');
                addOptionInput.setAttribute('type', 'text');
                addOptionInput.setAttribute('placeholder', 'Ajouter une option');
                addOptionInput.classList.add('option-input');
                addOptionBtnContainer.appendChild(addOptionInput);

                const addOptionBtn2 = document.createElement('button');
                addOptionBtn2.innerHTML = '+';
                addOptionBtn2.classList.add('add-option-btn2');
                addOptionBtnContainer.appendChild(addOptionBtn2);

                let optionCount = 1;

                addOptionBtn2.addEventListener('click', () => {
                    addOptionInput.focus();
                });

                addOptionBtn2.addEventListener('click', () => {
                    const newOption = document.createElement('option');
                    newOption.setAttribute('value', `option${optionCount}`);
                     newOption.classList.add('optionL');
                    newOption.innerHTML = addOptionInput.value;
                    select.appendChild(newOption);
                    addOptionInput.value = '';
                    optionCount++;
                    
                });
            }



            const requiredLabel22 = document.createElement('label1');
            requiredLabel22.innerHTML = 'Valeur par défaut';
            newInput2.setAttribute('id', 'valeur');
            requiredLabel22.setAttribute('for', 'input2');
            draggedTag.appendChild(requiredLabel22);
            draggedTag.appendChild(newInput2);




            //btn supprimer
            const deleteButton = document.createElement('button');
            deleteButton.innerHTML = 'Supprimer';
            deleteButton.classList.add('delete-tag');
            draggedTag.appendChild(deleteButton);
            deleteButton.addEventListener('click', (e) => {
                const parentTag = e.target.parentElement;
                const grandParentTag = parentTag.parentElement;
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


