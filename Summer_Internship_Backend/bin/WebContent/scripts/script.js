const responses = [
    [
        'unselected',
        'Andrea James',
        '2523532',
        '73457346735',
        '122.87K',
        '23-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'selected',
        'Jessica Joe',
        '3523312',
        '54723243652',
        '1.87K',
        '15-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'unselected',
        'Teresa Hawkins',
        '9888757',
        '76531467365',
        '22.87K',
        '03-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'unselected',
        'Dominic White',
        '4523426',
        '57635634655',
        '55.70K',
        '22-Mar-2021',
        '31-May-2021',
        'Lorem Ipsum dolor...'
    ],
    [
        'unselected',
        'Andrea James',
        '2523532',
        '73457346735',
        '122.87K',
        '23-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'unselected',
        'Jessica Joe',
        '3523312',
        '54723243652',
        '1.87K',
        '15-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'unselected',
        'Teresa Hawkins',
        '9888757',
        '76531467365',
        '22.87K',
        '03-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'selected',
        'Dominic White',
        '4523426',
        '57635634655',
        '55.70K',
        '22-Mar-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'unselected',
        'Jessica Joe',
        '3523312',
        '54723243652',
        '1.87K',
        '15-Jan-2021',
        '',
        'Lorem Ipsum dolor...'
    ],
    [
        'unselected',
        'Teresa Hawkins',
        '9888757',
        '76531467365',
        '22.87K',
        '03-Jan-2021',
        '03-Jan-2021',
        'Lorem Ipsum dolor...'
    ],
    [
        'unselected',
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
const table = document.getElementById('mainTable');
const masterCheckBox = document.getElementById("checkBoxMaster");
const buttonsAndInputs = document.querySelectorAll(".btn");

// Helper Functions 
function check(ele) {
    ele.checked = true;
};

function uncheck(ele) {
    ele.checked = false;
};

function printCheckedBoxes(selectedBoxes) {
    console.log(selectedBoxes);
};

// Method to add rows to the table from responses array
responses.forEach((response, index) => {
    const row = document.createElement('tr');
    response.forEach((item, responseIndex) => {
        const td = document.createElement('td');
        if (responseIndex === 0) {
            const checkbox = document.createElement('input');
            checkbox.setAttribute('type', 'checkbox');
            checkbox.setAttribute('id', index);
            checkbox.setAttribute('class', 'checkbox');
            checkbox.checked = false;
            td.appendChild(checkbox);
        } else {
            if (item.length > 0) {
                td.innerText = item;
                if (item === '03-Jan-2021' && responseIndex === 5) {
                    td.classList.add("text-red");
                }
            } else {
                td.innerText = "--";
            }
        }
        row.appendChild(td);
    });
    if (index % 2 === 1) {
        row.classList.add("darkRow");
    }
    table.appendChild(row);
});

// Method to handle clicks on master check box
masterCheckBox.addEventListener("click", (() => {
    if (masterCheckBox.checked === true) {
        responses.forEach((item, index) => {
            check(document.getElementById(index));
        });
    } else {
        responses.forEach((item, index) => {
            uncheck(document.getElementById(index));
        });
    }
}));

// method to handle clicks on individual checkboxes
const checkBoxes = document.querySelectorAll('.checkbox');
checkBoxes.forEach((checkbox) => {
    checkbox.addEventListener("change", () => {
        const checkedBoxes = document.querySelectorAll(".checkbox:checked");
        console.log(checkedBoxes.length, responses.length)
        const selectedElementsIndexes = [];
        checkedBoxes.forEach((checkedBox) => {
            selectedElementsIndexes.push(checkedBox.id);
        });
        if(responses.length !== checkedBoxes.length){
            if(masterCheckBox.checked === true){
                uncheck(masterCheckBox);
            }
        }else if(responses.length === checkedBoxes.length){
            if(masterCheckBox.checked === false){
                check(masterCheckBox);
            }
        }
        printCheckedBoxes(selectedElementsIndexes);
    });
});

// Handle the btn-active class on each button/input.
buttonsAndInputs.forEach((buttonOrInput) => {
    buttonOrInput.addEventListener("click", () => {
        const element = document.getElementById(buttonOrInput.id);
        buttonsAndInputs.forEach((buttonOrInput) => {
            if(buttonOrInput.classList.contains("btn-active")){
                buttonOrInput.classList.remove("btn-active");
            }
        });
        element.classList.add("btn-active");
    });
})