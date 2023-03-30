package co.andrescol.javastudy.leadcode;

public class Main {
    public static void main(String[] args) {
        short week = Short.parseShort(args[0]);
        try {
            Class<?> runnableClass = Class.forName("co.andrescol.javastudy.leadcode.week" + week + ".WeekCode");
            LeadCodeRunnable leadCode = (LeadCodeRunnable) runnableClass.getConstructors()[0].newInstance();
            long current = System.currentTimeMillis();
            leadCode.run();
            long end = System.currentTimeMillis();
            System.out.printf("the week %d took %dms", week, end - current);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}