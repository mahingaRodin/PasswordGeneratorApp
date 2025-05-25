package com.securePassword.secPassGen.saver;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PasswordRecordSaver {
    private static final String file_name = "my_passwords.txt";

    public void save(String site, String password) {
        Path path = Paths.get(System.getProperty("user.dir")).resolve(file_name);
        String record = String.format("Site/App: %s%nPassword: %s%n--------------------%n", site, password);

        try(FileWriter writer = new FileWriter(path.toFile(), true)) {
            writer.write(record);
        } catch (IOException e) {
            System.out.println("Error saving the password: " + e.getMessage());
        }
    }
}
