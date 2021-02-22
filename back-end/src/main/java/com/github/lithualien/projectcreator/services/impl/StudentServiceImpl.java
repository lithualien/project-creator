package com.github.lithualien.projectcreator.services.impl;

import com.github.lithualien.projectcreator.exceptions.ResourceAlreadyExistsException;
import com.github.lithualien.projectcreator.exceptions.ResourceNotFoundException;
import com.github.lithualien.projectcreator.models.Student;
import com.github.lithualien.projectcreator.repositories.StudentRepository;
import com.github.lithualien.projectcreator.services.StudentService;
import com.github.lithualien.projectcreator.vo.StudentVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final Converter<Student, StudentVO> modelToVo;
    private final Converter<StudentVO, Student> voToModel;

    public StudentServiceImpl(StudentRepository studentRepository, Converter<Student, StudentVO> modelToVo,
                              Converter<StudentVO, Student> voToModel) {
        this.studentRepository = studentRepository;
        this.modelToVo = modelToVo;
        this.voToModel = voToModel;
    }

    @Override
    public List<StudentVO> all() {
        List<Student> studentList = getStudentList(studentRepository.findAll());
        return getStudentVoList(studentList);
    }

    @Override
    public StudentVO findById(Long id) {
        Student student = getStudentById(id);
        return modelToVo.convert(student);
    }

    @Override
    public StudentVO save(StudentVO studentVO) {
        checkIfStudentExists(studentVO.getFirstName(), studentVO.getLastName());
        return saveOrUpdate(null, studentVO);
    }

    @Override
    public StudentVO update(Long id, StudentVO studentVO) {
        checkIfStudentExists(id);
        return saveOrUpdate(id, studentVO);
    }

    @Override
    public void delete(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    private List<Student> getStudentList(Iterable<Student> students) {
        return StreamSupport
                .stream(students.spliterator(), false)
                .collect(Collectors.toList());
    }

    private List<StudentVO> getStudentVoList(List<Student> studentList) {
        return studentList
                .stream()
                .map(modelToVo::convert)
                .collect(Collectors.toList());
    }

    private Student getStudentById(Long id) {
        return studentRepository
                .findById(id)
                .<ResourceNotFoundException> orElseThrow(() -> {
                    log.error("Student with id = " + id + " was not found!");
                    throw new ResourceNotFoundException("Student with id = " + id + " was not found!");
                });
    }

    private void checkIfStudentExists(String firstName, String lastName) {
        if(studentRepository.existsStudentByFirstNameAndLastName(firstName, lastName)) {
            log.error("Student " + firstName + " " + lastName + " already exists.");
            throw new ResourceAlreadyExistsException("Student " + firstName + " " + lastName + " already exists.");
        }
    }

    private void checkIfStudentExists(Long id) {
        if(!studentRepository.existsStudentById(id)) {
            throw new ResourceNotFoundException("Student with id = " + id + " was not found!");
        }
    }

    private StudentVO saveOrUpdate(Long id, StudentVO studentVO) {
        studentVO.setId(id);
        Student student = voToModel.convert(studentVO);
        if(student != null) {
            Student receivedStudent = studentRepository.save(student);
            return modelToVo.convert(receivedStudent);
        } else {
            log.error(getClass() + " saveOrUpdate() method was null after conversion.");
            return null;
        }
    }

}
