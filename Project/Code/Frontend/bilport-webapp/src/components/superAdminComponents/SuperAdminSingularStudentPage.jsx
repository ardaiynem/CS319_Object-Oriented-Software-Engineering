import { Card, Row, Col, Container } from "react-bootstrap";
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";

export default function SuperAdminSingularStudentPage(props){
    const { state } = useLocation();
    const { assignedStudent } = state;

    return(
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <SuperAdminSingularStudentInfo student = {assignedStudent}/>
        </div>
    )

        function SuperAdminSingularStudentInfo(props){
            return(
                <Card class = "standaloneCard">
                    <Card.Header>
                        <Link to="/superadmin" ><i class="material-icons" style= {{"font-size":"30px"}}>arrow_back</i></Link>
                    </Card.Header>
                    <Card.Body>
                        <Container fluid>
                            <Card.Title><b>{props.student.name}</b></Card.Title>
                            <hr/>
                            <Row>
                                <Col lg = {2}>ID: </Col>
                                <Col lg = {10}><div class = "text-secondary">{props.student.studentId}</div></Col>
                            </Row>
                            <hr/>
                            <Row>
                                <Col lg = {2}>Email: </Col>
                                <Col lg = {10}><div class = "text-secondary">{props.student.studentEmail}</div></Col>
                            </Row>
                            <hr/>
                            <Row>
                                <Col lg = {2}>Current Course: </Col>
                                <Col lg = {10}><div class = "text-secondary">{props.student.coursesTaken[0]}</div>
                                { props.student.coursesTaken[1] && 
                                <div class = "text-secondary">{props.student.coursesTaken[1]}</div>}
                                </Col>
                            </Row>
                            <hr/>
                        </Container>
                    </Card.Body>
                </Card>
            );
        }
}