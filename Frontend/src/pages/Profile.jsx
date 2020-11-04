import React from "react";
import { Redirect } from "react-router-dom";
import { connect } from "react-redux";

class ProfileUser extends React.Component {
    render() {
        const { user: currentUser } = this.props;

        if (!currentUser) {
            return <Redirect to="/login" />;
        }
        console.log(currentUser);
        return (
            <div className="container">
                <header className="jumbotron">
                    <h3>
                        <strong>{currentUser.username}</strong> Profile
                    </h3>
                </header>
                <p>
                    <strong>Token:</strong> {currentUser.token.substring(0, 20)}{" "}
                    ...{" "}
                    {currentUser.token.substr(currentUser.token.length - 20)}
                </p>
                <p>
                    <strong>Id:</strong> {currentUser.id}
                </p>
                <p>
                    <strong>Email:</strong> {currentUser.email}
                </p>
                <strong>Authorities:</strong>
                <ul>
                    {currentUser.roles &&
                        currentUser.roles.map((role, index) => (
                            <li key={index}>{role}</li>
                        ))}
                </ul>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        user : state.auth.user
    };
}

export default connect(mapStateToProps)(ProfileUser);
