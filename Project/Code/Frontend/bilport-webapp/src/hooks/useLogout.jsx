import useAxiosPrivate  from "./useAxiosPrivate";
import useAuth from "./useAuth";

const useLogout = () => {
    const { setAuth } = useAuth();
    const axiosPrivate = useAxiosPrivate();

    const logout = async () => {
        try {
            const response = await axiosPrivate('/auth/signout', {
            });
            setAuth({});
        } catch (err) {
            console.error(err);
        }
    }

    return logout;
}

export default useLogout