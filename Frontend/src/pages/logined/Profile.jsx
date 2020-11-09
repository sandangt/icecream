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
            firstname : "",
            lastname : "",
            address : "",
            gender : "",
            birthday : null,
            avatar : "/images/users/default.png",
            getloading: false,
            postAccountLoading: false,
            postProfileLoading: false
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
                firstname : response.data.userDetail !== null ? response.data.userDetail.firstname : "",
                lastname : response.data.userDetail !== null ? response.data.userDetail.lastname : "",
                address : response.data.userDetail !== null ? response.data.userDetail.address : "",
                gender : response.data.userDetail !== null ? response.data.userDetail.gender : "",
                birthday : response.data.userDetail !== null ? response.data.userDetail.birthday : null,
                avatar : response.data.userDetail !== null ? response.data.userDetail.avatar : "/images/users/default.png",
                getloading: true
                });
            })
            .catch( error => {
                console.log(error);
            });
    }
    
    homeButtonHandle = (e) => {
        e.preventDefault();
        this.props.history.push("/home");
    }

    onSubmit = async (e) => {
        e.preventDefault();
        const userPkg = {
            username: this.state.username,
            email: this.state.email
        };
        const userDetailPkg = {
            firstname : this.state.firstname,
            lastname : this.state.lastname,
            address : this.state.address,
            gender : this.state.gender,
            birthday : this.state.birthday,
            avatar : this.state.avatar
        };
        await baseUrl.put(`/users/${this.props.user.id}/account`, userPkg, {headers: authHeader()})
            .then(() => this.setState({ postAccountLoading: true}))
            .catch(error => console.log(error));
        await baseUrl.put(`/users/${this.props.user.id}/profile`, userDetailPkg, {headers: authHeader()})
            .then(() => this.setState({ postProfileLoading: true}))
            .catch(error => console.log(error));
    }

    render() {
        const { user, isLoggedIn } = this.props;

        if (!isLoggedIn) {
            return <Redirect to="/error"/>;
        }
        return (
<div>
    <header className="jumbotron">
        <div className="d-flex justify-content-left">
            <div className="image-container">
                <img src={this.state.avatar} className="avatar img-circle img-thumbnail" alt="avatar" height="200" width="200"/>
                <h6>Upload a different photo...</h6>
                <input type="file" 
                className="text-center center-block file-upload" 
                onChange={(e) => {
                    e.preventDefault();
                    this.setState({avatar: `/images/users/${e.target.value.split("\\")[2]}`});
                }}/>
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
        <div className="d-flex align-items-start justify-content-center">
            <h3>
                <strong>{user.username}</strong> Profile
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
                                            {user.id}
                                        </div>
                                    </div>
                                    <hr/>
                                    <div className="row">
                                        <div className="col-sm-3 col-md-2 col-5">
                                            <label style={{ fontWeight: "bold",}}>
                                                Username
                                            </label>
                                        </div>
                                        <div className="col-md-8 col-6">
                                            <input type="text" 
                                            value={this.state.username} 
                                            onChange={(e) => {
                                                e.preventDefault();
                                                this.setState({username:e.target.value});
                                            }}/>                                            
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
                                            <input type="text"
                                            value={this.state.email}
                                            onChange={(e) => {
                                                e.preventDefault();
                                                this.setState({email: e.target.value});
                                            }}
                                            />                                            
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
                                            <input type="text" 
                                            value={this.state.firstname} 
                                            onChange={(e) => {
                                                e.preventDefault();
                                                this.setState({firstname: e.target.value});
                                            }}/>                                            
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
                                            <input type="text" 
                                            value={this.state.lastname}
                                            onChange={(e) => {
                                                e.preventDefault();
                                                this.setState({lastname: e.target.value})
                                            }}
                                            />                                            
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
                                        <input type="date" 
                                        value={this.state.birthday}
                                        onChange={(e) => {
                                            e.preventDefault();
                                            this.setState({birthday: e.target.value})
                                        }}
                                        />
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
                                            <select value={this.state.gender} onChange={ (e) => {
                                                e.preventDefault();
                                                this.setState({gender:e.target.value})
                                            }}>
                                                <option value="MALE">male</option>
                                                <option value="FEMALE">female</option>
                                            </select>
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
                                            <input type="text" 
                                            value={this.state.address}
                                            onChange={(e) => {
                                                e.preventDefault();
                                                this.setState({address: e.target.value})
                                            }}
                                            />
                                        </div>
                                    </div>
                                    <hr />
                                    <div className="row">
                                        <div className="col-sm-3 col-md-2 col-5">
                                            <label style={{ fontWeight: "bold",}}>
                                                Token
                                            </label>
                                        </div>
                                        <div className="col-md-8 col-6">
                                            {this.props.user.token}
                                        </div>
                                    </div>
                                </div>
                                <button onClick={this.onSubmit} className="btn btn-primary">Submit</button>
                                <br/>
                                {this.state.postAccountLoading && this.state.postProfileLoading && (
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
        <button onClick={this.homeButtonHandle} className="btn btn-primary">Home</button>
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
