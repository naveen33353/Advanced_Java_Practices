package dao;

import java.util.List;

import entity.Book;
import entity.Member;
import entity.MembershipType;

public interface MemberDAO extends GenericDAO<Member, Long> {
    Member findByEmail(String email);
    List<Member> findActiveMembers();
    List<Member> findByMembershipType(MembershipType membershipType);
    List<Member> searchMembers(String keyword);
}
