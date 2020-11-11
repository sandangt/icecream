import React from "react";
import {connect} from "react-redux";

import authHeader from "services/authHeader.js";
import baseUrl from "baseUrl.js";
import BuyTuple from "components/tuples/BuyTuple.jsx";
import {removeItemFromCartService, destroyCartService} from "services/cartService.js";
 
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
            this.setState({orders: currentCart});
            let result = 0;
            currentCart.forEach(value => {
                result += value.price;
            });
            this.setState({
                totalPrice: result.toFixed(3)
            });
        }
    }

    // onClickCheck = (data) => {
    //     const {orders} = this.state
    //     let arr=[];
    //     orders.forEach( value =>{
    //         if(value.id===data.id){
    //             arr.push({
    //                 ... value,
    //                 quantity:data.data
    //             })
    //         }else{
    //             arr.push(value);
    //         }
    //     });
    //     this.setState({orders:arr});
    // }

    renderTuples = () => {
        return this.state.orders.map( (value, index) => {
            return <BuyTuple obj={value} key={index} onClickCheck={this.onClickCheck}/>
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
        await baseUrl.post(`/orders`, orderPkg, {headers: authHeader()})
        .then(() => {
            this.setState({postOrder:true});
            this.state.orders.forEach( async (productElement) => {
                const orderDetailPkg = {
                    quantity: 1,
                    orderCode: this.state.code,
                    product: {
                        id: productElement.id
                    }
                }
                await baseUrl.post(`/order-details/code`, orderDetailPkg, {headers: authHeader()})
                .then( () => {
                    removeItemFromCartService(productElement);
                })
                .catch( error => console.log(error));
            });
            this.setState({postOrderList:true});
            this.props.history.push("/history");
            })
            .catch(error => console.log(error));
    }

    render() {
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

            <div className="btn row">
                {this.state.orders.length !== 0 ? (
                    <React.Fragment>
                <button className="btn btn-primary" onClick={(e) => {
                    e.preventDefault();
                    destroyCartService();
                    window.location.reload();
                }}>
                    Destroy cart
                </button>
                <button className="btn btn-primary" onClick={this.onCheckButton}>
                    Check
                </button>
                </React.Fragment>) : null
                }   
                {this.state.postOrder && (
                <div className="alert alert-success" role="alert" >
                    Create order successfully
                </div>)}
                {this.state.postOrderList && (
                <div className="alert alert-success" role="alert" >
                    Create order detail list successfully
                </div>)}
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

