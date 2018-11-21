package pl.robert.myproject.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsername(String name);

    Admin findByPassword(String password);
}
