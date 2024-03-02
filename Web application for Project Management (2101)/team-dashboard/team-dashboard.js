const task_table = document.getElementById("taskTable");
const date_form = document.getElementById("date-form")

/**
 * Function to pad the digits of the dates 
 * @param {*} num 
 * @returns returns the number passed in as a padded string
 */
function padTo2Digits(num) {
    return num.toString().padStart(2, '0');
}

/**
 * Function to reformat the dates to follow YYYY-MM-DD pattern
 * @param {*} date 
 * @returns returns the date in the YYYY-MM-DD format
 */
function formatDate(date) {
    return [
        date.getFullYear(),
        padTo2Digits(date.getMonth() + 1),
        padTo2Digits(date.getDate()),
    ].join('-');
}

/**
 *  Function to get the number of days in the current month
 * @param {*} year 
 * @param {*} month 
 * @returns 
 */
function getDaysInMonth(year, month) {
    return new Date(year, month, 0).getDate();
  } 



/**
 * Function to display the tasks that have been completed 
 * and on what day they were completed
 */
function displayCompletedTasks() {
    taskList = retrieveAllTasks();
    // Setting up the header of the table
    task_table.innerHTML =
        `
        <thead> 
            <tr class="table-defult">
                <th>Date</th>
                <th>Tasks Completed</th>
            </tr>
        </thead>
    `
    // Extracting the dates entered
    dateRange = Object.fromEntries(new FormData(date_form))
    taskStartDate = new Date(dateRange._taskStartDate)
    taskEndDate = new Date(dateRange._taskEndDate)
    currentDate = new Date(dateRange._taskStartDate)
    const table = document.createElement('tbody');
    numDays = 0
    daysInCurrentMonth = 0
    // Check if there is difference in years
    if(taskEndDate.getFullYear() - taskStartDate.getFullYear() != 0){
        // Loop to check the difference in years
        for(let j = 0; j < (taskEndDate.getFullYear() - taskStartDate.getFullYear()); j++){
            // Loop again to check for difference in months 
            for(let l = 0; l < ((taskEndDate.getMonth()+(12*(j+1))) - taskStartDate.getMonth()); l++){
                // Holds the number of days in the current month, add 1 to month because getMonth function starts at 0
                daysInCurrentMonth += getDaysInMonth(taskStartDate.getFullYear()+j, taskStartDate.getMonth()+l+1);
            }
        }
        numDays = (taskEndDate.getDate() + daysInCurrentMonth) - taskStartDate.getDate()
    }
    // Check if there is difference in months 
    else if(taskEndDate.getMonth() - taskStartDate.getMonth() != 0){
        for(let k = 0; k < (taskEndDate.getMonth()) - (taskStartDate.getMonth()); k++){
            // Holds the number of days in the current month, add 1 to month because getMonth function starts at 0
            daysInCurrentMonth += getDaysInMonth(currentDate.getFullYear(), currentDate.getMonth()+k+1);
        }
        numDays = (taskEndDate.getDate() + daysInCurrentMonth) - taskStartDate.getDate()
    }
    // Check the difference between the number of days
    else{
        numDays = taskEndDate.getDate() - taskStartDate.getDate()
    }
    console.log(numDays)
    // Loop the number of days by taking the final date - the start date
    for (let i = 0; i <= numDays; i++) {
        taskCompletedCounter = 0
        // Loop through each task 
        for (let j = 0; j < taskList.getLength(); j++) {
            // Check if they have been completed
            if (taskList.getTask(j).status == "Completed") {
                // Check if their worklog is not empty or undefined
                if (taskList.getTask(j).worklog != [] && taskList.getTask(j).worklog != undefined) {
                    // Check if the last date logged equals to the current date being evaluated 
                    if (taskList.getTask(j).worklog[taskList.getTask(j).worklog.length - 1].date == formatDate(currentDate)) {
                        // Increment the counter for the number of tasks completed in the current day
                        taskCompletedCounter++
                    }
                }
            }
        }

        // Adds the day and number of tasks completed in the table
        table.innerHTML +=
            `
                <tr>
                    <td>${formatDate(currentDate)}</td>
                    <td>${taskCompletedCounter}</td>
                </tr>
            `
        // Increase by 1 to the next day
        currentDate.setDate(currentDate.getDate() + 1)
        task_table.append(table)
    }
}

/**
 * Checks if start or end date is not filled by user
 * @param {date string} start 
 * @param {date string} end 
 * @returns true if either date or start is empty string, false otherwise
 */
function dateNotFilled(start, end) {
    start = Date.parse(start);
    end = Date.parse(end);
    if (isNaN(start) || isNaN(end)) {
        return true;
    }
    return false;
}

/**
 * Checks if start date is larger than end date
 * @param {date string} start start of range
 * @param {date string} end end of range
 * @returns true if invalid, false otherwise
 */
function invalidRange(start, end) {
    start = Date.parse(start);
    end = Date.parse(end);
    if (start > end) {
        return true;
    }
    return false;
}

/**
 * Calculates the average number of hours in the given date range
 * @param {number} total total number of hours
 * @param {date string} start start of range
 * @param {date string} end end of range
 * @returns 
 */
function getAverageTime(total, start, end) {
    //define two date object variables to store the date values  
    start = new Date(start);
    end = new Date(end);

    //calculate time difference  
    var timeDiff = end.getTime() - start.getTime();

    //calculate days difference by dividing total milliseconds in a day  
    var daysDiff = timeDiff / (1000 * 60 * 60 * 24) + 1;

    return (total / daysDiff).toFixed(2);
}

/**
 * Goes through each member and create innerHTML to display average
 * time spent in the given date range
 * @returns none
 */
function displayAverageTimeByMembers() {
    let start = document.getElementById("_taskStartDate").value;
    let end = document.getElementById("_taskEndDate").value;
    let members = retrieveAllTeamMembers();
    let innerHTML = `<label id="sectionTitle">Average Time Spent:</label>`;

    if (dateNotFilled(start, end)) {
        return;
    }
    else if (invalidRange(start, end)) {
        document.getElementById("errorDate").innerText = "End Date cannot be earlier than Start Date";
        document.getElementById("averageTime").innerHTML = "";
        return;
    }else{
        document.getElementById("errorDate").innerText = "";
    }
    for (let i = 0; i < members.getLength(); i++) {
        let total = 0;
        for (let j = 0; j < members.getMemberByIndex(i).worklog.length; j++) {
            let worklogDate = Date.parse(members.getMemberByIndex(i).worklog[j].date)
            if (worklogDate >= Date.parse(start) && worklogDate <= Date.parse(end)) {
                total += parseInt(members.getMemberByIndex(i).worklog[j].duration);
            }
        }
        innerHTML += `<div class = "row">
                    <div class="column">
                        <h6>${members.getMemberByIndex(i).name}</h6>
                      </div>
                    <div class="column">
                        <h6>${getAverageTime(total, start, end)} Hours/Day</h6>
                    </div>
                    </div>`
    }
    document.getElementById("averageTime").innerHTML = innerHTML;
}

function runDisplayFunctions() {
    displayAverageTimeByMembers();
    displayCompletedTasks();
}