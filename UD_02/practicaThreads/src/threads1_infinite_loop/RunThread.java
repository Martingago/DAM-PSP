package threads1_infinite_loop;

public class RunThread implements Runnable {

    private int tipoVal;

    public RunThread(int tipoVal) {
        this.tipoVal = tipoVal;
    }

    @Override
    public void run() {

        while (true) {
            switch (tipoVal) {

                case 1:
                    for (char i = 'a'; i < 'z'; i++) {
                        System.out.println(i);
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        System.out.println(i);
                    }
                    break;

                default:
                    System.out.println("Error, numero no valido");
                    break;
            }

        }
    }

}
