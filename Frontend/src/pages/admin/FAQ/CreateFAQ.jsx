import React from 'react';
import {connect} from "react-redux";
import {Redirect} from "react-router-dom";

import baseUrl from 'baseUrl.js';
import authHeader from "services/authHeader.js";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import {isEmpty} from 'validator';


const required = (value) => {
    if (isEmpty(value)) {
        return (
            <div className="alert alert-danger" role="alert">
                This field is required!
            </div>
        );
    }
};

class CreateFAQ extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            question: '',
            answer: '',
            successful: false
        }
    }

    onChangeQuestion = (e) => {
        this.setState({
            question: e.target.value
        });
    }

    onChangeAnswer = (e) => {
        this.setState({
            answer: e.target.value
        });
    }

    onSubmit = (e) => {
        e.preventDefault();
        this.form.validateAll();

        const pkg = {
            question: this.state.question,
            answer: this.state.answer
        };
        
        if ( this.checkBtn.context._errors.length === 0 ) {

            baseUrl.post('/faq', pkg, {headers: authHeader()})
                .then(() => {
                    this.setState({successful: true});
                    alert("success");
                })
                .catch(error => {
                    console.log(error);
                });
            }
        this.props.history.push("/admin/faq");
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
                <Form onSubmit={e => this.onSubmit(e)} ref={c => { this.form = c }}>
                    <div className="form-group">
                        <label>Question: </label>
                        <Input type="text" className="form-control"
                                value={this.state.question}
                                onChange={this.onChangeQuestion}
                                validations={[required]}
                        />
                    </div>
                    <div className="form-group">
                        <label>Answer: </label>
                        <Input type="text" className="form-control" 
                            value={this.state.answer}
                            onChange={this.onChangeAnswer}
                            validations={[required]}
                        />
                    </div>
                    <div className="form-group">
                        <button onClick={this.backButtonHandle} className="btn btn-primary">Back</button>
                        <Input type="submit" value="Submit" 
                            className="btn btn-primary"
                            validations={[required]}
                        />
                    </div>
                    <CheckButton style={{ display: 'none' }} ref={c => { this.checkBtn = c }}/>
                </Form>
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