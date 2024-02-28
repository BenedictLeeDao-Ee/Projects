const title = document.getElementById("subject");
const canvas = document.getElementById("chart");
const selected_member = retrieveAllTeamMembers().getMemberByIndex(parseInt(retrieveData(MEMBER_TO_VIEW)));
const logs = selected_member.worklog
const member_name = selected_member.name;
title.innerHTML = `<h1>Analytics - ${member_name}</h1>`;
const dates = getDate().map(x => `${x.getFullYear()}-${x.getMonth() + 1}-${x.getDate()}`)
const labels = dates;


const data = {
labels: labels,
datasets: [{
    label: 'Time Spent(Hours)',
    backgroundColor: 'rgb(255, 99, 132)',
    borderColor: 'rgb(255, 99, 132)',
    data: dates.map(timeSpent),
}]
};

const config = {
    type: 'bar',
    data: data,
    options: {}
};

const myChart = new Chart(
    canvas,
    config
);

/**
 * Gets a week of dates 1 week from today.
 * @returns the dates from that week
 */
function getDate() {
    let curr = new Date;
    let dates = [];
    for (let i = 7; i > 0; i--) {
        dates.push(new Date(curr.getFullYear(), curr.getMonth(), curr.getDate()-i))
    }
    return dates;
}

/**
 * Given a date, calculates the time spent from task list of the assignee.
 * @param {*} date 
 */
function timeSpent(date) {
    let result = 0;
    for (let i = 0; i < logs.length; i++) {
        if (date === logs[i].date) {
            result += parseFloat(logs[i].duration);
        }
    }
    return result;
}