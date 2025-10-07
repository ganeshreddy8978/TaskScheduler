import java.time.LocalTime;
import java.util.*;

public class GoalReminder {
    static class Goal {
        String name;
        LocalTime time;

        public Goal(String name, LocalTime time) {
            this.name = name;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Goal> goals = new ArrayList<>();

        System.out.print("How many goals do you want to set today? ");
        int n = sc.nextInt();
        sc.nextLine();  // consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("Enter goal " + (i + 1) + ": ");
            String goalName = sc.nextLine();

            System.out.print("Enter reminder time for '" + goalName + "' (HH:mm): ");
            String timeStr = sc.nextLine();

            try {
                LocalTime time = LocalTime.parse(timeStr);
                goals.add(new Goal(goalName, time));
            } catch (Exception e) {
                System.out.println("Invalid time format! Skipping this goal.");
            }
        }

        Timer timer = new Timer();

        for (Goal goal : goals) {
            LocalTime now = LocalTime.now();
            long delay = java.time.Duration.between(now, goal.time).toMillis();

            if (delay < 0) {
                System.out.println("Skipping past time for goal: " + goal.name);
                continue;
            }

            timer.schedule(new TimerTask() {
                public void run() {
                    System.out.println("⏰ Reminder: Time to " + goal.name);
                }
            }, delay);
        }

        System.out.println("✅ Reminders set. Keep the program running...");
    }
}
