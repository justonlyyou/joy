package com.kvc.joy.core.persistence.flyway.po;

import java.io.Serializable;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月19日 上午12:20:39
 */
public class TSysDbSchemaVersionPk implements Serializable {

	private int versionRank;
	private String versionDomain;
	
	public TSysDbSchemaVersionPk() {
	}

	public TSysDbSchemaVersionPk(int versionRank, String versionDomain) {
		this.versionRank = versionRank;
		this.versionDomain = versionDomain;
	}

	public int getVersionRank() {
		return versionRank;
	}

	public void setVersionRank(int versionRank) {
		this.versionRank = versionRank;
	}

	public String getVersionDomain() {
		return versionDomain;
	}

	public void setVersionDomain(String versionDomain) {
		this.versionDomain = versionDomain;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((versionDomain == null) ? 0 : versionDomain.hashCode());
		result = prime * result + versionRank;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TSysDbSchemaVersionPk other = (TSysDbSchemaVersionPk) obj;
		if (versionDomain == null) {
			if (other.versionDomain != null)
				return false;
		} else if (!versionDomain.equals(other.versionDomain))
			return false;
		if (versionRank != other.versionRank)
			return false;
		return true;
	}

}
