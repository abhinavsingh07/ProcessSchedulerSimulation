/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processschedulersimulation;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Hp
 */
public class ProcessScheduler {

    private int maxWaitTime;
    private static int START_TIME = 0;
    Queue<Process> processPriorityQ;

    public ProcessScheduler(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
        processPriorityQ = new PriorityQueue<>(new ProcessComparatorPriority());
    }

    /**
     * startProcess simulates Process scheduling
     *
     * @param processList contains all Process in sorted order of Arrival Time.
     */
    public void startProcess(List<Process> processList) {

        //Whole Process executing in this loop
        for (int unitTime = START_TIME; unitTime > -1; unitTime++) {

            //if processList length >0 means there is atleast one process in list
            if (processList.size() > 0) {
                int arrivalTime = processList.get(0).getArrivalTime();
                //comapring arrivalTime of process in list with current unitTime
                if (arrivalTime <= unitTime) {
                    Process p = processList.get(0);
                    //Add process to Priority queue (Q)
                    processPriorityQ.add(p);
                    //Remove Process from List (D)
                    processList.remove(0);
                }
            }
            //if Priority Queue has Processes
            if (processPriorityQ.size() > 0) {
                //Take out top Process using peek method of Queue. It Doesn't remove the element from queue
                Process p = processPriorityQ.peek();

                if (!p.isIsExecuting()) {
                    ProcessUtil.printActivity("Now running Process id = " + p.getId());
                    ProcessUtil.printActivity("Arrival = " + p.getArrivalTime());
                    ProcessUtil.printActivity("Duration = " + p.getDuration());
                    ProcessUtil.printActivity("Run time left = " + p.getRunTimeLeft());
                    ProcessUtil.printActivity(" at time " + unitTime);
                }

                //call method    
                setStatusAndTimeProcess(p, unitTime);
                //decrement process duration
                int pProcessDuration = p.getRunTimeLeft()- 1;
                p.setRunTimeLeft(pProcessDuration);

                ProcessUtil.printActivity("Executed process ID:" + p.getId() + ", at time " + unitTime + " Remaining:" + p.getRunTimeLeft());
                //if process duration equals to zero remove from PriorityQ.
                if (p.getRunTimeLeft() == 0) {
                    processPriorityQ.remove(p);
                    ProcessUtil.printActivity("Finished running Process id = " + p.getId());
                    ProcessUtil.printActivity("Arrival = " + p.getArrivalTime());
                    ProcessUtil.printActivity("Duration = " + p.getDuration());
                    ProcessUtil.printActivity("Run time left = " + p.getRunTimeLeft());
                    ProcessUtil.printActivity(" at time = " + unitTime);
                }

                //call method
                UpdateProcessPriority(unitTime);

            }

            if (processPriorityQ.size() == 0 && processList.size() == 0) {
                ProcessUtil.printActivity("Finished running all processes at time " + unitTime,true);
                break;
            }

            ProcessUtil.sleepThread(100);
        }

    }

    /**
     * setStatusAndTimeProcess sets IsExecuting flag and setAtTime of process
     * obj. This method helps in managing which process is currently executing
     * and which are not currently executing.
     *
     * @param process
     * @param unitTime
     */
    private void setStatusAndTimeProcess(Process process, int unitTime) {

        Iterator<Process> itr = processPriorityQ.iterator();
        //looping over priorityQ
        while (itr.hasNext()) {
            Process pr = itr.next();
            pr.setIsExecuting(false);

            //if peek process id is equal to process id in priorityQ means this process is picked up from priorityQ updates its IsExecuting to true and rest proess are false
            if (process.getId() == pr.getId()) {
                process.setIsExecuting(true);
            }

            if (pr.isIsExecuting()) {
                //this helps to determine at what time process exited from processing.
                pr.setAtTime(unitTime);
            }

        }
    }

    /**
     * UpdateProcessPriority This method updating priority of all processes
     * according to their wait time.
     *
     * @param unitTime
     */
    private void UpdateProcessPriority(int unitTime) {

        Iterator<Process> itr = processPriorityQ.iterator();
        //Created temp priorityQ to insert process back to PriorityQ with decremented priority.
        Queue<Process> tempPQ = new PriorityQueue<>(new ProcessComparatorPriority());
        //looping over priorityQ
        while (itr.hasNext()) {
            Process pr = itr.next();
            //ProcessUtil.printActivity("Process in Priority Queue::`" + pr);
            //if Process is not executing we calculate wait time for those process.
            if (!pr.isIsExecuting()) {
                //main logic helping in determing process wait time
                int prWaitTime = unitTime - pr.getAtTime();
                //if wait time equals maxWaitTime(30 for now )
                if (prWaitTime == maxWaitTime) {
                    //decrement the priority
                    pr.setPriority(pr.getPriority() - 1);
                    //imp to set 
                    pr.setAtTime(unitTime);
                    //print message
                    ProcessUtil.printActivity("Process " + pr.getId() + " reached maximum wait time... decreasing priority to " + pr.getPriority());
                }
            }
            //Add process to new temp PriorityQ
            tempPQ.add(pr);

        }
        //clear our MainPriorityQ
        processPriorityQ.clear();
        //Add all elements from tempPriorityQ to MainPriorityQ.
        processPriorityQ.addAll(tempPQ);
    }

}
