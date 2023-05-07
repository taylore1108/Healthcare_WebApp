$(document).ready(function () {
    $('#calendar').fullCalendar({
        events: [
            {
                title: 'Event 1',
                start: '2023-05-01',
                end: '2023-05-02'
            },
            {
                title: 'Event 2',
                start: '2023-05-05',
                end: '2023-05-07'
            }
        ]
    });
});
