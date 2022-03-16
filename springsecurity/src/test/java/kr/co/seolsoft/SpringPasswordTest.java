package kr.co.seolsoft;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SpringPasswordTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncoding(){
        String password = "1111";
        //암호화
        String enPw = passwordEncoder.encode(password);

        //출력
        System.out.println("인코딩 된 1111:" + enPw);

        //평문과 비교 - 로그인을 할 때는 이런 형태로 비교
        System.out.println("비교:" + passwordEncoder.matches(password, enPw));
    }
}
