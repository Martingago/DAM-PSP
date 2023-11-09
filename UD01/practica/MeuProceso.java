
public class MeuProceso extends Thread {
	
	public MeuProceso(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		System.out.println(Thread.currentThread().getName()+":"+"Son un proceso creado en JAVA!!");
	}
}
