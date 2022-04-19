import createView from "../createView.js";


export default function UserProfile(props) {
    console.log(props)
    return `
    <!DOCTYPE html>
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>User Login</title>
            </head>
            <body>
                <h1>User</h1>
        
                <form id="User-form">
                    <label for="username">Username</label>
                    <input disabled id="username" name="username" type="text"/>
                    <label for="email">Email</label>
                    <input disabled id="email" name="email" type="email">
                    <label for="edit-password">Edit Password</label>
                    <input id="edit-password" name="password" type="password">
                    <button id="user-btn" type="button">Edit</button>
                 </form>
            </body>
        </html>
`;
}

export function UserEvents() {
    $("#change-password-button").click(function() {
        // 1. grab data from form fields
        const userId = 1; // $("#add-post-id").val();
        let uriExtra = '/1/updatePassword';
        // const oldPassword = $("#old-password").val()
        const newPassword = $("#new-password").val()

        // 2. assemble the request
        const request = {
            method: "PUT"
        }

        // 3. do the fetch with the correct URI please (check against Postman)
        fetch(`${BASE_URI}${uriExtra}?newPassword=${newPassword}`, request)
            .then(res => {
                console.log(`${request.method} SUCCESS: ${res.status}`);
            }).catch(error => {
            console.log(`${request.method} ERROR: ${error}`);
        }).finally(() => {
            createView("/users");
        });

    });
}
}




