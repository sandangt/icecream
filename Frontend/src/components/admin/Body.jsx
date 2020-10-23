import React from "react";

let DUMMY_DATA = [{
    title: "MAJOR",
    content: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo magni sapiente, tempore debitis beatae culpa natus architecto.",
    url: ""
    }, {
    title: "CURRICULUM",
    content: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sapiente esse necessitatibus neque."

    }, {
    title: "COURSE",
    content: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo magni sapiente, tempore debitis beatae culpa natus architecto."
    }
]
class Body extends React.Component {
    
    render() {
        return (
<main>
<div className="container">
    <header className="jumbotron my-4">
        <h1 className="display-3">Hello</h1>
        <p className="lead">CRUD app with spring MVC</p>
        <a href="<c:url value='/admin/fun'/>" class="btn btn-primary btn-lg">Call to action!</a>
    </header>
    <div className="row text-center">
        
        <div className="col-lg-4 col-md-6 mb-3">
            <div className="card h-100">
                <img className="card-img-top" src="https://picsum.photos/500/325?random=1" alt="random image size 500-325"/>
                <div className="card-body">
                    <h4 className="card-title">MAJOR</h4>
                    <p className="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo magni sapiente, tempore debitis beatae culpa natus architecto.</p>
                </div>
                <div className="card-footer">
                    <a href="<c:url value='/admin/major?page=${default_startPage}&maxItemsPerPage=${default_maxItemsPerPage}' />" className="btn btn-primary">Find Out More!</a>
                </div>
            </div>
        </div>

        <div className="col-lg-4 col-md-6 mb-3">
            <div className="card h-100">
                <img className="card-img-top" src="https://picsum.photos/500/325?random=2" alt="random image size 500-325"/>
                <div className="card-body">
                    <h4 className="card-title">CURRICULUM</h4>
                    <p className="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sapiente esse necessitatibus neque.</p>
                </div>
                <div className="card-footer">
                    <a href="<c:url value='/admin/curriculum'/>" className="btn btn-primary">Find Out More!</a>
                </div>
            </div>
        </div>

        <div class="col-lg-4 col-md-6 mb-3">
            <div class="card h-100">
                <img class="card-img-top" src="https://picsum.photos/500/325?random=3" alt="random image size 500-325"/>
                <div class="card-body">
                    <h4 class="card-title">COURSE</h4>
                    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo magni sapiente, tempore debitis beatae culpa natus architecto.</p>
                </div>
                <div class="card-footer">
                    <a href="/admin/course" class="btn btn-primary">Find Out More!</a>
                </div>
            </div>
        </div>
        DUMMY_DATA.map( () => {
    });
        <Card cardTitle="" cardContent="">

    </div>
</div>
</main>
        );
    }
}