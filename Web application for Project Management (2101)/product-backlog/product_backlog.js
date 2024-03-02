"use strict";

//fixed variables to be used by all functions in this file
const task_view = document.getElementById('task-view');
const form_display = document.getElementById("form-display");
const task_form = document.getElementById("task-form");


/*
Hides the form to create task from page view
*/
function removeVisible() {
    form_display.classList.add("hidden");
    event.stopPropagation()
    document.removeEventListener('click', removeVisible);
    location.reload();
}

/*
Make the form to create task visible and adds function for submit button
*/
function addVisible() {
    form_display.classList.remove("hidden");
    document.getElementById("errorDate").innerText = "";
    event.stopPropagation()

}

/**
 * Checks if the start and end date entered is valid
 * @param {*} sprint a sprint object
 * @returns the if the date is valid or not
 */
function isValidDate(task) {
    let taskDate = new Date(task._dueDate);
    let today = new Date();
    if (taskDate <= today) {
        document.getElementById("errorDate").innerText = "Please enter a valid date.";
        return false;
    } else {
        return true;
    }
}
/**
 * Stores submitted task data into local storage and displays all tasks
 * in product backlog page
 */
function handleSubmit() {
    event.preventDefault();
    let task = Object.fromEntries(new FormData(task_form));
    if (isValidDate(task)) {
        let selected = [];
        for (let option of document.getElementById('tagDropdown').options) {
            if (option.selected) {
                selected.push(option.value);
            }
        }
        task._tags = selected;
        task._sprintId = undefined;
        task._worklog = [];
        let newTask = new Task();
        let taskList = retrieveAllTasks();
        newTask.fromStorage(task);
        taskList.addTask(newTask);
        storeData(TASKLIST_KEY, taskList);
        removeVisible();
        location.reload();
    }
}

/**
 * Creates the innerHTML string to display tags
 * @param {*} task a Task object
 * @returns the innerHTML string
 */
function createTagHtml(task) {
    let tagsList = task.tags;
    let tagHTML = "";
    if (tagsList != undefined) {
        for (let j = 0; j < tagsList.length; j++) {
            tagHTML += `<span class="badge" id="tag">${tagsList[j]}</span>`
        }
    }
    return tagHTML;
}

/**
 * Creates the innerHTML for all tasks to be displayed in cards
 */
function displayCards(taskList) {
    task_view.innerHTML = '';
    for (let i = 0; i < taskList.getLength(); i++) {
        if (taskList.getTask(i)._sprintId == undefined) {
            let tagHTML = createTagHtml(taskList.getTask(i));
            const card = document.createElement('div');
            card.className = `card`;
            card.id = `task-${i}`;
            card.innerHTML =
                `
            <div class="card-container" title="View task">
            <div class="circle ${COLOR[taskList.getTask(i).priority]}"></div>
            <div class="card-body text-cutoff" >${taskList.getTask(i).taskName}</div>
            <div class="card-footer">
                ${tagHTML}
                <div class="storypoints">Story Points:${taskList.getTask(i).storyPoints}</div>
            </div>
            </div>
        `
            card.addEventListener('click', () => {
                storeData(TASK_TO_VIEW, i);
                window.location = "taskdetails.html";
                event.stopPropagation();
            });
            task_view.append(card);
        }
    }

}

// global code to run
document.getElementById('create-task').addEventListener('click', addVisible); // add task button
form_display.addEventListener('submit', () => {
    handleSubmit();
})
displayCards(retrieveAllTasks());
