package com.example.toukapp;

import com.example.toukapp.repositories.MovieRepository;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

import javax.swing.text.html.parser.Parser;
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
            int room = 1;
            int id = 1;
            int number = 1;
            while (room < 4) {
//                var room = stm.execute("select * from rooms where roomID = " + r + ";")
                for (int row = 1; row < 4; row += 1) {
                    for (int j = 0; j < 5; j += 1) {

                        String insertText = "insert into seats values (" + Integer.toString(id) + ", 0, " + Integer.toString(number) + ", " + Integer.toString(row) + ", " + Integer.toString(room) + ");";
                        stm.executeUpdate(insertText);
                        id += 1;
                        number += 1;
                    }
                }
                number = 1;
                room += 1;
            }

            System.out.println("Database filled with data");

        } catch (SQLException err) {
            System.out.println("Error occurred: " + err);
        }
    }
}
