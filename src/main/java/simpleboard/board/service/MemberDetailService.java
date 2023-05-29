package simpleboard.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import simpleboard.board.domain.Member;
import simpleboard.board.domain.MemberDetail;
import simpleboard.board.repository.MemberRepository;

import java.util.Optional;

@Service
public class MemberDetailService implements UserDetailsService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> byLoginId = memberRepository.findByLoginId(username);
        if (!byLoginId.isEmpty()) {
            return new MemberDetail(byLoginId.get());
        }
        return null;
    }
}
