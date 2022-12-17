/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processschedulersimulation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Hp
 */
public class ProcessSchedulerSimulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, Exception {
        String fileName="process_scheduling_input.txt";
        int maxWaitTime=30;
        List<Process> processList = ProcessUtil.readFIleAndFetchProcess(fileName);
        Collections.sort(processList, new ProcessComparatorArrival());
        ProcessUtil.printActivity("// print all processes first");
        processList.stream().forEach(process-> {
        ProcessUtil.printActivity("Id = "+process.getId()+", priority = "+process.getPriority()+", duration = "+process.getDuration()+", arrival time = "+process.getArrivalTime());
        });
        ProcessUtil.printActivity("Maximum wait time ="+maxWaitTime);
        ProcessScheduler ps = new ProcessScheduler(maxWaitTime);
        ps.startProcess(processList);
       
    }

}
