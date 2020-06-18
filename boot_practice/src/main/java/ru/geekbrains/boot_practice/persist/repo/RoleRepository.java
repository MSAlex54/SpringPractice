package ru.geekbrains.boot_practice.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.boot_practice.persist.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
