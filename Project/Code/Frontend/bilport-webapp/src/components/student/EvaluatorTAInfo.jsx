import { Card, Row, Col, Container } from "react-bootstrap";

import useAuth from '../../hooks/useAuth';
import useAxiosPrivate from "../../hooks/useAxiosPrivate";
import { useState } from "react";
import { useEffect } from "react";
import CourseTAInfo from "../commonComponents/CourseTAInfo";

export default function EvaluatorTAInfo() {
const { auth } = useAuth();
  const [student, setStudent] = useState([]);
  const axiosPrivate = useAxiosPrivate();
  const [evaluator, setEvaluator] = useState([]);


  useEffect(() => {
    const fetchUserStudent = async () => {
      try {
        const response = await axiosPrivate.get(`/students/${auth.user}`);
        setStudent(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    if (auth.user) {
      fetchUserStudent();
    }
  }, [auth.user, axiosPrivate]);

  useEffect(() => {
    const fetchUserEvaluator = async () => {
      try {
        const response = await axiosPrivate.get(`/evaluators/${student.assignedEvaluatorId}`);
        setEvaluator(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    if (student.assignedEvaluatorId) {
      fetchUserEvaluator();
    }
  }, [student.assignedEvaluatorId, axiosPrivate]);


    return(
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <h1 className = "bigPageTitle"> Evaluator / TA Information </h1>
            <div className = "standaloneCard">
                <Card>
                    <Card.Body>
                        <Card.Title> Your assigned evaluator </Card.Title>
                        <hr/>
                        <Container fluid>
                                <Row>
                                    <Col lg = {2} style={{ color: "purple"}}>Name: </Col>
                  <Col lg={10}><div className="text-secondary">{evaluator.evaluatorName} {evaluator.evaluatorSurname}</div></Col>
                                </Row>
                                <hr/>
                                <Row>
                                    <Col lg = {2} style={{ color: "purple"}}>Email: </Col>
                                    <Col lg = {10} ><div className = "text-secondary">{evaluator.evaluatorEmail}</div></Col>
                                </Row>
                        </Container>
                    </Card.Body>
                </Card>
            </div>
            <div className = "standaloneCard">
                <CourseTAInfo customStyle={{ marginLeft: 0, padding: 0 }} course="CS299" />
                <CourseTAInfo customStyle={{ marginLeft: 0, padding: 0 }} course="CS399" />
            </div>
        </div>
    );
}
