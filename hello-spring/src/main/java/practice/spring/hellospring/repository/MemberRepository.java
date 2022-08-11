package practice.spring.hellospring.repository;

import practice.spring.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

//회원 객체 저장하는 저장소
public interface MemberRepository {
    //회원 저장시, 저장된 회원 반환
    Member save(Member member);

    //
    //(시스템에서 지정한) id로 회원 찾는 기능
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
