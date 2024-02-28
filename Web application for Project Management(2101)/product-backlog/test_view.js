/**
 * This is meant to test UI logic and is not FINAL!
 * 
 */
const task_view = document.getElementById('task-view');
const form_display = document.getElementById("form-display");
const task_form = document.getElementById("task-form");

const task_name = document.getElementById("taskName");
const tags = document.getElementById("tags");
const type = document.getElementById("type");
const story_points = document.getElementById("storyPoints");
const assigned = document.getElementById("assigned");
const priority = document.getElementById("priority");
const status = document.getElementById("status");
const due_date = document.getElementById("dueDate");
const task_description = document.getElementById("taskDescription");

var EDIT_MODE = -1; // use this since edit form is not ready
const TASK_LIST = [{
    taskName : "Task 1",
    tags : "Tag 1",
    storyPoints : 5,
    dueDate: '10/10/2022',
    priority: 'High',
    taskDescription: 'This is an example description.'
}];

const COLOR = {
    "Low" : "green",
    "Medium" : "yellow",
    "High" : "red",
}

function addVisible() {
    form_display.classList.remove("hidden");
    event.stopPropagation();
    document.addEventListener('click', removeVisible);
}

function removeVisible() {
    clearForm();
    form_display.classList.add("hidden");
    event.stopPropagation();
    document.removeEventListener('click', removeVisible);
}

function clearForm() {
    jQuery("#task-form").find(':input').each(function() {
        switch(this.type) {
            case 'password':
            case 'text':
            case 'textarea':
            case 'file':
            case 'select-one':
            case 'select-multiple':
            case 'date':
            case 'number':
            case 'tel':
            case 'email':
                jQuery(this).val('');
                break;
            case 'checkbox':
            case 'radio':
                this.checked = false;
                break;
        }
      });
}

// function handleSubmit(taskJSON) {
//     event.preventDefault();
//     clearForm();
//     console.log(taskJSON);
//     if (EDIT_MODE === -1) {
//         TASK_LIST.push(taskJSON);
//     } else {
//         TASK_LIST[EDIT_MODE] = taskJSON;
//     }
//     refreshCards();
// }

function editTaskFromJSON(i) {
    task_name.value = TASK_LIST[i].taskName;
    story_points.value = TASK_LIST[i].storyPoints;
    due_date.value = TASK_LIST[i].dueDate;
    task_description.value = TASK_LIST[i].taskDescription;
    assigned.value = TASK_LIST[i].assigned;
    priority.value = TASK_LIST[i].priority;
    tags.value = TASK_LIST[i].tags;
    status.value = TASK_LIST[i].status;
    type.value = TASK_LIST[i].value;
    EDIT_MODE = i;
    addVisible();
}

function refreshCards(data) {
    let allTasks = retrieveAllTasks();
    task_view.innerHTML = '';
    for (let i = 0; i < allTasks.getLength(); i++) {
        let tagsList = allTasks.getTask(i).tags;
        let tagHTML = "";
        for (let j = 0; j < tagsList.length;j++)
        {
            tagHTML+=`<span class="badge" id="tag">${tagsList[j]}</span>`
        }
        const card = document.createElement('div');
        card.className = `card`;
        card.id = `task-${i}`;
        card.innerHTML = 
        `
            <div class="card-container text-cutoff">
            <div class="circle ${COLOR[allTasks.getTask(i).priority]}"></div>
            <div class="card-body" >${allTasks.getTask(i).taskName}</div>
            <div class="card-footer">
                ${tagHTML}
                <div class="storypoints">Story Points:${allTasks.getTask(i).storyPoints}</div>
            </div>
            </div>
        `
        card.addEventListener('click', () => {
            storeData(TASK_TO_VIEW,i);
            window.location = "taskdetails.html";
            event.stopPropagation();
        });
        task_view.append(card);
    }
    
}

form_display.addEventListener('click', () => event.stopPropagation());
form_display.addEventListener('submit', removeVisible);
document.getElementById('create-task').addEventListener('click', () => {
    addVisible();
    EDIT_MODE = -1;
});

task_form.addEventListener("submit", () => {
    event.preventDefault();
    const data = new FormData(event.target);
    const taskName = data.get("taskName")
    const priority = data.get("priority")
    const tags = [data.get("tags")] //might have problem only accepting one 
    const type = data.get("type")
    const dueDate = data.get("dueDate")
    const storyPoints = data.get("storyPoints")
    const status = data.get("status")
    const assigned = data.get("assigned")
    const taskDescription = data.get("taskDescription")
    
    console.log({taskName, priority, tags, type,dueDate,storyPoints,status,assigned,taskDescription})
    // handleSubmit({taskName, priority, tags, type,dueDate,storyPoints,status,assigned,taskDescription});
})
// storeData(PRODUCT_BACKLOG_KEY,{taskName, priority, tags, type,dueDate,storyPoints,status,assigned,taskDescription})
// console.log(retrieveAllTasks()[0].taskName)
refreshCards();

