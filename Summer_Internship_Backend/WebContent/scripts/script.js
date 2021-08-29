const responses = [
    [
        'Andrea James',
        '2523532',
        '73457346735',
        '122.87K',
        '23-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'Jessica Joe',
        '3523312',
        '54723243652',
        '1.87K',
        '15-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'Teresa Hawkins',
        '9888757',
        '76531467365',
        '22.87K',
        '03-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'Dominic White',
        '4523426',
        '57635634655',
        '55.70K',
        '22-Mar-2021',
        '31-May-2021',
        'Lorem Ipsum dolor...'
    ],
    [
        'Andrea James',
        '2523532',
        '73457346735',
        '122.87K',
        '23-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'Jessica Joe',
        '3523312',
        '54723243652',
        '1.87K',
        '15-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'Teresa Hawkins',
        '9888757',
        '76531467365',
        '22.87K',
        '03-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'Dominic White',
        '4523426',
        '57635634655',
        '55.70K',
        '22-Mar-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'Jessica Joe',
        '3523312',
        '54723243652',
        '1.87K',
        '15-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'Teresa Hawkins',
        '9888757',
        '76531467365',
        '22.87K',
        '03-Jan-2021',
        '03-Jan-2021',
        'Lorem Ipsum dolor...'
    ],
    [
        'Dominic White',
        '4523426',
        '57635634655',
        '55.70K',
        '22-Mar-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
]

// Constants throughout the script
const table = document.getElementById('alternateTable');
const masterCheckBox = document.getElementById("checkBoxMaster");
const buttonsAndInputs = document.querySelectorAll(".btn");
const addModal = document.querySelector("#addModal");
const editModal = document.querySelector("#editModal");
const deleteModal = document.querySelector("#deleteModal");
const smokedScreen = document.querySelector(".smokedScreen");
let selectedIndexes = [];

// Helper Functions 
function check(ele) {
    ele.checked = true;
};

function uncheck(ele) {
    ele.checked = false;
};

function updateSelectedIndexes(selectedBoxes) {
    selectedIndexes = Array.from(selectedBoxes);
    console.log(selectedIndexes);
};

// Method to add rows to the table from responses array
responses.forEach((response, index) => {
    const row = document.createElement('tr');
    row.setAttribute('id', `${index}row`);
    const checkboxTD = document.createElement('td');
    const checkbox = document.createElement('input');
    const label = document.createElement('label');
    checkbox.setAttribute('type', 'checkbox');
    checkbox.setAttribute('id', index);
    checkbox.setAttribute('class', 'checkbox');
    label.setAttribute('for', index);
    checkbox.checked = false;
    checkboxTD.appendChild(checkbox);
    checkboxTD.appendChild(label);
    row.appendChild(checkboxTD);

    // Mapping each response received to a new tr element as td elements.
    response.forEach((item, responseIndex) => {
        const td = document.createElement('td');
        if (item.length > 0) {
            td.innerText = item;
            if (item === '03-Jan-2021' && responseIndex === 4) {
                td.classList.add("text-red");
            }
        } else {
            td.innerText = "--";
        }
        row.appendChild(td);
    });

    // Adding darkRow class to odd rows for making alternate shade in the table.
    if (index % 2 === 1) {
        row.classList.add("darkRow");
    }
    table.appendChild(row);
});

// Method to handle clicks on master check box
masterCheckBox.addEventListener("click", (() => {
    if (masterCheckBox.checked === true) {
        const allIndexes = [];
        responses.forEach((item, index) => {
            check(document.getElementById(index));
            document.getElementById(`${index}row`).classList.add("highLightedRow");
            allIndexes.push(index);
        });
        updateSelectedIndexes(allIndexes);
    } else {
        responses.forEach((item, index) => {
            uncheck(document.getElementById(index));
            document.getElementById(`${index}row`).classList.remove("highLightedRow");
        });
        updateSelectedIndexes([]);
    }
}));

// Method to handle clicks on individual checkboxes
const checkBoxes = document.querySelectorAll('.checkbox');
checkBoxes.forEach((checkbox) => {
    checkbox.addEventListener("click", () => {
        const checkedBoxes = document.querySelectorAll(".checkbox:checked");
        const selectedElementsIndexes = [];

        // Making an array with indexes of all the selected rows.
        checkedBoxes.forEach((checkedBox) => {
            selectedElementsIndexes.push(checkedBox.id);
        });

        // Toggling the master checkbox button if all are selected/one element is deselected.
        if(responses.length !== checkedBoxes.length){
            if(masterCheckBox.checked === true){
                uncheck(masterCheckBox);
            }
        }else if(responses.length === checkedBoxes.length){
            if(masterCheckBox.checked === false){
                check(masterCheckBox);
            }
        }

        // Adding a highlightedrow class when a row is selected.
        selectedElementsIndexes.forEach((selectedItemIndex) => {
            document.getElementById(`${selectedItemIndex}row`).classList.add("highLightedRow");
        });

        // Removing highlightedrow class if a highlighted row is deselected.
        responses.forEach((element, index) => {
            if(!selectedElementsIndexes.includes(`${index}`) && document.getElementById(`${index}row`).classList.contains('highLightedRow')){
                document.getElementById(`${index}row`).classList.remove("highLightedRow");
            }
        });
        updateSelectedIndexes(selectedElementsIndexes);
    });
});

// Method to handle the btn-active class on each button/input.
buttonsAndInputs.forEach((buttonOrInput) => {
    buttonOrInput.addEventListener("click", () => {
        const element = document.getElementById(buttonOrInput.id);
        // Adding btn-active class to the button whenever they are clicked and removing the same from all other buttons.
        buttonsAndInputs.forEach((buttonOrInput) => {
            if(buttonOrInput.classList.contains("btn-active")){
                buttonOrInput.classList.remove("btn-active");
            }
        });
        element.classList.add("btn-active");

        // checking which button was clicked and launching a suitable alert/modal.
        if(element.id === 'add'){
            smokedScreen.style.display = "block";
            addModal.style.display = "block";
            smokedScreen.appendChild(addModal);
        }

        if(element.id === 'edit'){
            if(selectedIndexes.length > 1){
                alert("You have selected more than one rows. Only 1 row can be edited.");
            }else if(selectedIndexes.length === 0){
                alert("You have selected no rows to be edited.");
            }else{
                smokedScreen.style.display = "block";
                editModal.style.display = "block";
                smokedScreen.appendChild(editModal);
                document.querySelector("#edit-modal-invoice-amount").value = responses[selectedIndexes[0]][4];
                document.querySelector("#edit-modal-notes").value = responses[selectedIndexes[0]][6];
            }
        }

        if(element.id === 'delete'){
            if(selectedIndexes.length === 0){
                alert("You have selected no rows. Select at-least 1 or more row(s) to be deleted.");
            }else{
                smokedScreen.style.display = "block";
                deleteModal.style.display = "block";
                smokedScreen.appendChild(deleteModal);
            }
        }
    });
});

// A method to close the modal on click on cancel/close buttons.
document.querySelectorAll(".closeModal").forEach((closeModalButton) => {
    closeModalButton.addEventListener("click", () => {
        smokedScreen.removeChild(smokedScreen.lastElementChild);
        smokedScreen.style.display = "none";
    });
});

// A method to reset/clear all the changes made in addModal/editModals on clicking reset buttons.
document.querySelectorAll(".clearAllInputs").forEach((clearBtn) => {
    clearBtn.addEventListener("click", () => {
        if(`${clearBtn.id}` === "add-modal-clear"){
            document.querySelectorAll("#addModal input, #addModal textarea").forEach((element) => {
                element.value = "";
            });
        }
        if(`${clearBtn.id}` === "edit-modal-clear"){
            document.querySelectorAll("#editModal input, #editModal textarea").forEach((element) => {
                element.value = "";
            });
        }
    });
});

// Disabling the add button inside addModal when it is clicked once, for 10sec so that no one can spam requests using it.
document.querySelector("#add-modal-add").addEventListener("click", () => {
    document.querySelector("#add-modal-add").disabled = true;
    setTimeout(() => {
        document.querySelector("#add-modal-add").disabled = false;
        console.log("Button re-enabled.");
    }, 10000);
});