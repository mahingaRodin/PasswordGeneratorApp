package com.securePassword.secPassGen.shuffler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringShufflerImpl implements StringShuffler {
    @Override
    public String shuffle(String characters) {
        List<String> list = Arrays.asList(characters.split(""));
        Collections.shuffle(list);
        return String.join("", list);
    }
}
