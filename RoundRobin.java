import java.util.*;
public class RoundRobin {
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
            p[i].rt = p[i].bt;
        }
        System.out.print("Enter time quantum: ");
        int tq = sc.nextInt();
        Arrays.sort(p, Comparator.comparing(pr -> pr.at));
        double awt = 0, att = 0;
        System.out.println("Process\t\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0, time = 0, x = n, flag = 0; x != 0; ) {
            if (p[i].rt <= tq && p[i].rt > 0) {
                time += p[i].rt;
                p[i].rt = 0;
                flag = 1;
            } else if (p[i].rt > 0) {
                p[i].rt -= tq;
                time += tq;
            }
            if (p[i].rt == 0 && flag == 1) {
                x--;
                p[i].wt = time - p[i].at - p[i].bt;
                p[i].tt = time - p[i].at;
                awt += p[i].wt;
                att += p[i].tt;
                flag = 0;
                System.out.println("P" + p[i].pid + "\t\t\t\t" + p[i].at + "\t\t\t\t" + p[i].bt + "\t\t\t\t" + p[i].wt +
                        "\t\t\t\t" + p[i].tt);
            }
            if (i == n - 1) {
                i = 0;
            } else if (p[i + 1].at <= time) {
                i++;
            } else {
                i = 0;
            }
        }
        awt /= (double) n;
        att /= (double) n;
        System.out.println("Average waiting time: " + awt);
        System.out.println("Average turn around time: " + att);
    }
}