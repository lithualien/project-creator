package com.github.lithualien.projectcreator.controllers;

import com.github.lithualien.projectcreator.services.ProjectService;
import com.github.lithualien.projectcreator.vo.project.ProjectVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/projects/v1")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<?> all() {
        return new ResponseEntity<>(projectService.all(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(projectService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProjectVO projectVO) {
        return new ResponseEntity<>(projectService.save(projectVO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProjectVO projectVO) {
        return new ResponseEntity<>(projectService.update(id, projectVO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        projectService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

}
