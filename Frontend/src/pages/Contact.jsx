import React from "react";
import faker from "faker";

function Contact() {
    return (
<div>
    <h1>Contact</h1>
    <p>
        {faker.lorem.paragraphs()}
    </p>
    <p>
        {faker.lorem.paragraphs()}
    </p>
    <p>
        {faker.lorem.paragraphs()}   
    </p>    
</div>
        );
}

export default Contact;