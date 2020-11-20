import React from 'react';
import {connect} from "react-redux";
import {Redirect} from "react-router-dom";

import baseUrl from 'baseUrl.js';
import authHeader from "services/authHeader.js";

class CreateCategory extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            successful: false,
            warning:false
        }
    }

    componentDidUpdate(){
        setTimeout(() => this.setState({successful: false, warning: false}), 7500);
      }
    submitButtonHandle = async (e) => {
        e.preventDefault();

        const pkg = {
            name: this.state.name
        };
        if(this.state.name.length<200 || this.state.name.length>2){
        
            await baseUrl.post('/categories', pkg, {headers: authHeader()})
                .then(() => {
                    this.setState({
                        successful: true,
                        name:""
                    });
                })
                .catch(error => {
                    console.log(error);
                });
        }
        else {
            this.setState({warning:true});
        }
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
            <div style={{marginTop: 10}}>
                <h3>Add New FAQ</h3>
                    <div className="form-group">
                        <label>Category name: </label>
                        <input type="text" className="form-control"
                            value={this.state.name}
                            onChange={(e) => {
                                e.preventDefault();
                                this.setState({
                                    name: e.target.value
                                })}}
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
                    {this.state.warning && (
                    <div className="alert alert-danger" role="alert" >
                        String not qualified
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
export default connect(mapStateToProps)(CreateCategory);