package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //데이터 변경은 트랜젝션 안에서 이루어 진다.
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    //    @Autowired //의존성 주입 이것도 되는데
    //    private  MemberRepository memberRepository; <- 테스트 할 때 이게 변경이 안된다

    private final MemberRepository memberRepository;


//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository= memberRepository;
//    }

  /* 회원 가입*/
    @Transactional // 쓰기도 있어서 트랜젝션을 넣어서 기본값 false로 해주기
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    @Transactional(readOnly = true) //읽기에는 이 어노테이션을 넣어서 좀 더 최적화를 함
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findMember(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
