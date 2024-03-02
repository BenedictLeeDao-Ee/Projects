/*
Module that holds all user defined class objects and their methods, which include:
Task, TaskList, Sprint, SprintList, TeamMember and TeamMemberList
*/
class Task
{
    constructor(taskName="",tags=[],type = "",status="Get started",storyPoints=0,priority="low",taskDesc="",dueDate ="",assignee="",active=true)
    {
        this._taskName = taskName;
        this._storyPoints = storyPoints;
        this._type = type;
        this._tags = tags;
        this._status = status;
        this._priority = priority;
        this._taskDesc = taskDesc;
        this._dueDate = dueDate;
        this._assignee = assignee;
        this._active = active; //true if it's in pbi/sprint, false if it's deleted and is stored in recently deleted page
        this._sprintId = undefined; // undefined means not assigned, use 0,1,2.. to assign sprintId to this task
        this._worklog = []; // store worklog in an array of objects
    }

    //accessor
    get taskName(){return this._taskName;}
    get tags(){return this._tags;}
    get type(){return this._type;}
    get status(){return this._status;}
    get storyPoints(){return this._storyPoints;}
    get priority(){return this._priority;}
    get taskDesc(){return this._taskDesc;}
    get dueDate(){return this._dueDate;}
    get assignee(){return this._assignee;}
    get isActive(){return this._active;}
    get sprintId(){return this._sprintId;}
    get worklog(){return this._worklog;}

    //mutator
    set new_taskName(taskName){ this._taskName = taskName;}
    set new_status(status){this._status = status;}
    set new_type(type){this._type = type;}
    set new_storyPoints(storyPoints){this._storyPoints = storyPoints;}
    set new_priority(priority){this._priority = priority;}
    set new_taskDesc(taskDesc){this._taskDesc = taskDesc;}
    set new_dueDate(dueDate){this._dueDate= dueDate;}
    set new_assignee(assignee){this._assignee=assignee;}
    set new_isActive(active){this._active = active;}
    set new_sprintId(id){this._sprintId = id;}
    set new_worklog(worklog){this._worklog = worklog;}

    //gets data as JSON object and store in Task object
    //"data" parameter is a JSON object
    fromStorage(data)
    {
        this._taskName = data._taskName;
        this._type = data._type;
        this._tags = data._tags;
        this._status = data._status;
        this._storyPoints = data._storyPoints;
        this._tags = data._tags;
        this._priority = data._priority;
        this._taskDesc = data._taskDesc;
        this._dueDate = data._dueDate;
        this._assignee = data._assignee;
        this._active = data._active;
        this._sprintId = data._sprintId;
        this._worklog = data._worklog;
    }

    /**
     * Adds a tag to the tag array
     * @params: tag: a string for tag name to be added
     */
    addTag(tag)
    {
        this._tags.push(tag);
    }

    /**
     * Removes a tag to the tag array
     * @params: tag: a string for tag name to be removed
     */
    removeTag(tag)
    {
        for (let i = 0; i < this._tags.length; i++)
        {
            if (this._tags[i] == tag)
            {
                this._tags.splice(i);
            }
        }
    }

    /**
     * Adds the date and time spent as key-value pair into worklog property
     * @param {date} date the date that user logs for the hours spent on this task
     * @param {number} duration the number of hours spent on this task on this date
     */
    addTimeSpent(timelog)
    {
        this._worklog.push(timelog);
    }

    /**
     * Calculates total time spent on task based on the current worklog
     * @returns total time spent on this task
     */
    getTotalTime()
    {
        totalTime = 0;
        for (key in this._worklog)
        {
            totalTime += this._worklog[key];
        }
        return totalTime;
    }
}

/*
Class that stores an array of tasks
*/
class TaskList
{
    constructor(){this._tasks = [];}

    //accessors
    get tasks(){return this._tasks;}

    //remove task from list
    removeTask(index)
    {
        this._tasks.splice(index,1);
    }

    // set task at index
    setTask(index, task)
    {
        this._tasks[index] = task
    }

    //add task to list
    addTask(task)
    {
        this._tasks.push(task);
    }

    //get task of specific index
    getTask(index)
    {
        return this._tasks[index];
    }

    getLength()
    {
        return this._tasks.length;
    }

