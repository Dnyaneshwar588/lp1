                                  //FCFS 
                         
                         
                         
                         import java.util.Scanner;
public class FCFS 
{
  public static void main(String[] args) 
  {
    System.out.println("Enter the number of process");
    Scanner in = new Scanner(System.in);
    int numberOfProcesses = in.nextInt();
    int pid[] = new int[numberOfProcesses];
    int bt[] = new int[numberOfProcesses];
    int ar[] = new int[numberOfProcesses];
    int ct[] = new int[numberOfProcesses];
    int ta[] = new int[numberOfProcesses];
    int wt[] = new int[numberOfProcesses];
    for(int i = 0; i < numberOfProcesses; i++) 
    {
      System.out.println("Enter process " + (i+1) + " arrival time: ");
      ar[i] = in.nextInt();
      System.out.println("Enter process " + (i+1) + " brust time: ");
      bt[i] = in.nextInt();
      pid[i] = i+1;
    }
    int temp; 
    for (int i = 0; i < numberOfProcesses; i++)   
    {
      for (int j = i+1; j < numberOfProcesses; j++) 
      {
        if(ar[i] > ar[j]) 
        {
          temp = ar[i];
          ar[i] = ar[j];
          ar[j] = temp;
          temp = pid[i];
          pid[i] = pid[j];
          pid[j] = temp;
          temp = bt[i];
          bt[i] = bt[j];
          bt[j] = temp;
        }
      }
    }
    System.out.println();
    ct[0] = bt[0] + ar[0];
    for(int i = 1; i < numberOfProcesses; i++) 
    {
      ct[i] = ct[i - 1] + bt[i];
    }
    for(int i = 0; i < numberOfProcesses; i++) 
    {
      ta[i] = ct[i] - ar[i];
      wt[i] = ta[i] - bt[i];
    }
    System.out.println("Process\t\tAT\t\tBT\t\tCT\t\tTAT\t\tWT");
    for(int i = 0; i < numberOfProcesses; i++)   
    {
      System.out.println(pid[i]+"\t\t" + ar[i] + "\t\t" + bt[i]+ "\t\t" +
      ct[i]+ "\t\t" + ta[i]+ "\t\t" + wt[i]);
    }
    System.out.println("gantt chart: ");
    for(int i = 0; i < numberOfProcesses; i++)  
    {
      System.out.print("P" + pid[i] +" ");
    }
    System.out.println();
  }
}

/*

Output: 

student@student:~$ javac FCFS.java 
student@student:~$ java FCFS 
Enter the number of process
3
Enter process 1 arrival time: 
0
Enter process 1 brust time: 
5
Enter process 2 arrival time: 
0
Enter process 2 brust time: 
3
Enter process 3 arrival time: 
0
Enter process 3 brust time: 
8

Process		AT		BT		CT		TAT		WT
1		0		5		5		5		0
2		0		3		8		8		5
3		0		8		16		16		8
gantt chart: 
P1 P2 P3 
student@student:~$ java FCFS 
Enter the number of process
3
Enter process 1 arrival time: 
2
Enter process 1 brust time: 
5
Enter process 2 arrival time: 
0
Enter process 2 brust time: 
3
Enter process 3 arrival time: 
4
Enter process 3 brust time: 
4

Process		AT		BT		CT		TAT		WT
2		0		3		3		3		0
1		2		5		8		6		1
3		4		4		12		8		4
gantt chart: 
P2 P1 P3 
student@student:~$ 

*/


                                                //SJFPreemptive 




import java.util.Scanner;

public class SJFPreemptive 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Number of processes: ");
        int n = sc.nextInt();

        int[] at = new int[n], bt = new int[n], rt = new int[n];
        int[] ct = new int[n], wt = new int[n], tat = new int[n];
        boolean[] completed = new boolean[n];

        for (int i = 0; i < n; i++) 
        {
            System.out.print("Arrival time for P" + (i + 1) + ": ");
            at[i] = sc.nextInt();
            System.out.print("Burst time for P" + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
        }

        int completedCount = 0, currentTime = 0;

        while (completedCount < n) 
        {
            int idx = -1;
            int minRT = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) 
            {
                if (!completed[i] && at[i] <= currentTime && rt[i] < minRT && rt[i] > 0) 
                {
                    minRT = rt[i];
                    idx = i;
                }
            }
            if (idx == -1) 
            {
                currentTime++;
                continue;
            }
            rt[idx]--;
            currentTime++;

            if (rt[idx] == 0) 
            {
                completed[idx] = true;
                completedCount++;
                ct[idx] = currentTime;
                tat[idx] = ct[idx] - at[idx];
                wt[idx] = tat[idx] - bt[idx];
            }
        }

        System.out.println("\nP\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) 
        {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n", i + 1, at[i], bt[i], ct[i], tat[i], wt[i]);
        }

        sc.close();
    }
}



