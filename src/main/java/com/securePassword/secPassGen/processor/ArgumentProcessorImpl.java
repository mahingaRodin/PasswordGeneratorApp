package com.securePassword.secPassGen.processor;

import com.securePassword.secPassGen.configuration.Configuration;
import com.securePassword.secPassGen.display.Terminal;
import com.securePassword.secPassGen.generator.PasswordGenerator;
import com.securePassword.secPassGen.saver.PasswordRecordSaver;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class ArgumentProcessorImpl implements ArgumentProcessor {
    private final Configuration configuration;
    private final PasswordGenerator passwordGenerator;
    private final Terminal terminal;
    private final PasswordRecordSaver passwordRecordSaver;

    @Inject
    public ArgumentProcessorImpl(Configuration configuration, PasswordGenerator passwordGenerator, Terminal terminal, PasswordRecordSaver passwordRecordSaver) {
        this.configuration = configuration;
        this.passwordGenerator = passwordGenerator;
        this.terminal = terminal;
        this.passwordRecordSaver = passwordRecordSaver;
    }


    @Override
    public void process(String[] args) {
        configuration.load(args);

        if (!configuration.validate()) {
            final Properties properties = new Properties();
            try {
                properties.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String usage = "Usage:" + System.lineSeparator() +
                    "java -jar ./" + properties.getProperty("artifactId") + "-" + properties.getProperty("version") + ".jar [OPTIONS]" + System.lineSeparator() +
                    System.lineSeparator() +
                    "Options:" + System.lineSeparator() +
                    "--password-length\tLength of the password, allowing values between 8 and 128 characters.";
            terminal.show(usage);
            return;
        }
        //description
        Scanner scanner = new Scanner(System.in);
        terminal.show("🔒 Secure Password Generator");
        terminal.show("👉🏾 What will the password be used for (e.g., Facebook, Gmail, GitHub)?");
        String site = scanner.nextLine();
        //generate the password
        String password = passwordGenerator.generate();
        //generated the passwrd
        terminal.show("✅ Generated the password: "  + password);
        //sacve to a fle
        passwordRecordSaver.save(site,password);
        terminal.show("🗃️ Saved in 'my_passwords.txt'");
    }
}
