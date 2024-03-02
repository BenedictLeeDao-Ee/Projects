/**
 * Once the form is submitted, the accumulated time and the date will be displayed
 */
task_form.addEventListener("submit", () => {
    event.preventDefault();
    const data = new FormData(event.target);
    const startDate = data.get("date")
    const timeSpent = data.get("duration")
    
    console.log({startDate, timeSpent})
})
