
public class Exem_Proceso_OlaMundo {

	public static void main(String[] args) {
		System.out.println("Ola mundo");
	
		MeuProceso p1=new MeuProceso("p1");
		p1.start();
		
		MeuProceso p2=new MeuProceso("p2");
		p2.start();
		
		MeuProceso p3=new MeuProceso("p3");
		p3.start();
		
		MeuProceso p4=new MeuProceso("p4");
		p4.start();
		
		MeuProceso p5=new MeuProceso("p5");
		p5.start();
		
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName());
		}
	}
}
