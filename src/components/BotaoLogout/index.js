import { UsuarioContext, useUsuarioContext } from "../../contexts/Usuario";

export default function BotaoLogout() {
    const {logout} = useUsuarioContext(UsuarioContext);
    return (
        <button type="button" className="btn btn-light" 
        onClick={(e) => {
            e.preventDefault();
            logout();
        }} >
            Sair
            </button>
    )
}