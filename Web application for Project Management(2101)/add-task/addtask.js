var taskCards = document.getElementsByClassName('taskCards');
var dropZone = document.getElementsByClassName('drop-zone');
var dragItem = null;
var temptaskList = retrieveAllTasks();

/***
 * Creates innerHTML for product backlog container to display tasks in
 * product backlog that has not been assigned with a sprint id.
 */
function displayPbItems(){
  let pbItems = retrieveAllTasks();
  let pb = document.getElementById("pb");
  let sb = document.getElementById("sb");
  for (let i = 0; i < pbItems.getLength(); i++) {
    const card = document.createElement('div');
        card.className = `container taskCards`;
        card.id = `${i}`
        card.title = "Drag task"
        card.draggable = `true`;
        card.innerHTML = 
        `
          <div class="row">
            <div class="col-lg-2">
              <div class="circle ${COLOR[pbItems.getTask(i).priority]}"></div>
            </div>
            <div class="col-lg-9">
              <div>
                <label class="taskName">${pbItems.getTask(i).taskName}</label>
              </div>
            <div>
                <label class="taskStatus">Status: ${pbItems.getTask(i).status}</label>
            </div>
            </div>
          </div>`;
    if (pbItems.getTask(i).sprintId == undefined){
      pb.append(card);
    }
  }
}

displayPbItems();

/*
Runs when user start dragging the task card
*/
function dragStart() {
  dragItem=this;
  setTimeout(() => this.style.display="none",0);
}

/*
Runs when user stops dragging the task card
*/
function dragEnd() {
  setTimeout(() => this.style.display="block", 0);
  dragItem=null;
}

/*
Runs when user drops the task card
Task card dropped into the drop zone will be appended into it
*/
function Drop() {
  setTimeout(() => dragItem.style.display="block", 0);
  this.append(dragItem);
  if (this.id == "sb"){
    addToSprint(dragItem);
  }else if (this.id == "pb"){
    removeFromSprint(dragItem);
  }
}

/*
Add event listener for each task card
*/
for (var i of taskCards){
  i.addEventListener('dragstart', dragStart);
  i.addEventListener('dragend', dragEnd);
}

/**
 * Assign sprint id to task after being dragged
 * from product backlog to sprint backlog
 * @param {innterHTML} item innerHTML of a task card in drag n drop page
 */
function addToSprint(item){
  let taskIndex = parseInt(item.id);
  let sprintIndex = retrieveData(SPRINT_TO_VIEW);
  temptaskList.getTask(taskIndex).new_sprintId=sprintIndex;
}

/**
 * Removes assigned sprint id from task after being dragged
 * from sprint backlog back to product backlog
 * @param {innerHTML} item innerHTML of a task card in drag n drop page
 */
function removeFromSprint(item){
  let index = parseInt(item.id);
  
  temptaskList.getTask(index)._sprintId = undefined;

}

/*
Runs when user drags the task card into the drop zone
Parameters:
    e: the drag event
*/
function dragEnter(e) {
  e.preventDefault();
}

/*
Runs when user drags the task card over the drop zone
Parameters:
    e: the drag event
*/
function dragOver(e) {
  e.preventDefault();
}

/*
Runs when user drags the task away from the drop zone
*/
function dragLeave() {
  this.style.border = "none";
}

/*
Add event listener for each drop zone
*/
for(var j of dropZone) {
  j.addEventListener('dragover', dragOver);
  j.addEventListener('dragEnter', dragEnter);
  j.addEventListener('dragLeave', dragLeave);
  j.addEventListener('drop', Drop);
}

/**
 * Changes sprint status from "Get Started" to "In Progress"
 * and updates local storage with the sprint id assigned to the tasks
 */
function startSprint() {
  id = retrieveData(SPRINT_TO_VIEW);
  sprintIsEmpty = true;
  for (let i = 0; i < temptaskList.getLength();i++){
    task = temptaskList.getTask(i);
    if (task.sprintId == id){
      sprintIsEmpty = false
      break
    }
  }
  if (sprintIsEmpty == false){
    const sprintList = retrieveAllSprints();
    sprintList.getSprint(id)._status = "In Progress";
    storeData(SPRINTS_KEY, sprintList);
    storeData(TASKLIST_KEY, temptaskList);
    window.location = "sprintlist.html";
  }else{
    if (confirm("Your sprint is empty!")) {
      window.location = "sprintlist.html";
    } 
  }
}