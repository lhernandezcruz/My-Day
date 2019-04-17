package myday.configuration;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * Fixes bug where I was being redirected to wrong page 
	 * Fix in future with login page and handler. 
	 */
	@Override
	public void configure(WebSecurity security){
			security.ignoring().antMatchers("/css/**","/images/**","/js/**","/img/**");
	}

	/**
	 * Security configurations.
	 * /login, /userinfo, /webjars/**, /error are accesible by everyone
	 */
	@Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .antMatchers("/", "/login**", "/userinfo", "/webjars/**", "/error**")
        .permitAll()
      .anyRequest()
				.authenticated()
				.and().logout().logoutSuccessUrl("/").permitAll()
				.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
  }
}

