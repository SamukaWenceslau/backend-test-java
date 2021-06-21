package br.com.fcamara.parkingproject.config.security;

import br.com.fcamara.parkingproject.model.Company;
import br.com.fcamara.parkingproject.repository.CompanyRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationWithTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private CompanyRepository repository;

    public AuthenticationWithTokenFilter(TokenService tokenService, CompanyRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = retrieveToken(request);
        Boolean isValid = tokenService.isValidToken(token);

        if (isValid) {
            clientAuthenticate(token);
        }

        filterChain.doFilter(request, response);
    }

    private void clientAuthenticate(String token) {
        Long companyId = tokenService.getCompanyId(token);

        Company company = repository.findById(companyId).get();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
                (company, null, company.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    private String retrieveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
