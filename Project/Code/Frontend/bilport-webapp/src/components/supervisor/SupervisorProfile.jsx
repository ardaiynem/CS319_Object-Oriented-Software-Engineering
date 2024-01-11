import { Row, Col, Card, Container } from "react-bootstrap";
import ChangePassword from "../commonComponents/ChangePassword";
import useAuth from '../../hooks/useAuth';
import useAxiosPrivate from "../../hooks/useAxiosPrivate";
import { useState } from "react";
import { useEffect } from "react";

async function fetchSupervisor(axiosPrivate, auth, setSupervisor) {
    try {
        const response = await axiosPrivate.get('/supervisors/direct/' + auth.user);
        setSupervisor(response.data);
    } catch (err) {
        console.error(err);
    }
}

export default function SupervisorProfile() {
    const { auth } = useAuth();
    const [supervisor, setSupervisor] = useState({});
    const axiosPrivate = useAxiosPrivate();

    useEffect(() => { fetchSupervisor(axiosPrivate, auth, setSupervisor); }, []);

    return (
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <h1 class="bigPageTitle"> Profile Page </h1>
            <Card>
                <Card.Body>
                    <Container fluid>
                        <Row>
                            <Col lg={2} style={{ color: "purple" }}>Full name:</Col>
                            <Col lg={10}><div class="text-secondary">{supervisor.supervisorFullName}</div></Col>
                        </Row>
                        <hr />
                        <Row>
                            <Col lg={2} style={{ color: "purple" }}>Email:</Col>
                            <Col lg={10}><div class="text-secondary">{supervisor.supervisorEmail}</div></Col>
                        </Row>
                        <hr />
                        <Row>
                            <Col lg={2} style={{ color: "purple" }}>ID:</Col>
                            <Col lg={10}><div class="text-secondary">{supervisor.userName}</div></Col>
                        </Row>
                    </Container>
                </Card.Body>
            </Card>

            <div style={{ marginTop: '50px  ' }}>
                <ChangePassword />
            </div>
        </div>
    );
}
