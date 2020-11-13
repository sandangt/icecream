import React from 'react';
import {connect} from "react-redux";
import {Redirect} from "react-router-dom";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";

class UpdateFAQ extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            question: '',
            answer: '',
            getloading: false,
            postloading: false
        }
    }

    componentDidMount() {
        baseUrl.get(`/faq/${this.props.match.params.id}`, {headers: authHeader()})
            .then(response => {
                this.setState ({
                question : response.data.question,
                answer : response.data.answer,
                getloading: true
                });
            })
            .catch( error => {
                console.log(error);
            });
            
    }
    componentDidUpdate(){
        setTimeout(() => this.setState({postloading: false}), 7500);
    }

    onChangeQuestion = (e) => {
    }
    onChangeAnswer = (e) => {
    }

    submitButtonHandle = async (e) => {
        e.preventDefault();
        const obj = {
            id: this.props.match.params.id,
            question: this.state.question,
            answer: this.state.answer
        };
        await baseUrl.put(`/faq/${this.props.match.params.id}`, obj, {headers: authHeader()})
            .then(() => {
                this.setState({postloading:true});
            })
            .catch(error => console.log(error));
    }

    backButtonHandle = (e) => {
        e.preventDefault();
        this.props.history.push("/admin/faq");
    }

    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_ADMIN")) {
            return <Redirect to="/error"/>
        }
        return (
        <div>
            <div style={{ marginTop: 10 }}>
                <h3 align="center">Update FAQ</h3>
                    <div className="form-group">
                        <label>Question: </label>
                        <input
                            type="text"
                            className="form-control"
                            value={this.state.question}
                            onChange={(e) => {
                                e.preventDefault();
                                this.setState({
                                    question: e.target.value
                                });
                            }}
                        />
                    </div>
                    <div className="form-group">
                        <label>Answer: </label>
                        <input type="text"
                            className="form-control"
                            value={this.state.answer}
                            onChange={(e) => {
                                e.preventDefault();
                                this.setState({
                                    answer: e.target.value
                                });
                            }}
                        />
                    </div>
                    <div class="btn-group">
                        <button onClick={this.backButtonHandle} className="btn btn-primary">&laquo; Back</button>
                        <button  className="btn btn-primary" onClick={this.submitButtonHandle}>Submit &raquo;</button>
                    </div>
                    {this.state.postloading && (
                    <div className="alert alert-success" role="alert" >
                        Post data successfully
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
export default connect(mapStateToProps)(UpdateFAQ);