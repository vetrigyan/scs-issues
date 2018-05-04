package com.gyan.abc;

import com.google.protobuf.MessageLite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.gyan.abc.school.v1.Student.StudentInfo;

@EnableBinding(StudentsBinding.class)
//@Component
public class Producer
{

    @Autowired
    StudentsBinding students;


    public void publishStudent(MessageLite message)
    {
        students.studentsChannel().send(MessageBuilder.withPayload(message.toByteArray()).setHeader(MessageHeaders.CONTENT_TYPE, "application/protobuf").build());
    }


    @Bean("StudentConverter")
    public MessageConverter studentMessageConverter()
    {
        return new StudentProtoMessageConverter(StudentInfo.parser(), StudentInfo.class);
    }

}
