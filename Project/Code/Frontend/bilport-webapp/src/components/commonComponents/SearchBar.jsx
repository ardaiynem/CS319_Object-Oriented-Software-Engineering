import { useState, useEffect } from "react";
import { Form } from "react-bootstrap";

export default function SearchBar( {onSearch} ){
    const [searchQuery, setSearchQuery] = useState("");

    function handleSearch(event){
        event.preventDefault();
        onSearch(searchQuery);
    }

    return(
        <Form onSubmit = {handleSearch}>
            <Form.Group>
                <Form.Control
                    type = "text"
                    placeholder = "Search and enter"
                    value = {searchQuery}
                    onChange = {
                        (event) => setSearchQuery(event.target.value)
                    }
                />
            </Form.Group>
        </Form>
    );
}