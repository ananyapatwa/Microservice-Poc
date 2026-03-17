package com.ananya.poc.repo;
import com.ananya.poc.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<Users, Long> {
}
