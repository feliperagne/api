package med.voll.api.infraestrutura.security;

import java.io.IOException;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsuarioRepository;

@Component //PARA DIZER QUE É UM COMPONENTE GENÉRICO
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);   

        if(tokenJWT != null) {
       var subject = tokenService.getSubject(tokenJWT);
       var usuario = repository.findByLogin(subject);

       var authentication = new UsernamePasswordAuthenticationToken(usuario, "", usuario.getAuthorities());

       SecurityContextHolder.getContext().setAuthentication(authentication);
        }
       filterChain.doFilter(request, response); //PARA CHAMAR O PRÓXIMO FILTRO ENQUANTO TER FILTROS PARA SEREM EXECUTADOS
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer", "");
        }
        return null;
    }
    
}
