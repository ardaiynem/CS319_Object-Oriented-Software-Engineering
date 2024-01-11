import StudentsTable from "../commonComponents/StudentsTable";
import { useState } from "react";
import { Alert, Button, Card, Row, Stack } from "react-bootstrap";
import SearchBar from "../commonComponents/SearchBar";
import SortDropdown from "../commonComponents/SortDropdown";
import StudentsPage from "../commonComponents/StudentsPage";

export default function AdminStudents(props){
    return(
        <StudentsPage userType = "admin" students = {props.students} />
    )
}