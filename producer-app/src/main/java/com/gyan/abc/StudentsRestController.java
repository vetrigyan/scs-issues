package com.gyan.abc;

import com.gyan.abc.school.v1.Student.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentsRestController
{
    @Autowired
    Producer studentsPublisher;

    @RequestMapping("/publishStudent")
    public void sendNotification()
    {
            StudentInfo.Builder builder = StudentInfo.newBuilder();
            builder.setId(123);
            builder.setName("vetrigyan");
            studentsPublisher.publishStudent(builder.build());
    }
}
