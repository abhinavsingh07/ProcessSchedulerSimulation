# ProcessSchedulerSimulation

CS526 O2
Project Assignment
This project is an implementation of a small simulation program. It is a simulation of a process scheduler of a computer system. This simulated scheduler is a very small, simplified version, which reflects some of the basic operations of a typical process scheduler.
To successfully finish the program, students are expected to:
• Study and learn how to use PriorityQueues
• Study and learn how to write a small simulation program.
Name the file with the main method ProcessScheduling.java. You may define other classes as needed in separate files.
The following describes the scheduling system that is simulated:
Processes arrive at a computer system and the computer system choose which process to execute at each time point based on the process’s priority. Each process has a process id, priority, arrival time, and duration. The duration of a process is the amount of time it takes to completely execute the process. The system keeps a priority queue to keep arriving processes and prioritize the execution of processes. When a process arrives, it is inserted into the priority queue. Then, at each timestep the system removes a process with the smallest priority from the priority queue and executes it for a single timestep.
Suppose that a process p with a very large priority arrives at the system while the system is executing another process. While p is waiting in the queue, another process q with a relatively small priority arrives. After the execution of the current process is finished, the system will remove and execute q (because q has a smaller priority), and p must wait until q finishes. If another process with a smaller priority arrives while q is being executed, p will have to wait again after q is completed. If this is repeated, p will have to wait for a very long time. One way of preventing this is as follows: If a process has waited longer than a predetermined maximum wait time, we update the priority of the process by decreasing the priority by one. For example, if the current priority of the process is 5, then it is updated to 4.
For the purpose of this simulation, we assume the following:
• We use a modified version of the HeapAdaptablePriorityQueue given by the textbook
o Please use the version of net.datastructures given in the project code package and not the net.datastructures package that you used in other assignments.
• Each entry in the priority queue keeps a process object, which represents a process.
• Each process must have, at the minimum, the following attributes:
id: integer // process id
arrivalTime: integer // the time when the process arrives at the system duration: integer // execution of the process takes this amount of time
The simulation program uses a logical time to keep track of the simulation process and the same logical time is used to represent the arrivalTime and duration. The simulation goes through a series of iterations and each iteration represents the passage of one logical time unit (in what follows we will use time to refer to logical time unit). At the beginning, the current time is set to time 0. Each iteration implements what occurs during one time unit and, at the end of each iteration, the current time is incremented.
The following describes the general behavior of the simulation program:
• All processes are stored in a certain data structure D, which is supposed to be external to the computer system.
• In each iteration (one time unit), the following occurs (not necessarily in the given order):
• We compare the current time with the arrival time of a process with the earliest arrival time in D. If the arrival time of that process is equal to or smaller than the current time, we remove the process from D and insert it into the adaptable priority queue Q (this represents the arrival of a process at the system).
• If there is at least one process in Q, then the process with the smallest priority in Q is executed. If multiple processes have the same priority, then Java will arbitrarily decide which process to execute.
• The wait time of a process p is the amount of time p has waited in Q. It is calculated by subtracting the arrival time of p from the time when p is removed from Q. For example, if the arrival time of p is 12 and p is removed from Q at time 20, then the wait time is 20 – 12 = 8.
• Update the priorities of some processes. The update is performed as follows. If there is any process in Q that has been waiting longer than the predetermined maximum wait time, then the priority of that process is decreased by one. You may need an additional data structure to help make updates (since changing the priority of a Process will affect the order of Processes in the PriorityQueue).
• The current time is increased by one time unit.
• The above is repeated until D and Q are empty. At this time, all processes have arrived at the system. Some of them may have been completed and removed from Q and some may still wait in Q.
A pseudocode of the simulation is given below. Note that this pseudocode is a high-level description, and you must determine implementation details and convert the pseudocode to a Java program. In the pseudocode, Q is a priority queue.
Read all processes from an input file and store them in an appropriate data structure, D
Initialize currentTime
create an empty priority queue Q
// Each iteration of the while loop represents what occurs during one time unit
// Must increment currentTime in each iteration
While Q and D are not empty // while loop runs once for every time unit until Q is empty
If D is not empty
Get (don’t remove) a process p from D that has the earliest arrival time
If the arrival time of p <= currentTime
Remove p from D and insert it into Q
If Q is not empty
Execute the top process in Q for one time step
Update the wait times of all processes in Q
Update priorities of processes that have been waiting longer than max. wait time
End of While loop
Calculate average wait time
An input file stores information about all processes. The name of the input file is process_scheduling_input.txt. A sample input file is shown below (this sample input is posted on Blackboard):
1 4 25 10
2 3 15 17
3 1 17 26
4 4 9 17 30
5 10 9 40
6 6 14 47
7 7 7 18 52
8 5 18 70
9 2 16 93
10 8 20 125
Each line in the input file represents a process and it has four integers separated by a space(s). The four integers are the process id, priority, duration, and arrival time, respectively, of a process. Your program must read all processes from the input file and store them in an appropriate data structure. You can use any data structure that you think is appropriate.
While your program is performing the simulation, whenever a different process is being executed, your program must display information about the process that stopped execution and the process that started execution. Your program also needs to print other information as shown in the sample output below. After your program finishes the simulation of the execution of all
processes, it must display the average waiting time of all processes. A sample output is shown below, which is the expected output for the above sample input.
Note that:
• This output was generated using 30 as the maximum wait time. A different maximum wait time will result in a different output.
• Your output may not be the same as the one shown below. This is because when two processes have the same smallest priority and wait time, the choice is made by Java arbitrarily when a process is removed from the queue.
// print all processes first
Id = 1, priority = 4, duration = 25, arrival time = 10
Id = 2, priority = 3, duration = 15, arrival time = 17
Id = 3, priority = 1, duration = 17, arrival time = 26 Id = 4, priority = 9, duration = 17, arrival time = 30
Id = 5, priority = 10, duration = 9, arrival time = 40
Id = 6, priority = 6, duration = 14, arrival time = 47
Id = 7, priority = 7, duration = 18, arrival time = 52
Id = 8, priority = 5, duration = 18, arrival time = 70
Id = 9, priority = 2, duration = 16, arrival time = 93
Id = 10, priority = 8, duration = 20, arrival time = 125
Maximum wait time = 30
Now running Process id = 1
Arrival = 10
Duration = 25
Run time left = 25
at time 10
Executed process ID:1, at time 10 Remaining: 24
Executed process ID:1, at time 11 Remaining: 23
Executed process ID:1, at time 12 Remaining: 22
Executed process ID:1, at time 13 Remaining: 21
Executed process ID:1, at time 14 Remaining: 20
Executed process ID:1, at time 15 Remaining: 19
Executed process ID:1, at time 16 Remaining: 18
Now running Process id = 2
Arrival = 17
Duration = 15
Run time left = 15
at time 17
Executed process ID:2, at time 17 Remaining: 14
Executed process ID:2, at time 18 Remaining: 13
Executed process ID:2, at time 19 Remaining: 12
Executed process ID:2, at time 20 Remaining: 11
Executed process ID:2, at time 21 Remaining: 10
Executed process ID:2, at time 22 Remaining: 9
Executed process ID:2, at time 23 Remaining: 8
Executed process ID:2, at time 24 Remaining: 7
Executed process ID:2, at time 25 Remaining: 6
Now running Process id = 3
Arrival = 26
Duration = 17
Run time left = 17
at time 26
Executed process ID:3, at time 26 Remaining: 16
Executed process ID:3, at time 27 Remaining: 15
Executed process ID:3, at time 28 Remaining: 14
Executed process ID:3, at time 29 Remaining: 13
Executed process ID:3, at time 30 Remaining: 12
Executed process ID:3, at time 31 Remaining: 11
Executed process ID:3, at time 32 Remaining: 10
Executed process ID:3, at time 33 Remaining: 9
Executed process ID:3, at time 34 Remaining: 8
Executed process ID:3, at time 35 Remaining: 7
Executed process ID:3, at time 36 Remaining: 6
Executed process ID:3, at time 37 Remaining: 5
Executed process ID:3, at time 38 Remaining: 4
Executed process ID:3, at time 39 Remaining: 3
Executed process ID:3, at time 40 Remaining: 2
Executed process ID:3, at time 41 Remaining: 1
Executed process ID:3, at time 42 Remaining: 0
Finished running Process id = 3
Arrival = 26
Duration = 17
Run time left = 0
at time 42
Now running Process id = 2
Arrival = 17
Duration = 15
Run time left = 6
at time 43
Executed process ID:2, at time 43 Remaining: 5
Executed process ID:2, at time 44 Remaining: 4
Executed process ID:2, at time 45 Remaining: 3
Executed process ID:2, at time 46 Remaining: 2
Process 1 reached maximum wait time... decreasing priority to 3
Executed process ID:2, at time 47 Remaining: 1
Executed process ID:2, at time 48 Remaining: 0
Finished running Process id = 2
Arrival = 17
Duration = 15
Run time left = 0
at time 48
Now running Process id = 1
Arrival = 10
Duration = 25
Run time left = 18
at time 49
Executed process ID:1, at time 49 Remaining: 17
Executed process ID:1, at time 50 Remaining: 16
Executed process ID:1, at time 51 Remaining: 15
Executed process ID:1, at time 52 Remaining: 14
Executed process ID:1, at time 53 Remaining: 13
Executed process ID:1, at time 54 Remaining: 12
Executed process ID:1, at time 55 Remaining: 11
Executed process ID:1, at time 56 Remaining: 10
Executed process ID:1, at time 57 Remaining: 9
Executed process ID:1, at time 58 Remaining: 8
Executed process ID:1, at time 59 Remaining: 7
Process 4 reached maximum wait time... decreasing priority to 8
Executed process ID:1, at time 60 Remaining: 6
Executed process ID:1, at time 61 Remaining: 5
Executed process ID:1, at time 62 Remaining: 4
Executed process ID:1, at time 63 Remaining: 3
Executed process ID:1, at time 64 Remaining: 2
Executed process ID:1, at time 65 Remaining: 1
Executed process ID:1, at time 66 Remaining: 0
Finished running Process id = 1
Arrival = 10
Duration = 25
Run time left = 0
at time 66
Now running Process id = 6
Arrival = 47
Duration = 14
Run time left = 14
at time 67
Executed process ID:6, at time 67 Remaining: 13
Executed process ID:6, at time 68 Remaining: 12
Executed process ID:6, at time 69 Remaining: 11
Process 5 reached maximum wait time... decreasing priority to 9
Now running Process id = 8
Arrival = 70
Duration = 18
Run time left = 18
at time 70
Executed process ID:8, at time 70 Remaining: 17
Executed process ID:8, at time 71 Remaining: 16
Executed process ID:8, at time 72 Remaining: 15
Executed process ID:8, at time 73 Remaining: 14
Executed process ID:8, at time 74 Remaining: 13
Executed process ID:8, at time 75 Remaining: 12
Executed process ID:8, at time 76 Remaining: 11
Executed process ID:8, at time 77 Remaining: 10
Executed process ID:8, at time 78 Remaining: 9
Executed process ID:8, at time 79 Remaining: 8
Process 6 reached maximum wait time... decreasing priority to 5
Executed process ID:8, at time 80 Remaining: 7
Executed process ID:8, at time 81 Remaining: 6
Process 7 reached maximum wait time... decreasing priority to 6
Executed process ID:8, at time 82 Remaining: 5
Executed process ID:8, at time 83 Remaining: 4
Executed process ID:8, at time 84 Remaining: 3
Executed process ID:8, at time 85 Remaining: 2
Executed process ID:8, at time 86 Remaining: 1
Executed process ID:8, at time 87 Remaining: 0
Finished running Process id = 8
Arrival = 70
Duration = 18
Run time left = 0
at time 87
Now running Process id = 6
Arrival = 47
Duration = 14
Run time left = 11
at time 88
Executed process ID:6, at time 88 Remaining: 10
Executed process ID:6, at time 89 Remaining: 9
Process 4 reached maximum wait time... decreasing priority to 7
Executed process ID:6, at time 90 Remaining: 8
Executed process ID:6, at time 91 Remaining: 7
Executed process ID:6, at time 92 Remaining: 6
Now running Process id = 9
Arrival = 93
Duration = 16
Run time left = 16
at time 93
Executed process ID:9, at time 93 Remaining: 15
Executed process ID:9, at time 94 Remaining: 14
Executed process ID:9, at time 95 Remaining: 13
Executed process ID:9, at time 96 Remaining: 12
Executed process ID:9, at time 97 Remaining: 11
Executed process ID:9, at time 98 Remaining: 10
Executed process ID:9, at time 99 Remaining: 9
Process 5 reached maximum wait time... decreasing priority to 8
Executed process ID:9, at time 100 Remaining: 8
Executed process ID:9, at time 101 Remaining: 7
Executed process ID:9, at time 102 Remaining: 6
Executed process ID:9, at time 103 Remaining: 5
Executed process ID:9, at time 104 Remaining: 4
Executed process ID:9, at time 105 Remaining: 3
Executed process ID:9, at time 106 Remaining: 2
Executed process ID:9, at time 107 Remaining: 1
Executed process ID:9, at time 108 Remaining: 0
Finished running Process id = 9
Arrival = 93
Duration = 16
Run time left = 0
at time 108
Now running Process id = 6
Arrival = 47
Duration = 14
Run time left = 6
at time 109
Executed process ID:6, at time 109 Remaining: 5
Executed process ID:6, at time 110 Remaining: 4
Executed process ID:6, at time 111 Remaining: 3
Process 7 reached maximum wait time... decreasing priority to 5
Executed process ID:6, at time 112 Remaining: 2
Executed process ID:6, at time 113 Remaining: 1
Executed process ID:6, at time 114 Remaining: 0
Finished running Process id = 6
Arrival = 47
Duration = 14
Run time left = 0
at time 114
Now running Process id = 7
Arrival = 52
Duration = 18
Run time left = 18
at time 115
Executed process ID:7, at time 115 Remaining: 17
Executed process ID:7, at time 116 Remaining: 16
Executed process ID:7, at time 117 Remaining: 15
Executed process ID:7, at time 118 Remaining: 14
Executed process ID:7, at time 119 Remaining: 13
Process 4 reached maximum wait time... decreasing priority to 6
Executed process ID:7, at time 120 Remaining: 12
Executed process ID:7, at time 121 Remaining: 11
Executed process ID:7, at time 122 Remaining: 10
Executed process ID:7, at time 123 Remaining: 9
Executed process ID:7, at time 124 Remaining: 8
Executed process ID:7, at time 125 Remaining: 7
Executed process ID:7, at time 126 Remaining: 6
Executed process ID:7, at time 127 Remaining: 5
Executed process ID:7, at time 128 Remaining: 4
Executed process ID:7, at time 129 Remaining: 3
Process 5 reached maximum wait time... decreasing priority to 7
Executed process ID:7, at time 130 Remaining: 2
Executed process ID:7, at time 131 Remaining: 1
Executed process ID:7, at time 132 Remaining: 0
Finished running Process id = 7
Arrival = 52
Duration = 18
Run time left = 0
at time 132
Now running Process id = 4
Arrival = 30
Duration = 17
Run time left = 17
at time 133
Executed process ID:4, at time 133 Remaining: 16
Executed process ID:4, at time 134 Remaining: 15
Executed process ID:4, at time 135 Remaining: 14
Executed process ID:4, at time 136 Remaining: 13
Executed process ID:4, at time 137 Remaining: 12
Executed process ID:4, at time 138 Remaining: 11
Executed process ID:4, at time 139 Remaining: 10
Executed process ID:4, at time 140 Remaining: 9
Executed process ID:4, at time 141 Remaining: 8
Executed process ID:4, at time 142 Remaining: 7
Executed process ID:4, at time 143 Remaining: 6
Executed process ID:4, at time 144 Remaining: 5
Executed process ID:4, at time 145 Remaining: 4
Executed process ID:4, at time 146 Remaining: 3
Executed process ID:4, at time 147 Remaining: 2
Executed process ID:4, at time 148 Remaining: 1
Executed process ID:4, at time 149 Remaining: 0
Finished running Process id = 4
Arrival = 30
Duration = 17
Run time left = 0
at time 149
Now running Process id = 5
Arrival = 40
Duration = 9
Run time left = 9
at time 150
Executed process ID:5, at time 150 Remaining: 8
Executed process ID:5, at time 151 Remaining: 7
Executed process ID:5, at time 152 Remaining: 6
Executed process ID:5, at time 153 Remaining: 5
Executed process ID:5, at time 154 Remaining: 4
Process 10 reached maximum wait time... decreasing priority to 7
Executed process ID:5, at time 155 Remaining: 3
Executed process ID:5, at time 156 Remaining: 2
Executed process ID:5, at time 157 Remaining: 1
Executed process ID:5, at time 158 Remaining: 0
Finished running Process id = 5
Arrival = 40
Duration = 9
Run time left = 0
at time 158
Now running Process id = 10
Arrival = 125
Duration = 20
Run time left = 20
at time 159
Executed process ID:10, at time 159 Remaining: 19
Executed process ID:10, at time 160 Remaining: 18
Executed process ID:10, at time 161 Remaining: 17
Executed process ID:10, at time 162 Remaining: 16
Executed process ID:10, at time 163 Remaining: 15
Executed process ID:10, at time 164 Remaining: 14
Executed process ID:10, at time 165 Remaining: 13
Executed process ID:10, at time 166 Remaining: 12
Executed process ID:10, at time 167 Remaining: 11
Executed process ID:10, at time 168 Remaining: 10
Executed process ID:10, at time 169 Remaining: 9
Executed process ID:10, at time 170 Remaining: 8
Executed process ID:10, at time 171 Remaining: 7
Executed process ID:10, at time 172 Remaining: 6
Executed process ID:10, at time 173 Remaining: 5
Executed process ID:10, at time 174 Remaining: 4
Executed process ID:10, at time 175 Remaining: 3
Executed process ID:10, at time 176 Remaining: 2
Executed process ID:10, at time 177 Remaining: 1
Executed process ID:10, at time 178 Remaining: 0
Finished running Process id = 10
Arrival = 125
Duration = 20
Run time left = 0
at time 178
Finished running all processes at time 178
Average wait time: 41.3
A second simple example
1 3 100 1
2 3 100 2
// print all processes first
Id = 1, priority = 3, duration = 100, arrival time = 1
Id = 2, priority = 3, duration = 100, arrival time = 2
Now running Process id = 1
Arrival = 1
Duration = 100
Run time left = 100
at time 1
Executed process ID:1, at time 1 Remaining: 99
Executed process ID:1, at time 2 Remaining: 98
Executed process ID:1, at time 3 Remaining: 97
Executed process ID:1, at time 4 Remaining: 96
Executed process ID:1, at time 5 Remaining: 95
Executed process ID:1, at time 6 Remaining: 94
Executed process ID:1, at time 7 Remaining: 93
Executed process ID:1, at time 8 Remaining: 92
Executed process ID:1, at time 9 Remaining: 91
Executed process ID:1, at time 10 Remaining: 90
Executed process ID:1, at time 11 Remaining: 89
Executed process ID:1, at time 12 Remaining: 88
Executed process ID:1, at time 13 Remaining: 87
Executed process ID:1, at time 14 Remaining: 86
Executed process ID:1, at time 15 Remaining: 85
Executed process ID:1, at time 16 Remaining: 84
Executed process ID:1, at time 17 Remaining: 83
Executed process ID:1, at time 18 Remaining: 82
Executed process ID:1, at time 19 Remaining: 81
Executed process ID:1, at time 20 Remaining: 80
Executed process ID:1, at time 21 Remaining: 79
Executed process ID:1, at time 22 Remaining: 78
Executed process ID:1, at time 23 Remaining: 77
Executed process ID:1, at time 24 Remaining: 76
Executed process ID:1, at time 25 Remaining: 75
Executed process ID:1, at time 26 Remaining: 74
Executed process ID:1, at time 27 Remaining: 73
Executed process ID:1, at time 28 Remaining: 72
Executed process ID:1, at time 29 Remaining: 71
Executed process ID:1, at time 30 Remaining: 70
Executed process ID:1, at time 31 Remaining: 69
Process 2 reached maximum wait time... decreasing priority to 2
Now running Process id = 2
Arrival = 2
Duration = 100
Run time left = 100
at time 32
Executed process ID:2, at time 32 Remaining: 99
Executed process ID:2, at time 33 Remaining: 98
Executed process ID:2, at time 34 Remaining: 97
Executed process ID:2, at time 35 Remaining: 96
Executed process ID:2, at time 36 Remaining: 95
Executed process ID:2, at time 37 Remaining: 94
Executed process ID:2, at time 38 Remaining: 93
Executed process ID:2, at time 39 Remaining: 92
Executed process ID:2, at time 40 Remaining: 91
Executed process ID:2, at time 41 Remaining: 90
Executed process ID:2, at time 42 Remaining: 89
Executed process ID:2, at time 43 Remaining: 88
Executed process ID:2, at time 44 Remaining: 87
Executed process ID:2, at time 45 Remaining: 86
Executed process ID:2, at time 46 Remaining: 85
Executed process ID:2, at time 47 Remaining: 84
Executed process ID:2, at time 48 Remaining: 83
Executed process ID:2, at time 49 Remaining: 82
Executed process ID:2, at time 50 Remaining: 81
Executed process ID:2, at time 51 Remaining: 80
Executed process ID:2, at time 52 Remaining: 79
Executed process ID:2, at time 53 Remaining: 78
Executed process ID:2, at time 54 Remaining: 77
Executed process ID:2, at time 55 Remaining: 76
Executed process ID:2, at time 56 Remaining: 75
Executed process ID:2, at time 57 Remaining: 74
Executed process ID:2, at time 58 Remaining: 73
Executed process ID:2, at time 59 Remaining: 72
Executed process ID:2, at time 60 Remaining: 71
Executed process ID:2, at time 61 Remaining: 70
Process 1 reached maximum wait time... decreasing priority to 2
Executed process ID:2, at time 62 Remaining: 69
Executed process ID:2, at time 63 Remaining: 68
Executed process ID:2, at time 64 Remaining: 67
Executed process ID:2, at time 65 Remaining: 66
Executed process ID:2, at time 66 Remaining: 65
Executed process ID:2, at time 67 Remaining: 64
Executed process ID:2, at time 68 Remaining: 63
Executed process ID:2, at time 69 Remaining: 62
Executed process ID:2, at time 70 Remaining: 61
Executed process ID:2, at time 71 Remaining: 60
Executed process ID:2, at time 72 Remaining: 59
Executed process ID:2, at time 73 Remaining: 58
Executed process ID:2, at time 74 Remaining: 57
Executed process ID:2, at time 75 Remaining: 56
Executed process ID:2, at time 76 Remaining: 55
Executed process ID:2, at time 77 Remaining: 54
Executed process ID:2, at time 78 Remaining: 53
Executed process ID:2, at time 79 Remaining: 52
Executed process ID:2, at time 80 Remaining: 51
Executed process ID:2, at time 81 Remaining: 50
Executed process ID:2, at time 82 Remaining: 49
Executed process ID:2, at time 83 Remaining: 48
Executed process ID:2, at time 84 Remaining: 47
Executed process ID:2, at time 85 Remaining: 46
Executed process ID:2, at time 86 Remaining: 45
Executed process ID:2, at time 87 Remaining: 44
Executed process ID:2, at time 88 Remaining: 43
Executed process ID:2, at time 89 Remaining: 42
Executed process ID:2, at time 90 Remaining: 41
Executed process ID:2, at time 91 Remaining: 40
Process 1 reached maximum wait time... decreasing priority to 1
Now running Process id = 1
Arrival = 1
Duration = 100
Run time left = 69
at time 92
Executed process ID:1, at time 92 Remaining: 68
Executed process ID:1, at time 93 Remaining: 67
Executed process ID:1, at time 94 Remaining: 66
Executed process ID:1, at time 95 Remaining: 65
Executed process ID:1, at time 96 Remaining: 64
Executed process ID:1, at time 97 Remaining: 63
Executed process ID:1, at time 98 Remaining: 62
Executed process ID:1, at time 99 Remaining: 61
Executed process ID:1, at time 100 Remaining: 60
Executed process ID:1, at time 101 Remaining: 59
Executed process ID:1, at time 102 Remaining: 58
Executed process ID:1, at time 103 Remaining: 57
Executed process ID:1, at time 104 Remaining: 56
Executed process ID:1, at time 105 Remaining: 55
Executed process ID:1, at time 106 Remaining: 54
Executed process ID:1, at time 107 Remaining: 53
Executed process ID:1, at time 108 Remaining: 52
Executed process ID:1, at time 109 Remaining: 51
Executed process ID:1, at time 110 Remaining: 50
Executed process ID:1, at time 111 Remaining: 49
Executed process ID:1, at time 112 Remaining: 48
Executed process ID:1, at time 113 Remaining: 47
Executed process ID:1, at time 114 Remaining: 46
Executed process ID:1, at time 115 Remaining: 45
Executed process ID:1, at time 116 Remaining: 44
Executed process ID:1, at time 117 Remaining: 43
Executed process ID:1, at time 118 Remaining: 42
Executed process ID:1, at time 119 Remaining: 41
Executed process ID:1, at time 120 Remaining: 40
Executed process ID:1, at time 121 Remaining: 39
Process 2 reached maximum wait time... decreasing priority to 1
Executed process ID:1, at time 122 Remaining: 38
Executed process ID:1, at time 123 Remaining: 37
Executed process ID:1, at time 124 Remaining: 36
Executed process ID:1, at time 125 Remaining: 35
Executed process ID:1, at time 126 Remaining: 34
Executed process ID:1, at time 127 Remaining: 33
Executed process ID:1, at time 128 Remaining: 32
Executed process ID:1, at time 129 Remaining: 31
Executed process ID:1, at time 130 Remaining: 30
Executed process ID:1, at time 131 Remaining: 29
Executed process ID:1, at time 132 Remaining: 28
Executed process ID:1, at time 133 Remaining: 27
Executed process ID:1, at time 134 Remaining: 26
Executed process ID:1, at time 135 Remaining: 25
Executed process ID:1, at time 136 Remaining: 24
Executed process ID:1, at time 137 Remaining: 23
Executed process ID:1, at time 138 Remaining: 22
Executed process ID:1, at time 139 Remaining: 21
Executed process ID:1, at time 140 Remaining: 20
Executed process ID:1, at time 141 Remaining: 19
Executed process ID:1, at time 142 Remaining: 18
Executed process ID:1, at time 143 Remaining: 17
Executed process ID:1, at time 144 Remaining: 16
Executed process ID:1, at time 145 Remaining: 15
Executed process ID:1, at time 146 Remaining: 14
Executed process ID:1, at time 147 Remaining: 13
Executed process ID:1, at time 148 Remaining: 12
Executed process ID:1, at time 149 Remaining: 11
Executed process ID:1, at time 150 Remaining: 10
Executed process ID:1, at time 151 Remaining: 9
Process 2 reached maximum wait time... decreasing priority to 0
Now running Process id = 2
Arrival = 2
Duration = 100
Run time left = 40
at time 152
Executed process ID:2, at time 152 Remaining: 39
Executed process ID:2, at time 153 Remaining: 38
Executed process ID:2, at time 154 Remaining: 37
Executed process ID:2, at time 155 Remaining: 36
Executed process ID:2, at time 156 Remaining: 35
Executed process ID:2, at time 157 Remaining: 34
Executed process ID:2, at time 158 Remaining: 33
Executed process ID:2, at time 159 Remaining: 32
Executed process ID:2, at time 160 Remaining: 31
Executed process ID:2, at time 161 Remaining: 30
Executed process ID:2, at time 162 Remaining: 29
Executed process ID:2, at time 163 Remaining: 28
Executed process ID:2, at time 164 Remaining: 27
Executed process ID:2, at time 165 Remaining: 26
Executed process ID:2, at time 166 Remaining: 25
Executed process ID:2, at time 167 Remaining: 24
Executed process ID:2, at time 168 Remaining: 23
Executed process ID:2, at time 169 Remaining: 22
Executed process ID:2, at time 170 Remaining: 21
Executed process ID:2, at time 171 Remaining: 20
Executed process ID:2, at time 172 Remaining: 19
Executed process ID:2, at time 173 Remaining: 18
Executed process ID:2, at time 174 Remaining: 17
Executed process ID:2, at time 175 Remaining: 16
Executed process ID:2, at time 176 Remaining: 15
Executed process ID:2, at time 177 Remaining: 14
Executed process ID:2, at time 178 Remaining: 13
Executed process ID:2, at time 179 Remaining: 12
Executed process ID:2, at time 180 Remaining: 11
Executed process ID:2, at time 181 Remaining: 10
Process 1 reached maximum wait time... decreasing priority to 0
Executed process ID:2, at time 182 Remaining: 9
Executed process ID:2, at time 183 Remaining: 8
Executed process ID:2, at time 184 Remaining: 7
Executed process ID:2, at time 185 Remaining: 6
Executed process ID:2, at time 186 Remaining: 5
Executed process ID:2, at time 187 Remaining: 4
Executed process ID:2, at time 188 Remaining: 3
Executed process ID:2, at time 189 Remaining: 2
Executed process ID:2, at time 190 Remaining: 1
Executed process ID:2, at time 191 Remaining: 0
Finished running Process id = 2
Arrival = 2
Duration = 100
Run time left = 0
at time 191
Now running Process id = 1
Arrival = 1
Duration = 100
Run time left = 9
at time 192
Executed process ID:1, at time 192 Remaining: 8
Executed process ID:1, at time 193 Remaining: 7
Executed process ID:1, at time 194 Remaining: 6
Executed process ID:1, at time 195 Remaining: 5
Executed process ID:1, at time 196 Remaining: 4
Executed process ID:1, at time 197 Remaining: 3
Executed process ID:1, at time 198 Remaining: 2
Executed process ID:1, at time 199 Remaining: 1
Executed process ID:1, at time 200 Remaining: 0
Finished running Process id = 1
Arrival = 1
Duration = 100
Run time left = 0
at time 200
Finished running all processes at time 200
Average wait time: 95.0
Your program must write output to an output file named process_scheduling_output.txt.
Documentation
You need to prepare a documentation file, named project_documentation.docx or project_documentation.pdf, that includes the following:
• Discussion:
o Explain your choice for the data structure you used for D in this project
o For processes that had equal priority, it may have been better to execute the process with earlier arrival time instead of choosing arbitrarily. At a high level, how would you have to modify your project to accommodate this?
o What other changes could you consider making to your project to improve efficiency or readability or reusability?
Within the source code of your program, you must include sufficient inline comments within your program. Your inline comments must include a specification of each method in your program. A specification of a method must include at least the following:
• Brief description of what the method does
• Input parameters: Brief description of parameters and their names and types, or none
• Output: Brief description and the type of the return value of the method, or none
Deliverables
You must submit the following files:
• ProcessScheduling.java
• All other files that are necessary to compile and run your program
Combine all files into a single archive file and name it LastName_FirstName_project.EXT, where EXT is an appropriate file extension (such as zip or rar). Upload this file to Blackboard.
Grading
The project is worth 100 points.
Your simulation program will be tested with a test input and points will be deducted as follows:
• If your program does not compile, 60 points will be deducted.
• If your program compiles but causes runtime errors, 50 points will be deducted.
• If processes are not removed (from Q) in the correct order, up to 20 points will be deducted.
• If the removal times (from Q) are incorrect, up to 20 points will be deducted.
• If priorities are not updated correctly, up to 10 points will be deducted.
• If the average wait time is wrong (assuming there is no other error), up to 5 points will be deducted.
• If you do not include the description of data structures in your documentation, up to 10 points will be deducted.
• If the answers to the Discussion questions are not substantive, up to 10 points will be deducted
• If you do not include method specifications or sufficient inline comments, up to 10 points will be deducted.
