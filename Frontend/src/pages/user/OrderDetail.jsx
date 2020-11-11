import React from "react";
import {connect} from "react-redux";
import authHeader from "services/authHeader.js";
import baseUrl from "baseUrl.js";

import OrderDetailTuple from "components/tuples/OrderDetailTuple.jsx";

class OrderDetail extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            orders: [],
            totalPrice: 0,
            paymentMethod: "",
            code: "",
            postOrder: false,
            postOrderList: false
        };
    }
    componentDidMount() {
        this.getData();
    }    
    getData = () => {
        if (localStorage.getItem("cart") !== null) {
            const currentCart = JSON.parse(localStorage.getItem("cart"));
            this.setState({orders: currentCart});
            // currentCart.forEach(value => {
            //     result += value.price * value.quantity;
            // });
            // this.setState({
            //     totalPrice: result
            // });
            this.calculateTotalPrice(currentCart);
        }
    }

    calculateTotalPrice = (cart) => {
        const result = cart.reduce( (result, item) => {
            return (result + item.price * item.quantity);
        });
        this.setState({
            totalPrice: result
        });
    }

    onClickCheck = (data) => {
        const {orders} = this.state
        let arr=[];
        orders.forEach( value =>{
            if(value.id===data.id){
                arr.push({
                    ... value,
                    quantity:data.data
                })
            }else{
                arr.push(value);
            }
        });
        this.setState({orders:arr});
    }

    renderTuples = () => {
        return this.state.orders.map( (value, index) => {
            return <OrderDetailTuple obj={value} key={index} onClickCheck={this.onClickCheck}/>
        });
    }

    onCheckButton = (e) => {
        e.preventDefault();
        this.createOrder();
    }

    createOrder = async () => {
        this.setState({code : `${Date.now()}_user_${this.props.user.username}`});
        const orderPkg = {
            paymentMethod: this.state.paymentMethod,
            code: this.state.code,
            status: 1,
            user: {
                id: this.props.user.id
            }
        };

        await baseUrl.post(`/orders`, orderPkg, {headers: authHeader()})
        .then(() => this.setState({postOrderList: true}))
        .catch(error => console.log(error));
    }

    render() {
        return (
    <div>
        <div className="text-center">
            <div>
                <h1 className="h1-view">Order detail</h1>
            </div>
            {/* <p>
                <input
                    type="text"
                    placeholder="Search FAQ by question..."
                    name="searchtext"
                />
            </p>
            <p>
                <button
                    id="search button"
                    type="submit"
                    className="btn btn-primary"
                >
                    Search
                </button>
            </p> */}

            {/* <div className="d-flex flex-row align-items-center">
                Page size:
                <select defaultValue={pageLimit} onChange={this.onHandlePageLimitSelection}>
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                </select>
            </div><br/> */}

            <div className="d-flex table-data">
                <table className="table table-striped scrollTable center"
                    border={1}
                    cellSpacing={1}
                >
                    <thead className="thead-dark">
                        <tr>
                            {/* <th>select</th> */}
                            <th>ID</th>
                            <th>Quantity</th>
                            <th>Product's name</th>
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
                <select defaultValue="cash" onChange={(e) => {
                    this.setState({
                        paymentMethod: e.target.value
                    })
                }}>
                    <option></option>
                    <option value="cash">cash</option>
                    <option value="credit card">credit card</option>
                </select>
            </div>

            <div className="btn">
                <button className="btn btn-primary" onClick={this.onCheckButton}>
                    Check
                </button>
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
export default connect(mapStateToProps)(OrderDetail);

