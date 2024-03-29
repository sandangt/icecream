import React from "react";
import {connect} from "react-redux";
import {Redirect, Link} from "react-router-dom";

import authHeader from "services/authHeader.js";
import baseUrl from "baseUrl.js";
import BuyTuple from "components/tuples/BuyTuple.jsx";
import {removeItemFromCartService, 
    destroyCartService, 
    setNewCartService, 
    costOfCartService} from "services/cartService.js";
 
class Order extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            orders: [],
            totalPrice: 0,
            paymentMethod: "cash",
            code: "",
            postOrder: false,
            postOrderList: false
        };
    }
    componentDidMount() {
        this.getData();
    }    
    componentDidUpdate(){
        setTimeout(() => this.setState({postOrder: false, postOrderList: false}), 7500);
      }
    getData = () => {
        if (localStorage.getItem("cart") !== null) {
            const currentCart = JSON.parse(localStorage.getItem("cart"));
            this.setState({
                orders: currentCart,
                totalPrice: costOfCartService()
            });
        }
    }

    handleIncreaseQuantityButton = (data) => {
        let arr=[];
        this.state.orders.forEach( value => {
            if (value.id === data.id) {
                arr.push({
                    ... value,
                    quantity: data.quantity
                });
            }
            else {
                arr.push(value);
            }
        });
        setNewCartService(arr);
        this.setState({
            orders:arr,
            totalPrice: costOfCartService()
        });
    }

    handleDecreaseQuantityButton = (data) => {
        let arr=[];
        this.state.orders.forEach( value => {
            if (value.id === data.id) {
                arr.push({
                    ... value,
                    quantity: data.quantity
                });
            }
            else {
                arr.push(value);
            }
        });
        setNewCartService(arr);
        this.setState({
            orders:arr,
            totalPrice: costOfCartService()
        });
    }

    renderTuples = () => {
        return this.state.orders.map( (value, index) => {
            return <BuyTuple obj={value} key={index} 
            increaseQuantity={this.handleIncreaseQuantityButton}
            decreaseQuantity={this.handleDecreaseQuantityButton}
            />
        });
    }

    onCheckButton = (e) => {
        e.preventDefault();
        this.createOrder();
    }

    createOrder = async () => {
        await this.setState({code : `${Date.now()}_user_${this.props.user.username}`});
        const orderPkg = {
            paymentMethod: this.state.paymentMethod,
            code: this.state.code,
            status: 1,
            user: {
                id: this.props.user.id
            }
        };
        let cartPkg = {
            orderCode: "",
            itemList : []
        }
        await baseUrl.post(`/orders`, orderPkg, {headers: authHeader()})
        .then( async () => {
            this.setState({postOrder:true});
            cartPkg.orderCode = this.state.code;
            this.state.orders.forEach( (productElement) => {
                const orderDetailPkg = {
                    quantity: productElement.quantity,
                    orderCode: this.state.code,
                    product: {
                        id: productElement.id
                    }
                }
                cartPkg.itemList.push(orderDetailPkg);
                removeItemFromCartService(productElement);
            });
            await baseUrl.post(`/order-details/code`, cartPkg, {headers: authHeader()})
            .then( () => {
                this.setState({postOrderList:true});
                this.props.history.push("/history");
            })
            .catch( error => console.log(error));
            })
        .catch(error => console.log(error));
    }

    render() {
        if (!this.props.isLoggedIn || !this.props.user.roles.includes("ROLE_USER")) {
            return <Redirect to="/error"/>
        }
        return (
    <div>
        <div className="text-center">
            <div>
                <h1 className="h1-view">Order detail</h1>
            </div>
            <div className="d-flex table-data">
                <table className="table table-striped scrollTable center"
                    border={1}
                    cellSpacing={1}
                >
                    <thead className="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Product's name</th>
                            <th>Quantity</th>
                            <th>Product's price</th>
                            <th>Product's description</th>
                            <th colSpan="1">Action</th>
                        </tr>
                    </thead>
                    <tbody id="tbody">
                        {this.renderTuples()}
                    </tbody>
                </table>
            </div>
            <div className="d-flex flex-row py-4 align-items-center">
                <h3>total price: {this.state.totalPrice}</h3><hr/>
                <select defaultValue={this.state.paymentMethod} onChange={(e) => {
                    this.setState({
                        paymentMethod: e.target.value
                    })
                }}>
                    <option value="cash">cash</option>
                    <option value="credit card">credit card</option>
                </select>
            </div>

            <div className="btn-group">
                <Link className="btn btn-primary" to="/shop">
                        Continue shopping
                </Link>
                {this.state.orders.length !== 0 ? (
                    <React.Fragment>
                <button className="btn btn-danger" onClick={(e) => {
                    e.preventDefault();
                    destroyCartService();
                    window.location.reload();
                }}>
                    Destroy cart
                </button>
                <button className="btn btn-success" onClick={this.onCheckButton}>
                    Check
                </button>
                </React.Fragment>) : null
                }   
                { this.state.postOrder && this.state.postOrderList && (
                <div className="alert alert-success" role="alert" >
                    Create order successfully
                </div>) }
            </div>
        </div>
    </div>
        );
    }
}

const mapStateToProps = (state) => {
    return({
        isLoggedIn: state.auth.isLoggedIn,
        user: state.auth.user
    })
}
export default connect(mapStateToProps)(Order);

