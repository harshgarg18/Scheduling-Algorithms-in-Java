import java.util.*;
public class FCFS {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no. of processes in the system: ");
        int n = sc.nextInt();
        Process p[] = new Process[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Process();
            p[i].pid = i;
            System.out.print("Enter arrival time and burst time for P" + i + ": ");
            p[i].at = sc.nextInt();
            p[i].bt = sc.nextInt();
        }
        Arrays.sort(p, Comparator.comparing(pr -> pr.at));
        double awt = 0, att = 0;
        System.out.println("Process\t\tArrival Time\tBurst Time\tStart Time\tFinish Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            if(i != 0)
                p[i].st = p[i-1].st + p[i-1].bt;
            p[i].ft = p[i].st + p[i].bt;
            p[i].wt = p[i].st - p[i].at;
            p[i].tt = p[i].ft - p[i].at;
            awt += p[i].wt;
            att += p[i].tt;
            System.out.println("P" + p[i].pid + "\t\t\t\t" + p[i].at + "\t\t\t\t" + p[i].bt + "\t\t\t" + p[i].st +
                    "\t\t\t" + p[i].ft + "\t\t\t" + p[i].wt + "\t\t\t\t" + p[i].tt);
        }
        awt /= (double) n;
        att /= (double) n;
        System.out.println("Average waiting time: " + awt);
        System.out.println("Average turn around time: " + att);
    }
}