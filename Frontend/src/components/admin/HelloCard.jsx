import React from "react";

function HelloCard(props) {
    return (
        <header className="jumbotron my-4">
            <h1 className="display-3">{props.foreWord}</h1>
            <p className="lead">{props.content}</p>
            <a href={props.url} class="btn btn-primary btn-lg">Call to action!</a>
        </header>
    );

}

export default HelloCard;