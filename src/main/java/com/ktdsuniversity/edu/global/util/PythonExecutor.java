package com.ktdsuniversity.edu.global.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonExecutor {

    public static String runPython(String scriptPath, String... args) {
        String output = "";
    	try {
            ProcessBuilder pb = new ProcessBuilder("python", scriptPath);
            for (String arg : args) {
                pb.command().add(arg);
            }

            pb.redirectErrorStream(true);
            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[PYTHON] " + line);
                    output = output + line;
                }
            }

            int exitCode = process.waitFor();
            System.out.println("Python script exited with code " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    	return output;
    }
}
