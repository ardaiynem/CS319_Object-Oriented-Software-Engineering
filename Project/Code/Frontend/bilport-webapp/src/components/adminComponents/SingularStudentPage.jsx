import { Card, Container, Row, Col, Tab, Tabs, CloseButton, Spinner, Alert } from "react-bootstrap";
import { useLocation } from "react-router-dom";
import { Form, Button, Stack } from "react-bootstrap";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import useAxiosPrivate from "../../hooks/useAxiosPrivate";
import useAuth from '../../hooks/useAuth';

export default function SingularStudentPage() {
    const { state } = useLocation();
    const { assignedStudent } = state;
    console.log(assignedStudent);



    // Of course, this will be handled by an api call to the localhost:XXXX/admin. This is temporary.
    // const [evaluators] = useState([
    //     { id: 20022002, name: "Eray Tüzün", email: "eray.tuzun@cs.bilkent.edu.tr", student_limit: 25, num_of_students: 12 },
    //     { id: 2, name: "Halil Altay Güvenir", email: "altay.guvenir@cs.bilkent.edu.tr", student_limit: 30, num_of_students: 18 },
    //     { id: 3, name: "Fazlı Can", email: "fazli.can@cs.bilkent.edu.tr", student_limit: 40, num_of_students: 16 },
    //     { id: 4, name: "Selim Aksoy", email: "selim.aksoy@cs.bilkent.edu.tr", student_limit: 30, num_of_students: 27 }
    // ]);

    return (
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <SingularStudentInfo student={assignedStudent} />
        </div>
    );
}

