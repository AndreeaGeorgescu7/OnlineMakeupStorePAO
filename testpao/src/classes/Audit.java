package classes;

import exceptions.InvalidDataExc;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Audit {
    public static void writeAuditCSV(String action) throws IOException, InvalidDataExc {
        Path path = Paths.get("Logs.csv");

        try {
            BufferedWriter br = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            br.write("\n" + action + "," + new Date());
            br.close();
        } catch (FileNotFoundException e) {
            throw new InvalidDataExc("Fisierul Logs nu a fost gasit");
        } catch (IOException e) {
            throw new InvalidDataExc("Eroare la citire", e);
        }
    }
}
