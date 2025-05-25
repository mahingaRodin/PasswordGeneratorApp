package com.securePassword.secPassGen;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.securePassword.secPassGen.processor.ArgumentProcessor;
import jakarta.inject.Inject;

public class AppImpl implements App{
//main application class
    private final ArgumentProcessor argumentProcessor;

    @Inject
    public AppImpl(ArgumentProcessor argumentProcessor) {
        this.argumentProcessor = argumentProcessor;
    }

    public static void main(String[] args) {
        Injector in = Guice.createInjector(new GuiceModule());
        App app = in.getInstance(App.class);
        app.start(args);
    }

    @Override
    public void start(String[] args) {
    argumentProcessor.process(args);
    }

}
