package practice.spring.hellospring.repository;

import org.springframework.stereotype.Repository;
import practice.spring.hellospring.domain.Member;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    //메모리이므로 데이터를 저장하기 위해 Map을 사용 (key=id, value=Member 객체)
    private static Map<Long, Member> store = new HashMap<>();
    // key 값을 생성해 줌(0, 1, 2, ...)
    private static long sequence = 0L;

    //@Override
    //member save시 작동 method
    public Member save(Member member) {
        //맴버 save 시(=id 생성) sequence 값 먼저 +1 해준 다음 id set
        member.setId(++sequence);
        //store = Map객체
        store.put(member.getId(), member);
        //store에 value로 id값 세팅한(+ name은 객체에 담겨 넘어온 상태) Member 객체 저장
        //key값은 id로 객체 안에 포함된 id와 동일하다 (key값 = id = sequence값)
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //store에서 id 꺼내서 반환
        //null이 반환될 경우 고려 -> Optional로 감쌈
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //.values() = 해당 map의 value 목록을 list 형태로 반환
                .filter(member -> member.getName().equals(name))
                //파라미터로 받아온 member 객체의 name이 findByName의 파라미터인 name과 동일한 경우만 필터링 -> 반환
                .findAny(); //위 작업을 병렬처리 시 먼저 찾아진 요소(member 객체) 반환 (+Optional로 반환)
    }

    @Override
    public List<Member> findAll() { //store은 Map으로 선언 되었지만 반환은 List로
        return new ArrayList<>(store.values()); //store에 저장된 memeber 객체들이 ArrayList로 반환
    }

    public void clearStore(){
        store.clear();
    }
}
