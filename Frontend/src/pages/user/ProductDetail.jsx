import React from "react";
import {connect} from "react-redux";
import {Redirect, Link} from "react-router-dom";

import authHeader from "services/authHeader.js";
import baseUrl from "baseUrl.js";

class ProductDetail extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            product: {},
            getloading: false,
            postloading: false,
            title: "",
            content: ""
        };
    }
    componentDidMount() {
        this.getData();
    }

    getData = async () => {
        await baseUrl.get(`/products/${this.props.match.params.id}`, {headers: authHeader()})
        .then( (response) => {
            this.setState({
                product: response.data,
                getloading: true,
            });
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    backButtonHandle = (e) => {
        e.preventDefault();
        this.props.history.push("/shop");
    }

    onSubmitFeedback = async (e) => {
        e.preventDefault();
        const pkg = {
            content: this.state.content,
            title: this.state.title,
            productId: this.state.product.id,
            user: {
                id: this.props.user.id
            }
        };
        await baseUrl.post(`/feedbacks`, pkg, {headers: authHeader()})
        .then( () => {
            this.setState({
                postloading: true
            });
        })
        .catch( (error) => {
            console.log(error);
        });
    }

    onDeleteButton = (id) => {
        baseUrl.delete(`/feedbacks/${id}`, {headers: authHeader()})
            .then(console.log('Deleted'))
            .catch(err => console.log(err));
        window.location.reload();
    }

    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_USER")) {
            return <Redirect to="/error"/>
        }
    return(
<div>
<div className="row">
    <div className="col-12">
        <div className="card">
            <div className="card-body">
                <div className="card-title mb-4">
                    <div className="d-flex justify-content-start">
                        <div className="image-container">
                            <img src={this.state.product.image} class="avatar img-circle img-thumbnail" alt="product's image" height="200" width="200"/>
                        </div>
                        <div className="userData ml-3">
                            <h2 className="d-block" style={{fontSize: "1.5rem", fontWeight: "bold",}}>
                                Product's info
                            </h2>
                        </div>
                        <div className="ml-auto">
                            <input
                                type="button"
                                className="btn btn-primary d-none"
                                id="btnDiscard"
                                defaultValue="Discard Changes"
                            />
                        </div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-9">
                        <div className="tab-content ml-1" id="myTabContent">
                            <div className="tab-pane fade show active" id="basicInfo"
                                role="tabpanel"
                                aria-labelledby="basicInfo-tab"
                            >
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
                                        {this.state.product.name}
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
                                        {this.state.product.description}
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
                                        {this.state.product.price}
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
                                        {this.state.product.status}
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
                                        {this.state.product.categoryName}
                                    </div>
                                </div>
                                <hr/>
                                <div className="row">
                                    <div className="col-sm-3 col-md-2 col-5">
                                        <label style={{ fontWeight: "bold",}}>
                                            Feedback
                                        </label>
                                    </div>
                                    <div className="col-md-8 col-6">
                                        <input type="text" onChange={(e) => {
                                            e.preventDefault();
                                            this.setState({
                                                title: e.target.value
                                            });
                                        }}/>
                                        <textarea rows="5" cols="45" onChange={ (e) => {
                                            e.preventDefault();
                                            this.setState({
                                                content: e.target.value
                                            });
                                        }}>                                            
                                        </textarea>
                                    </div>
                                </div>
                                <hr/>
                                <button className="btn btn-primary" onClick={this.onSubmitFeedback}>submit</button>
                        </div>
                                {this.state.postloading && (
                                <div className="alert alert-success" role="alert" >
                                    Create order detail list successfully
                                </div>)}
                    </div>
                    </div>
                    <div className="col-3">
                        <div class="listWrapper">
                            <ul>
                                { this.state.product.feedbacks && this.state.product.feedbacks.map(value => {
                                    return (<li>
                                        <p>title: {value.title}</p>
                                        <p>posted by: {value.user.username}</p>
                                        <p>{value.content}</p>
                                        { value.user.id === this.props.user.id ? (
                                        <React.Fragment>
                                        <Link className="btn btn-primary" to={`/feedback/${value.id}`}>Edit</Link>
                                        <button className="btn btn-danger" onClick={ async (e) => {
                                            e.preventDefault();
                                            await baseUrl.delete(`/feedbacks/${value.id}`, {headers: authHeader()})
                                            .then(console.log('Deleted'))
                                            .catch(err => console.log(err));
                                            window.location.reload();
                                        }}>
                                            Delete
                                        </button>
                                        </React.Fragment>
                                        ) : null}   
                                    </li>);
                                })}
                            </ul>
                        </div>
                    </div>
            </div>
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
        user: state.auth.user,
        isLoggedIn: state.auth.isLoggedIn
    };
}

export default connect(mapStateToProps)(ProductDetail);