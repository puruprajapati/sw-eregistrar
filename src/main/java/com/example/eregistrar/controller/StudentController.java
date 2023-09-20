package com.example.eregistrar.controller;

import org.springframework.ui.Model;
import com.example.eregistrar.domain.Student;
import com.example.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
  @Autowired
  private StudentService studentService;

  @GetMapping(value = {"/", "/eregistrar", "/eregistrar/home"})
  public String displayHomePage(){
    return "student/index";
  }

  @GetMapping("/students/search")
  public String searchStudents(@ModelAttribute("keyword") String keyword, Model model){
    List<Student> students = studentService.searchStudents(keyword);

    if(students.isEmpty()){
      model.addAttribute("message","No students found for the given search");
    }else {
      model.addAttribute("students", students);
    }
    return "student/list";
  }

  @GetMapping("/students/list")
  public String listStudents(Model model){
    List<Student> students = studentService.getAllStudents();
    model.addAttribute("students", students);
    return "student/list";
  }

  @GetMapping("/students/add")
  public String showAddStudent(Model model){
    model.addAttribute("student", new Student());
    return "student/add";
  }

  @PostMapping("/students/add")
  public String addStudent(@ModelAttribute("students")Student student){
    studentService.addStudent(student);
    return "redirect:/students/list";
  }

  @GetMapping("/students/edit/{id}")
  public String showEditForm(@PathVariable("id") Long id, Model model){
    Student student = studentService.getStudentById(id);
    model.addAttribute("student", student);
    return "student/edit";
  }

  @PostMapping("/students/edit/{id}")
  public String editStudent(@PathVariable("id") Long id, @ModelAttribute("student") Student student ){
    studentService.updateStudent(id,student);
    return "redirect:/students/list";
  }

  @GetMapping("/students/delete/{id}")
  public String deleteStudent(@PathVariable("id") Long id){
    studentService.deleteStudent(id);
    return "redirect:/students/list";
  }
}
