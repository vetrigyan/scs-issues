package com.gyan.abc;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;


public interface StudentsBinding
{
    String STUDENTS = "students-channel" ;

    @Output(STUDENTS)
    SubscribableChannel studentsChannel();

}

