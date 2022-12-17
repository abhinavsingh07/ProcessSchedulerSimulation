/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processschedulersimulation;

/**
 *
 * @author Hp
 */
public class Process {

    private int Id;
    private int priority;
    private int duration;
    private int arrivalTime;
    private int atTime;
    private boolean isExecuting;
    private int runTimeLeft;

    public Process() {
    }

    public Process(int Id, int priority, int duration, int arrivalTime) {
        this.Id = Id;
        this.priority = priority;
        this.duration = duration;
        this.arrivalTime = arrivalTime;
        this.atTime = arrivalTime;
        this.runTimeLeft=duration;
    }

    public int getRunTimeLeft() {
        return runTimeLeft;
    }

    public void setRunTimeLeft(int runTimeLeft) {
        this.runTimeLeft = runTimeLeft;
    }

    public boolean isIsExecuting() {
        return isExecuting;
    }

    public void setIsExecuting(boolean isExecuting) {
        this.isExecuting = isExecuting;
    }

    public int getAtTime() {
        return atTime;
    }

    public void setAtTime(int atTime) {
        this.atTime = atTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Id=" + Id + ", priority=" + priority + ", duration=" + duration + ", arrivalTime=" + arrivalTime + ", atTime=" + atTime + ", isExecuting=" + isExecuting + ", runTimeLeft=" + runTimeLeft+"\n" ;
    }

  
  
}
