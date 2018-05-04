package com.gyan.abc;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gyan.abc.school.v1.Student.StudentInfo;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
import java.util.HashMap;

@EnableBinding(Sink.class)
@Configuration
public class Consumer
{

    @Bean
    public StudentProtoMessageConverter studentMessageConverter()
    {
        return new StudentProtoMessageConverter(StudentInfo.parser(),
                StudentInfo.class);
    }

    @StreamListener(Sink.INPUT)
    public void receivedStudent(StudentInfo studentInfo) {
        System.out.println("\n\n+++++++++++++++GOT SOMETHING from producer ++++++++++++" + studentInfo + "\n\n");
    }

}
