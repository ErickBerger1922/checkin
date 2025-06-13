import style from "./HeaderLink.module.css"
import { Link } from "react-router-dom";

export default function HeaderLink({ url, children }) {

    return (
        <Link to={url} className={style.link}>
            {children}
        </Link>
    )
}