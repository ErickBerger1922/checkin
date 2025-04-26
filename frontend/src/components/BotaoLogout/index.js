import { useDispatch } from "react-redux";
import { logout } from "../../redux/authSlice";

export default function BotaoLogout() {
    const dispatch = useDispatch();
    return (
        <button type="button" className="btn btn-light" 
        onClick={(e) => {
            e.preventDefault();
           dispatch(logout());
        }} >
            Sair
            </button>
    )
}