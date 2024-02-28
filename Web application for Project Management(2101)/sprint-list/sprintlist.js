
/**
 * Fixed variables to be used by all functions in this file
 *  */ 
const sprint_view = document.getElementById("sprint-view");

/**
 * Function to display all sprints in card form along with some of its properties
 */
function displaySprints(){
    sprint_tasks = retrieveAllSprints();
    sprintList = new SprintList();
    sprintList.fromStorage(sprint_tasks);
    sprint_view.innerHTML = '';
    for(let i = 0; i < sprintList.getLength();i++){
        const card = document.createElement('div');
        card.className = `card d-flex flex-row clickable`;
        card.id = `sprint-${i}`;
        card.innerHTML =
        `
            <div class="card-container flex-grow-1" title="Manage sprint">
                <h4 class="card-body text-cutoff">${sprintList.getSprint(i).sprintName}</h4>
                <div class="card-footer d-flex justify-content-between dates">
                    <div>Start Date: ${sprintList.getSprint(i).startDate}</div>
                    <div>End Date: ${sprintList.getSprint(i).endDate}</div>
                </div>
            </div>
            <div class="sprint-status rounded ${SPRINTCOLOR[sprintList.getSprint(i).status]}">
                ${sprintList.getSprint(i).status}
            </div>
        `
        // When the card is clicked, it will either go to addTask page or manage-sprint page depending
        // on the cards status
        card.addEventListener('click', () => {
            storeData(SPRINT_TO_VIEW,i);
            if(sprintList.getSprint(i).status == "Get Started"){
                window.location = "addtask.html";
            }else{
                window.location = "manage-sprint-view.html";
            }
            event.stopPropagation();
        });
        sprint_view.append(card);
    }
}
    

/**
 * Grants the add sprint image the click property which sends you to the createSprint page
 */
document.getElementById("create-sprint").addEventListener("click", () => {
    window.location = "createsprint.html";
})


displaySprints();