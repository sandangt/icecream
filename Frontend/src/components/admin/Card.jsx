import React from "react";

function Card(props) {
    return (
        <div class="col-lg-4 col-md-6 mb-3">
            <div class="card h-100">
                <img class="card-img-top" src="https://picsum.photos/500/325?random=3" alt="random image size 500-325"/>
                <div class="card-body">
                    <h4 class="card-title">COURSE</h4>
                    <h4 class="card-title">{props.cardTitle}</h4>
                    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo magni sapiente, tempore debitis beatae culpa natus architecto.</p>
                    <p class="card-text">{props.cardContent}</p>
                </div>
                <div class="card-footer">
                    <a href="/admin/course" class="btn btn-primary">Find Out More!</a>
                </div>
            </div>
        </div>
    );
}

export default Card;