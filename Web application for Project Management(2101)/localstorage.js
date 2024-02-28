"use strict";

/*
    This function checks if there is data exists in the given key in local storage
    Parameters: 
        data: JSON object that is parsed from local storage 
    Returns: 
        A boolean (true - if data exists and contains some value, false - if data does not exist or is empty)
*/
function dataExists(data)
{
    // Check if data exists
    if (typeof(data) !== "undefined" && data !== null && data !== undefined && data !== "")
    {
        return true;
    }
    else
    {
        return false;
    }
}

/*
Stores data into the specified key's local storage
Parameters:
    key: key for local storage
    data: data to be stored (can be any data type)
*/
function storeData(key,data){
    // save the id of every data if data is an array
    if (Array.isArray(data)) {
        
    }

    // Check if Data is object
    if (typeof data === "object")
    {
        data = JSON.stringify(data);
    }
    // Store data into local storage
    localStorage.setItem(key,data);
}

/*
Retrieves data from the specified key's local storage
Parameters:
    key: key for local storage
Return:
    a JSON object of the data
*/
function retrieveData(key){
    // Retrieve data from local storage
    let data = localStorage.getItem(key);
    
    // Return Data
    try{
        data = JSON.parse(data);
    }
    catch(e){
        console.log(e);
    }
    finally{
        return data;
    }
}

/**
 * Retrieves all tasks stored in local storage and parse to TaskList class object
 * @returns a TaskList object with all tasks stored in local storage
 */
function retrieveAllTasks()
{
    let tasksData = retrieveData(TASKLIST_KEY); //this retrieves the list of tasks as a JSON array
    let allTasks= new TaskList();
    if (dataExists(tasksData))
    {
        allTasks.fromStorage(tasksData); //this coverts the JSON array to a TaskList object which stores an array of Task objects
    }
    return allTasks;
}

/**
 * Retrieves all sprints stored in local storage and parse to SprintList class object
 * @returns a SprintList object with all tasks stored in local storage
 */
 function retrieveAllSprints()
 {
     let sprintData = retrieveData(SPRINTS_KEY); //this retrieves the list of tasks as a JSON array
     let allSprints= new SprintList();
     if (dataExists(sprintData))
     {
         allSprints.fromStorage(sprintData); //this coverts the JSON array to a TaskList object which stores an array of Task objects
     }
     return allSprints;
 }

/**
 * Retrieves all team members stored in local storage and parse to TeamMemberList class object
 * @returns a TeamMemberList object with all team members stored in local storage
 */
 function retrieveAllTeamMembers()
 {
     let memberData = retrieveData(MEMBERS_KEY); //this retrieves the list of TeamMember as a JSON array
     let allMembers= new TeamMemberList();
     if (dataExists(memberData))
     {
       allMembers.fromStorage(memberData); //this coverts the JSON array to a TeamMemberList object which stores an array of TeamMember objects
     } else  
     {  // Default members
        allMembers._members = [
            new TeamMember("Jane Mckewley", "mcja22@gmail.com"), 
            new TeamMember("Anders Nathan", "naan95@gmail.com"),
            new TeamMember("Lyndia Sylvia", "syly38@gmail.com")
        ]
     }
     return allMembers;
 }