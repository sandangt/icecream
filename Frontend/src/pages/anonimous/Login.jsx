import React from "react";
import { Redirect } from "react-router-dom";
import { connect } from "react-redux";

import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

import { login } from "actions/auth.js";

import "./AuthenticationCard.css";

const required = (value) => {
    if (!value) {
        return (
            <div className="alert alert-danger" role="alert">
                This field is required!
            </div>
        );
    }
};

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            successful: false,
        };
    }

    handleLogin = (e) => {
        e.preventDefault();
        this.setState({
            successful: true,
        });
        this.form.validateAll();
        if (this.checkBtn.context._errors.length === 0) {
            this.props.login(this.state.username, this.state.password)
                .then(() => {
                    this.props.history.push("/home");
                    window.location.reload();
                })
                .catch(() => {
                    this.setState({
                        successful: false,
                    });
                });
        } 
        else {
            this.setState({
                successful: false,
            });
        }
    };

    render() {
        if (this.props.isLoggedIn) {
            return <Redirect to="/home"/>
        }
        return (
            <div className="col-md-12">
                <div className="card card-container">
                    <img src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
                        alt="profile-img"
                        className="profile-img-card"/>
                    <Form
                        onSubmit={this.handleLogin}
                        ref={(c) => {this.form = c;}}
                    >
                        <div className="form-group">
                            <label htmlFor="username">Username</label>
                            <Input
                                type="text"
                                className="form-control"
                                name="username"
                                value={this.state.username}
                                onChange={(e) => this.setState({username: e.target.value})}
                                validations={[required]}
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="password">Password</label>
                            <Input
                                type="password"
                                className="form-control"
                                name="password"
                                value={this.state.password}
                                onChange={(e) => {this.setState({password: e.target.value,})}}
                                validations={[required]}
                            />
                        </div>

                        <div className="form-group">
                            <button className="btn btn-primary btn-block" disabled={this.state.successful}>
                                {this.state.successful && (
                                    <span className="spinner-border spinner-border-sm"></span>
                                )}
                                <span>Login</span>
                            </button>
                        </div>

                        {this.props.message && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    {this.props.message}
                                </div>
                            </div>
                        )}
                        <CheckButton style={{ display: "none" }} ref={(c) => {this.checkBtn = c;}}/>
                    </Form>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isLoggedIn : state.auth.isLoggedIn,
        message : state.message.message
    };
}

export default connect(mapStateToProps, {login:login})(Login);
