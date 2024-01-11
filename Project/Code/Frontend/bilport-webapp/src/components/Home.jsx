import { useNavigate, Link, Navigate } from "react-router-dom";
import useLogout from "../hooks/useLogout";
import useAuth from "../hooks/useAuth";
import { useState } from "react";

const Home = () => {
    const navigate = useNavigate();
    const logout = useLogout();

    const { auth } = useAuth();
    const [userRole] = useState( auth.roles[0].toLowerCase().slice(5) );
    console.log(userRole);

    const signOut = async () => {
        await logout();    
    }

    return (
        <section>
            <h1 className="text-white">You are logged in!</h1>
            <br />
            <p className="text-white">You are being directed to your page</p>
            <br />
            <Navigate to={`/${userRole}`} />
            {/** 
            <Link to="/editor">Go to the Editor page</Link>
            <br />
            <Link to="/admin">Go to the Admin page</Link>
            <br />
            <Link to="/user">Go to the Student page</Link>
            <br />
            <Link to="/evaluator">Go to the Evaluator page</Link>
            <br />
            <Link to="/supervisor">Go to the Supervisor page</Link>
            <br />
            <Link to="/ta">Go to the TA page</Link>
            <br />
            <Link to="/superadmin">Go to the Superadmin page</Link>
            <br />
            <Link to="/linkpage">Go to the link page</Link>
            <div className="flexGrow">
                <button onClick={signOut}>Sign Out</button>
            </div>
            */}
        </section>
    )
}

export default Home