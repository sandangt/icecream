import React from 'react';
import {connect} from "react-redux";
import {Redirect} from "react-router-dom";

import baseUrl from 'baseUrl.js';
import authHeader from "services/authHeader.js";

class CreateFAQ extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            question: '',
            answer: '',
            successful: false
        }
    }

    componentDidUpdate(){
        setTimeout(() => this.setState({successful: false}), 7500);
    }

    submitButtonHandle = async (e) => {
        e.preventDefault();
        const pkg = {
            question: this.state.question,
            answer: this.state.answer
        };
        await baseUrl.post('/faq', pkg, {headers: authHeader()})
            .then(() => {
                this.setState({
                    successful: true,
                    question: '',
                    answer: '',
                });
            })
            .catch(error => {
                console.log(error);
            });
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
        <div className="container">
            <div style={{marginTop: 10}}>
                <h3>Add New FAQ</h3>
                    <div className="form-group">
                        <label>Question: </label>
                        <input type="text" className="form-control"
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
                        <textarea className="form-control" 
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
                {this.state.successful && (
                <div className="alert alert-success" role="alert" >
                    Post data successfully
                </div>)}
            </div>
        </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isLoggedIn: state.auth.isLoggedIn,
        user: state.auth.user
    };
}
export default connect(mapStateToProps)(CreateFAQ);