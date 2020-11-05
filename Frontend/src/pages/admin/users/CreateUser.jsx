import React from 'react';
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

class CreateUser extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            question: '',
            answer: ''
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

        const obj = {
            question: this.state.question,
            answer: this.state.answer
        };
        baseUrl.post('/faq', obj, {headers: authHeader()})
            .then(response => console.log(response.data))
            .catch(error => console.log(error));

        if ( this.checkBtn.context._errors.length === 0 ) {
            alert('success');
        }
        this.props.history.push("/admin/faq");
    }

    backButtonHandle = (e) => {
        e.preventDefault();
        this.props.history.push("/admin/faq");
    }

    render() {
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


export default CreateUser;