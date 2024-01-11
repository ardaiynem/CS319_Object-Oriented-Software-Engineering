import { Row, Col, Card, Container } from "react-bootstrap";
import ChangePassword from "../commonComponents/ChangePassword";
import useAuth from '../../hooks/useAuth';
import useAxiosPrivate from "../../hooks/useAxiosPrivate";
import { useState } from "react";
import { useEffect } from "react";

async function fetchUserEvaluator(axiosInstance, auth, setEvaluator) {
    try {
        const response = await axiosInstance.get(`/evaluators/${auth.user}`);
        setEvaluator(response.data);
    } catch (error) {
        console.error(error);
    }
}

export default function EvaluatorProfile(){
    { /* Instead of placeholder info, this component should take the evaluator object, probably as a parameter */}
    const { auth } = useAuth();
    const [evaluator, setEvaluator] = useState({});
    const axiosPrivate = useAxiosPrivate();

    useEffect(() => { fetchUserEvaluator(axiosPrivate, auth, setEvaluator); }, []);

    return (
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <h1 class = "bigPageTitle"> Profile Page </h1>
            <Card>
                <Card.Body>
                    <Container fluid>
                        <Row>
                            <Col lg = {2} style={{ color: "purple"}}>Full name:</Col>
                            <Col lg = {10}><div class = "text-secondary">{evaluator.evaluatorName + " " + evaluator.evaluatorSurname}</div></Col>
                        </Row>
                        <hr/>
                        <Row>
                            <Col lg = {2} style={{ color: "purple"}}>Email:</Col>
                            <Col lg = {10}><div class = "text-secondary">{evaluator.evaluatorEmail}</div></Col>
                        </Row>
                    </Container>
                </Card.Body>
            </Card>

            <div style={{ marginTop: '50px  ' }}>
                <ChangePassword/>
            </div>
        </div>
    );
}