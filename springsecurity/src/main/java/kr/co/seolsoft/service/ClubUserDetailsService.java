package kr.co.seolsoft.service;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.seolsoft.dto.ClubAuthMember;
import kr.co.seolsoft.entity.ClubMember;
import kr.co.seolsoft.repository.ClubMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService {
    private final ClubMemberRepository clubMemberRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadByUserName:" + username);
        //데이터베이스에서 username 에 해당하는 데이터를 찾아오기
        ClubMember clubMember = clubMemberRepository.findByEmail(username, false).get();
        
        //인증을 위한 클래스의 인스턴스를 생성
        ClubAuthMember clubAuthMember = new ClubAuthMember(clubMember.getEmail(),
        												   clubMember.getPassword(),
        												   clubMember.getRoleSet().stream()
        												   						  .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
        												   						  .collect(Collectors.toSet()));
        clubAuthMember.setName(clubMember.getName());
        clubAuthMember.setFromSocial(clubMember.isFromSocial());
        log.info(clubMember);
        log.info(clubAuthMember);
        return clubAuthMember;
    }
}
