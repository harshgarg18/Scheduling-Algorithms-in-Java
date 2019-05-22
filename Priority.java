import java.util.*;

public class Priority {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no. of processes in the system: ");
        int n = sc.nextInt();
        Process p[] = new Process[n + 1];
        for (int i = 0; i < n; i++) {
            p[i] = new Process();
            p[i].pid = i;
            System.out.print("Enter arrival time, burst time and priority for P" + i + ": ");
            p[i].at = sc.nextInt();
            p[i].bt = sc.nextInt();
            p[i].pr = sc.nextInt();
            p[i].rt = p[i].bt;
        }
        p[n] = new Process();
        p[n].at = Integer.MAX_VALUE;
        Arrays.sort(p, Comparator.comparing(pr -> pr.at));
        double awt = 0, att = 0;
        System.out.println("Process\t\tArrival Time\tBurst Time\tPriority\tWaiting Time\tTurnaround Time");
        p[n].pr = Integer.MIN_VALUE;
        int remain = n, end;
        for (int time = 0; remain != 0; time++) {
            int largest = n;
            for(int i = 0; i < n; i++) {
                if(p[i].at <= time && p[i].pr > p[largest].pr && p[i].rt > 0)
                    largest = i;
            }
            p[largest].rt--;
            if(p[largest].rt == 0) {
                remain--;
                end = time + 1;
                p[largest].wt = end - p[largest].at - p[largest].bt;
                p[largest].tt = end - p[largest].at;
                awt += p[largest].wt;
                att += p[largest].tt;
                System.out.println("P" + p[largest].pid + "\t\t\t\t" + p[largest].at + "\t\t\t\t" + p[largest].bt +
                        "\t\t\t" + p[largest].pr + "\t\t\t\t" + p[largest].wt + "\t\t\t\t" + p[largest].tt);
            }
        }
        awt /= (double) n;
        att /= (double) n;
        System.out.println("Average waiting time: " + awt);
        System.out.println("Average turn around time: " + att);
    }
}