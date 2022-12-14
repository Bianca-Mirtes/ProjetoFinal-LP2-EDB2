package main;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import grafos.*;

public class Input {
    private Grafo<String> grafo = new Grafo<>();
    private List<String> linhas = new LinkedList<>();

    public Input(String entrada) throws IOException { // abertura e leitura do arquivo de entrada
        FileReader fileRead = new FileReader(entrada);
        BufferedReader buffRead = new BufferedReader(fileRead);
		String linha = "";
		while (true) {
            linha = buffRead.readLine();
			if (linha != null) {
				this.linhas.add(linha);
			} else {
                break;
            }		
		}

        try {
            fileRead.close();
            buffRead.close();
        } catch (Exception e1) {
            System.out.println("Arquivo não pôde ser fechado e/ou buffer não pode ser fechado.");
        }

        try {
            buildGrafo();
        } catch (Exception e2) {
            System.out.println("Erro ao gerar o grafo.");
        }
		
    }

    protected Grafo<String> getGrafo(){ // retorna o grafo criado
        return this.grafo;
    }

    private void buildGrafo() { // constroi o grafo
        for (int ii = 0; ii < linhas.size(); ii++) {
            if (ii == 0) {
                grafo.setNumVertices(Integer.parseInt(linhas.get(ii).split(" ")[0]));
                grafo.setNumMaxArestas(Integer.parseInt(linhas.get(ii).split(" ")[1]));
                for (int jj = 1; jj <= grafo.getNumVertices(); jj++) {
                    grafo.addVertice("Casa" + jj);
                }
                
            } else {
                String[] custos = linhas.get(ii).split(" ");
                int count = ii;
                for(var item : custos){
                    grafo.addAresta(Integer.parseInt(item), "Casa" + ii, "Casa" + (count+1));
                    count++;
                }
            }
        }
    }
}
