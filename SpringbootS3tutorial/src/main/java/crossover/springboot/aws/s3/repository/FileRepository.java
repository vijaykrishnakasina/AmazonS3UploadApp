package crossover.springboot.aws.s3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crossover.springboot.aws.s3.Entity.FileObj;

@Repository
public interface FileRepository extends JpaRepository<FileObj, Long>{

    
}
