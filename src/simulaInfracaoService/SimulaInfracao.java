package simulaInfracaoService;
import java.util.Timer;


public class SimulaInfracao {

	public static void main(String[] args) {
		Timer timer = new Timer();
		JobSimulaInfracao tarefa = new JobSimulaInfracao();
		timer.schedule(tarefa, 0,  5000);

	}

}
