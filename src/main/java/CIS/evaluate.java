package CIS;

public class evaluate {
    public int days;
    public int money;
    public boolean alive;


    public evaluate(int a, int b, boolean c) {
        days = a;
        money = b;
        alive = c;
    }

    public int getDays() {
        return days;
    }

    public int getMoney() {
        return money;
    }

    public  boolean isAlive() {
        return alive;
    }

}
