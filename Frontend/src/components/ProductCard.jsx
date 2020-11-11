import React from "react";
import {Link} from "react-router-dom";
import authHeader from "services/authHeader.js";
import baseUrl from "baseUrl.js";
import {createCartService, destroyCartService, addItemToCartService} from "services/cartService.js";

function ProductCard(props) {
    return (
    <div className="col-lg-4 col-md-6 mb-4">
        <div className="card h-100">
            <Link to={`/product/${props.id}`}>
                <img
                    className="card-img-top"
                    src={props.image}
                    alt
                    width="50" height="100"
                />
            </Link>
            <div className="card-body">
                <h4 className="card-title">
                    <Link to={`/product/${props.id}`}>{props.name}</Link>
                </h4>
                <h5>{props.price}</h5>
                <p className="card-text">
                    {props.description}
                </p>
            </div>
            <div className="card-footer row">
                {props.status === "AVAILABLE" ? (<button className="btn btn-primary col"
                onClick={ async (e) => {
                    e.preventDefault();
                    addItemToCartService(props);
                }}>
                    Buy
                </button>) : null}
                <div className="col">
                    {props.status}
                </div>
            </div>
        </div>
    </div>
    );
}

export default ProductCard;