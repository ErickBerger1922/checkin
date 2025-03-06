import HeaderLink from '../HeaderLink'
import style from './header.module.css'

export default function Header() {

    return (
        <header className={`${style.header} bg-dark`}>
            <nav>
                <HeaderLink url="./">
                    Home
                </HeaderLink>
                <HeaderLink url="./cadastro">
                    Cadastro
                </HeaderLink>
                <HeaderLink url="./login">
                    Login
                </HeaderLink>
            </nav>
        </header>)

}