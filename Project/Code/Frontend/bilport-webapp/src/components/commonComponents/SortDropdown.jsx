import { useState } from "react";
import { Dropdown, DropdownButton } from "react-bootstrap";

export default function SortDropdown( {onSelect, dropdownItems} ){
    const [selectedOption, setSelectedOption] = useState(null);

    function handleSelect(eventKey){
        setSelectedOption(eventKey);
        onSelect(eventKey);
    };

    return(
        <DropdownButton title = {selectedOption || "Sort by"} variant = "primary" onSelect={handleSelect}>
            {dropdownItems.map(
                (item) =>
                <Dropdown.Item key = {item.key} eventKey = {item.key}>
                    {item.name}
                </Dropdown.Item>
            )}
        </DropdownButton>
    )
}