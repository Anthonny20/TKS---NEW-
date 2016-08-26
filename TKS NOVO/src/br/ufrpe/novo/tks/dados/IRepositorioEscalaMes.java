package br.ufrpe.novo.tks.dados;

import br.ufrpe.novo.tks.negocios.beans.EscalaMes;
import java.util.ArrayList;

public interface IRepositorioEscalaMes {
	
	void cadastrarEscala (EscalaMes mesAno);
	
	void removerEscala (EscalaMes mesAno);
	
	EscalaMes procurarEscala (String mesAno);
	
	ArrayList<EscalaMes> getEscalas();
	
	void salvarbd();

}
