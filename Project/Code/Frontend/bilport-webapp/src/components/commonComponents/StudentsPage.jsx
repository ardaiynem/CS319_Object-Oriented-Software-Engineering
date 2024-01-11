import StudentsTable from "../commonComponents/StudentsTable";
import { useState, useEffect } from "react";
import { Alert, Button, Card, Row, Stack, Accordion, Form } from "react-bootstrap";
import SearchBar from "../commonComponents/SearchBar";
import SortDropdown from "../commonComponents/SortDropdown";
import useAxiosPrivate from "../../hooks/useAxiosPrivate";

export default function StudentsPage(props) {

    //const [students, setStudents] = useState(props.students)
    const [isCourseChecked, setIsCourseChecked] = useState(false);
    const [displayedStudents, setDisplayedStudents] = useState(props.students);
    const [isNewStudentSubmitted, setIsNewStudentSubmitted] = useState(false);
    const [newStudent, setNewStudent] = useState({ assignedSupervisorId: "" });
    const axiosPrivate = useAxiosPrivate();

    useEffect(() => {
        setDisplayedStudents(props.students);
    }
        , [props.students]);


    const sortingOptions = [
        { name: "Sort by name (ascending)", key: "Name" },
        { name: "Sort by name (descending)", key: "Name (Desc)" }
    ];

    function handleSearch(searchQuery) {
        if (searchQuery !== "") {
            const searchedStudents = props.students.filter(student =>
                (student.studentName + student.studentSurname).toLowerCase().includes(searchQuery.toLowerCase())
            );
            setDisplayedStudents(searchedStudents);
        }
    };

    async function fetchNewStudent(axiosPrivate, newStudent) {
        try {
            const response = await axiosPrivate.post('/students/newStudent', newStudent);
            console.log(response.data)
        } catch (err) {
            console.error(err);
        }
    }

    function handleRefreshDisplay() {
        setDisplayedStudents(props.students);
    }

    function handleSort(option) {

        const sortedStudents = [...displayedStudents];

        if (option === "Name") {
            sortedStudents.sort((student1, student2) => (student1.studentName > student2.studentName ? 1 : -1));
        }
        else if (option === "Name (Desc)") {
            sortedStudents.sort((student1, student2) => (student1.studentName < student2.studentName ? 1 : -1));
        }
        else if (option === "Date") {
            sortedStudents.sort((s1, s2) => {
                return new Date(s1.last_submission_date).getTime() - new Date(s2.last_submission_date).getTime();
            });
            console.log(sortedStudents)
        }
        else if (option === "Date (Desc)") {
            sortedStudents.sort((s1, s2) => {
                return new Date(s2.last_submission_date).getTime() - new Date(s1.last_submission_date).getTime();
            });
            console.log(sortedStudents)
        }

        setDisplayedStudents(sortedStudents);
    };

    function handleChange() {
        var courses = [];
        var checkBox299 = document.getElementById("check-course-299");
        var checkBox399 = document.getElementById("check-course-399");

        if(checkBox299.checked == true)
            courses.push("CS299");

        if(checkBox399.checked == true)
            courses.push("CS399");

        console.log(courses);

        setNewStudent({ ...newStudent, coursesTaken: courses});
        setIsCourseChecked(courses.length > 0);
    }

    return (
        <div>
            <Card>
                <Card.Body>
                    {(() => {
                        if (props.userType == "admin") //This is necessary because this is only rendered in admin
                            return (
                                <div className="standaloneCard">
                                    <Alert variant="success" > Green students are assigned to an evaluator. </Alert>
                                    <Alert variant="danger" > Red students are not assigned to an evaluator yet. </Alert>
                                </div>
                            )
                    })()}
                    <Stack direction="horizontal" gap={4}>
                        <SearchBar onSearch={handleSearch} />
                        <div className="vr" />
                        <Button variant="outline-primary" onClick={handleRefreshDisplay}>Refresh</Button>
                        <div className="vr" />
                        <SortDropdown onSelect={handleSort} dropdownItems={sortingOptions} />
                        <div className="vr" />
                    </Stack>
                    <br />
                    {(() => {
                        if (props.userType == "admin" || props.userType == "superadmin") return (
                            <div className="d-grid gap-2">
                                <Accordion>
                                    <Accordion.Item eventKey="0">
                                        <Accordion.Header>Add a new Student</Accordion.Header>
                                        <Accordion.Body>
                                            <Form.Group controlId="formFileLg" className="mb-3">
                                                <Form.Label className="mt-2"> Student ID:</Form.Label>
                                                <Form.Control id="formReportName" type="text" placeholder="Enter the ID of the student" onChange={(e) => setNewStudent({ ...newStudent, userName: e.target.value })} />

                                                <Form.Label className="mt-2"> Student Name:</Form.Label>
                                                <Form.Control id="formReportName" type="text" placeholder="Enter the name of the student" onChange={(e) => setNewStudent({ ...newStudent, studentName: e.target.value })} />

                                                <Form.Label className="mt-2"> Student Surname:</Form.Label>
                                                <Form.Control id="formReportName" type="text" placeholder="Enter the surname of the student" onChange={(e) => setNewStudent({ ...newStudent, studentSurname: e.target.value })} />

                                                <Form.Label className="mt-2"> Student Email:</Form.Label>
                                                <Form.Control id="formReportName" type="text" placeholder="Enter the email of the student" onChange={(e) => setNewStudent({ ...newStudent, studentEmail: e.target.value })} />

                                                <Form.Label className="mt-2"> Student Temporary Password:</Form.Label>
                                                <Form.Control id="formReportName" type="text" placeholder="Enter the temporary password of the student" onChange={(e) => setNewStudent({ ...newStudent, userPassword: e.target.value })} />

                                                <Form.Label className="mt-2">Set Courses Taken by Student:</Form.Label>
                                                {!isCourseChecked && 
                                                <Form.Text className="text-danger m-2">Choose at least one course in order to proceed</Form.Text>}
                                                <br/>
                                                <Form.Check inline
                                                    type="checkbox"
                                                    name="group1"
                                                    id="check-course-299"
                                                    label="CS299"
                                                    value="CS299"
                                                    onChange={() =>  handleChange()
                                                    }
                                                />

                                                <Form.Check
                                                    type="checkbox"
                                                    name="group1"
                                                    label="CS399"
                                                    value="CS399"
                                                    id="check-course-399"
                                                    onChange={() => handleChange()}
                                                />
                                            </Form.Group>
                                            { isNewStudentSubmitted && <Alert dismissible>New student succesfully submitted</Alert>}

                                            <Button disabled = {!isCourseChecked }variant="primary" onClick={() => {fetchNewStudent(axiosPrivate, newStudent); setIsNewStudentSubmitted(true);}}> Add New Student </Button>
                                        </Accordion.Body>
                                    </Accordion.Item>
                                </Accordion>
                            </div>
                        )
                    })()}
                    <div className="standaloneCard" id="standartStudentsList">
                        <StudentsTable students={displayedStudents} userType={props.userType} reports={props.reports} />
                    </div>
                </Card.Body>
            </Card>
        </div>
    )
}