/*

Output:
student@student:~$ javac SJFPreemptive.java 
student@student:~$ java SJFPreemptive 
Number of processes: 3
Arrival time for P1: 0
Burst time for P1: 6
Arrival time for P2: 0
Burst time for P2: 8
Arrival time for P3: 0
Burst time for P3: 5

P	AT	BT	CT	TAT	WT
1	0	6	11	11	5
2	0	8	19	19	11
3	0	5	5	5	0
student@student:~$ java SJFPreemptive 
Number of processes: 3
Arrival time for P1: 0
Burst time for P1: 6
Arrival time for P2: 1
Burst time for P2: 3
Arrival time for P3: 2
Burst time for P3: 7

P	AT	BT	CT	TAT	WT
1	0	6	9	9	3
2	1	3	4	3	0
3	2	7	16	14	7
student@student:~$ 

*/









                         //priorityNonPreemptive





// import java.util.Scanner;

// public class PriorityNonPreemptive {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         System.out.print("Number of processes: ");
//         int n = sc.nextInt();

//         int[] at = new int[n], bt = new int[n], priority = new int[n];
//         int[] ct = new int[n], wt = new int[n], tat = new int[n];
//         boolean[] completed = new boolean[n];

//         for (int i = 0; i < n; i++) {
//             System.out.print("Arrival time for P" + (i + 1) + ": ");
//             at[i] = sc.nextInt();
//             System.out.print("Burst time for P" + (i + 1) + ": ");
//             bt[i] = sc.nextInt();
//             System.out.print("Priority for P" + (i + 1) + " (lower number = higher priority): ");
//             priority[i] = sc.nextInt();
//         }

//         int completedCount = 0, currentTime = 0;

//         while (completedCount < n) {
//             int idx = -1;
//             int highestPriority = Integer.MAX_VALUE;

//             for (int i = 0; i < n; i++) {
//                 if (!completed[i] && at[i] <= currentTime && priority[i] < highestPriority) {
//                     highestPriority = priority[i];
//                     idx = i;
//                 }
//             }

//             if (idx == -1) {
//                 currentTime++;
//                 continue;
//             }

//             // Execute the selected process completely (non-preemptive)
//             currentTime += bt[idx];
//             ct[idx] = currentTime;
//             tat[idx] = ct[idx] - at[idx];
//             wt[idx] = tat[idx] - bt[idx];
//             completed[idx] = true;
//             completedCount++;
//         }

//         System.out.println("\nP\tAT\tBT\tPriority\tCT\tTAT\tWT");
//         for (int i = 0; i < n; i++) {
//             System.out.printf("%d\t%d\t%d\t%d\t\t%d\t%d\t%d\n", i + 1, at[i], bt[i], priority[i], ct[i], tat[i], wt[i]);
//         }

//         sc.close();
//     }
// }










                                            // RoundRobinPreemptive






// import java.util.Scanner;

// public class RoundRobinPreemptive {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         System.out.print("Number of processes: ");
//         int n = sc.nextInt();

//         int[] at = new int[n], bt = new int[n], rt = new int[n];
//         int[] ct = new int[n], tat = new int[n], wt = new int[n];
//         boolean[] completed = new boolean[n];

//         for (int i = 0; i < n; i++) {
//             System.out.print("Arrival time for P" + (i + 1) + ": ");
//             at[i] = sc.nextInt();
//             System.out.print("Burst time for P" + (i + 1) + ": ");
//             bt[i] = sc.nextInt();
//             rt[i] = bt[i];
//         }

//         System.out.print("Enter time quantum: ");
//         int tq = sc.nextInt();

//         int currentTime = 0, completedCount = 0;
//         boolean done;

//         while (completedCount < n) {
//             done = true;
//             for (int i = 0; i < n; i++) {
//                 if (at[i] <= currentTime && rt[i] > 0) {
//                     done = false;
//                     if (rt[i] > tq) {
//                         rt[i] -= tq;
//                         currentTime += tq;
//                     } else {
//                         currentTime += rt[i];
//                         rt[i] = 0;
//                         completedCount++;
//                         ct[i] = currentTime;
//                         tat[i] = ct[i] - at[i];
//                         wt[i] = tat[i] - bt[i];
//                     }
//                 } else if (at[i] > currentTime) {
//                     // If no process has arrived yet, advance time
//                     if (done) currentTime = at[i];
//                 }
//             }
//         }

//         System.out.println("\nP\tAT\tBT\tCT\tTAT\tWT");
//         for (int i = 0; i < n; i++) {
//             System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n", i + 1, at[i], bt[i], ct[i], tat[i], wt[i]);
//         }

//         sc.close();
//     }
// }
