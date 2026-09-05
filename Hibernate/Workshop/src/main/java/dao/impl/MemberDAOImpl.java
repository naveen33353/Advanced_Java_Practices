package dao.impl;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.MemberDAO;
import entity.Member;
import entity.MembershipType;

import java.util.List;

public class MemberDAOImpl extends GenericDAOImpl<Member, Long> implements MemberDAO {
    
    private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
    
    @Override
    public Member findByEmail(String email){
        try (Session session = openSession()) {
            Query<Member> query = session.createQuery(
                "FROM Member m WHERE m.email = :email", Member.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            logger.error("Error finding member by email: " + email, e);
            return null;
        }
    
}
    
    @Override
    public List<Member> findActiveMembers() {
        Session session = null;
        try {
            session = openSession();
            
            String hql = "FROM Member m WHERE m.isActive = true ORDER BY m.lastName, m.firstName";
            Query<Member> query = session.createQuery(hql, Member.class);
            
            List<Member> members = query.getResultList();
            logger.debug("Found {} active members", members.size());
            return members;
            
        } catch (Exception e) {
            logger.error("Error finding active members", e);
            throw new RuntimeException("Failed to find active members", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public List<Member> findByMembershipType(MembershipType membershipType) {
        Session session = null;
        try {
            session = openSession();
            
            String hql = "FROM Member m WHERE m.membershipType = :membershipType AND m.isActive = true";
            Query<Member> query = session.createQuery(hql, Member.class);
            query.setParameter("membershipType", membershipType);
            
            List<Member> members = query.getResultList();
            logger.debug("Found {} members with membership type: {}", members.size(), membershipType);
            return members;
            
        } catch (Exception e) {
            logger.error("Error finding members by membership type: {}", membershipType, e);
            throw new RuntimeException("Failed to find members by membership type", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public List<Member> searchMembers(String keyword) {
        Session session = null;
        try {
            session = openSession();
            
            String hql = "FROM Member m WHERE " +
                        "LOWER(m.firstName) LIKE LOWER(:keyword) OR " +
                        "LOWER(m.lastName) LIKE LOWER(:keyword) OR " +
                        "LOWER(m.email) LIKE LOWER(:keyword)";
            
            Query<Member> query = session.createQuery(hql, Member.class);
            query.setParameter("keyword", "%" + keyword + "%");
            
            List<Member> members = query.getResultList();
            logger.debug("Found {} members matching keyword: {}", members.size(), keyword);
            return members;
            
        } catch (Exception e) {
            logger.error("Error searching members with keyword: {}", keyword, e);
            throw new RuntimeException("Failed to search members", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}