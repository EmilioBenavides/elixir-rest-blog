export function getNotificationElement() {
    return `<div id="notification-box" class= "d-none">
    </div>`;
}

export function showNotification(messageText, messageType){
    //TODO: change display of message based on message type
    const notifyBox = $("#notification-box");
    notifyBox.hide();
    notifyBox.removeClass();
    notifyBox.addClass("alert");
    notifyBox.addClass("alert-" + messageType);
    notifyBox.text(messageText);
    notifyBox.slideDown(1000).delay(3500).slideUp(1000);
}