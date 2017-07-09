package Estruturas;

import java.util.ArrayList;
import java.util.Date;

public class Lista {
	private String tipo;
	private ArrayList<String> lista;
	private Date ultima;
	
	@SuppressWarnings("null")
	public void Limpa(ArrayList<Integer> mantem){
		ArrayList<String> temp = null;
		for(int i : mantem){
			temp.add(i, lista.get(i));
		}
		lista.clear();
		for (int i=0; i<temp.size(); i++){
			lista.add(temp.get(i));
		}
	}
	@SuppressWarnings("null")
	public String VerifTipo(){
		ArrayList<String> tipos = null;
		tipos.add("mercado");
		tipos.add("farmacia");
		tipos.add("outros");
		boolean k=false;
		for(int i=0; i<tipos.size(); i++){
			if(tipo.equalsIgnoreCase(tipos.get(i))){
				k = true;
			}
		}
		if(k)
			return "ok";
		else
			return "tipo invalido";
	}

}
