import Login from './components/Login'
import Home from './components/Home';
import Layout from './components/Layout';
import Admin from './components/Admin';
import User from './components/User';
import Missing from './components/Missing';
import Unauthorized from './components/Unauthorized';
import LinkPage from './components/LinkPage';
import RequireAuth from './components/RequireAuth';
import PersistLogin from './components/PersistLogin';
import LoginRoute from './components/LoginRoute';
import { Routes, Route } from 'react-router-dom';
import Evaluator from './components/Evaluator';
import Superadmin from './components/Superadmin';
import Supervisor from './components/Supervisor';
import TeachingAssistant from './components/TeachingAssistant';

const ROLES = {
  'User': "ROLE_USER",
  'Admin': "ROLE_ADMIN",
  'Student': "ROLE_STUDENT",
  'Evaluator': "ROLE_EVALUATOR",
  'Ta': "ROLE_TA",
  'Supervisor': "ROLE_SUPERVISOR",
  'Superadmin': "ROLE_SUPERADMIN",
}

function App() {


  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        {/* public routes */}

        <Route element={<LoginRoute />}>
          <Route path="login" element={<Login />} />
        </Route>

        <Route path="linkpage" element={<LinkPage />} />
        <Route path="unauthorized" element={<Unauthorized />} />

        {/* we want to protect these routes */}
        <Route element={<PersistLogin />}>
          <Route element={<RequireAuth allowedRoles={[ROLES.Admin, ROLES.Student, ROLES.Evaluator, ROLES.Ta, ROLES.Supervisor, ROLES.Superadmin]} />}>
            <Route path="/" element={<Home />} />
          </Route>

          <Route element={<RequireAuth allowedRoles={[ROLES.Student]} />}>
            <Route path="student/*" element={<User />}></Route>
          </Route>

          <Route element={<RequireAuth allowedRoles={[ROLES.Evaluator]} />}>
            <Route path="evaluator/*" element={<Evaluator />}></Route>
          </Route>

          <Route element={<RequireAuth allowedRoles={[ROLES.Ta]} />}>
            <Route path="ta/*" element={<TeachingAssistant />}></Route>
          </Route>

          <Route element={<RequireAuth allowedRoles={[ROLES.Admin]} />}>
            <Route path="admin/*" element={<Admin />}></Route>
          </Route>

          <Route element={<RequireAuth allowedRoles={[ROLES.Superadmin]} />}>
            <Route path="superadmin/*" element={<Superadmin />}></Route>
          </Route>

          <Route element={<RequireAuth allowedRoles={[ROLES.Supervisor]} />}>
            <Route path="supervisor/*" element={<Supervisor />}></Route>
          </Route>
        </Route>

        {/* catch all */}
        <Route path="*" element={<Missing />} />
      </Route>
    </Routes>
  );
}

export default App
