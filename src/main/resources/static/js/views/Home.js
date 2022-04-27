import {showNotification} from "../messaging.js";
import {getHeaders, getUser, getUserRole} from "../auth.js";

export default function Home(props) {
    console.log("The frontend did it. HER FAULT");
    return `
        <header>
            <h1>Home Page</h1>
        </header>
        <main>
            <div>
                <p>
                    This is the home page text.
                </p>    
            </div>
        </main>
    `;
}

export function HomeEvents(){
    //TODO: use an enum for message type

   const user = getUser();
   if(!user) {
       showNotification("Welcome visitor", "info");
   } else {
       showNotification("Welcome, " + user.userName, "info")
   }
}