# ProcessSchedulerSimulation

This project is an implementation of a small simulation program. It is a simulation of a process scheduler of a computer system. This simulated scheduler is a very small, simplified version, which reflects some of the basic operations of a typical process scheduler.

# The following describes the scheduling system that is simulated:

**Processes arrive at a computer system and the computer system choose which process to execute at each time point based on the process’s priority. Each process has a process id, priority, arrival time, and duration. The duration of a process is the amount of time it takes to completely execute the process. The system keeps a priority queue to keep arriving processes and prioritize the execution of processes. When a process arrives, it is inserted into the priority queue. Then, at each timestep the system removes a process with the smallest priority from the priority queue and executes it for a single timestep.
Suppose that a process p with a very large priority arrives at the system while the system is executing another process. While p is waiting in the queue, another process q with a relatively small priority arrives. After the execution of the current process is finished, the system will remove and execute q (because q has a smaller priority), and p must wait until q finishes. If another process with a smaller priority arrives while q is being executed, p will have to wait again after q is completed. If this is repeated, p will have to wait for a very long time. One way of preventing this is as follows: If a process has waited longer than a predetermined maximum wait time, we update the priority of the process by decreasing the priority by one. For example, if the current priority of the process is 5, then it is updated to 4.**

**An input file stores information about all processes. The name of the input file is process_scheduling_input.txt. A sample input file is shown below**

![image](https://user-images.githubusercontent.com/67514678/208240861-5606ae2f-29b4-471a-beec-9130e21711de.png)

**Sample Output**

![image](https://user-images.githubusercontent.com/67514678/208240965-55e46328-8b18-4f3a-ad96-38a3aaff2c09.png)

![image](https://user-images.githubusercontent.com/67514678/208240981-ebacf44a-7b0b-416e-8375-d12284fee7fa.png)

![image](https://user-images.githubusercontent.com/67514678/208241025-53f55025-305f-4127-a87e-9c8262d04f6d.png)


