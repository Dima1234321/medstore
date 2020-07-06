import view.MainMenu;
public class Main {
    public static void main(String[] args) {
        try {
            new MainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}