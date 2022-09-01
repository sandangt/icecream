import React from 'react';
import {connect} from "react-redux";
import {Redirect} from "react-router-dom";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";

class UpdateUser extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            email: "",
            status: "1",
            roles: [],
            profile: {},
            getloading: false,
            postloading: false
        }
        this.inputRoles= [];
        this.inputStatus = "1";
    }

    componentDidMount() {
        this.getData();
    }
    componentDidUpdate(){
        setTimeout(() => this.setState({postloading: false}), 7500);
    }
    getData = async () => {
        await baseUrl.get(`/users/${this.props.match.params.id}`, {headers: authHeader()})
            .then(response => {
                this.setState ({
                username : response.data.username,
                email: response.data.email,
                status: response.data.status,
                roles: response.data.roles,
                profile: response.data.userDetail,
                getloading:true
                });
            })
            .catch( error => {
                console.log(error);
            });
    }

    renderRoles = () => {
        return this.state.roles.map((value) => <li>{value.name}</li>);
    }

    backButtonHandle = (e) => {
        e.preventDefault();
        this.props.history.push("/admin/users");
    }

    onSelectStatus = (e) => {
        this.inputStatus = e.target.value;
    }

    onCheckBox = (e) => {
        let obj = {
            id : e.target.value.split(" ")[0],
            name : e.target.value.split(" ")[1]
        }
        if (e.target.checked) {
            this.inputRoles.push(obj);
        }
        else {
            this.inputRoles.pop(obj);
        }
    }

    onSubmit = (e) => {
        e.preventDefault();
        const pkg = {
            status: this.inputStatus,
            roles: this.inputRoles
        };
        baseUrl.put(`/users/${this.props.match.params.id}/roles-status`, pkg, {headers: authHeader()})
            .then(() => this.setState({ postloading: true}))
            .catch(error => console.log(error));
    }

    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_ADMIN")) {
            return <Redirect to="/error"/>;
        }
        return (
    <div>
        <div className="row">
            <div className="col-12">
                <div className="card">
                    <div className="card-body">
                        <div className="d-flex justify-content-center">
                            <div className="image-container">
                                {this.state.profile && <img src={this.state.profile.avatar} class="avatar img-circle img-thumbnail" alt="avatar" height="200" width="200"/>}
                            </div>
                            <div className="userData ml-3">
                                <h2 className="d-block" style={{fontSize: "1.5rem", fontWeight: "bold",}}>
                                    username: {this.state.username}<br/>
                                </h2>
                                {this.state.roles && (
                                <ul>
                                    {this.renderRoles()}
                                </ul>
                                )}
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-12">
                                <ul
                                    className="nav nav-tabs mb-4"
                                    id="myTab"
                                    role="tablist"
                                >
                                    <li className="nav-item">
                                        <a
                                            className="nav-link active"
                                            id="basicInfo-tab"
                                            data-toggle="tab"
                                            href="#basicInfo"
                                            role="tab"
                                            aria-controls="basicInfo"
                                            aria-selected="true"
                                        >
                                            Basic Info
                                        </a>
                                    </li>
                                    <li className="nav-item">
                                        <a
                                            className="nav-link"
                                            id="connectedServices-tab"
                                            data-toggle="tab"
                                            href="#connectedServices"
                                            role="tab"
                                            aria-controls="connectedServices"
                                            aria-selected="false"
                                        >
                                            Update user's role and status
                                        </a>
                                    </li>
                                </ul>
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
                                                    Email
                                                </label>
                                            </div>
                                            <div className="col-md-8 col-6">
                                                {this.state.email}
                                            </div>
                                        </div>
                                        <hr/>
                                        <div className="row">
                                            <div className="col-sm-3 col-md-2 col-5">
                                                <label style={{ fontWeight:"bold",}}>
                                                    Full Name
                                                </label>
                                            </div>
                                            <div className="col-md-8 col-6">
                                                {this.state.profile !== null ? this.state.profile.fullname : null}
                                            </div>
                                        </div>
                                        <hr />
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
                                                <label
                                                    style={{
                                                        fontWeight:
                                                            "bold",
                                                    }}
                                                >
                                                    Address
                                                </label>
                                            </div>
                                            <div className="col-md-8 col-6">
                                            {this.state.profile !== null ? this.state.profile.address : null}
                                            </div>
                                        </div>
                                        <hr />
                                    </div>
                                    <div
                                        className="tab-pane fade"
                                        id="connectedServices"
                                        role="tabpanel"
                                        aria-labelledby="ConnectedServices-tab"
                                    >
                                    <div className="row">
                                        <div className="col-sm-3 col-md-2 col-5">
                                            <label
                                                style={{ fontWeight: "bold",}}>
                                                Status
                                            </label>
                                        </div>
                                        <div className="col-md-8 col-6">
                                            <div className="d-flex flex-row align-items-center">
                                                <select defaultValue={this.state.status} onChange={this.onSelectStatus}>
                                                    <option value="1">AVAILABLE</option>
                                                    <option value="0">UNAVAILABLE</option>
                                                </select>
                                            </div><br/>
                                        </div>
                                    </div>
                                    <hr />
                                    <div className="row">
                                        <div className="col-sm-3 col-md-2 col-5">
                                            <label style={{fontWeight:"bold",}}>
                                                Roles
                                            </label>
                                        </div>
                                        <div className="col-md-8 col-6">
                                            <input type="checkbox" name="ROLE_ADMIN" value="1 ROLE_ADMIN" onChange={this.onCheckBox} />
                                            ROLE_ADMIN
                                            <br/>
                                            {/* <input type="checkbox" name="ROLE_STAFF" value="2 ROLE_STAFF" onChange={this.onCheckBox}/>
                                            ROLE_STAFF
                                            <br/> */}
                                            <input type="checkbox" name="ROLE_USER" value="3 ROLE_USER" onChange={this.onCheckBox}/>
                                            ROLE_USER
                                        </div>
                                    </div>
                                    <hr />
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
export default connect(mapStateToProps)(UpdateUser);