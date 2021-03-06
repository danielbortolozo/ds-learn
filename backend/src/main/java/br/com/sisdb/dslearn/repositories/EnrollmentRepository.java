package br.com.sisdb.dslearn.repositories;

import br.com.sisdb.dslearn.entities.Enrollment;
import br.com.sisdb.dslearn.entities.pk.EnrollmentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentPK> {
}
