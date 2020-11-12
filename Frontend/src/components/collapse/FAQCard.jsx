import React from "react";

function FAQCard(props) {
    return (        
    <div className="card">
        <div className="card-header" id="headingOne">
            <h5 className="mb-0">
                <button
                    className="btn btn-link"
                    data-toggle="collapse"
                    data-target="#collapseOne"
                    aria-expanded="true"
                    aria-controls="collapseOne"
                >
                    {props.question}
                </button>
            </h5>
        </div>
        <div
            id="collapseOne"
            className="collapse show"
            aria-labelledby="headingOne"
            data-parent="#accordion"
        >
            <div className="card-body">
                {props.answer}
            </div>
        </div>
    </div>
    );
}

export default FAQCard;