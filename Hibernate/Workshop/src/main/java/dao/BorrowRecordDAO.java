package dao;

import java.time.LocalDate;
import java.util.List;

import entity.BorrowRecord;
import entity.BorrowStatus;

public interface BorrowRecordDAO extends GenericDAO<BorrowRecord,Long>{

	 List<BorrowRecord> findByMemberId(Long memberId);
	    List<BorrowRecord> findByBookId(Long bookId);
	    List<BorrowRecord> findByStatus(BorrowStatus status);
	    List<BorrowRecord> findOverdueBooks();
	    List<BorrowRecord> findByDateRange(LocalDate startDate, LocalDate endDate);
	    long countActiveBorrowsByMember(Long memberId);

}
