import React from 'react';
import {connect} from "react-redux";
import {Redirect} from "react-router-dom";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";

class UpdateCategory extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            postloading: false,
            getloading:false
        }
    }

    componentDidMount() {
        baseUrl.get(`/categories/${this.props.match.params.id}`, {headers: authHeader()})
            .then(response => {
                this.setState ({
                    name : response.data.name,
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

    submitButtonHandle = (e) => {
        e.preventDefault();
        const pkg = {
            id: this.props.match.params.id,
            name: this.state.name
        };
        baseUrl.put(`/categories/${this.props.match.params.id}`, pkg, {headers: authHeader()})
            .then(() => {
                this.setState({postloading:true});
            })
            .catch(error => console.log(error));
    }
    backButtonHandle = (e) => {
        e.preventDefault();
        this.props.history.push("/admin/categories");
    }

    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_ADMIN")) {
            return <Redirect to="/error"/>
        }
        return (
        <div className="container">
            <div style={{ marginTop: 10 }}>
                <h3 align="center">Update Category</h3>
                <form onSubmit={this.onSubmit}>
                    <div className="form-group">
                        <label>Category Name: </label>
                        <input
                            type="text"
                            className="form-control"
                            value={this.state.name}
                            onChange={(e) => {
                                e.preventDefault();
                                this.setState({
                                    name: e.target.value
                                })
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
                </form>
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
export default connect(mapStateToProps)(UpdateCategory);