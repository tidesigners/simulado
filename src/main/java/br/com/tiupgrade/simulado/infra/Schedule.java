package br.com.tiupgrade.simulado.infra;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.tiupgrade.simulado.util.UrlReader;

public class Schedule implements Serializable, Job {

    private static final long serialVersionUID = 7852910769388422291L;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        // TODO Auto-generated method stub
		/*List<Instalacao> instalacoes = new ArrayList<Instalacao>();
		instalacoes.addAll(todasAsInstalacoes.obterTodas());
		for(Instalacao i:instalacoes){
			System.out.println(i.getDescricao());
		}*/

        try {
            UrlReader.getHTML("http://localhost:8080/palantir/api/consulta/atualizar");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
