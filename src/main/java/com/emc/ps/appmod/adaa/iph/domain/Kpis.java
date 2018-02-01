package com.emc.ps.appmod.adaa.iph.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Kpis {

	private Integer id;

	private String additionalNotes;

	private String apiCode;

	private boolean canBeExtrapolated;

	private String color;

	private String denominator;

	private String descriptionLong;

	private String desiredValue;

	private String displayType;

	private boolean frequentUpdated;

	private String icon;

	private Integer inputDivisor;

	private Integer inputMultiplier;

	private Integer isCalculatedKpi;

	private boolean isDataSource;

	private String name;
	
	private String nameOtherLang;

	private Integer order;

	private String originalIosId;

	private String quantifier;

	private boolean redFlag;

	private boolean saudiOnly;

	private String url;

	private Integer valueTypeId;

	private boolean visible;

	private BigDecimal weight;

	private com.emc.ps.appmod.adaa.iph.domain.Source source;

	private Subtopics subtopic;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public boolean isCanBeExtrapolated() {
		return canBeExtrapolated;
	}

	public void setCanBeExtrapolated(boolean canBeExtrapolated) {
		this.canBeExtrapolated = canBeExtrapolated;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDenominator() {
		return denominator;
	}

	public void setDenominator(String denominator) {
		this.denominator = denominator;
	}

	public String getDescriptionLong() {
		return descriptionLong;
	}

	public void setDescriptionLong(String descriptionLong) {
		this.descriptionLong = descriptionLong;
	}

	public String getDesiredValue() {
		return desiredValue;
	}

	public void setDesiredValue(String desiredValue) {
		this.desiredValue = desiredValue;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public boolean isFrequentUpdated() {
		return frequentUpdated;
	}

	public void setFrequentUpdated(boolean frequentUpdated) {
		this.frequentUpdated = frequentUpdated;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getInputDivisor() {
		return inputDivisor;
	}

	public void setInputDivisor(Integer inputDivisor) {
		this.inputDivisor = inputDivisor;
	}

	public Integer getInputMultiplier() {
		return inputMultiplier;
	}

	public void setInputMultiplier(Integer inputMultiplier) {
		this.inputMultiplier = inputMultiplier;
	}

	public Integer getIsCalculatedKpi() {
		return isCalculatedKpi;
	}

	public void setIsCalculatedKpi(Integer isCalculatedKpi) {
		this.isCalculatedKpi = isCalculatedKpi;
	}

	public boolean isDataSource() {
		return isDataSource;
	}

	public void setDataSource(boolean isDataSource) {
		this.isDataSource = isDataSource;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameOtherLang() {
		return nameOtherLang;
	}

	public void setNameOtherLang(String nameOtherLang) {
		this.nameOtherLang = nameOtherLang;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getOriginalIosId() {
		return originalIosId;
	}

	public void setOriginalIosId(String originalIosId) {
		this.originalIosId = originalIosId;
	}

	public String getQuantifier() {
		return quantifier;
	}

	public void setQuantifier(String quantifier) {
		this.quantifier = quantifier;
	}

	public boolean isRedFlag() {
		return redFlag;
	}

	public void setRedFlag(boolean redFlag) {
		this.redFlag = redFlag;
	}

	public boolean isSaudiOnly() {
		return saudiOnly;
	}

	public void setSaudiOnly(boolean saudiOnly) {
		this.saudiOnly = saudiOnly;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getValueTypeId() {
		return valueTypeId;
	}

	public void setValueTypeId(Integer valueTypeId) {
		this.valueTypeId = valueTypeId;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public com.emc.ps.appmod.adaa.iph.domain.Source getSource() {
		return source;
	}

	public void setSource(com.emc.ps.appmod.adaa.iph.domain.Source source) {
		this.source = source;
	}

	public Subtopics getSubtopic() {
		return subtopic;
	}

	public void setSubtopic(Subtopics subtopic) {
		this.subtopic = subtopic;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalNotes == null) ? 0 : additionalNotes.hashCode());
		result = prime * result + ((apiCode == null) ? 0 : apiCode.hashCode());
		result = prime * result + (canBeExtrapolated ? 1231 : 1237);
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((denominator == null) ? 0 : denominator.hashCode());
		result = prime * result + ((descriptionLong == null) ? 0 : descriptionLong.hashCode());
		result = prime * result + ((desiredValue == null) ? 0 : desiredValue.hashCode());
		result = prime * result + ((displayType == null) ? 0 : displayType.hashCode());
		result = prime * result + (frequentUpdated ? 1231 : 1237);
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inputDivisor == null) ? 0 : inputDivisor.hashCode());
		result = prime * result + ((inputMultiplier == null) ? 0 : inputMultiplier.hashCode());
		result = prime * result + ((isCalculatedKpi == null) ? 0 : isCalculatedKpi.hashCode());
		result = prime * result + (isDataSource ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((originalIosId == null) ? 0 : originalIosId.hashCode());
		result = prime * result + ((quantifier == null) ? 0 : quantifier.hashCode());
		result = prime * result + (redFlag ? 1231 : 1237);
		result = prime * result + (saudiOnly ? 1231 : 1237);
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((subtopic == null) ? 0 : subtopic.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((valueTypeId == null) ? 0 : valueTypeId.hashCode());
		result = prime * result + (visible ? 1231 : 1237);
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Kpis other = (Kpis) obj;
		if (additionalNotes == null) {
			if (other.additionalNotes != null)
				return false;
		} else if (!additionalNotes.equals(other.additionalNotes))
			return false;
		if (apiCode == null) {
			if (other.apiCode != null)
				return false;
		} else if (!apiCode.equals(other.apiCode))
			return false;
		if (canBeExtrapolated != other.canBeExtrapolated)
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (denominator == null) {
			if (other.denominator != null)
				return false;
		} else if (!denominator.equals(other.denominator))
			return false;
		if (descriptionLong == null) {
			if (other.descriptionLong != null)
				return false;
		} else if (!descriptionLong.equals(other.descriptionLong))
			return false;
		if (desiredValue == null) {
			if (other.desiredValue != null)
				return false;
		} else if (!desiredValue.equals(other.desiredValue))
			return false;
		if (displayType == null) {
			if (other.displayType != null)
				return false;
		} else if (!displayType.equals(other.displayType))
			return false;
		if (frequentUpdated != other.frequentUpdated)
			return false;
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inputDivisor == null) {
			if (other.inputDivisor != null)
				return false;
		} else if (!inputDivisor.equals(other.inputDivisor))
			return false;
		if (inputMultiplier == null) {
			if (other.inputMultiplier != null)
				return false;
		} else if (!inputMultiplier.equals(other.inputMultiplier))
			return false;
		if (isCalculatedKpi == null) {
			if (other.isCalculatedKpi != null)
				return false;
		} else if (!isCalculatedKpi.equals(other.isCalculatedKpi))
			return false;
		if (isDataSource != other.isDataSource)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (originalIosId == null) {
			if (other.originalIosId != null)
				return false;
		} else if (!originalIosId.equals(other.originalIosId))
			return false;
		if (quantifier == null) {
			if (other.quantifier != null)
				return false;
		} else if (!quantifier.equals(other.quantifier))
			return false;
		if (redFlag != other.redFlag)
			return false;
		if (saudiOnly != other.saudiOnly)
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (subtopic == null) {
			if (other.subtopic != null)
				return false;
		} else if (!subtopic.equals(other.subtopic))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (valueTypeId == null) {
			if (other.valueTypeId != null)
				return false;
		} else if (!valueTypeId.equals(other.valueTypeId))
			return false;
		if (visible != other.visible)
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
