package com.practice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practice.entity.Student;
import com.practice.studentrepo.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {
 private final StudentRepository studentRepository;

 public StudentService(StudentRepository studentRepository) {
     this.studentRepository = studentRepository;
 }

 public List<Student> getAllStudents() {
     return studentRepository.findAll();
 }

 public Optional<Student> getStudentById(Long id) {
     return studentRepository.findById(id);
 }

 public Student createStudent(Student student) {
     return studentRepository.save(student);
 }

 public Student updateStudent(Long id, Student updatedStudent) {
     if (!studentRepository.existsById(id)) {
         throw new EntityNotFoundException("Student with ID " + id + " not found.");
     }
     updatedStudent.setId(id);
     return studentRepository.save(updatedStudent);
 }

 public void deleteStudent(Long id) {
     studentRepository.deleteById(id);
 }
}
