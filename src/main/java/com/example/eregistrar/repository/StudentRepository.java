package com.example.eregistrar.repository;

import com.example.eregistrar.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  Student findByStudentNumber(String studentNumber);
  List<Student> findByFirstNameContainingOrLastNameContainingOrStudentNumberContaining(String keyword, String keyword1, String keyword2);
}
