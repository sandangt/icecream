import React from 'react';
import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";

class UpdateFeedback extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            title: '',
            content: '',
            productId: 0, 
            user: {}
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

    onChangeTitle = (e) => {
        this.setState({
            title: e.target.value
        });
    }
    onChangeContent = (e) => {
        this.setState({
            content: e.target.value
        })
    }

    onSubmit = async (e) => {
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
            .then(response => console.log(response.data))
            .catch(error => console.log(error));

        this.props.history.push(`/product/${this.state.productId}`);
    }

    backButtonHandle = (e) => {
        e.preventDefault();
        this.props.history.push(`/product/${this.state.productId}`);
    }

    render() {
        console.log(this.props.match);
        return (
        <div className="container">
            <div style={{ marginTop: 10 }}>
                <h3 align="center">Update FAQ</h3>
                <form onSubmit={this.onSubmit}>
                    <div className="form-group">
                        <label>Title: </label>
                        <input
                            type="text"
                            className="form-control"
                            onChange={this.onChangeTitle}
                            value={this.state.title}
                        />
                    </div>
                    <div className="form-group">
                        <label>Content: </label>
                        <input type="text"
                            className="form-control"
                            onChange={this.onChangeContent}
                            value={this.state.content}
                        />
                    </div>
                    <div className="form-group">
                        <button onClick={this.backButtonHandle} className="btn btn-primary">Back</button>
                        <input type="submit"
                            value="Update feedback"
                            className="btn btn-primary"/>
                    </div>
                </form>
            </div>
        </div>
        )
    }
}

export default UpdateFeedback;