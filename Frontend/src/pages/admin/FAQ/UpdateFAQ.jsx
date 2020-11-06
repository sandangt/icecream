import React from 'react';
import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";

class UpdateFAQ extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            question: '',
            answer: ''
        }

    }

    componentDidMount() {
        baseUrl.get(`/faq/${this.props.match.params.id}`, {headers: authHeader()})
            .then(response => {
                this.setState ({
                question : response.data.question,
                answer : response.data.answer
                });
            })
            .catch( error => {
                console.log(error);
            });
            
    }

    onChangeQuestion = (e) => {
        this.setState({
            question: e.target.value
        });
    }
    onChangeAnswer = (e) => {
        this.setState({
            answer: e.target.value
        })
    }

    onSubmit = (e) => {
        e.preventDefault();
        const obj = {
            id: this.props.match.params.id,
            question: this.state.question,
            answer: this.state.answer
        };
        baseUrl.put(`/faq/${this.props.match.params.id}`, obj, {headers: authHeader()})
            .then(response => console.log(response.data))
            .catch(error => console.log(error));

        this.props.history.push('/admin/faq');
    }

    backButtonHandle = (e) => {
        e.preventDefault();
        this.props.history.push("/admin/faq");
    }

    render() {
        console.log(this.props.match);
        return (
        <div className="container">
            <div style={{ marginTop: 10 }}>
                <h3 align="center">Update FAQ</h3>
                <form onSubmit={this.onSubmit}>
                    <div className="form-group">
                        <label>Question: </label>
                        <input
                            type="text"
                            className="form-control"
                            onChange={this.onChangeQuestion}
                            value={this.state.question}
                        />
                    </div>
                    <div className="form-group">
                        <label>Answer: </label>
                        <input type="text"
                            className="form-control"
                            onChange={this.onChangeAnswer}
                            value={this.state.answer}
                        />
                    </div>
                    <div className="form-group">
                        <button onClick={this.backButtonHandle} className="btn btn-primary">Back</button>
                        <input type="submit"
                            value="Update FAQ"
                            className="btn btn-primary"/>
                    </div>
                </form>
            </div>
        </div>
        )
    }
}

export default UpdateFAQ;