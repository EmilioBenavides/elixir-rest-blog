import createView from "../createView.js";

const BASE_URI = 'http://localhost:8081/api/users';

export default function UserProfile(props) {
    console.log(props)
    return `
            <head>
                <meta charset="UTF-8"/>
                <title>User Login</title>
            </head>
            <body>
                <h1>User Page</h1>
        
                <form id="User-form">
                    <label for="username">Username</label>
                    <input disabled id="username" name="username" value="${props.users.username}" type="text"/> 
                    <label for="email">Email</label>
                    <input disabled id="email" name="email" value="${props.users.email}" type="email">
                    <label for="new-password">New Password</label>
                    <input id="new-password" name="new-password" value="incorrect password" type="password">
                    <button id="user-btn" type="button">Edit Password</button>
                 </form>
            </body>
`;
}

export function UserEvents() {
    $("#change-password-button").click(function() {
        // 1. grab data from form fields
        const userId = 1; // $("#add-post-id").val();
        let uriExtra = '/1/updatePassword';
        // const oldPassword = $("#old-password").val()
        const newPassword = $("#new-password").val()
        const oldPassword = $("#old-password").val()

        // 2. assemble the request
        const request = {
            method: "PUT",
        }

        // 3. do the fetch with the correct URI please (check against Postman)
        fetch(`${BASE_URI}${uriExtra}?oldPassword=${oldPassword}&newPassword=${newPassword}`, request)
            .then(res => {
                console.log(`${request.method} SUCCESS: ${res.status}`);
            }).catch(error => {
            console.log(`${request.method} ERROR: ${error}`);
        }).finally(() => {
            createView("/users");
        });

    });
}




