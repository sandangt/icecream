import React from "react";

function Card(props) {
    return (
<div className="col-lg-4 col-md-6 mb-3">
    <div className="card h-100">
        <img className="card-img-top" src={`https://picsum.photos/500/325?random=${props.randomNumber}`} alt="random image size 500-325"/>
        <div className="card-body">
            <h4 className="card-title">{props.cardTitle}</h4>
            <p className="card-text">{props.cardContent}</p>
        </div>
        <div className="card-footer">
            <a href={props.cardUrl} className="btn btn-primary">Find Out More!</a>
        </div>
    </div>
</div>
    );
}

export default Card;