package com.practice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.entity.Student;
import com.practice.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
 private final StudentService studentService;

 public StudentController(StudentService studentService) {
     this.studentService = studentService;
 }

 @GetMapping
 public List<Student> getAllStudents() {
     return studentService.getAllStudents();
 }

 @GetMapping("/{id}")
 public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
     Optional<Student> student = studentService.getStudentById(id);
     return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
 }

 @PostMapping
 public ResponseEntity<Student> createStudent(@RequestBody Student student) {
     Student savedStudent = studentService.createStudent(student);
     return ResponseEntity.created(URI.create("/api/students/" + savedStudent.getId())).body(savedStudent);
 }

 @PutMapping("/{id}")
 public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
     Student updated = studentService.updateStudent(id, updatedStudent);
     return ResponseEntity.ok(updated);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
     studentService.deleteStudent(id);
     return ResponseEntity.noContent().build();
 }
}

