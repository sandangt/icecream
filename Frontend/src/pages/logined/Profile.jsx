import React from "react";
import { Redirect } from "react-router-dom";
import { connect } from "react-redux";

import authHeader from "services/authHeader.js";
import baseUrl from "baseUrl.js";

class ProfileUser extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            email: "",
            status: 1,
            roles: [],
            profile: {},
            getloading: false,
            postloading: false
        }
    }
    componentDidMount() {
        this.getData();
    }
    getData = async () => {
        await baseUrl.get(`/users/${this.props.user.id}`, {headers: authHeader()})
            .then(response => {
                this.setState ({
                username : response.data.username,
                email: response.data.email,
                status: response.data.status,
                roles: response.data.roles,
                profile: response.data.userDetail,
                getloading: true
                });
            })
            .catch( error => {
                console.log(error);
            });
    }

    render() {
        const { user, isLoggedIn } = this.props;

        if (!isLoggedIn) {
            return <Redirect to="/error" />;
        }
        return (
<div>
    <header className="jumbotron">
        <div className="d-flex justify-content-left">
            <div className="image-container">
                {this.state.profile && <img src={this.state.profile.avatar} class="avatar img-circle img-thumbnail" alt="avatar" height="200" width="200"/>}
                <h6>Upload a different photo...</h6>
                <input type="file" class="text-center center-block file-upload"></input>
            </div>
            <div className="ml-auto">
                <input
                    type="button"
                    className="btn btn-primary d-none"
                    id="btnDiscard"
                    defaultValue="Discard Changes"
                />
            </div>
        </div>
        <div className="d-flex justify-content-center">
            <h3>
                <strong>{this.props.user.username}</strong> Profile
            </h3>
            <ul>
                {user.roles &&
                    user.roles.map((role, index) => (
                        <li key={index}>{role}</li>
                    ))}
            </ul>
        </div>
    </header>
    <div className="row">
        <div className="col-12">
            <div className="card">
                <div className="card-body">
                    <div className="row">
                        <div className="col-12">
                            <div className="tab-content ml-1" id="myTabContent">
                                <div
                                    className="tab-pane fade show active"
                                    id="basicInfo"
                                    role="tabpanel"
                                    aria-labelledby="basicInfo-tab"
                                >
                                    <div className="row">
                                        <div className="col-sm-3 col-md-2 col-5">
                                            <label style={{ fontWeight: "bold",}}>
                                                user id
                                            </label>
                                        </div>
                                        <div className="col-md-8 col-6">
                                            {this.props.user.id}
                                        </div>
                                    </div>
                                    <hr/>
                                    <div className="row">
                                        <div className="col-sm-3 col-md-2 col-5">
                                            <label style={{ fontWeight: "bold",}}>
                                                Email
                                            </label>
                                        </div>
                                        <div className="col-md-8 col-6">
                                            <input type="text" value={this.state.email}/>                                            
                                        </div>
                                    </div>
                                    <hr/>
                                    <div className="row">
                                        <div className="col-sm-3 col-md-2 col-5">
                                            <label style={{ fontWeight:"bold",}}>
                                                First Name
                                            </label>
                                        </div>
                                        <div className="col-md-8 col-6">
                                            <input type="text" value={this.state.profile !== null ? this.state.profile.fullname : null}/>                                            
                                        </div>
                                    </div>
                                    <hr />
                                    <div className="row">
                                        <div className="col-sm-3 col-md-2 col-5">
                                            <label style={{ fontWeight:"bold",}}>
                                                Last Name
                                            </label>
                                        </div>
                                        <div className="col-md-8 col-6">
                                            <input type="text" value={this.state.profile !== null ? this.state.profile.fullname : null}/>                                            
                                        </div>
                                    </div>
                                    <hr/>
                                    <div className="row">
                                        <div className="col-sm-3 col-md-2 col-5">
                                            <label style={{ fontWeight:"bold",}}>
                                                Birth Date
                                            </label>
                                        </div>
                                        <div className="col-md-8 col-6">
                                        {this.state.profile !== null ? this.state.profile.birthday : null}
                                        </div>
                                    </div>
                                    <hr />
                                    <div className="row">
                                        <div className="col-sm-3 col-md-2 col-5">
                                            <label
                                                style={{ fontWeight: "bold",}}>
                                                Gender
                                            </label>
                                        </div>
                                        <div className="col-md-8 col-6">
                                            {this.state.profile !== null ? this.state.profile.gender : null}
                                        </div>
                                    </div>
                                    <hr />
                                    <div className="row">
                                        <div className="col-sm-3 col-md-2 col-5">
                                            <label style={{fontWeight:"bold",}}>
                                                Address
                                            </label>
                                        </div>
                                        <div className="col-md-8 col-6">
                                            {this.state.profile !== null ? this.state.profile.address : null}
                                        </div>
                                    </div>
                                    <hr />
                                    <div className="row">
                                        <div className="col-sm-3 col-md-2 col-5">
                                            <label
                                                style={{ fontWeight: "bold",}}>
                                                Token
                                            </label>
                                        </div>
                                        <div className="col-md-8 col-6">
                                            {this.props.user.token}
                                        </div>
                                    </div>
                                </div>
                                <button onClick={this.onSubmit} className="btn btn-primary">Submit</button>

                                {this.state.postloading && (
                            <div className="alert alert-success" role="alert" >
                                Post data successfully
                            </div>
                            )}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button onClick={this.backButtonHandle} className="btn btn-primary">Back</button>
    </div>
</div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isLoggedIn: state.auth.isLoggedIn,
        user : state.auth.user
    };
}

export default connect(mapStateToProps)(ProfileUser);
