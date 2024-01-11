import { useLocation } from "react-router-dom";
import { Form, Row, Col, Card, Container, Stack, Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import useAxiosPrivate from "../../hooks/useAxiosPrivate";
import { useState, useEffect } from "react";

export default function TASingleStudentPage(props) {
    const { state } = useLocation();
    const { assignedStudent } = state;
    const [report, setReport] = useState({});
    const axiosPrivate = useAxiosPrivate();

    async function fetchReport(axiosPrivate, course, assignedStudent, setReport) {
        try {
            const response = await axiosPrivate.get('/reports/report/' + (course == "CS299" ? assignedStudent.reports299[0] : assignedStudent.reports399[0]));
            setReport(response.data);
            console.log(response.data)
        } catch (err) {
            console.error(err);
        }
    }

    useEffect(() => {
        fetchReport(axiosPrivate, props.ta.assignedCourse, assignedStudent, setReport);
    }, []);


    return (
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <TASingleStudentInfo student={assignedStudent} ta={props.ta} report={report}/>
            <TASingleStudentReport student={assignedStudent} ta={props.ta} />
        </div>
    )

    function TASingleStudentInfo(props) {
        return (
            <Card class="standaloneCard">
                <Card.Header>
                    <Link to="/ta" ><i class="material-icons" style={{ "font-size": "30px" }}>arrow_back</i></Link>
                </Card.Header>
                <Card.Body>
                    <Container fluid>
                        <Card.Title><b>{props.student.studentName + " " + props.student.studentSurname}</b></Card.Title>
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
                        {props.ta.assignedCourse == "CS299" &&
                            <div>
                                <hr />
                                <Row>
                                    <Col lg={2}>CS299 Report Status: </Col>
                                    <Col lg={10}><div class="text-primary">{(props.student.reports299 && props.student.reports299.length == 0) ? "Not Submitted" : (props.report ? props.report.reportStatus : "...")}</div></Col>
                                </Row>
                            </div>}

                        {props.ta.assignedCourse == "CS399" &&
                            <div>
                                <hr />
                                <Row>
                                    <Col lg={2}>CS399 Report Status: </Col>
                                    <Col lg={10}><div class="text-primary">{(props.student.reports399 && props.student.reports399.length == 0) ? "Not Submitted" : (props.report ? props.report.reportStatus : "...")}</div></Col>
                                </Row>
                            </div>}
                    </Container>
                </Card.Body>
            </Card>
        );
    }

    function TASingleStudentReport(props) {
        const axiosPrivate = useAxiosPrivate();

        const handleDownloadReportFile = () => {
            const downloadReport = async () => {
                try {
                    const response = await axiosPrivate.get("/reports/file/" + (props.ta.assignedCourse == "CS299" ? (props.student.reports299)[0] : (props.student.reports399)[0]), { responseType: 'blob' }).then((response) => {
                        let fileName = (props.ta.assignedCourse == "CS299" ? (props.student.reports299)[0] : (props.student.reports399)[0]) + '.pdf';
                        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
                            // IE variant
                            window.navigator.msSaveOrOpenBlob(
                                new Blob([response.data], {
                                    type: 'application/pdf',
                                    encoding: 'UTF-8'
                                }),
                                fileName
                            );
                        } else {
                            const url = window.URL.createObjectURL(
                                new Blob([response.data], {
                                    type: 'application/pdf',
                                    encoding: 'UTF-8'
                                })
                            );
                            const link = document.createElement('a');
                            link.href = url;
                            link.setAttribute('download', fileName);
                            document.body.appendChild(link);
                            link.click();
                            link.remove();
                        }
                    });
                } catch (err) {
                    console.error(err);
                }
            };

            downloadReport();
        }

        const [file, setFile] = useState();

        const handleFileChange = (e) => {
            if (e.target.files) {
                setFile(e.target.files[0]);
                console.log(e.target.files[0].name);
            }
        };

        const handleUploadReport = () => {
            const formData = new FormData();
            formData.append('file', file)

            const uploadReportFile = async () => {
                try {
                    const response = await axiosPrivate.post('/feedbacks/file/T_' + props.student.studentId + "_" + props.ta.assignedCourse,
                        formData,
                        {
                            headers: { "Content-Type": "multipart/form-data" },
                        }
                    ).then((response) => { console.log("AAAA") });


                } catch (err) {
                    console.error(err);
                }
            };

            console.log(formData.get('file'));
            uploadReportFile();
        }

        return (
            <div className="standaloneCard">
                <Card>
                    <Card.Body>
                        <Container>
                            <Row className="justify-content-md-center">
                                <Button variant="outline-primary" onClick={handleDownloadReportFile} >Download Student's Report</Button>
                            </Row>
                            <hr />
                            <Form.Label>Upload Feedback</Form.Label>
                            <Stack direction="horizontal" gap={3}>
                                <Form.Control type="file" onChange={handleFileChange}></Form.Control>
                                <div className="vr" />
                                <Button variant="success" onClick={handleUploadReport}> Upload </Button>
                            </Stack>
                        </Container>
                    </Card.Body>
                </Card>
            </div>
        );
    }

    function TASingleStudentSupervisorStamp() {
        return (
            <div className="standaloneCard">
                <Card>
                    <Card.Body>
                        <Container>
                            <Row>
                                <Col xs={6}>
                                    <div style={{ padding: "20px" }}>
                                        <Button variant="outline-primary" size="lg"> Download the Sign/Stamp of Supervisor</Button>
                                    </div>
                                </Col>
                                <Col>
                                    <div style={{ padding: "10px" }}>
                                        <Form.Label>Does it match with the sign/stamp in the acceptance letter?</Form.Label>
                                        <Stack direction="horizontal" gap={5}>
                                            <Form.Check type="radio" name="signStampRadio" label="Yes" />
                                            <Form.Check type="radio" name="signStampRadio" label="No" />
                                            <Button variant="success" size="sm">Submit Selection</Button>
                                        </Stack>
                                    </div>
                                </Col>
                            </Row>
                        </Container>
                    </Card.Body>
                </Card>
            </div>
        )
    }

}
