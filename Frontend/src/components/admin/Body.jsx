import React from "react";

import CardList from "components/admin/CardList.jsx";
import HelloCard from "components/admin/HelloCard.jsx";

let DUMMY_DATA = [{
    title: "MAJOR",
    content: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo magni sapiente, tempore debitis beatae culpa natus architecto.",
    url: "/admin/major",
    randomImageUrl: "https://picsum.photos/500/325?random=3"
    }, {
    title: "CURRICULUM",
    content: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sapiente esse necessitatibus neque.",
    url: "/admin/curriculum",
    randomImageUrl: "https://picsum.photos/500/325?random=3"
    }, {
    title: "COURSE",
    content: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo magni sapiente, tempore debitis beatae culpa natus architecto.",
    url: "/admin/course",
    randomImageUrl: "https://picsum.photos/500/325?random=3"
    }]
class Body extends React.Component {
    
    render() {
        return (
<main>
    <div className="container">
        <HelloCard foreWord="Hello" content="CRUD app with spring MVC" url="/admin/fun"/>
        <CardList cardInfo={DUMMY_DATA}/>
    </div>
</main>
        );
    }
}

export default Body;