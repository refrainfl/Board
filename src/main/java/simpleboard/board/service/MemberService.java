package simpleboard.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simpleboard.board.domain.Member;
import simpleboard.board.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        Member save = memberRepository.save(member);
        return save.getMemberId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByLoginId(member.getLoginId());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");

        }
    }

    public Long login(Member member) {
        List<Member> members = memberRepository.findByLoginIdAndPassword(member.getLoginId(), member.getPassword());
        if (members.isEmpty() || members.size() > 1) {
            throw new IllegalStateException("존재하지 않는 회원입니다." + members.size());
        }
        return members.get(0).getMemberId();
    }

}
