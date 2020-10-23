import React from "react";

class Home extends React.Component {
    render() {
        return (
<main>
    <div className="container">

    <header className="jumbotron my-4">
    <h1 className="display-3">Hello</h1>
    <p className="lead">CRUD app with spring MVC</p>
    <a href="<c:url value='/admin/fun'/>" class="btn btn-primary btn-lg">Call to action!</a>
    </header>

    <!-- Page Features -->
    <div class="row text-center">

    <div class="col-lg-4 col-md-6 mb-3">
    <div class="card h-100">
    <!-- image 500x325 -->
    <img class="card-img-top" src="https://picsum.photos/500/325?random=1" alt="random image size 500-325">
    <div class="card-body">
    <h4 class="card-title">MAJOR</h4>
    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo magni sapiente, tempore debitis beatae culpa natus architecto.</p>
    </div>
    <div class="card-footer">
    <a href="<c:url value='/admin/major?page=${default_startPage}&maxItemsPerPage=${default_maxItemsPerPage}' />" class="btn btn-primary">Find Out More!</a>
    </div>
    </div>
    </div>

    <div class="col-lg-4 col-md-6 mb-3">
    <div class="card h-100">
    <img class="card-img-top" src="https://picsum.photos/500/325?random=2" alt="random image size 500-325">
    <div class="card-body">
    <h4 class="card-title">CURRICULUM</h4>
    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sapiente esse necessitatibus neque.</p>
    </div>
    <div class="card-footer">
    <a href="<c:url value='/admin/curriculum'/>" class="btn btn-primary">Find Out More!</a>
    </div>
    </div>
    </div>

    <div class="col-lg-4 col-md-6 mb-3">
    <div class="card h-100">
    <img class="card-img-top" src="https://picsum.photos/500/325?random=3" alt="random image size 500-325">
    <div class="card-body">
    <h4 class="card-title">COURSE</h4>
    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo magni sapiente, tempore debitis beatae culpa natus architecto.</p>
    </div>
    <div class="card-footer">
    <a href="/admin/course" class="btn btn-primary">Find Out More!</a>
    </div>
    </div>
    </div>

    </div>

    </div>
</main>
        );
    }
}

export default Home;