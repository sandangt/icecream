import React from "react";
import faker from "faker";
import {Link} from "react-router-dom";
import {connect} from "react-redux";

import "./Home.css";

class Home extends React.Component {
    render() {
        return (
<div>
    <div className="row align-items-center my-5">
        <div className="col-lg-7">
            <img
                className="img-fluid rounded mb-4 mb-lg-0"
                src={faker.image.image()}
                alt=""
            />
        </div>
        <div className="col-lg-5">
            <h1 className="font-weight-light">
                {faker.lorem.words()}
            </h1>
            <p>
                {faker.lorem.paragraph()}
            </p>
            <Link className="btn btn-primary" to="/about">
                About us!
            </Link>
        </div>
    </div>
    <div className="row">

        <div className="col-md-4 mb-5">
            <div className="card h-100">
                <img src={faker.random.image()} height="300" width="300"/>
                <div className="card-body">
                    <h2 className="card-title">{faker.lorem.words()}</h2>
                    <p className="card-text">
                        {faker.lorem.paragraph()}
                    </p>
                </div>
                <div className="card-footer">
                    <Link to="/home" className="btn btn-primary btn-sm">
                        More Info
                    </Link>
                </div>
            </div>
        </div>

        <div className="col-md-4 mb-5">
            <div className="card h-100">
                <img src={faker.random.image()} height="300" width="300"/>
                <div className="card-body">
                    <h2 className="card-title">{faker.lorem.words()}</h2>
                    <p className="card-text">
                        {faker.lorem.paragraph()}
                    </p>
                </div>
                <div className="card-footer">
                    <Link to="/home" className="btn btn-primary btn-sm">
                        More Info
                    </Link>
                </div>
            </div>
        </div>

        <div className="col-md-4 mb-5">
            <div className="card h-100">
                <img src={faker.random.image()} height="300" width="300"/>
                <div className="card-body">
                    <h2 className="card-title">{faker.lorem.words()}</h2>
                    <p className="card-text">
                        {faker.lorem.paragraph()}
                    </p>
                </div>
                <div className="card-footer">
                    <Link to="/home" className="btn btn-primary btn-sm">
                        More Info
                    </Link>
                </div>
            </div>
        </div>

    </div>
</div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isLoggedIn: state.auth.isLoggedIn,
        user: state.auth.user
    }
}
export default connect(mapStateToProps)(Home);
