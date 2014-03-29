package pl.edu.agh.iosr.sis.core.daos;

import java.util.List;

import org.springframework.data.repository.Repository;

import pl.edu.agh.iosr.sis.core.entities.Index;

public interface IndexDAO extends Repository<Index, String> {

	public List<Index> findAll();
}
