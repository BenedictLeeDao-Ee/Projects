const task_display = document.getElementById("taskName");
const task_form = document.getElementById("ui");
const allTasks = retrieveAllTasks();
const task = allTasks.getTask(retrieveData(TASK_TO_VIEW))

function addTime() {
    const time = Object.fromEntries(new FormData(task_form));
    let allMembers = retrieveAllTeamMembers();
    let thisMember = allMembers.getMemberByName(task.assignee)
    if (task._worklog === undefined) {
        task._worklog = [];
    }
    thisMember.addTimeLog(time);
    task._worklog.push(time);
    storeData(MEMBERS_KEY,allMembers);
    storeData(TASKLIST_KEY, allTasks);
    window.location = "change-task-status.html";
}


task_display.innerHTML = task._taskName;