import java.util.*;
public class SRTF {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no. of processes in the system: ");
        int n = sc.nextInt();
        Process p[] = new Process[n + 1];
        for (int i = 0; i < n; i++) {
            p[i] = new Process();
            p[i].pid = i;
            System.out.print("Enter arrival time and burst time for P" + i + ": ");
            p[i].at = sc.nextInt();
            p[i].bt = sc.nextInt();
            p[i].rt = p[i].bt;
        }
        p[n] = new Process();
        p[n].at = Integer.MAX_VALUE;
        Arrays.sort(p, Comparator.comparing(pr -> pr.at));
        double awt = 0, att = 0;
        System.out.println("Process\t\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        int remain = 0, end;
        p[n].rt = Integer.MAX_VALUE;
        for (int time = 0; remain != n; time++) {
            int s = n;
            for(int i = 0; i < n; i++) {
                if(p[i].at <= time && p[i].rt < p[s].rt && p[i].rt > 0)
                    s = i;
            }
            p[s].rt--;
            if(p[s].rt == 0) {
                remain++;
                end = time + 1;
                p[s].wt = end - p[s].at - p[s].bt;
                p[s].tt = end - p[s].at;
                awt += p[s].wt;
                att += p[s].tt;
                System.out.println("P" + p[s].pid + "\t\t\t\t" + p[s].at + "\t\t\t\t" + p[s].bt + "\t\t\t\t" + p[s].wt +
                        "\t\t\t\t" + p[s].tt);
            }
        }
        awt /= (double) n;
        att /= (double) n;
        System.out.println("Average waiting time: " + awt);
        System.out.println("Average turn around time: " + att);
    }
}