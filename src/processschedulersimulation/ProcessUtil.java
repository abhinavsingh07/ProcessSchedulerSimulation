/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processschedulersimulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hp
 */
public class ProcessUtil {

    private static File file;
    private static FileWriter fw;
    private static BufferedWriter bw;

    static {
        String userDir = System.getProperty("user.dir");
        String filePath = userDir + "\\src\\process_scheduling_output.txt";
        file = new File(filePath);
        if(file.exists()){
            file.delete();
            file = new File(filePath);
        }
        // If file doesn't exists, then create it
        if (!file.exists()) {
            try {
                file.createNewFile();
                fw = new FileWriter(file.getAbsoluteFile());
            } catch (IOException ex) {
                System.out.println("Exception caught at static block.."+ex);
            }
        }
    }

    public static void printActivity(String str) {
        try {
           
            fw.write(str+"\n");
            fw.flush();
           // bw.close();

        } catch (Exception ex) {
            System.out.println("Exception at printActivity.."+ex);
        }

        System.out.println(str);
    }
    
    public static void printActivity(String str,boolean closeFile) {
        try {
           fw.write(str+"\n");
           fw.flush();
           if(closeFile){
               fw.close();
           }

        } catch (Exception ex) {
            System.out.println("Exception at printActivity.."+ex+" closeFile::"+closeFile);
        }

        System.out.println(str);
    }

    public static void sleepThread(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException ex) {
            System.out.println("Exception in sleepThread" + ex.getMessage());
        }
    }

    public static List<Process> readFIleAndFetchProcess(String fileName) throws FileNotFoundException, Exception {
        List<Process> processList = new ArrayList<>();

        String userDir = System.getProperty("user.dir");
        String filePath = userDir + "\\src\\" + fileName;
        // pass the path to the file as a parameter
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] processStr = sc.nextLine().split(" ");
            if (processStr.length != 4) {
                throw new Exception("Incorrect format file");
            }

            int id = Integer.parseInt(processStr[0]);
            int priority = Integer.parseInt(processStr[1]);
            int duration = Integer.parseInt(processStr[2]);
            int arrivalTime = Integer.parseInt(processStr[3]);
            Process p = new Process(id, priority, duration, arrivalTime);
            processList.add(p);
        }
        return processList;
    }

}
