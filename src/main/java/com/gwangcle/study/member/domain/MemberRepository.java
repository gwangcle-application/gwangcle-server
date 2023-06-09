package com.gwangcle.study.member.domain;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    List<Member> findAllMembersDesc();

    Optional<Member> findById(Long id);

    void deleteById(Long id);
}
