package com.workday.custom.int0025.ssk147;

public class FlexLogField implements Cloneable {
	private String parameter;
	private String header;
	
	public FlexLogField() {
		super();
	}

	public FlexLogField(String parameter, String header) {
		super();
		this.parameter = parameter;
		this.header = header;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object theClone = super.clone();
		
		FlexLogField f = (FlexLogField)theClone;
		f.header = new String(this.header);
		f.parameter = new String(this.parameter);
		
		return theClone;
	}
	
	public FlexLogField replicate() throws Throwable {
		return (FlexLogField)this.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		
		result = prime * result + ((parameter == null) ? 0 : parameter.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof FlexLogField)) {
			return false;
		}

		FlexLogField other = (FlexLogField)obj;
		
		if (parameter == null) {
			if (other.parameter != null) {
				return false;
			}
		} else if (!parameter.equals(other.parameter)) {
			return false;
		}
		
		if (header == null) {
			if (other.header != null) {
				return false;
			}
		} else if (!header.equals(other.header)) {
			return false;
		}
		
		return true;
	}

}
