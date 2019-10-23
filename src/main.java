import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		File arquivo = new File("C:/Users/samue/Desktop/ddl.txt");

		BufferedReader br = new BufferedReader(new FileReader(arquivo));

		String linha;
		Analisador analisador = new Analisador();
		
		while (( linha = br.readLine()) != null){
			analisador.analisarLinha(linha);
		}
		
		analisador.analisarAtributos();
		
		// CRIAR ARQUIVO DA CLASSE
		PrintWriter writer = new PrintWriter("C:/Users/samue/Desktop/testeClasse.txt", "UTF-8");
		
		for (Atributo atributo : analisador.getDados() ) {
			escreverAnnotation( atributo, writer );
			escreverAtributo( atributo, writer );
		}
		
		writer.close();
		
		System.out.println( "Pronto");
	}

	private static void escreverAtributo(Atributo atributo, PrintWriter writer) {
		if ( atributo.isJoincolumn() ){
			writer.println("private Entity " + atributo.getNomeAtributo() + ";" );
			writer.println("");
		}else {
			writer.println("private " + atributo.getTipoAtributo() + " " + atributo.getNomeAtributo() + ";" );
			writer.println("");
		}
	}

	private static void escreverAnnotation( Atributo atributo, PrintWriter writer ) {
		if ( atributo.isNotNull() ){
			writer.println("@NotNull" );
		}
		
		if ( atributo.isManyToOne() ){
			writer.println("@ManyToOne" );
		}
		
		if ( atributo.isJoincolumn() ){
			writer.println("@JoinColumn( name = \"" + atributo.getNomeAtributo() + "\" )" );
		}
		
		if ( atributo.isId() ){
			writer.println("@id");
		}
		
		if ( atributo.isColumn() ){
			writer.println("@Column( name = \"" + atributo.getNomeAtributo() + "\" )");
		}
		
		if ( atributo.isGeneratedValue() ){
			writer.println("@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = \"IT_TABELA_ID_GENERATOR\" )");
		}
		
		if ( atributo.isSequenceGenerator() ){
			writer.println("@SequenceGenerator( name = \"IT_TABELA_ID_GENERATOR\", sequenceName = \"SEQ_IT_TABELA\", allocationSize = 0 )" );
		}

		
	}

}
