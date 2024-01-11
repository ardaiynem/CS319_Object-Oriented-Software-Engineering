import StudentsPage from "../commonComponents/StudentsPage";
// import useAxiosPrivate from "../../hooks/useAxiosPrivate";
// import useAuth from '../../hooks/useAuth';




export default function TAStudents(props) {


    return (
        <div style={{ marginLeft: '250px', padding: '20px' }}>
            <h1 className="bigPageTitle"> Students </h1>
            <StudentsPage userType="ta" students={props.students} />
        </div>
    )
}