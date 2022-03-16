package kr.co.seolsoft.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ClubMember extends BaseEntity{
    @Id
    private String email;
    private String password;
    private String name;
    private boolean fromSocial;
    //권한을 하나만 가지는 경우
    //private ClubMemberRole rols;
    
    //권한을 여러개 가질 수 있는 경우
    @Builder.Default //특정 필드를 특정 값으로 초기화
    @ElementCollection(fetch= FetchType.LAZY) //지연 로딩
    private Set<ClubMemberRole> roleSet = new HashSet<>();
    
    //권한을 추가하는 메서드
    public void addMemberRole(ClubMemberRole clubMemberRole){
        roleSet.add(clubMemberRole);
    }
    
}
