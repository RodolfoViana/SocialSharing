package Classes;

public class Atividade implements Comparable<Atividade>{
	
	private String atividade;
	private long criacaoAtividade;
	
	public static void main(String[] args) {
		String x = "William Henry Gates III precisa do item Guia do mochileiro das galáxias";
		String y = "William Henry Gates III precisa do item Guia do mochileiro das galáxias";
		System.out.println(x.equals(y));
	}
	
	public Atividade(String atividade, long criacaoAtividade){
		this.atividade = atividade;
		this.criacaoAtividade = criacaoAtividade;
	}
	
	public String getAtividade(){
		return atividade;
	}
	
	public long getCriacaoAtividade(){
		return criacaoAtividade;
	}

	@Override
	public int compareTo(Atividade atv) {
		if(atv.getCriacaoAtividade()>this.getCriacaoAtividade()){
			return 1;
		}
		else if(atv.getCriacaoAtividade()<this.getCriacaoAtividade()){
			return -1;
		}
		return 0;
	}
	
	public boolean equals(Atividade atv){
		try{
			String nome1, nome2;
			
			if(!atv.getAtividade().contains("amigos")){
				return false;
			}
			if(atv.getAtividade().equals(this.atividade)){
				return true;
			}
			
			nome1 = atv.getAtividade().split(" e ")[0];
			nome2 = atv.getAtividade().split(" e ")[1].split(" são ")[0].trim();
			
			if(atividade.contains(nome2) && atividade.contains(nome1)){
				return true;
			}
			else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}

}
