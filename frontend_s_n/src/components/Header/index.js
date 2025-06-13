import HeaderLink from '../HeaderLink';
import BotaoLogout from '../BotaoLogout';  
import style from './header.module.css';
import { useSelector } from 'react-redux';

export default function Header() {
    const roles = useSelector(state => state.auth.roles || []);

    const isAdmin = roles.includes("ROLE_ADMIN");
    const isEnterprise = roles.includes("ROLE_ENTERPRISE");
    const isUser = roles.includes("ROLE_USER");

    return (
        <header className={`${style.header} bg-dark`}>
            <div className="p-3 text-center">
                <img 
                  src="https://checkoutonline.com.br/imagens/logo.png"
                  alt="Logo"
                  className="img-fluid md-2"
                  style={{ maxWidth: "120px" }}
                />
            </div>
            <nav>
                <HeaderLink url="/">Home</HeaderLink>

                {isAdmin ? (
                    <>
                        <HeaderLink url="/listaeventos">Lista de Eventos</HeaderLink>
                        <HeaderLink url="/cadastroevento">Cadastro de Evento</HeaderLink>
                        <HeaderLink url="/listacheckins">Lista de Checkins</HeaderLink>
                        <HeaderLink url="/checkin">Faça seu Checkin</HeaderLink>
                    </>
                ) : isEnterprise ? (
                    <>
                        <HeaderLink url="/listaeventos">Lista de Eventos</HeaderLink>
                        <HeaderLink url="/cadastroevento">Cadastro de Evento</HeaderLink>
                        <HeaderLink url="/listacheckins">Lista de Checkins</HeaderLink>
                    </>
                ) : isUser ? (
                    <HeaderLink url="/checkin">Faça seu Checkin</HeaderLink>
                ) : null}

                <BotaoLogout />
            </nav>
        </header>
    );
}
