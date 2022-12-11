import java.util.Scanner;

public class RR
{
    static void CalcWaitingTime(String p[], int n, int BurstTime[], int WaitingTime[], int Quantum)
    {
        int tmpBurstTime[] = new int[n];
        for (int i = 0 ; i < n ; i++)
            tmpBurstTime[i] =  BurstTime[i];

        int t = 0; // time counter

        while(true)
        {
            boolean done = true;

            for (int i = 0 ; i < n; i++)
            {
                if (tmpBurstTime[i] > 0)
                {
                    done = false;

                    if (tmpBurstTime[i] > Quantum)
                    {
                        t += Quantum;

                        tmpBurstTime[i] -= Quantum;
                    }

                    else
                    {
                        t += tmpBurstTime[i];
                        WaitingTime[i] = t - BurstTime[i];
                        tmpBurstTime[i] = 0;
                    }
                }
            }

            if (done == true)
                break;
        }
    }

    static void CalcTurnAround(String p[], int n, int BurstTime[], int WaitingTime[], int TurnAroundTime[])
    {
        for (int i = 0; i < n ; i++)
            TurnAroundTime[i] = BurstTime[i] + WaitingTime[i];
    }

    static void AverageCalculations(String p[], int n, int BurstTime[], int Quantum)
    {
        int WaitingTime[] = new int[n], TurnAroundTime[] = new int[n];
        double total_WaitingTime = 0, total_TurnAroundTime = 0;

        CalcWaitingTime(p, n, BurstTime, WaitingTime, Quantum);
        CalcTurnAround(p, n, BurstTime, WaitingTime, TurnAroundTime);

        for (int i=0; i<n; i++)
        {
            total_WaitingTime += WaitingTime[i];
            total_TurnAroundTime += TurnAroundTime[i];
            System.out.println("P" + (i+1) + " "+"Waiting time: "+
                    WaitingTime[i] +" " +"Turn Around time: "+ TurnAroundTime[i]);
        }

        System.out.println("Average Waiting Time is: " + (total_WaitingTime / n ) );
        System.out.println("Average Turn Around Time is: " + (total_TurnAroundTime /n));
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n , Quantum;
        System.out.println("Please Enter number of processes: ");
        n = sc.nextInt();
        String p[] = new String[n];
        int burst_time[] = new int[n];
        System.out.println("Please Enter Quantum Time: ");
        Quantum = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Please Enter Process " + (i + 1) + " Name or Number");
            p[i] = sc.next();
            System.out.println("Please Enter Process " +  p[i] + " Burst time:");
            burst_time[i] = sc.nextInt();
        }

        AverageCalculations(p, n, burst_time, Quantum);
    }
}
