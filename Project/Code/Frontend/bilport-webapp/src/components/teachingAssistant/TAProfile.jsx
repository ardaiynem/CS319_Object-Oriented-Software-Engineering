import { Row, Col, Card, Container } from "react-bootstrap";
import ChangePassword from "../commonComponents/ChangePassword";
import useAuth from '../../hooks/useAuth';
import useAxiosPrivate from "../../hooks/useAxiosPrivate";
import { useState } from "react";
import { useEffect } from "react";

async function fetchUserTA(axiosInstance, auth, setTa) {
    try {
        const response = await axiosInstance.get(`/TAs/${auth.user}`);
        setTa(response.data);
    } catch (error) {
        console.error(error);
    }
}

export default function TAProfile() {
    const { auth } = useAuth();
    const [ta, setTa] = useState({});
    const axiosPrivate = useAxiosPrivate();

    useEffect(() => { fetchUserTA(axiosPrivate, auth, setTa); }, []);

    return (
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <h1 class="bigPageTitle"> Profile Page </h1>
            <Card>
                <Card.Body>
                    <Container fluid>
                        <Row>
                            <Col lg={2} style={{ color: "purple" }}>Full name:</Col>
                            <Col lg={10}><div class="text-secondary">{ta.taFullName}</div></Col>
                        </Row>
                        <hr />
                        <Row>
                            <Col lg={2} style={{ color: "purple" }}>Email:</Col>
                            <Col lg={10}><div class="text-secondary">{ta.taEmail}</div></Col>
                        </Row>
                        <hr />
                        <Row>
                            <Col lg={2} style={{ color: "purple" }}>ID:</Col>
                            <Col lg={10}><div class="text-secondary">{ta.userName}</div></Col>
                        </Row>
                        <hr />
                        <Row>
                            <Col lg={2} style={{ color: "purple" }}>Assigned Course:</Col>
                            <Col lg={10}><div class="text-secondary">{ta.assignedCourse}</div></Col>
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