package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AtividadesUsuario {

	List<Atividade> listaAtividades;
	List<Long> dataCriacaoAtividades;

	public AtividadesUsuario() {
		listaAtividades = new ArrayList<Atividade>();
		dataCriacaoAtividades = new ArrayList<Long>();
	}

	public void adicionarAtividades(Atividade atividade) {
		if (!listaAtividades.contains(atividade)
				&& !atividadeJaExiste(atividade)) {
			System.out.println((atividade.getAtividade()));
			listaAtividades.add(atividade);
			Collections.sort(listaAtividades);
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getCriacaoAtividades() {
		return formataArray2(dataCriacaoAtividades).toString();
	}

	public List<Atividade> getAtividades() {
		return (listaAtividades);
	}

	public boolean naoTemAtividades() {
		return listaAtividades.isEmpty();
	}

	public static void main(String[] args) {

		// String x =
		// "[Steven Paul Jobs e Mark Zuckerberg são amigos agora][William Henry Gates III e Mark Zuckerberg são amigos agora][Mark Zuckerberg e William Henry Gates III são amigos agora, Mark Zuckerberg e Steven Paul Jobs são amigos agora]";

		String x = "Mark Zuckerberg e William Henry Gates III são amigos agora;Mark Zuckerberg e Steven Paul Jobs são amigos agora;";

		String z = "";
		int cont = 0;
		for (String i : x.split(";")) {
			if (cont != x.split(";").length - 1) {
				z += i + "; ";
			} else {
				z += i;
			}
			cont++;

		}
		System.out.println(z);

	}

	private ArrayList<Long> formataArray2(List<Long> lista) {

		ArrayList<Long> temp = new ArrayList<Long>();

		for (int i = lista.size() - 1; i >= 0; i--) {
			temp.add(lista.get(i));
		}
		return temp;

	}

	private boolean atividadeJaExiste(Atividade atv) {
		System.out.println(atv.getAtividade());
		for (Atividade atvs : listaAtividades) {
			if (atvs.getAtividade().trim().equals(atv.getAtividade().trim())) {
				return true;
			}
		}
		return false;
	}


}
