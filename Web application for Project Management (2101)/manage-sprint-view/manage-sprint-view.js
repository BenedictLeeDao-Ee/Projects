/**
 *  Constants that are used throughout this file
 */
const not_started_view = document.getElementById("not-started");
const in_progress_view = document.getElementById("in-progress");
const completed_view = document.getElementById("completed");

const sprint_tasks = retrieveAllTasks();
const sprint_id = retrieveData(SPRINT_TO_VIEW);

/**
 * Function to change the sprint status to completed
 * and move uncompleted tasks back to product backlog and
 * update local storages.
 */
function endSprint(){
    for(let i = 0; i < sprint_tasks.getLength(); i++){
        if(sprint_tasks.getTask(i).sprintId == sprint_id & sprint_tasks.getTask(i).status != "Completed"){
            sprint_tasks.getTask(i).new_sprintId = undefined
        }
    }
    storeData(TASKLIST_KEY,sprint_tasks)
    let sprints = retrieveAllSprints();
    sprints.getSprint(sprint_id).newStatus = "Completed";
    storeData(SPRINTS_KEY,sprints);
    window.location = "./sprintlist.html";
}

/**
 * Categorize and display tasks in their respective column based on its status
 */
function loadSprints() {
    for (let i = 0;  i < sprint_tasks.getLength(); i++) {
        if (sprint_tasks.getTask(i).sprintId !== sprint_id) {
            continue;
        }
        const card = document.createElement('div');
        //loads the html code of each task of the current selected sprint 
        card.className = `container taskCards clickable`;
        card.title = "Edit task";
        card.innerHTML =
        `
            <div class="row">
                <div class="col-lg-2">
                    <div class="circle ${COLOR[sprint_tasks.getTask(i).priority]}"></div>
                </div>
                <div class="col-lg-9">
                    <div class="taskName">
                        ${sprint_tasks.getTask(i)._taskName}
                    </div>
                    <div class="taskStatus">
                        Status: ${sprint_tasks.getTask(i)._status}
                    </div>
                </div>
            </div>
        `
        card.addEventListener("click", () => {
            storeData(TASK_TO_VIEW, i);
            window.location = "change-task-status.html";
        });
        // checks each task status and moves that task to its corresponding list
        if (sprint_tasks.getTask(i)._status === "Not Started") {
            not_started_view.append(card);
        } else if (sprint_tasks.getTask(i)._status === "In Progress") {
            in_progress_view.append(card);
        } else if (sprint_tasks.getTask(i)._status === "Completed") {
            completed_view.append(card);
        }
       
    }
}

loadSprints();
