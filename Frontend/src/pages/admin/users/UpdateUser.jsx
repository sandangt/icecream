import React from 'react';
import baseUrl from "baseUrl.js";
import authHeader from "services/authHeader.js";

class UpdateUser extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            email: "",
            status: "",
            profile: {},
            loading: false
        }

    }

    componentDidMount() {
        this.getData();
    }

    getData = async () => {
        await baseUrl.get(`/users/${this.props.match.params.id}`, {headers: authHeader()})
            .then(response => {
                this.setState ({
                username : response.data.username,
                email: response.data.email,
                status: response.data.status
                });
            })
            .catch( error => {
                console.log(error);
            });
        await baseUrl.get(`/user-details/${this.props.match.params.id}`, {headers: authHeader()})
        .then(response => {
            this.setState({
                profile: response.data,
                loading:true
            });
        })
        .catch(error => {
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
        return (
    <div className="container">
        <div className="row">
            <div className="col-12">
                <div className="card">
                    <div className="card-body">
                        <div className="card-title mb-4">
                            <div className="d-flex justify-content-start">
                                <div className="image-container">
                                    <img src={this.state.profile.avatar} class="avatar img-circle img-thumbnail" alt="avatar" height="200" width="200"/>
                                    <h6>Upload a different photo...</h6>
                                    <input type="file" class="text-center center-block file-upload"></input>
                                </div>
                                <div className="userData ml-3">
                                    <h2
                                        className="d-block"
                                        style={{
                                            fontSize: "1.5rem",
                                            fontWeight: "bold",
                                        }}
                                    >
                                        <a href="javascript:void(0);">
                                            {this.state.username}
                                        </a>
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
                            <div className="col-12">
                                <ul
                                    className="nav nav-tabs mb-4"
                                    id="myTab"
                                    role="tablist"
                                >
                                    <li className="nav-item">
                                        <a
                                            className="nav-link active"
                                            id="basicInfo-tab"
                                            data-toggle="tab"
                                            href="#basicInfo"
                                            role="tab"
                                            aria-controls="basicInfo"
                                            aria-selected="true"
                                        >
                                            Basic Info
                                        </a>
                                    </li>
                                    <li className="nav-item">
                                        <a
                                            className="nav-link"
                                            id="connectedServices-tab"
                                            data-toggle="tab"
                                            href="#connectedServices"
                                            role="tab"
                                            aria-controls="connectedServices"
                                            aria-selected="false"
                                        >
                                            Connected Services
                                        </a>
                                    </li>
                                </ul>
                                <div
                                    className="tab-content ml-1"
                                    id="myTabContent"
                                >
                                    <div
                                        className="tab-pane fade show active"
                                        id="basicInfo"
                                        role="tabpanel"
                                        aria-labelledby="basicInfo-tab"
                                    >
                                        <div className="row">
                                            <div className="col-sm-3 col-md-2 col-5">
                                                <label
                                                    style={{
                                                        fontWeight:
                                                            "bold",
                                                    }}
                                                >
                                                    Full Name
                                                </label>
                                            </div>
                                            <div className="col-md-8 col-6">
                                                Jamshaid Kamran
                                            </div>
                                        </div>
                                        <hr />
                                        <div className="row">
                                            <div className="col-sm-3 col-md-2 col-5">
                                                <label
                                                    style={{
                                                        fontWeight:
                                                            "bold",
                                                    }}
                                                >
                                                    Birth Date
                                                </label>
                                            </div>
                                            <div className="col-md-8 col-6">
                                                March 22, 1994.
                                            </div>
                                        </div>
                                        <hr />
                                        <div className="row">
                                            <div className="col-sm-3 col-md-2 col-5">
                                                <label
                                                    style={{
                                                        fontWeight:
                                                            "bold",
                                                    }}
                                                >
                                                    Something
                                                </label>
                                            </div>
                                            <div className="col-md-8 col-6">
                                                Something
                                            </div>
                                        </div>
                                        <hr />
                                        <div className="row">
                                            <div className="col-sm-3 col-md-2 col-5">
                                                <label
                                                    style={{
                                                        fontWeight:
                                                            "bold",
                                                    }}
                                                >
                                                    Something
                                                </label>
                                            </div>
                                            <div className="col-md-8 col-6">
                                                Something
                                            </div>
                                        </div>
                                        <hr />
                                        <div className="row">
                                            <div className="col-sm-3 col-md-2 col-5">
                                                <label
                                                    style={{
                                                        fontWeight:
                                                            "bold",
                                                    }}
                                                >
                                                    Something
                                                </label>
                                            </div>
                                            <div className="col-md-8 col-6">
                                                Something
                                            </div>
                                        </div>
                                        <hr />
                                    </div>
                                    <div
                                        className="tab-pane fade"
                                        id="connectedServices"
                                        role="tabpanel"
                                        aria-labelledby="ConnectedServices-tab"
                                    >
                                        Facebook, Google, Twitter
                                        Account that are connected to
                                        this account
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
        );
    }
}

export default UpdateUser;