import createView from "../createView.js";

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
                  <button mt-4 id="add-post-button" class="btn btn-primary">Add Post</button>
                  <button mt-4 id="edit-post-button" class="btn btn-primary">Save Post</button>
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
    // TODO: add edit post listener function
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
            headers: {'Content-Type': 'application/json'},
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
            headers: {'Content-Type': 'application/json'},
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
    console.log("Deleting a post")
    $(".delete-post-button").click(function () {
        const id = $(this).data("id")
        const currentTitle = $(`#title-${id}`).html();
        const currentContent = $(`#content-${id}`).text();
        const currentPost = {
            title,
            content,
        }
        console.log(currentTitle);
        console.log(currentContent);

        const request = {
            method: "DELETE",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(currentPost)
        }
        fetch(POST_URI + "/" + id, request)
            .then(res => {
                console.log(res.status);
            }).catch(error => {
            console.log(error);
        }).finally(() => {
            createView("/delete")
        })
    })

}
