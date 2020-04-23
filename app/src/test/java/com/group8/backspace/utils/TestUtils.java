package com.group8.backspace.utils;


import com.group8.backspace.application.Main;

import java.io.File;
import java.io.IOException;
//import java.nio.file.Files;

import com.google.common.io.Files;

public class TestUtils {
    private static final File DB_SRC = new File("src/main/assets/db/SC.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC, target);
        Main.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}
