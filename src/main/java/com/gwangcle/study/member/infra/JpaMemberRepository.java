package com.gwangcle.study.member.infra;

import com.gwangcle.study.member.domain.Member;
import com.gwangcle.study.member.domain.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Member save(Member member);

    @Query("select m from Member m order by m.id desc")
    List<Member> findAllMembersDesc();

    @Override
    Optional<Member> findById(Long id);

    @Override
    void deleteById(Long id);
}
