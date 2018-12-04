package pl.robert.myproject.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);

    User findByEmail(String email);

    User findByPassword(String password);

    List<User> findAll();

    User getById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE users u SET u.id = :newId WHERE u.id = :oldId")
    void updateUserId(@Param("newId") Long newId, @Param("oldId") Long oldId);
}
