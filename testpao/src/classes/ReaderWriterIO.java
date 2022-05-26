package classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.*;
import java.io.*;
import persistence.*;

public class ReaderWriterIO  {
    private static ReaderWriterIO instance = null;
    private static BufferedReader br;
    private static BufferedWriter bw;
    private int lastRead;


    private ReaderWriterIO() {}

    public static List<Client> readClient(String fileName) throws IOException {

        List<Client> clients = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

            BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8);

            String line = br.readLine();
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                String name = fields[0];
                String surname = fields[1];
                char gender = fields[2].charAt(0);
                int age = Integer.parseInt(fields[3]);
                String email= fields[4];
                String phone= fields[5];
                boolean over18=Boolean.parseBoolean(fields[6]);
                int yearsOfFidelity= Integer.parseInt(fields[7]);


                Client cl =  new Client(name, surname, gender, age,null, email, phone, over18, null, yearsOfFidelity, null);
                clients.add(cl);

            }
            br.close();
            return clients;

    }

    public static List<Lips> readLips(String fileName) throws IOException {
        List<Lips> lip = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

           br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8);

            String line = br.readLine();

            while ((line = br.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                String product_name=fields[0];
                String brand=fields[1];
                String valability=fields[2];
                float price=Float.parseFloat(fields[3]);
                String shade=fields[4];
                String type=fields[5];


                Lips l =  new Lips(product_name, brand, valability, price, null, shade, type);
                lip.add(l);
            }
            br.close();
            return lip;

    }

    public static List<Foundation> readFoundation(String fileName) throws IOException {
        List<Foundation> foundations = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);


            br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8);

            String line = br.readLine();

            while ((line = br.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                String product_name=fields[0];
                String brand=fields[1];
                String valability=fields[2];
                float price=Float.parseFloat(fields[3]);
                String foundationShade=fields[4];
                String forTypeOfSkin=fields[5];

                Foundation found = new Foundation(product_name, brand, valability, price, null, foundationShade, forTypeOfSkin);
                foundations.add(found);
            }
            br.close();
            return foundations;

    }

    public static List<Eyeshadow> readEyeshadow(String fileName) throws IOException {
        List<Eyeshadow> eyes = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);


             br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8);

            String line = br.readLine();

            while ((line = br.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                String product_name=fields[0];
                String brand=fields[1];
                String valability=fields[2];
                float price=Float.parseFloat(fields[3]);
                List<String> colors = new ArrayList<String>(Arrays.asList(fields[4].split(" ")));
               // System.out.println("Culori: "+colors);
                Eyeshadow e = new Eyeshadow(product_name, brand, valability, price, null,colors);
                eyes.add(e);
            }

            br.close();
            return eyes;

    }
//    private <T> void readProduct(String fileName, Class<T> classOf) {
//        try {
//
//            Path pathToFile = Paths.get(fileName);
//
//
//            br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8);
//
//            String line = br.readLine();
//
//            while ((line = br.readLine()) != null && !line.isEmpty()) {
//                String[] fields = line.split(",");
//                String product_name=fields[0];
//                String brand=fields[1];
//                String valability=fields[2];
//                float price=Float.parseFloat(fields[3]);
//
//                if (classOf.toString().equals("class classes.Eyeshadow")) {
//                    this.readEyeshadow("EyeshadowCSV");
//                }
//                else if (classOf.toString().equals("class classes.Lips")) {
//                    this.readLips("LipsCSV");
//                }
//                else if (classOf.toString().equals("class classes.Foundation")){
//                    this.readFoundation("FoundationCSV");
//                }
//                else
//                    System.out.println("Eroare la citire");
//
//                line = br.readLine();
//            }
//        }
//        catch (IOException e) {
//
//            e.printStackTrace();
//        }
//    }
    public static void writeClient(String fileName, Client client) throws IOException {
        Path pathToFile = Paths.get(fileName);


             bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            String string = String.format("\n%s,%s,%s,%d",
                    client.getName(),client.getSurname(),client.getEmail(),client.getAge());


            bw.write(string);
            bw.flush();
            bw.close();


    }

    public static void writeLips(String fileName, Lips lip) throws IOException {
        Path pathToFile = Paths.get(fileName);


             bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            String stringToWrite = String.format("\n%s,%s,%f,%s,%s",
                    lip.getProduct_name(),lip.getBrand(),lip.getPrice(),lip.getShade(),lip.getType());

            bw.write(stringToWrite);
            bw.flush();
            bw.close();



    }

    public static void writeFoundation(String fileName, Foundation found) throws IOException {
        Path pathToFile = Paths.get(fileName);


          bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            String stringToWrite = String.format("\n%s,%s,%f,%s,%s",
                    found.getProduct_name(),found.getBrand(),found.getPrice(),found.getFoundationShade(), found.getForTypeOfSkin());

            bw.write(stringToWrite);
            bw.flush();
            bw.close();


    }

    public static void writeEyeshadow(String fileName, Eyeshadow eyes) throws IOException {
        Path pathToFile = Paths.get(fileName);

       bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            String stringToWrite = String.format("\n%s,%s,%f,%s",
                    eyes.getProduct_name(),eyes.getBrand(),eyes.getPrice(),eyes.getColors());


            bw.write(stringToWrite);
            bw.flush();
            bw.close();



    }

    public static <T> void writeProduct(Object product, Class<T> classOf, String path) {

        try {
            BufferedWriter buffer;
            buffer = new BufferedWriter(new FileWriter(path, true));


            if (classOf.toString().equals("class classes.Foundation")) {
                System.out.println("Write foundation");
                buffer.write(((Foundation)product).stringFormat());
            }
            else if (classOf.toString().equals("class classes.Eyeshadow")) {
                System.out.println("Write eyeshadow");
                buffer.write(((Eyeshadow)product).stringFormat());
            }
            else if (classOf.toString().equals("class classes.Lips")){
                System.out.println("Write lipstick");
                buffer.write(((Lips)product).stringFormat());
            }

            buffer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean hasReachedEnd() {
        return lastRead == -1;
    }

    public void close() throws IOException{
        if (bw != null) {
            bw.close();
        }
        if (br != null) {
            br.close();
        }
    }

    public static  ReaderWriterIO getInstance() {
        if (instance == null)
            instance = new ReaderWriterIO();
        return instance;
    }
}