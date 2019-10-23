
public class Atributo {

	private boolean id;
	private boolean column;
	private boolean joincolumn;
	private boolean generatedValue;
	private boolean sequenceGenerator;
	private boolean manyToOne;
	private boolean notNull;

	private String tipoAtributo;
	private String nomeAtributo;

	public boolean isId() {
		return id;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	public boolean isColumn() {
		return column;
	}

	public void setColumn(boolean column) {
		this.column = column;
	}

	public boolean isJoincolumn() {
		return joincolumn;
	}

	public void setJoincolumn(boolean joincolumn) {
		this.joincolumn = joincolumn;
	}

	public boolean isGeneratedValue() {
		return generatedValue;
	}

	public void setGeneratedValue(boolean generatedValue) {
		this.generatedValue = generatedValue;
	}

	public boolean isSequenceGenerator() {
		return sequenceGenerator;
	}

	public void setSequenceGenerator(boolean sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}

	public boolean isManyToOne() {
		return manyToOne;
	}

	public void setManyToOne(boolean manyToOne) {
		this.manyToOne = manyToOne;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public String getTipoAtributo() {
		return tipoAtributo;
	}

	public void setTipoAtributo(String tipoAtributo) {
		this.tipoAtributo = tipoAtributo;
	}

	public String getNomeAtributo() {
		return nomeAtributo;
	}

	public void setNomeAtributo(String nomeAtributo) {
		this.nomeAtributo = nomeAtributo;
	}

}
