package mds.uvod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController

public class StudentsController {

    @GetMapping("student")
    public String st(@RequestParam(defaultValue = "Pavol Repa") String name, @RequestParam(defaultValue = "230632") String id) {
        return "Student: <b>" + name + "</b> ID: <b>" + id + "</b>";
    }

    @GetMapping("students")
    public String sts(@RequestParam(required = false) Integer vutid) {
        if (vutid == null) {


            //if (Student.students.size() == 0) {

            Student.setStudents();
            StringBuilder strStuds = new StringBuilder();
            for (Student s : Student.students) {
                strStuds.append(s.toString()).append("<br>");
            }
            return strStuds.toString();
        }

        Optional<Student> result = Student.students.stream().filter(item -> item.getId() == vutid).findFirst();
        if (result.isEmpty()) {
            return "Student s ID: " + vutid + " neexistuje!";
        }
        return result.get().toString();
    }



}