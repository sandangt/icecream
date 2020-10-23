import React from "react";

import Card from "components/admin/Card.jsx";

function CardList(props) {
    let data = props.cardInfo.map( (element, index) => {    
    return (<Card cardTitle={element.title} cardContent={element.content} cardUrl={element.url} randomNumber={index}/>);
});
    return (
<div className="row text-center">
    {data}
</div>
    );
}

export default CardList;