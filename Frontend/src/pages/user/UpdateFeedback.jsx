import React from 'react';
import {connect} from "react-redux";
import {Redirect} from "react-router-dom";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";


class UpdateFeedback extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            title: '',
            content: '',
            productId: 0, 
            user: {},
            postloading: false
        }
    }

    componentDidMount() {
        baseUrl.get(`/feedbacks/${this.props.match.params.id}`, {headers: authHeader()})
            .then(response => {
                this.setState ({
                title : response.data.title,
                content : response.data.content,
                productId: response.data.productId,
                user: response.data.user
                });
            })
            .catch( error => {
                console.log(error);
            });
    }
    componentDidUpdate(){
        setTimeout(() => this.setState({postloading: false}), 7500);
    }

    submitButtonHandle = async (e) => {
        e.preventDefault();
        const pkg = {
            id: this.props.match.params.id,
            title: this.state.title,
            content: this.state.content,
            productId: this.state.productId,
            user: {
                id:this.state.user.id
            }
        };
        await baseUrl.put(`/feedbacks/${this.props.match.params.id}`, pkg, {headers: authHeader()})
            .then(() => this.setState({postloading: true}))
            .catch(error => console.log(error));

    }

    backButtonHandle = (e) => {
        e.preventDefault();
        this.props.history.push(`/product/${this.state.productId}`);
    }

    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_USER")) {
            return <Redirect to="/error"/>
        }
        return (
        <div className="container">
            <div style={{ marginTop: 10 }}>
                <h3 align="center">Update FAQ</h3>
                    <div className="form-group">
                        <label>Title: </label>
                        <input
                            type="text"
                            className="form-control"
                            onChange={(e) => {
                                e.preventDefault();
                                this.setState({
                                    title: e.target.value
                                });
                            }}
                            value={this.state.title}
                        />
                    </div>
                    <div className="form-group">
                        <label>Content: </label>
                        <input type="text"
                            className="form-control"
                            onChange={(e) => {
                                e.preventDefault();
                                this.setState({
                                    content: e.target.value
                                });
                            }}
                            value={this.state.content}
                        />
                    </div>
                <div class="btn-group">
                    <button onClick={this.backButtonHandle} className="btn btn-primary">&laquo; Back</button>
                    <button  className="btn btn-primary" onClick={this.submitButtonHandle}>Submit &raquo;</button>
                </div>
                {this.state.postloading && (
                <div className="alert alert-success" role="alert" >
                    Update data successfully
                </div>)}
            </div>
        </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        isLoggedIn: state.auth.isLoggedIn,
        user: state.auth.user
    };
}

export default connect(mapStateToProps)(UpdateFeedback);