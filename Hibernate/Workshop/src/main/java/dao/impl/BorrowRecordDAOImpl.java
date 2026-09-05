package dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.BorrowRecordDAO;
import entity.BorrowRecord;
import entity.BorrowStatus;

public class BorrowRecordDAOImpl extends GenericDAOImpl<BorrowRecord,Long> implements BorrowRecordDAO{

private static final Logger logger = LoggerFactory.getLogger(BorrowRecordDAOImpl.class);
    
    @Override
    public List<BorrowRecord> findByMemberId(Long memberId) {
        Session session = null;
        try {
            session = openSession();
            
            String hql = "FROM BorrowRecord br WHERE br.member.memberId = :memberId ORDER BY br.borrowDate DESC";
            Query<BorrowRecord> query = session.createQuery(hql, BorrowRecord.class);
            query.setParameter("memberId", memberId);
            
            List<BorrowRecord> records = query.getResultList();
            logger.debug("Found {} borrow records for member ID: {}", records.size(), memberId);
            return records;
            
        } catch (Exception e) {
            logger.error("Error finding borrow records by member ID: {}", memberId, e);
            throw new RuntimeException("Failed to find borrow records by member", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public List<BorrowRecord> findByBookId(Long bookId) {
        Session session = null;
        try {
            session = openSession();
            
            String hql = "FROM BorrowRecord br WHERE br.book.bookId = :bookId ORDER BY br.borrowDate DESC";
            Query<BorrowRecord> query = session.createQuery(hql, BorrowRecord.class);
            query.setParameter("bookId", bookId);
            
            List<BorrowRecord> records = query.getResultList();
            logger.debug("Found {} borrow records for book ID: {}", records.size(), bookId);
            return records;
            
        } catch (Exception e) {
            logger.error("Error finding borrow records by book ID: {}", bookId, e);
            throw new RuntimeException("Failed to find borrow records by book", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public List<BorrowRecord> findByStatus(BorrowStatus status) {
        Session session = null;
        try {
            session = openSession();
            
            String hql = "FROM BorrowRecord br WHERE br.status = :status ORDER BY br.borrowDate DESC";
            Query<BorrowRecord> query = session.createQuery(hql, BorrowRecord.class);
            query.setParameter("status", status);
            
            List<BorrowRecord> records = query.getResultList();
            logger.debug("Found {} borrow records with status: {}", records.size(), status);
            return records;
            
        } catch (Exception e) {
            logger.error("Error finding borrow records by status: {}", status, e);
            throw new RuntimeException("Failed to find borrow records by status", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public List<BorrowRecord> findOverdueBooks() {
        Session session = null;
        try {
            session = openSession();
            
            String hql = "FROM BorrowRecord br WHERE br.status = :status AND br.dueDate < :currentDate";
            Query<BorrowRecord> query = session.createQuery(hql, BorrowRecord.class);
            query.setParameter("status", BorrowStatus.ACTIVE);
            query.setParameter("currentDate", LocalDate.now());
            
            List<BorrowRecord> records = query.getResultList();
            logger.debug("Found {} overdue books", records.size());
            return records;
            
        } catch (Exception e) {
            logger.error("Error finding overdue books", e);
            throw new RuntimeException("Failed to find overdue books", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public List<BorrowRecord> findByDateRange(LocalDate startDate, LocalDate endDate) {
        Session session = null;
        try {
            session = openSession();
            
            String hql = "FROM BorrowRecord br WHERE br.borrowDate BETWEEN :startDate AND :endDate ORDER BY br.borrowDate DESC";
            Query<BorrowRecord> query = session.createQuery(hql, BorrowRecord.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            
            List<BorrowRecord> records = query.getResultList();
            logger.debug("Found {} borrow records between {} and {}", records.size(), startDate, endDate);
            return records;
            
        } catch (Exception e) {
            logger.error("Error finding borrow records by date range: {} - {}", startDate, endDate, e);
            throw new RuntimeException("Failed to find borrow records by date range", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public long countActiveBorrowsByMember(Long memberId) {
        Session session = null;
        try {
            session = openSession();
            
            String hql = "SELECT COUNT(br) FROM BorrowRecord br WHERE br.member.memberId = :memberId AND br.status = :status";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("memberId", memberId);
            query.setParameter("status", BorrowStatus.ACTIVE);
            
            Long count = query.getSingleResult();
            logger.debug("Found {} active borrows for member ID: {}", count, memberId);
            return count;
            
        } catch (Exception e) {
            logger.error("Error counting active borrows for member ID: {}", memberId, e);
            throw new RuntimeException("Failed to count active borrows", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
