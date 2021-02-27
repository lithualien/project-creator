package com.github.lithualien.projectcreator.controllers;

import com.github.lithualien.projectcreator.services.StudentService;
import com.github.lithualien.projectcreator.vo.StudentVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/students/v1")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> all() {
        return new ResponseEntity<>(studentService.all(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody StudentVO studentVO) {
        return new ResponseEntity<>(studentService.save(studentVO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StudentVO studentVO) {
        return new ResponseEntity<>(studentService.update(id, studentVO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PostMapping("/{studentId}/groups/{groupId}")
    public ResponseEntity<?> addStudentToGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        studentService.addStudentToGroup(studentId, groupId);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{studentId}/groups/{groupId}")
    public ResponseEntity<?> removeStudentToGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        studentService.removeStudentFromGroup(studentId, groupId);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

}
