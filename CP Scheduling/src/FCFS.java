import java.util.Scanner;

class Process {
    int pid; // Process ID
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnAroundTime;
    int waitingTime;

    public Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class FCFS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        Process[] processes = new Process[n];
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time and burst time for process " + (i + 1) + ": ");
            int arrivalTime = scanner.nextInt();
            int burstTime = scanner.nextInt();
            processes[i] = new Process(i + 1, arrivalTime, burstTime);
        }
        
        // Sorting processes by arrival time
        sortByArrivalTime(processes);
        
        // Calculate scheduling details
        scheduleProcesses(processes);
        
        // Display results
        printResults(processes);
        
        scanner.close();
    }
    
    public static void sortByArrivalTime(Process[] processes) {
        for (int i = 0; i < processes.length - 1; i++) {
            for (int j = 0; j < processes.length - i - 1; j++) {
                if (processes[j].arrivalTime > processes[j + 1].arrivalTime) {
                    Process temp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp;
                }
            }
        }
    }
    
    public static void scheduleProcesses(Process[] processes) {
        int currentTime = 0;
        
        for (Process process : processes) {
            if (currentTime < process.arrivalTime) {
                currentTime = process.arrivalTime;
            }
            process.completionTime = currentTime + process.burstTime;
            process.turnAroundTime = process.completionTime - process.arrivalTime;
            process.waitingTime = process.turnAroundTime - process.burstTime;
            currentTime = process.completionTime;
        }
    }
    
    public static void printResults(Process[] processes) {
        System.out.println("\nProcess | Arrival Time | Burst Time | Completion Time | Turnaround Time | Waiting Time");
        for (Process process : processes) {
            System.out.println(process.pid + "\t\t" + process.arrivalTime + "\t\t" +
                    process.burstTime + "\t\t" + process.completionTime + "\t\t" +
                    process.turnAroundTime + "\t\t" + process.waitingTime);
        }
    }
}
