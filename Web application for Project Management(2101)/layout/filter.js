/**
 * Filters the tasks based on the tags selected
 */
function filter(){
    let filteredArray = new TaskList();
    let selected = [];
    for (let option of document.getElementById('filterDropdown').options)
    {
        if (option.selected) {
            selected.push(option.value);
        }
    }
    let  taskList= retrieveAllTasks(); // taskList is now a TaskList object class (see main.js on the functions u can use on this object)
    
    for(let i = 0; i < taskList.tasks.length; i++){
        for(let j = 0; j < selected.length; j++){
            if (taskList.tasks[i]._tags.some(tag => tag === selected[j])){
                filteredArray.addTask(taskList.tasks[i]);
                break;
            }
        }

    }
    if (selected.length > 0){ // check if any tags selected then display cards with those tags
        displayCards(filteredArray);
    }
    else{ // if no tags selected then display every card
        displayCards(retrieveAllTasks())
    }

}

