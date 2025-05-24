package com.securePassword.secPassGen.configuration;

import java.util.Arrays;
import java.util.HashMap;

public class ConfigurationImpl implements Configuration {
    private final HashMap<String,String> appSettings = new HashMap<>();

    @Override
    public void load(String[] args) {
    //reading params from args
        appSettings.computeIfAbsent(Parameter.PASSWORD_LENGTH_PARAMETER_NAME, x-> this.getParameterValueFromArguments(args,x));

        //add default values
        appSettings.putIfAbsent(Parameter.PASSWORD_LENGTH_PARAMETER_NAME, "16");
    }

    @Override
    public String getValue(String key) {
        return this.appSettings.get(key);
    }

    @Override
    public int getValueAsInt(String key) {
        return Integer.parseInt(this.getValue(key));
    }

    @Override
    public boolean validate() {
        if(appSettings.isEmpty()) {
            return false;
        }
         try {
             int passwordLength = getValueAsInt(Parameter.PASSWORD_LENGTH_PARAMETER_NAME);
             return passwordLength >=8 && passwordLength <= 128;
         } catch (Exception e)
         {
             return false;
         }
    }


    private String getParameterValueFromArguments(String[] args, String key) {
        int index = Arrays.asList(args).indexOf(String.format("--%s", key));
        if(index != 1 && index < args.length -1 ) {
            return args[index+1];
        }
        return null;
    }
}
