package kr.co.seolsoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
    @Bean
    public PasswordEncoder passwordEncoder(){
        //암호화해서 평문과 비교는 할 수 있지만 복호화는 할 수 없는 클래스의 인스턴스를 생성해서 리턴
        return new BCryptPasswordEncoder();
    }
    
    /*
     * //설정 메서드 - Jpa를 사용하면 주석 처리
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //메모리에 유저 생성
        auth.inMemoryAuthentication().withUser("user1")
                					 .password("$2a$10$GwhJD3yy./.r265/ADE.9OWdTs3LFsqZF/pSfLywCYa3c3g/reA1O") //1111
                					 .roles("USER");
    }
    */
    
    //인가 설정 메서드 오버라이딩
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/home/all").permitAll() //로그인 여부 와 상관없이 접근 가능
                			    .antMatchers("/home/member").hasRole("USER"); //USER 권한이 있어야 만 접근 가능
        
        
        //커스텀 로그인 페이지
        /*
        http.formLogin()
        	.loginPage("/customlogin") //사용자가 작성한 로그인 페이지 출력
        	.loginProcessingUrl("/login"); //로그인 처리 URL 직접 설정
        */
        
        //권한이 없는 경우 로그인 페이지로 이동
        http.formLogin();
        
        //CSRF 토큰 비교하는 작업을 수행하지 않음
        http.csrf().disable();
        
        http.oauth2Login();

    }

}
