const memberList = retrieveAllTeamMembers();
const memberForm = document.getElementById("_assignee");

memberForm.innerHTML = "";
for (let i = 0; i < memberList.getLength(); i++) {
    const option = document.createElement("option");
    option.innerHTML = memberList.getMemberByIndex(i).name;
    memberForm.append(option);
}