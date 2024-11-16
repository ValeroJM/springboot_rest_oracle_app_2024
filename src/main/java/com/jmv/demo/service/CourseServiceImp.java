package com.jmv.demo.service;

import com.jmv.demo.binding.Course;
import com.jmv.demo.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImp implements CourseService{
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public String upsert(Course course) {
        courseRepository.save(course); //upsert (insert / update base on primary key)
        return "Success";
    }

    @Override
    public Course getById(Long id) {
        Optional<Course> findById = courseRepository.findById(id);

        if(findById.isPresent()){
            return findById.get();
        }
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public String deleteById(Long id) {
        if(courseRepository.existsById(id)){
            courseRepository.deleteById(id);
            return "Delete Success";
        }else{
            return "No Record Found";
        }
    }
}
