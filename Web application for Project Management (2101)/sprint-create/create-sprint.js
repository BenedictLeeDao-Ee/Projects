const sprint_form = document.getElementById("sprint-form");

/**
 * Checks if the start and end date entered is valid
 * @param {*} sprint a sprint object
 * @returns the if the date is valid or not
 */
function isValidDate(sprint){
    let sprintStartDate = new Date(sprint._startDate);
    let sprintEndDate = new Date(sprint._endDate);
    let today = new Date();

    if(sprintStartDate < today || sprintEndDate <= today || sprintEndDate <= sprintStartDate){
        document.getElementById("errorDate").innerText="Please enter a valid date."
        return false;
    }
    else{
        return true;
    }
}


/**
 * Stores submitted task data into local storage and displays all tasks
 * in product backlog page
 */
 function handleSubmit() {
    event.preventDefault();
    let sprint = Object.fromEntries(new FormData(sprint_form));
    console.log(sprint)
    sprint._status = "Get Started"
    if (isValidDate(sprint)) {     // should check isValidDate
        let newSprint = new Sprint();
        let sprintList = retrieveAllSprints();
        newSprint.fromStorage(sprint);
        sprintList.addSprint(newSprint);
        storeData(SPRINTS_KEY, sprintList);
        window.location = "sprintlist.html";
    }
}

document.getElementById('form-display').addEventListener('submit', () => handleSubmit())