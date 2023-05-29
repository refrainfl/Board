package simpleboard.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simpleboard.board.domain.Member;
import simpleboard.board.repository.MemberRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    @Autowired
    MemberRepository memberRepository;


    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        Member save = memberRepository.save(member);
        return save.getMemberId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMembers = memberRepository.findByLoginId(member.getLoginId());
        if (!findMembers.isEmpty()) throw new NoSuchElementException("이미 존재하는 회원입니다.");
    }
}
