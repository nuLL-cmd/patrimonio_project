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




//Classe de configuração de segurança que herda de WebSecurityConfigureAdapter
@EnableWebSecurity //Informa ao spring que a segurança para a aplicação está habilitada.
@Configuration //Informa ao spring que esta é uma classe de configuração.
public class SecuritySpring extends WebSecurityConfigurerAdapter {

    /*
        Dependencia para captar o ambiente em que esta rodando a aplicação
        pois no caso de uso onde se esta usando o h2 como banco de dados para teste e desenvovl

    */

    @Autowired 
    private Environment env;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityJWT security;

    /*
        Rotas que serão denominadas como publicas, ou seja, não ha a necessidade de autenticação por parte do usuario
        um exemplo disso é a rota de autenticação, mas em um caso em que haja um catalago de produtos por exemplo, seria possivel visualizar

        Sera reconsiderada apenas para a autenticação e e fuguramente para ambiente de teste, pois um novo array de rotas publicas apenas para metodo GET sera criado.
    */ 
    private static final String[] ROTAS_PUBLICAS ={
        "/auth/**",
        
      
    };

     /*
        Rotas que serão denominadas como publicas apenas para metodos do tipo GET, ou seja, não ha a necessidade de autenticação por parte do usuario.
        Neste caso de uso, sera apenas como exemplo.
    */ 
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

        http.cors().and().csrf().disable();
        http.authorizeRequests()
        .antMatchers(HttpMethod.GET, ROTAS_PUBLICAS_GET).permitAll()
        .antMatchers(ROTAS_PUBLICAS).permitAll()
        .anyRequest()
        .authenticated();
        http.addFilter(new JWTAuthenticatorFilter(authenticationManager(), security));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

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
