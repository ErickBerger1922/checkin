import HeaderLink from '../HeaderLink';
import BotaoLogout from '../BotaoLogout';  
import style from './header.module.css';

export default function Header({ usuario }) {
    return (
        <header className={`${style.header} bg-dark`}>
            <div className="p-3 text-center">
                <img src="https://checkoutonline.com.br/imagens/logo.png"
                alt="Logo"
                className="img-fluid md-2"
                style={{maxWidth:"120px"}}>
                </img>
            </div>
            <nav>
                <HeaderLink url="/">Home</HeaderLink>
                {usuario.administrador ? (
                    <>
                        <HeaderLink url="/cadastroevento">Cadastro de Evento</HeaderLink>
                        <HeaderLink url="/listacheckin">Lista de Checkin</HeaderLink>
                    </>
                ) : (
                    <HeaderLink url="/checkin">Faça seu Checkin</HeaderLink>
                )}
                <BotaoLogout />
            </nav>
        </header>
    );
}