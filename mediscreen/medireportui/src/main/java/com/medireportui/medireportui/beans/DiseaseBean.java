package com.medireportui.medireportui.beans;

public class DiseaseBean {

    public enum RiskLevel {
        NONE,
        BORDERLINE,
        IN_DANGER,
        EARLY_ONSET
    }

    private String diseaseName;
    private RiskLevel riskLevel;
    private final String[] triggers = new String[]{"hemoglobin A1C", "microalbumin", "height", "weight",
            "smoker", "abnormal", "cholesterol","dizziness","relapse","reaction","antibodies"};

    public DiseaseBean() {
        this.diseaseName = "Diabetes";
        this.riskLevel = RiskLevel.EARLY_ONSET;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String[] getTriggers() {
        return triggers;
    }
}
