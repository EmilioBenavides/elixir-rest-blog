import createView from "../createView.js";

export default function Logout(props){
return "";
}

export function LogoutEvents(){
    localStorage.clear();
    // this will redirect you to the home page
    createView("/home");
}






