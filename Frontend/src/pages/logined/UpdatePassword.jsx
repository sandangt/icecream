import React from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import {Redirect} from "react-router-dom";

import { connect } from "react-redux";

import "./AuthenticationCard.css";
import {logout, changePassword} from "actions/auth.js";

const required = (value) => {
    if (!value) {
        return (
            <div className="alert alert-danger" role="alert">
                This field is required!
            </div>
        );
    }
};

const validateNewPassword = (value) => {
    if (value.length < 6 || value.length > 40) {
        return (
            <div className="alert alert-danger" role="alert">
                The password must be between 6 and 40 characters.
            </div>
        );
    }
};

class UpdatePassword extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            oldPassword: "",
            newPassword: "",
            successful: false,
            areSamePassword: false
        };
    }

    onChangeOldPassword = (e) => {
        this.setState({
            oldPassword: e.target.value,
        });
    }

    onChangeNewPassword = (e) => {
        e.preventDefault();
        this.setState({
            newPassword: e.target.value,
        });
    }
    handleSubmit = (e) => {
        e.preventDefault();

        this.setState({
            successful: false
        });
        
        this.form.validateAll();
        if (this.state.oldPassword !== this.state.newPassword) {
            if (this.checkBtn.context._errors.length === 0) {
                this.props.dispatch(changePassword(this.props.user.id, this.state.oldPassword, this.state.newPassword))
                .then(() => {
                    this.setState({successful: true});
                    this.props.dispatch(logout());
                    window.location.href="/home";
                })
                .catch(() => {
                    this.setState({successful: false});
                })
            }
        }
        else {
            this.setState({areSamePassword:true});
        }
    }

    render() {
        if (!this.props.isLoggedIn)
            return <Redirect to="/error"/>;
        const {areSamePassword} = this.state;
        return (
            <div className="col-md-12">
                <div className="card card-container">
                    <img
                        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
                        alt="profile-img"
                        className="profile-img-card"
                    />

                    <Form
                        onSubmit={this.handleSubmit}
                        ref={(c) => {
                            this.form = c;
                        }}
                    >
                        {!this.state.successful && (
                            <div>
                                <div className="form-group">
                                    <p>Username:</p>
                                    <h3>{this.props.user.username}</h3>
                                </div>

                                <div className="form-group">
                                    <label htmlFor="oldPassword">Enter password</label>
                                    <Input
                                        type="password"
                                        className="form-control"
                                        name="oldPassword"
                                        value={this.state.odlPassword}
                                        onChange={this.onChangeOldPassword}
                                        validations={[required]}
                                    />
                                </div>
                                <div className="form-group">
                                    <label htmlFor="newPassword">Enter new password</label>
                                    <Input
                                        type="password"
                                        className="form-control"
                                        name="newPassword"
                                        value={this.state.newPassword}
                                        onChange={this.onChangeNewPassword}
                                        validations={[required, validateNewPassword]}
                                    />
                                </div>

                                <div className="form-group">
                                    <button className="btn btn-primary btn-block">
                                        Submit
                                    </button>
                                </div>
                            </div>
                        )}
                        {areSamePassword && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert" >
                                    New password must not be the same as old password
                                </div>
                            </div>
                        )}
                        {this.props.message && (
                            <div className="form-group">
                                <div
                                    className={
                                        this.state.successful
                                            ? "alert alert-success"
                                            : "alert alert-danger"
                                    }
                                    role="alert"
                                >
                                    {this.props.message}
                                </div>
                            </div>
                        )}
                        <CheckButton
                            style={{ display: "none" }}
                            ref={(c) => {
                                this.checkBtn = c;
                            }}
                        />
                    </Form>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    console.log(state);
    return {
        isLoggedIn: state.auth.isLoggedIn,
        user: state.auth.user,
        message : state.message.message
    };
}

export default connect(mapStateToProps)(UpdatePassword);
