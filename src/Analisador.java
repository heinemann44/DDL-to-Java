import java.util.ArrayList;
import java.util.List;

public class Analisador {

	private List<String> atributos;
	private List<String> fk;
	private List<String> nulo;
	private List<String> pk;
	
	private List<Atributo> dados;
	
	public Analisador(){
		this.atributos = new ArrayList<>();
		this.fk = new ArrayList<>();
		this.nulo = new ArrayList<>();
		this.pk = new ArrayList<>();
		this.dados = new ArrayList<>();
	}
	
	public void analisarLinha( String linha ){
		if ( linha.contains( "CONSTRAINT PK" ) ){
			this.pk.add(linha);
		}else if ( linha.contains( "CONSTRAINT SYS" ) ){
			this.nulo.add(linha);
		}else if ( linha.contains( "CONSTRAINT FK" ) ){
			this.fk.add(linha);
		}else{
			this.atributos.add(linha);
		}
		
	}
	
	public void analisarAtributos(){
		
		for (String linhaAtributo : this.atributos ) {
			String[] dados = linhaAtributo.split( " ", 2 );
			
			Atributo atributo = new Atributo();
			
			String nomeAtributo = dados[0];
			String metadata = dados[1];
			atributo.setNomeAtributo( nomeAtributo );

			atributo.setTipoAtributo( this.verificarTipoAtributo( metadata ) );
			
			this.isPK( nomeAtributo, atributo );
			this.isFK( nomeAtributo, atributo );
			this.isNotNull( nomeAtributo, atributo );
			
			this.dados.add(atributo);
		}
		
	}

	private void isNotNull(String nomeAtributo, Atributo atributo) {

		for (String notnull : atributos) {
			
			if ( atributo.isId() ){
				return;
			}
			
			if ( atributo.getNomeAtributo() == "DTINCLUSAO" ){
				return;
			}
			
			if ( atributo.getNomeAtributo() == "DTALTERACAO" ){
				return;
			}
			
			if ( notnull.contains( nomeAtributo ) ){
				atributo.setNotNull( true );
				return;
			}
			
		}
	}

	private void isFK(String nomeAtributo, Atributo atributo) {
		
		for ( String fk : this.fk ) {
			
			if ( fk.contains( nomeAtributo ) ){
				atributo.setManyToOne( true );
				atributo.setJoincolumn( true );
				return;
			}
		}
		
		atributo.setColumn( true );
		
	}

	private void isPK(String nomeAtributo, Atributo atributo) {
		
		for (String pk : this.pk) {
			
			if ( pk.contains( nomeAtributo ) ){
				atributo.setId( true );
				atributo.setColumn( true );
				atributo.setGeneratedValue( true );
				atributo.setSequenceGenerator( true );
				return;
			}
		}
	}

	private String verificarTipoAtributo(String metadata) {

		String STRING = "String";
		String INTEGER = "Integer";
		String LONG = "Long";
		String BIGDECIMAL = "BigDecimal";
		String LOCALDATETIME = "LocalDateTime";
		String BOOLEAN = "boolean";
		String BLOB = "byte[]";
		
		if ( metadata.contains( "VARCHAR2" ) ){
			return STRING;
		}else if ( metadata.contains( "CHAR(1)" ) ){
			return BOOLEAN;
		}else if ( metadata.contains( "TIMESTAMP" ) ){
			return LOCALDATETIME;
		}else if ( metadata.contains( "CLOB" ) ){
			return STRING;
		}else if ( metadata.contains( "BLOB" ) ){
			return BLOB;
		}else if ( metadata.contains( "NUMBER" ) ){
			String[] s = metadata.split( "\\(" );
			String[] aux = s[1].split( "\\)" );
			
			String valor = aux[0];
			String[] aux2 = valor.split( "," );
			
			int valor1 = Integer.parseInt( aux2[0] );
			int valor2 = Integer.parseInt( aux2[1] );

			if ( valor2 != 0 ){
				return BIGDECIMAL;
			}else if ( valor1 > 9){
				return LONG;
			}else if ( valor1 <= 9 ){
				return INTEGER;
			}
			
		}
		
		
		return null;
	}

	public List<String> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<String> atributos) {
		this.atributos = atributos;
	}

	public List<String> getFk() {
		return fk;
	}

	public void setFk(List<String> fk) {
		this.fk = fk;
	}

	public List<String> getNulo() {
		return nulo;
	}

	public void setNulo(List<String> nulo) {
		this.nulo = nulo;
	}

	public List<String> getPk() {
		return pk;
	}

	public void setPk(List<String> pk) {
		this.pk = pk;
	}

	public List<Atributo> getDados() {
		return dados;
	}

	public void setDados(List<Atributo> dados) {
		this.dados = dados;
	}
	
}
