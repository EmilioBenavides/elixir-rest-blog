import createView from './createView.js';// We use imports/exports to get access from different files,
                                        // you have to use relative paths when communicating to other files

export default function init() {
    loadViewOnPageRequest();
    addListenerToNavLinks();

    removeStaleTokens
}
/**
 * When the DOM loads, build the view given the current endpoint.
 */
function loadViewOnPageRequest() {
    window.addEventListener('DOMContentLoaded', function() { // <-------
        //TODO: Switched to location.pathname so the route would be accurate to current view
        createView(location.pathname);// this function happens when the "DOMContent loads"
    });
}

/**
 * Add a listener that will change the view if a nav link is clicked.
 */
function addListenerToNavLinks() { // this adds the listener when any of the tabs are clicked
    document.addEventListener('click', e => {
        e.preventDefault();
        if (e.target.dataset['link'] !== undefined) {
            const URI = e.target.href.substring(location.origin.length);
            createView(URI);
        }
    });
}

