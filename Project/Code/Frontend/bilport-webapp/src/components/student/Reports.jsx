import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';
import Dropdown from 'react-bootstrap/Dropdown';
import { Button, Form, Card, Alert, Spinner } from "react-bootstrap";
import { useRef, useState, useEffect, useLayoutEffect, version } from 'react';
import useAuth from '../../hooks/useAuth';
import useAxiosPrivate from "../../hooks/useAxiosPrivate";


async function fetchReports(axiosPrivate, auth, setReports) {
    try {
        const response = await axiosPrivate.get('/reports/' + auth.user);
        setReports(response.data);
    } catch (err) {
        console.error(err);
    }
}

async function fetchReportsWithCourse(axiosPrivate, auth, course, setReports) {
    try {
        const response = await axiosPrivate.get('/reports/' + auth.user + "/" + course);
        setReports(response.data);
    } catch (err) {
        console.error(err);
    }
}


export default function Reports() {
    async function fetchUserStudent(axiosPrivate, auth, setStudent) {
        try {
            const response = await axiosPrivate.get(`/students/${auth.user}`);
            setStudent(response.data);
        } catch (error) {
            console.error(error);
        }
    }

    const [student, setStudent] = useState({});

    useEffect(() => {
        fetchUserStudent(axiosPrivate, auth, setStudent);
    }, []);

    const { auth } = useAuth();

    const [reports, setReports] = useState([]);
    const [reports299, setReports299] = useState([]);
    const [reports399, setReports399] = useState([]);
    const [report, setReport] = useState({});
    const [newReportSubmit, setNewReportSubmit] = useState(0);

    const axiosPrivate = useAxiosPrivate();

    useEffect(() => {
        fetchReports(axiosPrivate, auth, setReports);
        fetchReportsWithCourse(axiosPrivate, auth, "CS299", setReports299);
        fetchReportsWithCourse(axiosPrivate, auth, "CS399", setReports399);
    }
        , [newReportSubmit]);

    return (
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <h1 className="bigPageTitle">Reports</h1>
            <Tabs defaultActiveKey="reportList" >
                <Tab eventKey="reportList" title="Report List" tabClassName='coloredTab' >
                    {/* <div style={{ marginTop: "25px" }}><ReportsDropdown reports={reports} report={report} setReport={setReport} /></div> */}
                    <ReportsInformation report={report} student={student} setReport={setReport}/>
                </Tab>
                <Tab eventKey="reportSubmit" title="Submit Report" tabClassName='coloredTab' >
                    <ReportSubmit student={student}/>
                </Tab>
            </Tabs>
        </div>
    );

    function ReportsDropdown({ reports, report, setReport }) {
        return (
            <Dropdown>
                <Dropdown.Toggle variant="secondary" id="dropdown-basic" style={{ backgroundColor: "purple" }}>
                    {report?.reportId ? report?.reportId : "Select a report"}
                </Dropdown.Toggle>
                <Dropdown.Menu>
                    {reports.map((report, i) => <Dropdown.Item key={i} onClick={() => { setReport(report); }}>{report?.reportId}</Dropdown.Item>)}
                </Dropdown.Menu>
            </Dropdown>
        );
    }

    function ReportsInformation(props) {
        const axiosPrivate = useAxiosPrivate();

        const handleDownloadReportFile = () => {
            const downloadReport = async () => {
                try {
                    const response = await axiosPrivate.get('/reports/file/' + props.report?.reportId, { responseType: 'blob' }).then((response) => {
                        let fileName = props.report?.reportId + '.pdf';
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
            console.log("KKK");
        }

        
        const handleDownloadFeedbackFile = (course, userType) => {
            const downloadReport = async () => {
                try {
                    const response = await axiosPrivate.get("/feedbacks/file/" + userType + "_" + auth.user + "_" + course, { responseType: 'blob' }).then((response) => {
                        let fileName = (userType + "_" + auth.user + "_" + course) + '.pdf';
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

        console.log(props.student);

        if (props.student.coursesTaken != undefined) {
            return (
                props.student.coursesTaken.map((course) =>
                    <div className="standaloneCard">
                        <Card>
                            <Card.Header>  {course} </Card.Header>
                            <Card.Body>
                                <Card.Title>Report Information</Card.Title>
                                <ReportsDropdown reports={course == "CS299" ? reports299 : reports399 } report={report} setReport={setReport}/>
                                <hr />
                                <ul className="report-info-list">
                                    <li>Report name: <div className="text-info">{props.report?.reportId ? props.report?.reportId : "Report Not Selected"}</div></li>
                                    <li>Submission date: <div className="text-info"> {props.report?.reportDate ? props.report?.reportDate : "Report Not Selected"} </div></li>
                                    <li>Status: <div className="text-danger">{props.report?.reportStatus ? props.report?.reportStatus : "Report Not Selected"} </div></li>
                                    <li>Version: <div className="text-danger">{props.report?.version ? props.report?.version : "Report Not Selected"} </div></li>
                                </ul>
                                <br />
                                <div className="d-grid gap-2">
                                    <Button variant="primary" onClick={handleDownloadReportFile} size="lg">
                                        Download Report
                                    </Button>
                                    <Button variant="primary" size="lg" onClick={() => handleDownloadFeedbackFile(course, "T")}>
                                        Download TA Feedback
                                    </Button>
                                    <Button variant="primary" size="lg" onClick={() => handleDownloadFeedbackFile(course, "E")}>
                                        Download Evaluator Feedback
                                    </Button>
                                </div>
                            </Card.Body>
                        </Card>
                    </div>));
        }
        else {
            return (
            <div>
                <br/>
                <Card>
                    <Card.Header> <Spinner variant='primary'></Spinner> </Card.Header>
                </Card>
            </div>)
        }
    }

    function ReportSubmit(props) {
        const { auth } = useAuth();

        const [reportId, setReportId] = useState({});

        const [reportTmp, setReportTmp] = useState({ course: "CS299" });

        const axiosPrivate = useAxiosPrivate();

        const [file, setFile] = useState();

        const [showAlert, setShowAlert] = useState(false);
        const [showSuccessfulAlert, setShowSuccessfulAlert] = useState(false);


        const handleFileChange = (e) => {
            if (e.target.files) {
                setFile(e.target.files[0]);
                console.log(e.target.files[0].name);
            }
        };

        const handleAlertDismiss = () => {
            setShowAlert(false);
        }

        const handleUploadReport = () => {
            if (!reportTmp || !reportId || !file) {
                setShowAlert(true);
                return;
            }
            const uploadReport = async () => {
                try {
                    console.log(props.student.reportVersion);

                    const response = await axiosPrivate.post('/reports/' + auth.user,
                        {
                            reportId: auth.user + "_" + reportTmp.course + "_" + (reportTmp.course == "CS299" ? (props.student.reports299.length + 1) : (props.student.reports399.length + 1)),
                            reportOwner: auth.user,
                            reportFileId: auth.user + "_" + reportTmp.course + "_" + (reportTmp.course == "CS299" ? (props.student.reports299.length + 1) : (props.student.reports399.length + 1)),
                            reportDate: new Date(),
                            reportStatus: "Submitted",
                            version: reportTmp.course == "CS299" ? (props.student.reports299.length + 1) : (props.student.reports399.length + 1),
                            course: reportTmp.course,
                        });


                } catch (err) {
                    console.error(err);
                }
            };

            const formData = new FormData();
            formData.append('file', file)

            const uploadReportFile = async () => {
                try {
                    const response = await axiosPrivate.post('/reports/file/' + auth.user + "_" + reportTmp.course + "_" + (reportTmp.course == "CS299" ? (student.reports299.length + 1) : (student.reports399.length + 1)),
                        formData,
                        {
                            headers: { "Content-Type": "multipart/form-data" },
                        }
                    ).then((response) => { console.log("AAAA") });


                } catch (err) {
                    console.error(err);
                }
            };

            //if (reportTmp.reportId != undefined) {
                uploadReport();
                uploadReportFile();
                setShowSuccessfulAlert(true);
                document.getElementById("formReportName").value = "";
                setNewReportSubmit(newReportSubmit + 1);
            //}

        }

        if (props.student.coursesTaken == undefined) { return(<Card>LOADING</Card>)}
        return (
            <div className="standaloneCard">
                <Card>
                    <Card.Body>
                        <Form.Group controlId="formFileLg" className="mb-3">
                            <br />
                            <Form.Check  inline defaultChecked disabled={!student.coursesTaken.includes("CS299")}
                                type="radio"
                                name="group1"
                                id={`default-radio`}
                                label="CS299"
                                value="CS299"
                                onChange={(e) => { setReportTmp({ ...reportTmp, course: e.target.value }) }
                                }
                            />

                            <Form.Check disabled={!student.coursesTaken.includes("CS399")} 
                                type="radio"
                                name="group1"
                                label="CS399"
                                value="CS399"
                                id={`2-default-radio`}
                                onChange={(e) => setReportTmp({ ...reportTmp, course: e.target.value })}
                            />

                            <Form.Label> Upload your report: </Form.Label>
                            <Form.Control type="file" accept='.pdf' onChange={handleFileChange} />
                        </Form.Group>
                        <Button variant="primary" onClick={handleUploadReport} size="lg">
                            Upload Report
                        </Button>
                        <div>
                            <br />
                            {showAlert &&
                                <Alert variant='warning' dismissible onClose={handleAlertDismiss}>
                                    Please enter both a file name and choose a file before submitting your report!
                                </Alert>}
                        </div>
                        <div>
                            <br />
                            {showSuccessfulAlert &&
                                <Alert variant='success' dismissible onClose={() => { setShowSuccessfulAlert(false) }}>
                                    Report uploaded successfully.
                                </Alert>}
                        </div>
                    </Card.Body>
                </Card>
            </div>
        );
    }
}

