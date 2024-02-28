"user strict";
const table = document.getElementById("time-table");
const statusId = document.getElementById("editable-status");

/*
 * Changes the innerHTML of the status div so that status is editable through
 * a drop down list.
 */
function changeStatus() {
    statusId.innerHTML = `<select name="_status" id="_status" class="custom-select" required onchange="confirmChange()">
        <option>Not Started</option>
        <option>In Progress</option>
        <option>Completed</option>
    </select>`
    let thisTask = getTaskFromTaskList(TASK_TO_VIEW);
    document.getElementById("_status").value = thisTask.status;
}

/**
 * Creates a pop up to confirm with user when they
 * change the status and stores the change into local storage
 * if confirmed
 */
function confirmChange(){
    if(confirm("Confirm status change?")){
        let allTasks = retrieveAllTasks();
        let thisTask = allTasks.getTask(retrieveData(TASK_TO_VIEW));
        thisTask.new_status = document.getElementById("_status").value;
        allTasks.setTask(retrieveData(TASK_TO_VIEW),thisTask);
        storeData(TASKLIST_KEY, allTasks);
        statusId.innerHTML = `<span id="_status">${thisTask.status}</span>`;
    }else{
        window.location.reload();
    }
}

/**
 * gets the task from task list based on the key
 * @param {string} key TASK_TO_VIEW key
 * @returns task object
 */
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

/**
 * Displays time logged for each date in task details
 * by changing the innterHTML
 * @returns nothing if there is no worklog
 */
function displayTime() {
    let task = getTaskFromTaskList(TASK_TO_VIEW);
    let total_duration = 0;
    if (task._worklog === undefined) {
        return;
    }
    const body = document.createElement("tbody");
    for (let i = 0; i < task._worklog.length; i++) {
        const row = document.createElement("tr");
        row.innerHTML =
        `
            <th scope="row">${i+1}</th>
            <td>${task._worklog[i].date}</td>
            <td>${task._worklog[i].duration} hour</td>
        `
        total_duration +=  parseFloat(task._worklog[i].duration);
        body.append(row);
    }
    const footer = document.createElement("tfoot");
    footer.innerHTML = 
    `
        <tr>
            <th scope="row" colspan="2">Total</th>
            <td>
                ${total_duration} hour(s)
            </td>
        </tr>
    `
    table.append(body);
    table.append(footer);
}


displayDetails();
displayTime();