"user strict";
const form_display = document.getElementById('form-display');
const task_form = document.getElementById("edit-form");

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
    document.getElementById("container").innerHTML = ``;
    event.stopPropagation();
    fillEditForm(getTaskFromTaskList(TASK_TO_VIEW));
}

function getTaskFromTaskList(key) {
    let indexData = retrieveData(key);
    let allTasks = retrieveAllTasks();
    let task = null;
    if (dataExists(indexData)) {
        task = allTasks.getTask(indexData);
    }
    return task;
}

/*
Fills the edit form based on task json
*/
function fillEditForm(task) {
    document.getElementById("_taskName").value = `${task._taskName}`;
    document.getElementById("_priority").value = `${task._priority}`;
    $('#_tags').selectpicker('val', task.tags);
    document.getElementById("_type").value = `${task._type}`;
    document.getElementById("_dueDate").value  = `${task._dueDate}`;
    document.getElementById("_storyPoints").value = `${task.storyPoints}`;
    document.getElementById("_status").value = `${task.status}`;
    document.getElementById("_taskDesc").value = `${task.taskDesc}`;
    document.getElementById("_assignee").value = `${task.assignee}`;
    //document.querySelector(".circle").classList.add(COLOR[task.priority]);
}

/**
 * to check if the date is 
 * @param {Task} task 
 * @returns 
 */
function isValidDate(task){
    let taskDate = new Date(task._dueDate);
    let today = new Date();
    if (taskDate <= today)
    {
        document.getElementById("errorDate").innerText="Please enter a valid date.";
        return false;
    }else{
        return true;
    }
}

function handleSubmit() {
    event.preventDefault();
    const task = Object.fromEntries(new FormData(task_form));
    if (isValidDate(task)) {
        let selected = [];
        for (let option of document.getElementById('_tags').options) {
            if (option.selected) {
                selected.push(option.value);
            }
        }
        task._tags = selected;
        task._worklog = getTaskFromTaskList(TASK_TO_VIEW)._worklog;
        let newTask = new Task();
        let taskList = retrieveAllTasks();
        newTask.fromStorage(task);
        delete newTask._sprintId;
        taskList.setTask(retrieveData(TASK_TO_VIEW), {...getTaskFromTaskList(TASK_TO_VIEW), ...newTask});
        storeData(TASKLIST_KEY, taskList);
        removeVisible();
        location.reload();
    }
}

/**
 * Removes task from the task list and update local storage
 * Brings back user to product backlog page once done
 */
function removeTask() {
    let taskIndex = retrieveData(TASK_TO_VIEW);
    let taskList = retrieveAllTasks();
    taskList.removeTask(taskIndex);
    storeData(TASKLIST_KEY, taskList);
    window.location = "taskview.html";
}


function displayDetails() {
    let task = getTaskFromTaskList(TASK_TO_VIEW);
    // Display country, date, departure, arrival, number of stops, and route
    document.getElementById("taskName").innerHTML = `<div>${task.taskName}</div>`;
    document.getElementById("priority").innerHTML = `<div>${task.priority}</div>`;
    document.getElementById("tags").innerHTML = `<div>${task.tags}</div>`;
    document.getElementById("type").innerHTML = `<div>${task.type}</div>`;
    document.getElementById("dueDate").innerHTML = `<div>${task.dueDate}</div>`;
    document.getElementById("storyPoints").innerHTML = `<div>${task.storyPoints}</div>`;
    document.getElementById("Status").innerHTML = `<div>${task.status}</div>`;
    document.getElementById("taskDescription").innerHTML = `<div>${task.taskDesc}</div>`;
    document.getElementById("Assignees").innerHTML = `<div>${task.assignee}</div>`;
    document.querySelector(".circle").classList.add(COLOR[task.priority]);
}

form_display.addEventListener('submit', () => {
    handleSubmit();
    displayDetails();
    removeVisible();
})

displayDetails();