export function getNotificationElement(){
    return `<div id="notification-box" class= "alert alert-danger">
    return </div>`;
}

export function showNotification(messageText, messageType){
    //TODO: change display of message based on message type
    const notifyBox = $("#notification-box");
    $("#notification-box").hide();
    $("#notification-box").removeClass();
    $("#notification-box").addClass("alert");
    $("#notification-box").addClass("alert-" + messageType);
    $("#notification-box").text(messageText);
    $("#notification-box").slideDown(1000).delay(3500).slideUp(1000).hide();
}