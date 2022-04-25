import createView from "../createView.js";
import {getHeaders} from "../auth.js";

const POST_URI = "http://localhost:8080/api/posts"

export default function PostIndex(props) {
    return `
        <header xmlns="http://www.w3.org/1999/html">
            <h1>Posts Page</h1>
        </header>
        <main>
            <div id="posts-container">
             ${props.posts.map(post =>
        `<h3 id="title-${post.id}">${post.title}</h3>
                <p id="content-${post.id}">${post.content}</p>
                
                <button type="button" class="btn btn-primary edit-post-button" data-id="${post.id}"> Edit</button>
                <button type="button" class="btn btn-primary delete-post-button" data-id="${post.id}"> Delete</button>
                `).join('')}  
            </div>
                  <div id="add-post-container">
            <form id="add-post-container"> 
            <div class="mb-3 mt-4">
                  <label for="add-post-container"><h5>Title</h5></label>
                  <input id="add-post-title" class="form-control" type="text">
                  <textarea name="userImput"  id="add-post-content" rows="10" cols="50">Please enter content</textarea>
                  <button id="add-post-button" class="btn btn-primary">Add Post</button>
                  <button  id="edit-post-button" class="btn btn-primary">Save Post</button>
            </div>
            </form>     
                </div>
        </main>
    `;
}

export function PostsEvent() {
    createAddPostListener();
    editPostListener();
    savePostListener();
    deletePostListener();
}

function createAddPostListener() {
    console.log("adding add post listener");
    $("#add-post-button").click(function () {
        const title = $("#add-post-title").val();
        const content = $("#add-post-content").val();
        const newPost = {
            title,
            content,
        }
        console.log("ready to add: ");
        console.log(newPost);

        const request = {
            method: "POST",
            headers: getHeaders(),
            body: JSON.stringify(newPost)
        }
        fetch(POST_URI, request)
            .then(res => {
                console.log(res.status);
            }).catch(error => {
            console.log(error);
        }).finally(() => {
            createView("/posts")
        })
    })

}


function editPostListener() {
    $(".edit-post-button").click(function () {
        const id = $(this).data("id")
        const oldTitle = $(`#title-${id}`).html();
        const oldContent = $(`#content-${id}`).text();
        console.log(oldTitle);
        console.log(oldContent);
        $("#add-post-title").val(oldTitle);
        $("#add-post-content").val(oldContent);
        $("#edit-post-button").data("id", id)
    })
}

function savePostListener() {
    $("#edit-post-button").click(function () {
        const id = $(this).data("id")
        const title = $("#add-post-title").val();
        const content = $("#add-post-content").val();
        const newPost = {
            title,
            content,
        }
        console.log("ready to save: ");
        console.log("savePost");

        const request = {
            method: "PUT",
            headers: getHeaders(),
            body: JSON.stringify(newPost)
        }
        fetch(POST_URI + "/" + id, request)
            .then(res => {
                console.log(res.status);
            }).catch(error => {
            console.log(error);
        }).finally(() => {
            createView("/posts")
        })
    })
}

function deletePostListener(){

    $(".delete-post-button").click(function () {
        const id = $(this).data("id")
        const request = {
            method: "DELETE",
            headers: getHeaders()
        }
        fetch(POST_URI + "/" + id, request)
            .then(res => {
                console.log(res.status);
                console.log("Deleting a post");
            }).catch(error => {
            console.log(error);
        }).finally(() => {
        })
    })
}
// <p id="author-${post.id}">Author: ${post.author.username}</p>