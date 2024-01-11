import { useEffect, useState } from "react";
import { Card, Row, Col, Container, Form, Accordion, Button, FormCheck } from "react-bootstrap";
import { useLocation } from "react-router-dom";
import { Link } from "react-router-dom";
import useAuth from '../../hooks/useAuth';
import useAxiosPrivate from "../../hooks/useAxiosPrivate";


async function fetchSupervisorForm(axiosInstance, auth, setForm, setQ1, setQ2, setQ3, setQ4, setQ5, setQ6, setQ7, setQ8, setComment, setInternshipStartDate, setInternshipEndDate) {
    try {
        console.log("a");
        const response = await axiosInstance.get(`/supervisorForms/direct/` + auth.user);
        setForm(response.data);
    } catch (err) {
        console.error(err);
    }
}

export default function SupervisorEvaluationForm() {
    const { auth } = useAuth();
    const [internshipStartDate, setInternshipStartDate] = useState("");
    const [internshipEndDate, setInternshipEndDate] = useState("");
    const [internshipDuration, setInternshipDuration] = useState(0);
    const [showLoading, setShowLoading] = useState(false);
    const [showErrorAlert, setShowErrorAlert] = useState(false);

    let [q1, setQ1] = useState({});
    let [q2, setQ2] = useState({});
    let [q3, setQ3] = useState({});
    let [q4, setQ4] = useState({});
    let [q5, setQ5] = useState({});
    let [q6, setQ6] = useState({});
    let [q7, setQ7] = useState({});
    let [q8, setQ8] = useState({});

    const [studentId, setStudentId] = useState(auth.user);
    const [comment, setComment] = useState({});
    const [form, setForm] = useState([]);


    useEffect(() => {     
        fetchSupervisorForm(
        axiosPrivate,
        auth,
        setForm,
        setQ1,
        setQ2,
        setQ3,
        setQ4,
        setQ5,
        setQ6,
        setQ7,
        setQ8,
        setComment,
        setInternshipStartDate,
        setInternshipEndDate); 
        console.log(form);}, []);
        

    useEffect(() => {
            console.log(form);
        }, [form]);

    const axiosPrivate = useAxiosPrivate();



    useEffect( () => {
        const start = new Date(internshipStartDate);
        const end = new Date(internshipEndDate);

        if (end - start > 0){
            setInternshipDuration(Math.ceil( (end - start) / (1000 * 60 * 60 * 24)));
        }
        else{
            setInternshipDuration(0);
        }

    }, [internshipStartDate, internshipEndDate] )

    const handleFormSubmission = (id) => {
        if (Object.keys(q1) == null || Object.keys(q2) == null ||
            Object.keys(q3) == null || Object.keys(q4) == null || Object.keys(q5) == null ||
            Object.keys(q6) == null || Object.keys(q7) == null) {
            setShowErrorAlert(true);
            setShowLoading(false);
            return;
            }

            const submitForm = async () => {
                try {
                    const response = await axiosPrivate.post(('/supervisorForms/' + studentId),
                        {
                            studentId: studentId,
                            q1: q1,
                            q2: q2,
                            q3: q3,
                            q4: q4,
                            q5: q5,
                            q6: q6,
                            q7: q7,
                            q8: q8,
                            comment: comment,
                            course: "CS299",
                            startDate: internshipStartDate,
                            endDate: internshipEndDate
                        });
                    setShowLoading(false);
                    setShowErrorAlert(false);


                } catch (err) {
                    console.error(err);
                    setShowErrorAlert(true);
                    setShowLoading(false);
                }
        };

        if (studentId != undefined) {

                submitForm();
                console.log("s");
        }
    }

    return(
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <Card style={{backgroundColor: "rgb(91, 14, 130,0.2)"}}>
                <Card.Header style={{color:"white"}}>
                    <Card.Title> Confidential Summer Training Form </Card.Title>
                </Card.Header>
                <Card.Body>
                    <Container>
                        <Card>
                            <Card.Body>
                                <Row>
                                    <Card.Title> Course Name </Card.Title>
                                    <Card.Subtitle className="mb-2 text-secondary">Which course did the trainee do the summer training for?</Card.Subtitle>
                                    <Col><Form.Check type="radio" name="courseRadio" label="CS299"/></Col>
                                    <Col><Form.Check type="radio" name="courseRadio" label="CS399"/></Col>
                                </Row>
                                <hr/>
                                <Row>
                                    <Col>Internship starting date:</Col>
                                    <Col>
                                        <input type="date" value={internshipStartDate}
                                        onChange={(event) => {setInternshipStartDate(event.target.value)}}/>
                                    </Col>
                                    <Col>Internship ending date:</Col>
                                    <Col>
                                        <input type="date" value={internshipEndDate}
                                        onChange={(event) => {setInternshipEndDate(event.target.value)}}/>
                                    </Col>
                                </Row>
                                <hr/>
                                <Row className="justify-content-md-center">
                                    <Col md="auto">
                                        <Card.Title>
                                            Duration of the training (including weekends and holidays): <b style={{color: "purple"}}>{internshipDuration}</b>
                                        </Card.Title>
                                    </Col>
                                </Row>
                            </Card.Body>
                        </Card>
                        <br/>
                        <Card>
                            <Card.Header>
                                <Row className="mb-3">
                                    <Col xs={4}><b>Summer Trainee:</b></Col>
                                    <Col><b>Good</b></Col>
                                    <Col><b>Average</b></Col>
                                    <Col><b>Poor</b></Col>
                                    <Col><b>Insufficient Observation</b></Col>
                                </Row>
                            </Card.Header>
                            <Card.Body>
                                <Row className="mb-3">
                                    <Col xs={4}>How much did the trainee improve her/his proffessional skills during the training?</Col>
                                    <Col><Form.Check type="radio" aria-label="good" name="radioGroup1" onChange={(e) => setQ1(3)} /></Col>
                                    <Col><Form.Check type="radio" aria-label="avg" name="radioGroup1" onChange={(e) => setQ1(2)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="poor" name="radioGroup1" onChange={(e) => setQ1(1)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="insuff" name="radioGroup1" onChange={(e) => setQ1(0)}/></Col>
                                </Row>
                                <hr/>
                                <Row className="mb-3">
                                    <Col xs={4}>How well did the trainee contribute to the solution of technical problems at work?</Col>
                                    <Col><Form.Check type="radio" aria-label="good" name="radioGroup2" onChange={(e) => setQ2(3)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="avg" name="radioGroup2" onChange={(e) => setQ2(2)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="poor" name="radioGroup2" onChange={(e) => setQ2(1)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="insuff" name="radioGroup2" onChange={(e) => setQ2(0)}/></Col>
                                </Row>
                                <hr/>
                                <Row className="mb-3">
                                    <Col xs={4}>How did the trainee cooperate with her/his colleagues and supervisors?</Col>
                                    <Col><Form.Check type="radio" aria-label="good" name="radioGroup3" onChange={(e) => setQ3(3)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="avg" name="radioGroup3" onChange={(e) => setQ3(2)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="poor" name="radioGroup3" onChange={(e) => setQ3(1)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="insuff" name="radioGroup3" onChange={(e) => setQ3(0)}/></Col>
                                </Row>
                                <hr/>
                                <Row className="mb-3">
                                    <Col xs={4}>Did the trainee fairly treat all individuals at the workplace regardless of factors such as race, religion, gender, disability, age, or national origin?</Col>
                                    <Col><Form.Check type="radio" aria-label="good" name="radioGroup4" onChange={(e) => setQ4(3)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="avg" name="radioGroup4" onChange={(e) => setQ4(2)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="poor" name="radioGroup4" onChange={(e) => setQ4(1)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="insuff" name="radioGroup4" onChange={(e) => setQ4(0)}/></Col>
                                </Row>
                                <hr/>
                                <Row className="mb-3">
                                    <Col xs={4}>How did the trainee contribute to teamwork?</Col>
                                    <Col><Form.Check type="radio" aria-label="good" name="radioGroup5"  onChange={(e) => setQ5(3)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="avg" name="radioGroup5" onChange={(e) => setQ5(2)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="poor" name="radioGroup5" onChange={(e) => setQ5(1)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="insuff" name="radioGroup5" onChange={(e) => setQ5(0)}/></Col>
                                </Row>
                                <hr/>
                                <Row className="mb-3">
                                    <Col xs={4}>In the context of teamwork, did the trainee take into account the opinions of other team members?</Col>
                                    <Col><Form.Check type="radio" aria-label="good" name="radioGroup6" onChange={(e) => setQ6(3)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="avg" name="radioGroup6" onChange={(e) => setQ6(2)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="poor" name="radioGroup6" onChange={(e) => setQ6(1)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="insuff" name="radioGroup6" onChange={(e) => setQ6(0)}/></Col>
                                </Row>
                                <hr/>
                                <Row className="mb-3">
                                    <Col xs={4}>Did the trainee behave responsibly in making decisions consistent with the safety and health of others?</Col>
                                    <Col><Form.Check type="radio" aria-label="good" name="radioGroup7" onChange={(e) => setQ7(3)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="avg" name="radioGroup7" onChange={(e) => setQ7(2)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="poor" name="radioGroup7" onChange={(e) => setQ7(1)}/></Col>
                                    <Col><Form.Check type="radio" aria-label="insuff" name="radioGroup7" onChange={(e) => setQ7(0)}/></Col>
                                </Row>
                                <hr/>
                                <Row className="justify-content-md-center">
                                    <Col md="auto">
                                        <Form.Label><b>General evaluation of the trainee: </b></Form.Label>
                                        <Form.Control id="evaluationFormBlock" aria-describedby="evaluationFormBlock" onChange={(e) => setQ8(e.target.value)} />
                                        <Form.Text id="evaluationFormBlock" muted>(evaluate out of 10 points: 5 is the passing grade)</Form.Text>
                                    </Col>
                                </Row>
                            </Card.Body>
                        </Card>
                        <br/>
                        <Row className="mb-3">
                            <Accordion>
                                <Accordion.Item eventKey="0">
                                    <Accordion.Header>Add Additional Comments:</Accordion.Header>
                                    <Accordion.Body>
                                        <Form.Label>Enter your comments: </Form.Label>
                                        <Form.Control as="textarea" rows={4} onChange={(e) => setComment(e.target.value)}/>
                                    </Accordion.Body>
                                </Accordion.Item>
                            </Accordion>
                        </Row>
                        <Row className="justify-content-md-center">
                            <Button variant="primary" onClick={handleFormSubmission}>Submit</Button>
                        </Row>
                    </Container>
                </Card.Body>
            </Card>
        </div>
    );
}
