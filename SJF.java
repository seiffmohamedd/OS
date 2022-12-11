import java.util.*;

public class SJF {
    public static void main (String args[]) {

        Scanner sc = new Scanner(System.in);
        int n , ContextSwitching;
        System.out.println("Please Enter number of processes: ");
        n = sc.nextInt();
        System.out.println("Please Enter Context Switching Time: ");
        ContextSwitching = sc.nextInt();
        String Processes[];
        int ArrivalTime[],BurstTime[],EndBurstTime[],TurnAroundTime[],WaitingTime[];
         Processes = new String[n];
         ArrivalTime = new int[n];
         BurstTime = new int[n];
         EndBurstTime = new int[n];
         TurnAroundTime = new int[n];
         WaitingTime = new int[n];
         Boolean FinishedProcess[] = new Boolean[n];          //boolean to check if the process is completed or still there burst time
         int TimeCounter = 0;
         int  ProcessesCompletedCounter = 0;
         int minimum=1000;

        String PreviousProcessName=" ";
         double AvgWaitingTime = 0;
         double AvgTurnAround = 0;
         int tmpBurstTime[] = new int[n];


        for (int i = 0; i < n; i++) {
            System.out.println("Please Enter Process " + (i + 1) + " Name or Number");
            Processes[i] = sc.next();
            System.out.println("Please Enter Process " + Processes[i] + " Arrival time:");
            ArrivalTime[i] = sc.nextInt();
            System.out.println("Please Enter Process " +  Processes[i] + " Burst time:");
            BurstTime[i] = sc.nextInt();
            FinishedProcess[i] = false;         //auto initiate boolean with false
            tmpBurstTime[i] = BurstTime[i];

            if(ArrivalTime[i]<minimum){
                minimum=ArrivalTime[i];
                PreviousProcessName=Processes[i];
            }

        }
        while (true)
        {
            int MinIndex = n;
            int min = 1000;

            if (ProcessesCompletedCounter == n)     //if All Processes Are Finished(= no of processes) then break
            {

                break;
            }


            for (int i = 0; i < n; i++) {
                if ((ArrivalTime[i] <= TimeCounter) && (FinishedProcess[i] == false) && (BurstTime[i] < min)) {  //loop to get the Process of min burst time with respect to arrival time counter
                    min = BurstTime[i];   //m3ana el minimum we m3ana el index bta3o
                    MinIndex = i;

                }
            }



            if (MinIndex == n)
            {
                TimeCounter++;            //lw el index == num of process yb2a ++ EL time counter +1 cuz there is no new process entered or even in the queue

            }
            else
            {
                if(PreviousProcessName!=Processes[MinIndex])
                {
                    TimeCounter+=ContextSwitching;
//                System.out.println("----------------");
//                System.out.println(TimeCounter);
//                System.out.println("----------------");
                    System.out.println("P" + PreviousProcessName);
                    PreviousProcessName=Processes[MinIndex];
                    if(ProcessesCompletedCounter==n-1)
                    {
                        System.out.println("P" + Processes[MinIndex]);

                    }
                }
                BurstTime[MinIndex]--;     // hn minus el burst[min]-1
                TimeCounter++;               //hn ++ el time counter

                if (BurstTime[MinIndex] == 0)
                {
                    EndBurstTime[MinIndex] = TimeCounter;    //EndBurstTime is the whole time taken(TimeCounter)to finish the specific (MinIndex) Process


//                    System.out.println("--------------");
//                    System.out.println(EndBurstTime[MinIndex]);
//                    System.out.println("--------------");

                    FinishedProcess[MinIndex] = true;
                    ProcessesCompletedCounter++;
                }
            }

        }

        for (int i = 0; i < n; i++)
        {
            WaitingTime[i]=EndBurstTime[i]-tmpBurstTime[i]-ArrivalTime[i];
            TurnAroundTime[i]=WaitingTime[i]+tmpBurstTime[i];
            AvgWaitingTime += WaitingTime[i];
            AvgTurnAround += TurnAroundTime[i];
        }
            System.out.println('\n');

        for (int i=0 ; i< n;i++)
        {
            System.out.println("P"+Processes[i]+ " " +"Waiting Time : "+ WaitingTime[i] + " " +"Turn Around Time : "+ TurnAroundTime[i]);
        }
        System.out.println("\n");
        System.out.println("Average waiting time is " + (AvgWaitingTime / n));
        System.out.println("Average Turn around time is " +(AvgTurnAround / n));

        sc.close();
    }
}