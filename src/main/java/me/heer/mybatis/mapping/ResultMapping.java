package me.heer.mybatis.mapping;

public class ResultMapping {
	private String property;
	private String column;
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "ResultMapping [property=" + property + ", column=" + column + "]";
	}

	public static class Builder {

		private ResultMapping resultMapping = new ResultMapping();
		
		public Builder(String property, String column) {
			resultMapping.property = property;
			resultMapping.column = column;
		}

		public ResultMapping build() {

			return resultMapping;
		}
	}

}