    //get all task from local storage and store in this list
    fromStorage(data)
    {
        this._tasks = [];
        let datalist = data._tasks;
		for(let i = 0; i < datalist.length; i++)
		{
			let task = new Task();
			task.fromStorage(datalist[i]);
            this._tasks.push(task);
        }
    }
}

class Sprint
{
    constructor(id, name = "", startDate = Date.now(), endDate = Date.now(),status = "Not started"){
        this._id = id;
        this._sprintName = name;
        this._startDate = startDate;
        this._endDate = endDate;
        this._status = status;
    }

    //accessors
    get id(){return this._id;}
    get sprintName(){return this._sprintName;}
    get startDate(){return this._startDate;}
    get endDate(){return this._endDate;}
    get status(){return this._status;}

    //setters
    set newStatus(status){this._status = status;}
    set newSprintName(name){this._sprintName = name;}
    set newId(id){this._id = id;}
    set newEndDate(endDate){this._endDate = endDate;}
    set newStartDate(startDate){this._startDate = startDate;}

    fromStorage(data)
    {
        this._id = data._id;
        this._sprintName = data._sprintName;
        this._startDate = data._startDate;
        this._endDate = data._endDate;
        this._status = data._status;
    }

}

class SprintList
{
    constructor(){this._sprints=[]}

    //accessors
    get sprints(){return this._sprints;}

    //add task to list
    addSprint(sprint)
    {
        this._sprints.push(sprint);
    }

    //get task of specific index
    getSprint(index)
    {
        return this._sprints[index];
    }

    //get number of sprints
    getLength()
    {
        return this._sprints.length;
    }

    setSprint(index,sprint){
        this._sprints[index] = sprint;
    }

    //get all task from local storage and store in this list
    fromStorage(data)
    {
        this._tasks = [];
        let datalist = data._sprints;
		for(let i = 0; i < datalist.length; i++)
		{
			let sprint = new Sprint();
			sprint.fromStorage(datalist[i]);
            this._sprints.push(sprint);
        }
    }
}

class TeamMember
{
    constructor(name,email)
    {
        this._name = name;
        this._email = email;
        this._worklog = [];
    }

    //accessors
    get name(){return this._name;}
    get email(){return this._email;}
    get worklog(){return this._worklog;}

    //setters
    set newName(name){this._name = name;}
    set newEmail(email){this._email = email;}
    set newWorklog(worklog){this._worklog = worklog;}

    /**
     * Adds new time log (data & duration) into time log array
     * @param {JSON String} timelog JSON string in {date: "<date>", duration:"<duration>"} format
     */   
    addTimeLog(timelog)
    {
        this._worklog.push(timelog);
    }

    fromStorage(data)
    {
        this._name = data._name;
        this._email = data._email;
        this._worklog = data._worklog;
    }
}

class TeamMemberList
{
    constructor(){this._members = [];}

    /**
     * Adds new team member into the list of members
     * @param {TeamMember} member team member object 
     */
    addMember(member)
    {
        this._members.push(member);
    }

    /**
     * Obtains the TeamMember object that is specified by the index
     * @param {number} index index of the team member in the list 
     * @returns TeamMember object 
     */
    getMemberByIndex(index)
    {
        return this._members[index];
    }

    /**
     * Obtains the TeamMember object that is specified by the name
     * @param {string} name name of the member to retrieve
     * @returns TeamMember object
     */
    getMemberByName(name)
    {
        for(let i = 0; i < this._members.length; i++)
        {
            if (name == this._members[i].name)
            {
                return this._members[i];
            }
        }
    }

    /**
     * Get number of members in the list
     * @returns number of members
     */
    getLength()
    {
        return this._members.length;
    }

    /**
     * Remove member from list
     * @param {number} index index of member to be removed
     */
    deleteMember(index)
    {
        this._members.splice(index,1);
    }

    //get all task from local storage and store in this list
    fromStorage(data)
    {
        this._members = [];
        let datalist = data._members;
		for(let i = 0; i < datalist.length; i++)
		{
			let member = new TeamMember();
			member.fromStorage(datalist[i]);
            this._members.push(member);
        }
    }
}

// Local Storage Keys
const TASKLIST_KEY= "pbItems";
const TASK_TO_VIEW = "selectedTaskIndex";
const SPRINTS_KEY = "sprintsList";
const SPRINT_TO_VIEW = "selectedSprintIndex";
const MEMBERS_KEY = "teamMemberList";
const MEMBER_TO_VIEW = "selectedMemberIndex";