import { Table } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { useRef, useState, useEffect, useLayoutEffect, version } from 'react';

export default function StudentsTable(props) {
    const navigate = useNavigate();

    function handleRowClicked(id) {
        console.log(id);

        const assignedStudent = props.students.find(obj => obj.studentId === id);
        console.log("ass");
        console.log(assignedStudent);
        if (props.userType == "evaluator" || props.userType == "ta")
            navigate(`/${props.userType}/students/${id}`, { state: { assignedStudent } });
        else if (props.userType == "admin" || props.userType == "superadmin")
            navigate(`/${props.userType}/students/${id}`, { state: { assignedStudent } });

    };

    // useEffect(() => {
    //     console.log("studentz changed");
    //     console.log(props);
    // }
    //     , [props.students]);


    // If the user is an evaluator, we render a simple table. If the user is an admin, we render a table with green-red rows.
    return (
        <div>
            {(() => {
                if (props.userType == "evaluator" || props.userType == "superadmin" || props.userType == "ta") {
                    //console.log(props.students);
                    return (
                        <Table striped bordered hover>
                            <thead>
                                <th>Name</th>
                                <th>Surname</th>
                                <th>ID</th>
                                <th>Mail</th>
                                <th>Courses Taken</th>
                            </thead>
                            <tbody>
                                {
                                    props.students.map((student) =>
                                        <tr class="clickRow" key={student.studentId} onClick={() =>
                                            handleRowClicked(student.studentId, student.course)} >
                                            <td>{student.studentName}</td>
                                            <td>{student.studentSurname}</td>
                                            <td>{student.studentId}</td>
                                            <td>{student.studentEmail}</td>
                                            <td>{student.coursesTaken.join(" & ")}</td>
                                        </tr>)
                                }
                            </tbody>
                        </Table>)
                }
                else if (props.userType == "admin") {
                    return (
                        <Table striped bordered hover>
                            <thead>
                                <th>Name</th>
                                <th>Surname</th>
                                <th>ID</th>
                                <th>Mail</th>
                                <th>Courses Taken</th>
                            </thead>
                            <tbody>
                                {
                                    props.students.map((student) =>
                                        <tr class={(student.assignedEvaluatorId && student.assignedEvaluatorId != "") ? "clickRowGreen" : "clickRowRed"} key={student.id} onClick={() => handleRowClicked(student.studentId)} >
                                            <td>{student.studentName}</td>
                                            <td>{student.studentSurname}</td>
                                            <td>{student.studentId}</td>
                                            <td>{student.studentEmail}</td>
                                            <td>{student.coursesTaken.join(" & ")}</td>
                                        </tr>)
                                }
                            </tbody>
                        </Table>)
                }
            })()}
        </div>
    );
}
