package mds.uvod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@SpringBootApplication
public class UvodApplication {

	public static void main(String[] args) {
		SpringApplication.run(UvodApplication.class, args);
	}
		@GetMapping
		@ResponseBody
	public String hello() {
		return "Hello World";
		}

	ArrayList<Student> students = new ArrayList<>();
}
