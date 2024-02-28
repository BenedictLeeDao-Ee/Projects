/*
put this file as one of the source script in your html to test your code
*/
let allTasks = retrieveAllTasks();
if (allTasks.getLength() == 0){
let task1 = new Task("1",["UI","Core"],"User Story","Not Started",4,"High","hello world","2022-09-19",ASSIGNEES[0],true);
let task2 = new Task("This is a short name",["Testing","Core"],"Task","Not Started",4,"High","hello world","2022-09-19",ASSIGNEES[1],true);
let task3 = new Task("123456789123456789123456789123456789123456",["UI"],"Bug","In Progress",1,"Medium","hello world","2022-09-19",ASSIGNEES[2],true);
let task4 = new Task("This is task 4",["Core"],"Task","Development",10,"High","hello world","2022-09-19",ASSIGNEES[1],false);
let task5 = new Task("This is task 5",["Testing"],"Bug","Not Started",99,"Low","hello world","2022-09-19",ASSIGNEES[1],true);
let task6 = new Task("This is task 6",["UI","Core"],"User Story","Completed",17,"High","hello world","2022-09-19",ASSIGNEES[0],true);

let testList = new TaskList();
testList.addTask(task1);
testList.addTask(task2);
testList.addTask(task3);
testList.addTask(task4);
testList.addTask(task5);
testList.addTask(task6);

storeData(TASKLIST_KEY,testList);
}