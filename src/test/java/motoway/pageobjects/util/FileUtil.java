package motoway.pageobjects.util;

import motoway.pageobjects.Car;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

    public static void main(String[] args) {
        new FileUtil().readOutputFile();
        new FileUtil().testReadInputFile();
    }

    public List<String> testReadInputFile()
    {
        String filePath = "car_input - V5.txt";
        String regex = "\\b[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        List<String> registrationNumberList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for(String line: lines){

                matcher = pattern.matcher(line);

                // Find and print the registration number
                while (matcher.find()) {
                    String matches = matcher.group();
                    registrationNumberList.add(matches);
                    System.out.println("Found registration number: " + matches);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return registrationNumberList;
    }


    public Map<String, Car> readOutputFile() {
        String filePath = "car_output - V5.txt";
        HashMap<String, Car> map = new HashMap<>();

        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); // Read each line
                if (line.startsWith("VARIANT_REG"))
                {
                    continue;
                }
                String[] split = line.split(",");
                String regNumber = split[0];
                String model = split[1];
                String year = split[2];

                map.put(regNumber, new Car(model, regNumber, year));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
