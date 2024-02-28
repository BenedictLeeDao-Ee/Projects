/**
 * Fixed variables to be used by all functions in this file
 */ 
const members_view = document.getElementById("members-view");

/**
 * Removes member from local storage and changes assignee of
 * tasks assigned to this member to "Unassigned"
 * @param {number} index 
 */
function deleteMember(index){
    if(confirm("Confirm delete?")){
        let allMembers = retrieveAllTeamMembers();
        let allTasks = retrieveAllTasks();
        let thisMember = allMembers.getMemberByIndex(index);
        //change task assignee to unassigned
        for (let i = 0; i < allTasks.getLength();i++)
        {
            if (thisMember.name == allTasks.tasks[i].assignee)
            {
                allTasks.tasks[i].new_assignee="Unassigned";
            }
        }
        allMembers.deleteMember(index);
        storeData(MEMBERS_KEY,allMembers);
        storeData(TASKLIST_KEY, allTasks);
        window.location.reload();
    }else{
        window.location.reload();
    }
}

/**
 * Function to display all members in card form along with its details
 */
function displayMembers(){
    // Extract all members from the local storage
    team_members = retrieveAllTeamMembers();
    membersList = new TeamMemberList();
    membersList.fromStorage(team_members);
    members_view.innerHTML = '';
    // Loops through the list of members and adds them to the html to display them as cards
    for(let i = 0; i < membersList.getLength();i++){
        const card = document.createElement('div');
        card.className = `card d-flex flex-row`;
        card.id = `member-${i}`;
        card.innerHTML =
        `
        <div class="card-container flex-grow-1">
            <h4 class="card-body text-cutoff">${membersList.getMemberByIndex(i).name}</h4>
            <p class="card-body text-cutoff" id="memberAddress"><u>${membersList.getMemberByIndex(i).email}</u></p>
        </div>
        <div class="sprint-status rounded blue clickable" id=button-${i}>
            Analytics
        </div>
        <div>
            <input type="image" src="./images/rubbish-bin.svg" name="deleteMember" class="btTxt submit icon1 deleteMember" onclick="deleteMember(${i})">
        </div>
        `
        members_view.append(card);
        // Takes you to the analytics page 
        const analytics_button = document.getElementById(`button-${i}`);
        analytics_button.addEventListener('click', () => {
            storeData(MEMBER_TO_VIEW,i);
            window.location = "analytics.html";
            event.stopPropagation();
        });
    }
}


document.getElementById("add-member").addEventListener("click", () => {
    window.location = "addmembers.html";
})

displayMembers();