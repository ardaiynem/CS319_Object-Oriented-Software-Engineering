import { Row, Col, Card, Container, Button } from "react-bootstrap";
import { useState, useEffect } from "react";
import useAuth from "../../hooks/useAuth";
import useAxiosPrivate from "../../hooks/useAxiosPrivate";

export default function CourseTAInfo(props){
    const [ta299, setTa299] = useState({});
    const [ta399, setTa399] = useState({});
    const axiosPrivate = useAxiosPrivate();

    async function fetchCourseTa(axiosInstance, setTa, course) {
        try {
            const response = await axiosInstance.get(`/courses/ta/${course}`);
            setTa(response.data);
        } catch (error) {
            console.error(error);
        }
    }

    useEffect(() => {
        fetchCourseTa(axiosPrivate, setTa299, props.course);
    }, []);

    return (
        <div style={{ ...{ marginLeft: '250px', padding: '20px' }, ...props.customStyle }}>
            <h1 class = "bigPageTitle">  </h1>
            <Card>
                <Card.Header>
                    <Card.Title> {props.course} Teaching Assistant </Card.Title>
                </Card.Header>
                <Card.Body>
                    <Container fluid>
                        <Row>
                            <Col lg = {2} style={{ color: "purple"}}>Full name:</Col>
                            <Col lg = {10}><div class = "text-secondary">{ta299.taFullName}</div></Col>
                        </Row>
                        <hr/>
                        <Row>
                            <Col lg = {2} style={{ color: "purple"}}>Email:</Col>
                            <Col lg = {10}><div class = "text-secondary">{ta299.taEmail}</div></Col>
                        </Row>
                        <br/>
                    </Container>
                </Card.Body>
            </Card>
        </div>
    );
}