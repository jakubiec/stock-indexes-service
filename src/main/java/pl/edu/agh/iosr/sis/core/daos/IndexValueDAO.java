package pl.edu.agh.iosr.sis.core.daos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.agh.iosr.sis.core.entities.IndexValue;
import pl.edu.agh.iosr.sis.core.entities.IndexValueId;

public interface IndexValueDAO extends Repository<IndexValue, IndexValueId> {
	
	@Transactional
	IndexValue save(IndexValue entity);

	@Query("SELECT i FROM IndexValue i WHERE i.symbol = :symbol AND i.valueDate <= :end AND i.valueDate >= :start ")
	List<IndexValue> findInPeriod(@Param("symbol") String symbol,
			@Param("start") Date start, @Param("end") Date end);
	
	List<IndexValue> findBySymbol(@Param("symbol") String symbol);

}