function SingularStudentInfo(props) {
    const [studentsEvaluator, setStudentsEvaluator] = useState({});
    const [isChooseEvaluatorDisplayed, setChooseEvaluatorDisplay] = useState(false);
    const [evaluators, setEvaluators] = useState([]);
    const [evaluatorToAssign, setEvaluatorToAssign] = useState({ evaluatorId: props.student.assignedEvaluatorId });
    const { auth } = useAuth();
    // const [student, setStudent] = useState({});
    const axiosPrivate = useAxiosPrivate();
    const [studentNewEvalSubmitted, setStudentNewEvalSubmitted] = useState(false);

    // async function fetchStudent(axiosPrivate, setStudent) {
    //     try {
    //         const response = await axiosPrivate.get('/students/' + props.student.studentId);
    //         setStudent(response.data);
    //         const foundEval = props.evaluators.find(obj => obj.id == response.data.assignedEvaluatorId);
    //         setStudentsEvaluator(foundEval);
    //         //console.log(response.data)
    //     } catch (err) {
    //         console.error(err);
    //     }
    // }

    async function fetchAllEvaluators(axiosInstance, setEvaluator) {
        try {
            const response = await axiosInstance.get(`/evaluators`);
            setEvaluators(response.data);
            console.log(response.data);
        } catch (error) {
            console.error(error);
        }
    }

    useEffect(() => {
        //fetchStudents(axiosPrivate, auth, setStudentz)
        //fetchReports(axiosPrivate, auth, setReports);
        //fetchStudent(axiosPrivate, setStudent);
        fetchAllEvaluators(axiosPrivate, setEvaluators);
    }
        , []);

    useEffect(() => {
        const foundEval = evaluators.find(obj => obj.userName == props.student.assignedEvaluatorId);
        setStudentsEvaluator(foundEval);
        console.log(foundEval + " " + props.student.assignedEvaluatorId);
    }, [evaluators, props.student.assignedEvaluatorId]);

    function AssignStudentToEvaluator(props) {
        return (
            <Card>
                <Card.Header>
                    <Card.Title>Choose an evaluator</Card.Title>
                </Card.Header>
                <Card.Body>
                    {
                        props.evaluators.map((evaluator) => (
                            <Form.Check
                                type="radio"
                                checked={evaluatorToAssign.evaluatorId === evaluator.userName} 
                                disabled={evaluator.userName == props.student.assignedEvaluatorId}
                                id="assign-evaluator-radio"
                                name="assign-evaluator-radio-group"
                                value={evaluator.userName}
                                label={
                                    <span className={evaluator.studentLimit - evaluator.assignedStudents.length < 0 ? 'text-danger' : ''}>
                                      {`${evaluator.evaluatorName} ${evaluator.evaluatorSurname} (Remaining Quota: ${evaluator.studentLimit - evaluator.assignedStudents.length})`}
                                    </span>
                                  }
                                onChange={(e) => {
                                    if (e.target.checked) { 
                                        setEvaluatorToAssign({ evaluatorId: e.target.value });
                                    }
                                }} />
                        ))
                    }
                    <br />
                    <Button variant="warning" onClick={handleSubmitChange} disabled={evaluatorToAssign.evaluatorId == props.student.assignedEvaluatorId}>Submit Change</Button>
                    {studentNewEvalSubmitted && <div className="mt-4">
                        <Alert variant="warning" dismissible>Student is succesfully assigned to new evaluator. Refresh the page to see the changes.</Alert>
                    </div>}
                </Card.Body>
            </Card>
        );
    }

    // When the "assing student to evaluator" button is clikced, set the component's display to its opposite boolean value
    function handleAssignStudentButtonClick() {
        setChooseEvaluatorDisplay(!isChooseEvaluatorDisplayed);
    }

    function handleSubmitChange() {
        console.log("submit change: " + evaluatorToAssign.evaluatorId);
        async function fetchAssign(axiosPrivate) {
            try {
                const response = await axiosPrivate.get('/evaluators/assign/' + evaluatorToAssign.evaluatorId + "/" + props.student.studentId);
                console.log(response.data);
                setStudentNewEvalSubmitted(true);
                
            } catch (err) {
                console.error(err);
            }
        }

        fetchAssign(axiosPrivate);
    }

    // When the "isChooseEvaluatorDisplayed" boolean changes according to "handleAssignStudentButtonClick" function, set display accordingly
    useEffect(() => {
        if (document.getElementById("assignStudentToEvaluatorDisplay") == null) return;

        if (isChooseEvaluatorDisplayed) {
            document.getElementById("assignStudentToEvaluatorDisplay").style.display = "block";
        }
        else {
            document.getElementById("assignStudentToEvaluatorDisplay").style.display = "none";
        }
    }
        , [isChooseEvaluatorDisplayed])


    if (!props.student.assignedEvaluatorId || studentsEvaluator) {
        //console.log(studentsEvaluator.userName);
        return (
            <Card className="standaloneCard">
                <Card.Header>
                    <Link to="/admin" ><i class="material-icons" style={{ "font-size": "30px" }}>arrow_back</i></Link>
                </Card.Header>
                <Card.Body>
                    <Container fluid>
                        <Card.Title><b>{props.student.studentName} {props.student.studentSurname}</b></Card.Title>
                        <hr />
                        <Row>
                            <Col lg={2}>ID: </Col>
                            <Col lg={10}><div class="text-secondary">{props.student.studentId}</div></Col>
                        </Row>
                        <hr />
                        <Row>
                            <Col lg={2}>Email: </Col>
                            <Col lg={10}><div class="text-secondary">{props.student.studentEmail}</div></Col>
                        </Row>
                        <hr />
                        <Row>
                            <Col lg={2}>Courses Taken: </Col>
                            <Col lg={10}><div class="text-secondary">{props.student.coursesTaken.join(" & ")}</div></Col>
                        </Row>
                        <hr />
                        <Row>
                            <Col lg={2}>Evaluator: </Col>
                            {studentsEvaluator?.userName &&
                                <Col lg={10}><div class="text-primary">{studentsEvaluator.evaluatorName + " " + studentsEvaluator.evaluatorSurname}</div></Col>}
                            {!studentsEvaluator?.userName &&
                                <Col lg={10}><div class="text-danger">Not assigned to an evaluator yet</div></Col>}
                        </Row>
                        <hr />
                        <Row>
                            <Col lg={2}>CS299 Report Iteration Amount: </Col>
                            <Col lg={10}><div class="text-primary">{props.student.reports299 && props.student.reports299.length}</div></Col>
                        </Row>
                        <hr />
                        <Row>
                            <Col lg={2}>CS399 Report Iteration Amount: </Col>
                            <Col lg={10}><div class="text-primary">{props.student.reports399 && props.student.reports399.length}</div></Col>
                        </Row>
                        <br />
                        <Row>
                            <Button id="assignButton" variant="outline-primary" onClick={handleAssignStudentButtonClick}>Assign student to an evaluator</Button>
                            <div id="assignStudentToEvaluatorDisplay">
                                <br />
                                <AssignStudentToEvaluator evaluators={evaluators} student={props.student} />
                            </div>
                        </Row>
                    </Container>
                </Card.Body>
            </Card>)
    }
    else {
        return (<Card>
            <Card.Header>
                <Spinner variant="primary"></Spinner>
            </Card.Header>
        </Card>
        );

    }

}
