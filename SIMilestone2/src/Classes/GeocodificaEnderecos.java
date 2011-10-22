package Classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GeocodificaEnderecos {
	
	private static int recuperaIndiceDoMenorDaLista(List<Double> lista){
		int indice = 0;
		double menorDalista = Double.MAX_VALUE;
		for(int i = 0  ; i< lista.size();i++){
			if(lista.get(i)<=menorDalista){
				indice = i;
				menorDalista = lista.get(i);
			}
		}return indice;
		
	}
	
	public static void main(String[] args) throws IOException {
		
		List<Double> x = new ArrayList<Double>();
		x.add(-10.0);
		x.add(8.0);
		x.add(11.0);
		x.add(1.0);
		x.add(0.0);
		System.out.println(x);
		System.out.println(recuperaIndiceDoMenorDaLista(x));
		
	}
	
	public double calculaDistancia(Usuario usr1, Usuario usr2) throws IOException{
		
		String endereco1 = formataEndereco(usr1.getEndereco());
		String endereco2 = formataEndereco(usr2.getEndereco());
		
		URL url1 = new URL("http://maps.google.com/maps/api/geocode/json?address=" + endereco1 + "&sensor=true");
		URL url2 = new URL("http://maps.google.com/maps/api/geocode/json?address=" + endereco2 + "&sensor=true");
		
		endereco1 = geocodificar(url1);
		endereco2 = geocodificar(url2);
		
		return recuperaDistancia(endereco1, endereco2);
	}
	
	private double recuperaDistancia(String ponto1, String ponto2){
		
		//D² = (Xb - Xa)² + (Yb - Ya)²
		
		double distancia;
		
		double latitudePonto1 = Double.parseDouble(ponto1.split(", ")[0].trim());
		double longitudePonto1 = Double.parseDouble(ponto1.split(",")[1].trim());
		
		double latitudePonto2 = Double.parseDouble(ponto2.split(", ")[0].trim());
		double longitudePonto2 = Double.parseDouble(ponto2.split(", ")[1].trim());
		
		distancia =  Math.sqrt( Math.pow( latitudePonto1 - latitudePonto2 ,2)+ Math.pow(longitudePonto1-longitudePonto2,2) );
		return distancia;
	}
	
	private static String geocodificar(URL url) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuffer buffer = new StringBuffer();
		String lat = "";
		String lng = "";
		String linha;
		
		while ((linha = br.readLine()) != null) {

			if (linha.contains("lat") && lat.isEmpty()) {
				lat = linha.split(":")[1];
			}
			if (linha.contains("lng") && lng.isEmpty()) {
				lng = linha.split(":")[1];
			}

			buffer.append(linha);
		}
		br.close();
		return lat + lng;
	}

	private String formataEndereco(String endereco){
		//Campina Grande, Paraiba => Campina+Grande,+Paraiba
		String resposta = "";
		int cont = 0;
		for(String i : endereco.split(" ")){
			cont++;
			if(cont==endereco.split(" ").length){
				resposta += i; 
			}
			else{				
				resposta += i + "+"; 
			}	
		}return resposta.trim();
	}
	

}
