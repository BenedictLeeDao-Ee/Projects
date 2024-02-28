const member_form = document.getElementById("add-member-form");

/**
 * Stores submitted member data into local storage
 */
 function handleSubmit() {
    event.preventDefault();
    // Extracts the data from teh form that the user filled
    let member = Object.fromEntries(new FormData(member_form));
    if (true) {
        // Adds a new team member to the list along with its details stored in the local storage
        let newMember = new TeamMember(member._name,member._email);
        let memberList = retrieveAllTeamMembers();
        memberList.addMember(newMember);
        storeData(MEMBERS_KEY, memberList);
        window.location = "team-board.html";
    }
}