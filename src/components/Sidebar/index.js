import { Link } from "react-router-dom";
import styles from "./sidebar.module.css"

export default function Sidebar(){

    return (
    <div className={`${styles.sidebar} d-flex flex-column vh-100 bg-dark text-light`}>
            <div className="p-3 text-center">
                <img src="https://www.unesc.net/files/editor/files/logos%20unesc/Logo%20Unesc-05.png"
                alt="Logo"
                className="img-fluid md-2"
                style={{maxWidth:"120px"}}>
                </img>
            </div>
        <ul className="nav flex-column">
            <li className="nav-item">    
                <Link className="nav-link text-light" to="/"> 🏡 Home </Link>
            </li>

            <li className="nav-item text-light">
                <a className="nav-link text-light"
                    data-bs-toggle="collapse"
                    href="#submenuCadastro"
                    role="button"
                    aria-expanded="false"
                    aria-controls="submenuCadastro"
                >
                    📝Cadastro
                </a>

                <ul className="collapse list-unstyled ms-3" id="submenuCadastro">
                    <li>
                        <Link to="/usuario" className="nav-link text-light"> 👤Usuário </Link>
                    </li>
                    <li>
                        <Link to="/evento" className="nav-link text-light">  📍 Evento </Link>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
)

}