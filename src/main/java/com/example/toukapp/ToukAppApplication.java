package com.example.toukapp;

import com.example.toukapp.repositories.MovieRepository;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

import java.io.IOException;
import java.sql.*;

@SpringBootApplication
public class ToukAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToukAppApplication.class, args);
        String url = "jdbc:h2:mem:toukdbtest";
        String user = "touk";
        String passwd = "";
        try {
            Connection con = DriverManager.getConnection(url, user, passwd);
            Statement stm = con.createStatement();
            stm.executeUpdate("insert into movies (MovieID, Title) values (1, 'Taken');");
            stm.executeUpdate("insert into movies (MovieID, Title) values (2, 'Matrix');");
            stm.executeUpdate("insert into movies (MovieID, Title) values (3, 'ĄŻĆŚŹŁ');");
            stm.executeUpdate("insert into rooms (RoomID, Name) values (1, 'Room 1');");
            stm.executeUpdate("insert into rooms (RoomID, Name) values (2, 'Room 2');");
            stm.executeUpdate("insert into rooms (RoomID, Name) values (3, 'Room 3');");
            stm.executeUpdate("insert into screenings values (1, '2022-03-01', '10:00:00', 1, 2);");
            stm.executeUpdate("insert into screenings values (2, '2022-03-01', '14:00:00', 2, 2);");
            stm.executeUpdate("insert into screenings values (3, '2022-03-01', '13:00:00', 3, 1);");
            stm.executeUpdate("insert into screenings values (4, '2022-03-01', '19:00:00', 1, 1);");
            stm.executeUpdate("insert into screenings values (5, '2022-03-02', '12:30:00', 2, 3);");
            stm.executeUpdate("insert into screenings values (6, '2022-03-02', '17:30:00', 3, 3);");
            System.out.println("Rows inserted");

        } catch (SQLException err) {
            System.out.println("Error occurred: " + err);
        }
//        try {
//            System.in.read();
//        } catch (IOException err) {
//            System.out.println("Error occurred: " + err);
//        }
    }
}
