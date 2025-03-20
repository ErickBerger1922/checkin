import HeaderLink from '../HeaderLink';
import BotaoLogout from '../BotaoLogout';  
import style from './header.module.css';

export default function Header() {
    return (
        <header className={`${style.header} bg-dark`}>
            <nav>
                <HeaderLink url="/">Home</HeaderLink>
                <div className={style.dropdown}>
                    <HeaderLink url="/cadastro">Cadastro</HeaderLink>
                    <div className={style.dropdownMenu}>
                        <HeaderLink url="/cadastro/usuario">Usuário</HeaderLink>
                        <HeaderLink url="/cadastro/evento">Evento</HeaderLink>
                    </div>
                </div>
                <HeaderLink url="/login">Faça seu Checkin</HeaderLink>
                <BotaoLogout />  {}
            </nav>
        </header>
    )
}