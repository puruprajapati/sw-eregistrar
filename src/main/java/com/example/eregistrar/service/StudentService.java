package com.example.eregistrar.service;

import com.example.eregistrar.domain.Student;
import com.example.eregistrar.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
  @Autowired
  private StudentRepository studentRepository;

  public List<Student> getAllStudents(){
    return studentRepository.findAll();
  }

  public List<Student> searchStudents(String keyword){
    return studentRepository.findByFirstNameContainingOrLastNameContainingOrStudentNumberContaining(keyword, keyword, keyword);
  }

  public void addStudent(Student student){
    Student existingStudent = studentRepository.findByStudentNumber(student.getStudentNumber());
    if(existingStudent!=null){
      //throw new InvalidPropertiesFormatException("Already exists");
    }else {
      studentRepository.save(student);
    }
  }

  public Student getStudentById(Long id){
    return studentRepository.findById(id).orElse(null);
  }

  public void updateStudent(Long id, Student student){
    Student existingStudent = studentRepository.findById(id).orElse(null);
    if(existingStudent != null){
      existingStudent.setFirstName(student.getFirstName());
      existingStudent.setLastName(student.getLastName());
      existingStudent.setCgpa(student.getCgpa());
      existingStudent.setIsInternational(student.getIsInternational());
      existingStudent.setEnrollmentDate(student.getEnrollmentDate());
      existingStudent.setMiddleName(student.getMiddleName());
      studentRepository.save(student);
    }
  }

  public  void deleteStudent(Long id){
    studentRepository.deleteById(id);
  }
}
