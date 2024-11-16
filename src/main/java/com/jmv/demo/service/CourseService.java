package com.jmv.demo.service;

import com.jmv.demo.binding.Course;

import java.util.List;

public interface CourseService {

    public String upsert(Course course); //It will use to create and update a source. A Course for our example

    public Course getById(Long id);

    public List<Course> getAllCourses();

    public String deleteById(Long id);

}
