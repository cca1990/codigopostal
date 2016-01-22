package com.migmig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by mramos on 1/22/2016.
 */
public class Bar {
    public static void main(String[] args) {
        List<String> toWrite = new ArrayList<>();
        String fileName = "c:/dev/CP.txt";
        String format = "insert into codigospostales (codigoPostal, colonia, municipio, estado) values(%s, '%s', '%s', '%s');";
        CharsetDecoder dec= StandardCharsets.UTF_8.newDecoder()
                .onMalformedInput(CodingErrorAction.IGNORE);
        System.out.println(new File(fileName).exists());
        try (Stream<String> stream = Files.lines(Paths.get(fileName), Charset.forName("windows-1252"))) {
            stream.forEach(s -> {
                String[] splitted = s.split("\\|");
                toWrite.add(String.format(format, splitted[0],splitted[1], splitted[3], splitted[4]));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.write(Paths.get(fileName+"t"),toWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(toWrite);
    }
}
