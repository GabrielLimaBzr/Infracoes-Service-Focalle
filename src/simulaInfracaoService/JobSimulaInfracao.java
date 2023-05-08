package simulaInfracaoService;

import java.util.Date;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.text.SimpleDateFormat;
import java.util.TimerTask;

import simulaInfracaoService.entidades.ClasseVeiculos;

import java.util.Random;

public class JobSimulaInfracao extends TimerTask {

	private static final String API_URL = "http://localhost:8083/infracao";

	@Override
	public void run() {
		try {
			String json = this.gerarJsonAleatorio();
			this.requestApi(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String gerarJsonAleatorio() throws Exception {

		String placa = null;
		Long velocidade = null;
		String dtInfracao = null;
		String classeVeiculo = null;

		try {

			SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dataAtual = new Date();
			Random random = new Random();

			ClasseVeiculos[] classes = ClasseVeiculos.values();
			int tamanho = classes.length;
			ClasseVeiculos classeAleatoria = classes[random.nextInt(tamanho)];

			placa = retornaPlacaAleatoria(); // placa aleatoria
			velocidade = (long) (random.nextInt(21) + 80);// velocidade entre 80-100 km
			dtInfracao = formatoData.format(dataAtual); // hora atual
			classeVeiculo = classeAleatoria.toString(); // classe aleatoria

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "{\"dtRegistro\": \"" + dtInfracao + "\", \"velocidade\": " + velocidade + ", \"placa\": \"" + placa
				+ "\", \"classeVeiculo\": \"" + classeVeiculo + "\"}";

	}

	private String retornaPlacaAleatoria() {

		// gerando uma placa no padrao antigo XXX0000

		Random random = new Random();
		StringBuilder placa = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			char letra = (char) (random.nextInt(26) + 'A');
			placa.append(letra);
		}

		for (int i = 0; i < 4; i++) {
			int digito = random.nextInt(10);
			placa.append(digito);
		}

		return placa.toString();
	}

	private void requestApi(String json) throws Exception {
		URL url = new URL(API_URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");

		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(json.getBytes());
		os.flush();
		os.close();

		int responseCode = con.getResponseCode();
		System.out.println("Código de resposta: " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		System.out.println("Resposta da API: " + response.toString());
	}

}
