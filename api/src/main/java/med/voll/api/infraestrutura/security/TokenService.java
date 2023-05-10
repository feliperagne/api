package med.voll.api.infraestrutura.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Voll.Med") //IDENTIFICACAO DO PROJETO
                    .withSubject(usuario.getLogin()) //IDENTIFICACAO do USUARIO QUE ESTÁ LOGADO
                    .withExpiresAt(dataExpericacao())
                    .withClaim("id", usuario.getId())
                    .sign(algoritmo); //VARIAVEL DECLARADA EM CIMA
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao criar o token!", exception);
        }
    }


        private Instant dataExpericacao () {
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
            //FAZER COM QUE O TOKEN DURE APENAS 2 HORAS , PEGANDO A DATA ATUAL E ADICIONANDO 2 HORAS;
        }
    }

