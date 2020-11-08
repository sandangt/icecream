import React from "react";
import { Redirect } from "react-router-dom";
import { connect } from "react-redux";

class ProfileUser extends React.Component {
    render() {
        const { user } = this.props;

        if (!user) {
            return <Redirect to="/error" />;
        }
        return (
            <div className="container">
                <header className="jumbotron">
                    <h3>
                        <strong>{user.username}</strong> Profile
                    </h3>
                </header>
                <p>
                    <strong>Token:</strong> {user.token.substring(0, 20)}{" "}
                    ...{" "}
                    {user.token.substr(user.token.length - 20)}
                </p>
                <p>
                    <strong>Id:</strong> {user.id}
                </p>
                <p>
                    <strong>Email:</strong> {user.email}
                </p>
                <strong>Authorities:</strong>
                <ul>
                    {user.roles &&
                        user.roles.map((role, index) => (
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
