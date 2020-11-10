import React from "react";
import authHeader from "services/authHeader.js";
import baseUrl from "baseUrl.js";

class ProductDetail extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            product: {},
            getloading: false
        };
    }
    componentDidMount() {
        this.getData();
    }

    getData = async () => {
        baseUrl.get(`/products/${this.props.match.params.id}`, {headers: authHeader()})
        .then( (response) => {
            this.setState({
                product: response.data,
                getloading: true
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

    render() {
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
                    <div className="col-12">
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
                        </div>
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

export default ProductDetail;