package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = requset.getInputStream();
        String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("msgBody = " + msgBody);
        HelloData helloData = objectMapper.readValue(msgBody, HelloData.class);
        System.out.println("helloData = " + helloData.getUsername());
        System.out.println("helloData = " + helloData.getAge());
    }

}
