package kr.co.seolsoft.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.seolsoft.entity.ClubMember;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String>{
	//이메일을 가지고 조회하는 메서드
	//roleSet 속성은 EAGER로 패치. 나머지는 entity에 명시한 fetch type이나 디폴트 fetchType
    @EntityGraph(attributePaths = {"roleSet"}, type= EntityGraph.EntityGraphType.LOAD)
    @Query("select m from ClubMember m " + "where m.fromSocial = :social and m.email=:email")
    Optional<ClubMember> findByEmail(@Param("email") String email, @Param("social") boolean social);

}
