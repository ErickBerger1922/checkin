package backend.backend2.application.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    /*
    @Value("${spring.expiration_time}")
    private Long expirationTime;

    @Value("${spring.secretkey}")
    private String secret;

    @Value("${spring.emissor}")
    private String emissor;

    public String gerarToken(LoginRecord loginRequest){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer(emissor)
                    .withSubject(loginRequest.email())
                    .withExpiresAt(this.gerarDataExpiracao())
                    .sign(algorithm);

            return token;

        } catch (Exception e) {
            return null;
        }
    }

    public DecodedJWT validarToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(emissor).build();

        return verifier.verify(token);
    }

    private Instant gerarDataExpiracao(){
        return LocalDateTime.now().plusMinutes(expirationTime).toInstant(ZoneOffset.of("-03:00"));
    }
     */
}
