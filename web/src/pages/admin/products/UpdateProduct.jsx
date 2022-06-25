import React from 'react';
import {connect} from "react-redux";
import {Redirect} from "react-router-dom";

import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";

class UpdateProduct extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            description: "",
            price: 0,
            status: 1,
            image: "",
            categoryList: [],
            categoryName: "",
            categoryId: "",
            getloading: false,
            postloading: false
        }
    }

    componentDidMount() {
        this.getData();
    }
    componentDidUpdate(){
        setTimeout(() => this.setState({postloading: false}), 7500);
      }
    getData = async () => {
        await baseUrl.get(`/categories/name`, {headers:authHeader()})
            .then(response => {
                this.setState({
                    categoryList: response.data
                });
            })
            .catch(error => console.log(error));
        await baseUrl.get(`/products/${this.props.match.params.id}`, {headers: authHeader()})
            .then(response => {
                this.setState ({
                name: response.data.name,
                description: response.data.description,
                price: response.data.price,
                status: response.data.status,
                image: response.data.image,
                categoryId: response.data.categoryId,
                categoryName: response.data.categoryName,
                getloading:true
                });
            })
            .catch( error => {
                console.log(error);
            });
    }

    backButtonHandle = (e) => {
        e.preventDefault();
        this.props.history.push("/admin/products");
    }

    onSubmit = (e) => {
        e.preventDefault();
        const pkg = {
            id: this.props.match.params.id,
            name: this.state.name,
            description: this.state.description,
            price: this.state.price,
            status: this.state.status,
            image: this.state.image,
            categoryId: this.state.categoryId
        };
        baseUrl.put(`/products/${this.props.match.params.id}`, pkg, {headers: authHeader()})
            .then(() => this.setState({ postloading: true}))
            .catch(error => console.log(error));
    }

    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_ADMIN")) {
            return <Redirect to="/error"/>;
        }
        return (
    <div>
        <div className="row">
            <div className="col-12">
                <div className="card">
                    <div className="card-body">
                        <div className="card-title d-flex justify-content-center">
                            <div className="image-container">
                                <img src={this.state.image} class="avatar img-circle img-thumbnail" alt="product's image" height="200" width="200"/>
                                <h6>Upload a different photo...</h6>
                                <input type="file" 
                                className="text-center center-block file-upload" 
                                onChange={(e) => {
                                    e.preventDefault();
                                    this.setState({image: `/images/products/${e.target.value.split("\\")[2]}`});}}/>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-sm-3 col-md-2 col-5">
                                <label style={{ fontWeight: "bold",}}>
                                    Id
                                </label>
                            </div>
                            <div className="col-md-8 col-6">
                                {this.props.match.params.id}
                            </div>
                        </div>
                        <hr/>
                        <div className="row">
                            <div className="col-sm-3 col-md-2 col-5">
                                <label style={{ fontWeight: "bold",}}>
                                    Name
                                </label>
                            </div>
                            <div className="col-md-8 col-6">
                                <input type="text"
                                value={this.state.name}
                                onChange={(e) => {
                                    e.preventDefault();
                                    this.setState({name: e.target.value});}}
                                />
                            </div>
                        </div>
                        <hr/>
                        <div className="row">
                            <div className="col-sm-3 col-md-2 col-5">
                                <label style={{ fontWeight:"bold",}}>
                                    Description
                                </label>
                            </div>
                            <div className="col-md-8 col-6">
                                <textarea 
                                value={this.state.description}
                                onChange={(e) => {
                                    e.preventDefault();
                                    this.setState({description: e.target.value});
                                }} rows="3" cols="40"></textarea>
                            </div>
                        </div>
                        <hr />
                        <div className="row">
                            <div className="col-sm-3 col-md-2 col-5">
                                <label style={{ fontWeight:"bold",}}>
                                    price
                                </label>
                            </div>
                            <div className="col-md-8 col-6">
                                <input type="text"
                                value={this.state.price}
                                onChange={(e) => {
                                    e.preventDefault();
                                    this.setState({price: e.target.value});
                                }}/>
                            </div>
                        </div>
                        <hr />
                        <div className="row">
                            <div className="col-sm-3 col-md-2 col-5">
                                <label style={{ fontWeight: "bold",}}>
                                    Status
                                </label>
                            </div>
                            <div className="col-md-8 col-6">
                                <select defaultValue={this.state.status} onChange={ (e) => {
                                    e.preventDefault();
                                    this.setState({status:e.target.value})
                                }}>
                                    <option value="0">UNAVAILABLE</option>
                                    <option value="1">AVAILABLE</option>
                                </select>
                            </div>
                        </div>
                        <hr />
                        <div className="row">
                            <div className="col-sm-3 col-md-2 col-5">
                                <label style={{ fontWeight: "bold",}}>
                                    Category
                                </label>
                            </div>
                            <div className="col-md-8 col-6">
                                <select defaultValue={this.state.categoryId} onChange={ (e) => {
                                    e.preventDefault();
                                    this.setState({categoryId:e.target.value})
                                }}>
                                    {this.state.categoryList.map( value => <option value={value.id}>{value.name}</option>)}
                                </select>
                            </div>
                        </div>
                        <hr/>
                    <button onClick={this.onSubmit} className="btn btn-primary">Submit</button>
                    {this.state.postloading && (
                    <div className="alert alert-success" role="alert" >
                        Post data successfully
                    </div>)}
                    </div>
            </div>
            <button onClick={this.backButtonHandle} className="btn btn-primary">Back</button>
        </div>
    </div>
</div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isLoggedIn: state.auth.isLoggedIn,
        user: state.auth.user
    }
}
export default connect(mapStateToProps)(UpdateProduct);