package com.securePassword.secPassGen;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.securePassword.secPassGen.configuration.Configuration;
import com.securePassword.secPassGen.configuration.ConfigurationImpl;
import com.securePassword.secPassGen.display.Terminal;
import com.securePassword.secPassGen.display.TerminalImpl;
import com.securePassword.secPassGen.generator.PasswordGenerator;
import com.securePassword.secPassGen.generator.PasswordGeneratorImpl;
import com.securePassword.secPassGen.generator.RandomCharacterGenerator;
import com.securePassword.secPassGen.generator.RandomCharacterGeneratorImpl;
import com.securePassword.secPassGen.processor.ArgumentProcessor;
import com.securePassword.secPassGen.processor.ArgumentProcessorImpl;
import com.securePassword.secPassGen.saver.PasswordRecordSaver;
import com.securePassword.secPassGen.shuffler.StringShuffler;
import com.securePassword.secPassGen.shuffler.StringShufflerImpl;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Configuration.class).to(ConfigurationImpl.class).in(Scopes.SINGLETON);
        bind(ArgumentProcessor.class).to(ArgumentProcessorImpl.class);
        bind(PasswordGenerator.class).to(PasswordGeneratorImpl.class);
        bind(RandomCharacterGenerator.class).to(RandomCharacterGeneratorImpl.class);
        bind(StringShuffler.class).to(StringShufflerImpl.class);
        bind(Terminal.class).to(TerminalImpl.class);
        bind(PasswordRecordSaver.class);
        bind(App.class).to(AppImpl.class);
    }
}
