/**
 * Autofills the create task form and edit form.
 * Call a() in console with the form opened
*/

function randomTime(start, end) {
    // get the difference between the 2 dates, multiply it by 0-1, 
    // and add it to the start date to get a new date 
    var diff =  end.getTime() - start.getTime();
    var new_diff = diff * Math.random();
    var date = new Date(start.getTime() + new_diff);
    console.log(date.toDateString());
    return date.toISOString().split('T')[0];
}

function a() {
    const task_name = document.getElementById("taskName");
    const tags = document.getElementById("tags");
    const type = document.getElementById("type");
    const story_points = document.getElementById("storyPoints");
    const assigned = document.getElementById("assigned");
    const priority = document.getElementById("priority");
    const status = document.getElementById("status");
    const due_date = document.getElementById("dueDate");
    const task_description = document.getElementById("taskDescription");
    task_name.value = `Sample Task Name ${Math.floor(Math.random() * 90) + 10}`;
    tags.value = _.sample(['Core', 'UI', 'Bug'], Math.floor(Math.random() * 3));
    type.value = _.sample(['User Story', 'Task', 'Bug']);
    story_points.value = Math.floor(Math.random() * 15) + 1
    due_date.value = randomTime(new Date("2022-10-10"), new Date("2022-12-12"))
    priority.value = _.sample(['Low', 'Medium', 'High']);
    status.value = _.sample(['Not Started', 'In Progress', 'Development', 'Testing', 'Completed']);
    assigned.value = _.sample(['Jane Mckewley', 'Anders Nathan', 'Lyndia Silva']);
    task_description.value = _.sample('abcdefghijklmnopqrs', 10);
}