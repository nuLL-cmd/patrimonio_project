package br.com.fujioka.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.fujioka.service.UserAuthService;


/**
 * @author Marco Aurleio
 * Classe de configuração do framework SpriingSecurity que implementa WebSecurityConfigurerAdapter (exigido pelo framework).
 * A classe possui algumas configurações como Cors, rotas publicas para acesso por usuarios não logados configurada para metodos GET, 
 * A classe deve ser anotada com:
 * @EnableWebSecurity informando ao spring que a segurança da aplicação esta habilitada, 
 * @Configuration  informa ao spring que esta é uma classe de configuração.
 */
@EnableWebSecurity
@Configuration
public class ConfigSpringSecurity extends WebSecurityConfigurerAdapter {


    @Autowired 
    private Environment env;


    @Autowired
    private UserAuthService userDetailsService;

    @Autowired
    private ConfigJWT security;


    private static final String[] ROTAS_PUBLICAS_GET ={
        "/produtos/**",
        "/patrimonios/**",
        "/funcionarios/**"
        
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * Configuração para usar o H2 como exceção na segurança do spring
         */
        if(Arrays.asList(env.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }

        http.cors().and().csrf().disable(); //Segurança csrf desabilitada, pois não sera gravado sessão do usuario.

        //Configurações gerais do http
        http.authorizeRequests()
        .antMatchers(HttpMethod.GET, ROTAS_PUBLICAS_GET).permitAll()
        .anyRequest()
        .authenticated();
        http.addFilter(new JWTAuthenticatorFilter(authenticationManager(), security));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
    /**
     * Metodo sobrposto que configura o AhtenticationManager 
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * Bean de cofniguração de Cors, verificar a documentação 
     * 
     * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/cors/CorsConfiguration.html
     * 
     * https://docs.spring.io/springsecurity/site/docs/current/apidocs/org/springframework/security/config/annotation/web/builders/HttpSecurity.html

     * @return
     */
    @Bean
    CorsConfigurationSource conrsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

        return source;
    }

    /**
     * Bean referente a configuração da encriptação de senha, sera codificada ao gravar no banco, e decodificada cas seja utilizada.
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
