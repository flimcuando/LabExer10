$(document).ready(function() {
    $("#departing").datepicker();
    $("#returning").datepicker();
    $("button").click(function() {
        var departing = $("#departing").val();
        var returning = $("#returning").val();
        if (departing === "" || returning === "") {
			alert("Please select departing and returning dates.");
        } else {
			confirm("Would you like to book the date from " + departing + " to " + returning + "?");
        }
    });
});