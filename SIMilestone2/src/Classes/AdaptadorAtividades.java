package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AdaptadorAtividades {
	
	public static void main(String[] args) {
		List<Atividade> x = new ArrayList<Atividade>();
		
		x.iterator();
	}
	
	public String adaptar(List<Atividade> atividades){
		if(atividades.isEmpty()){
			return "Não há atividades";
		}
		Collections.sort(atividades);

		String resposta = "";
		
		int cont = 0;
		
		Iterator<Atividade> it = atividades.iterator();
		
		while (it.hasNext()){
			it.next();
			
			if (cont == atividades.size()-1){
				resposta += atividades.get(cont).getAtividade();
			}
			else {
				resposta += atividades.get(cont).getAtividade()+"; ";
			}
			cont ++;
		}
		
//		for(Atividade atv : atividades){
//			if(cont==atividades.size()-1){
//				resposta += atividades.get(cont).getAtividade();
//			}
//			else{
//				resposta += atividades.get(cont).getAtividade()+"; ";
//			}
//			cont++;
//		}
		return resposta;
		
	}

}
