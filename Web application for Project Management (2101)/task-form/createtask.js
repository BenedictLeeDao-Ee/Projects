/** 
 * createtask.js
 * Purpose: This file contains code that will run on createtask.html
 * Team: 4 
 * Author: Daisuke Murakami
 */
"use strict";

/*
    This function checks if the task name has been inputted correctly.
    Parameters:
        -
    Returns:
        -
*/
function checkTaskName(){
    
    // Check error area
    errorTaskNameRef.innerText="";
    // Check if the task name has been inputted correctly.
    if (taskNameRef == ""){
        errorTaskNameRef.innerText = "Please input a valid Task Name";
    }
}

/*
The create() function sends the input data to the local storage.
*/
function create(){
    let taskNameValid = true;
    let taskCheck = TaskList.tasks.findIndex(task => task.taskName === taskNameRef.value);
    if (taskCheck !== -1)
    {
        //Prints an error message that describes an mistake in the input.
        errorTaskNameRef.innerText = "This Task Name already exists.";
        taskNameValid = false;
    }
 
    if(taskNameValid){
        // Displays an empty string for error identification.
        errorTaskNameRef.innerText = "";
        TaskList.addTask(taskNameRef.value);
        let index = TaskList.tasks.length - 1;

        // Update Local Storage
        storeData(taskList,TASK_DATA_KEY);
        storeData(index,TASK_INDEX_KEY);
    }
    //Redirect
}
function retrieveData(key)
{
    // Retrieve data from local storage
    let data = localStorage.getItem(key);
 
    // Return Data
    try
    {
        data = JSON.parse(data);
    }
    catch(e)
    {
        console.log(e);
    }
    finally
    {
        return data;
    }
}

$(function() {
    $('#datepicker').datepicker();
});

$(function() {
    $('select').selectpicker();
});

/*
    Global Code
*/
// HTML Reference Variables
let taskNameRef = document.getElementById("taskName");
/*
let priorityRef = document.getElementById("priority");
let tagRef = document.getElementById("tag");
let typeRef = document.getElementById("type");
let dueDateRef = document.getElementByI("dueDate");
let assigneeRef = document.getElementById("assignee");
let statusRef = document.getElementById("status");
let storyPointsRef = document.getElementById("storyPoints"); 
let descriptionRef = document.getElementById("description");
*/
// HTML Error Areas
let errorTaskNameRef = document.getElementById("errorTaskName");
/*
let errorPriorityRef = document.getElementById("errorPriority");
let errorTagRef = document.getElementById("errorTag");
let errorTypeRef = document.getElementById("errorType");
let errorDueDateRef = document.getElementByI("errorDueDate");
let errorAssigneeRef = document.getElementById("errorAssignee");
let errorStatusRef = document.getElementById("errorStatus");
let errorStoryPointsRef = document.getElementById("errorStoryPoints");
let errorDescriptionRef = document.getElementById("errorDescription");
*/