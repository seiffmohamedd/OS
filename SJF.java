import java.util.*;

public class SJF {
    public static void main (String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter number of processes: ");
        int n = sc.nextInt();
        int Processes[] = new int[n];
        int arrivalTime[] = new int[n];
        int BurstTime[] = new int[n];
        int CompleteTime[] = new int[n];
        int TurnAroundTime[] = new int[n];
        int WaitingTime[] = new int[n];
        Boolean FinishedProcess[] = new Boolean[n];
        int ArrivalTimeCounter = 0;
        int  ProcessCompletedCounter = 0;
        double AvgWaitingTime = 0;
        double AvgTurnAround = 0;
        int tmpBurstTime[] = new int[n];


        for (int i = 0; i < n; i++) {
            System.out.println("Please Enter Process " + (i + 1) + " Name");
            Processes[i] = sc.nextInt();
            System.out.println("Please Enter Process " + Processes[i] + " Arrival time:");
            arrivalTime[i] = sc.nextInt();
            System.out.println("Please Enter Process " +  Processes[i] + " Burst time:");
            BurstTime[i] = sc.nextInt();
            tmpBurstTime[i] = BurstTime[i];
            FinishedProcess[i] = false;
        }

        while (true) {
            int min = 99, MinIndex = n;
            if (ProcessCompletedCounter == n)     //All Processes Are Completed
                break;

            for (int i = 0; i < n; i++) {
                if ((arrivalTime[i] <= ArrivalTimeCounter) && (FinishedProcess[i] == false) && (BurstTime[i] < min)) {
                    min = BurstTime[i];   //m3ana el min we m3ana el index bta3o
                    MinIndex = i;
                }
            }

            if (MinIndex == n)
                ArrivalTimeCounter++;     //lw el index == num of process yb2a n++ EL arrival time +1
            else {
                BurstTime[MinIndex]--;     // hn minus el bt[min]-1
                ArrivalTimeCounter++;               //hn ++ el arrival time
                if (BurstTime[MinIndex] == 0) {
                    CompleteTime[MinIndex] = ArrivalTimeCounter;
                    FinishedProcess[MinIndex] = true;
                    ProcessCompletedCounter++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            TurnAroundTime[i] = CompleteTime[i] - arrivalTime[i];
            WaitingTime[i] = TurnAroundTime[i] - tmpBurstTime[i];
            AvgWaitingTime += WaitingTime[i];
            AvgTurnAround += TurnAroundTime[i];
        }

        for (int i=0 ; i< n;i++)
        {
            System.out.println("P"+Processes[i] + "\t" +"waiting : "+ WaitingTime[i] + "\t" +"turn around: "+ TurnAroundTime[i] +"\t");
        }
        System.out.println("\n");
        System.out.println("Average waiting time is " + (AvgWaitingTime / n));
        System.out.println("Average Turn around time is " +(AvgTurnAround / n));

        sc.close();
    }
}