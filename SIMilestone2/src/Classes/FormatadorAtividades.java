package Classes;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FormatadorAtividades {

	public String adaptarAtividades(List<Atividade> atividades) {
		String resposta = "";
		int cont = 0;
		Iterator<Atividade> it = atividades.iterator();

		if (atividades.isEmpty()) {
			return "Não há atividades";
		}
		Collections.sort(atividades);

		while (it.hasNext()) {
			it.next();

			if (cont == atividades.size() - 1) {
				resposta += atividades.get(cont).getAtividade();
			} else {
				resposta += atividades.get(cont).getAtividade() + "; ";
			}
			cont++;
		}

		return resposta;

	}
}
