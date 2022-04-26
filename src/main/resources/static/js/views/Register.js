import CreateView from "../createView.js"
import {getHeaders} from "../auth.js";


export default function Register(props) {
    return `
    <!DOCTYPE html>
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Register</title>
            </head>
            <body>
                <h1>Register</h1>
        
                <form id="register-form">
                    <label for="username">Username</label>
                    <input id="username" name="username" type="text"/>
                    <label for="email">Email</label>
                    <input id="email" name="email" type="email">
                    <label for="password">Password</label>
                    <input id="password" name="password" type="password"/>  <!--Applied the hidden attiribute here-->
                    <button id="register-btn" type="button">Register</button>
                </form>
            </body>
        </html>
`;
}

export function RegisterEvent(){
    $("#register-btn").click(function(){
// When this button is clicked is allows you to get input userinfo
        let newUser = {
            username: $("#username").val(),
            email: $("#email").val(),
            password: $("#password").val()
        }
// This creates the newUser object for new input
        console.log(newUser);

        let request = {
            method: "POST",
            headers: getHeaders(),
            body: JSON.stringify(newUser)
        }
// This makes the object "POST" method request parameters (what it is)
        fetch("http://localhost:8080/api/users/create", request)
            .then(response => {
                console.log(response.status);
                CreateView("/");
            })
// This sends the request for users
    })
}