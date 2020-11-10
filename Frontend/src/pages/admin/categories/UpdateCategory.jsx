import React from 'react';
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

    onSubmit = (e) => {
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
        console.log(this.props.match);
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
                    <div className="form-group">
                        <button onClick={this.backButtonHandle} className="btn btn-primary">Back</button>
                        <input type="submit"
                            value="Update category"
                            className="btn btn-primary"/>
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

export default UpdateCategory;