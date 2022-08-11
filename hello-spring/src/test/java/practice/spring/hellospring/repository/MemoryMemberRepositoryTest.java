package practice.spring.hellospring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import practice.spring.hellospring.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//다른 곳에서 갖다 쓸게 아니므로 public 써줄 필요 없음
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //0. 각 테스트 후에 repository를 초기화 해주는 메서드 정의 (각 테스트 끝날 때마다 실행)
    @AfterEach
    public void AfterEach(){
        repository.clearStore();
    }

    //1. save기능 동작하는지 테스트
    @Test
    public void save(){
        //main 메서드 사용과 유사함
        Member member = new Member();
        member.setName("hong-gil-dong"); //새로 생성한 맴버 객체에 이름할당

        //리포지토리에 회원 저장
        repository.save(member);
        //저장 테스트 (제대로 들어갔는지 확인) - findById + getId(저장되면 아이디 자동 할당되므로)
        Member result = repository.findById(member.getId()).get();//Optional에서 값 꺼낼때는 get 메서드 이용 가능
        // result와 member가 같은지 확인해 출력 - DB에 정상적으로 저장 됐는지
        //1. 단순 출력
        //System.out.println("result = " + (result == member));
        //2. assertEquals 메서드 이용 (junit 사용)
        //Assertions.assertEquals(member, result);
        //3. assertion (assertj 사용)
        assertThat(result).isEqualTo(member);
    }
    //2. findByName 기능 동작하는지 테스트
    @Test
    public void findByName(){
        //given - spring1, spring2 가 회원 객체로 repository에 저장
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //비교 대상 Optional에서 꺼낸 후 result로 저장
        Member result = repository.findByName("spring1").get();

        //then
        assertThat(result).isEqualTo(member1);
    }
    //3. findAll 기능 동작하는지 테스트
    @Test
    public void findAll(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //저장된 회원 객체 수 == 2명으로 나와야 함
        assertThat(result.size()).isEqualTo(2);

    }
}
