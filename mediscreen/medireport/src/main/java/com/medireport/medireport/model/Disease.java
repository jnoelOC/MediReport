package com.medireport.medireport.model;

public class Disease {
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

    public Disease() {
        this.diseaseName = "Diabetes";
        this.riskLevel = RiskLevel.EARLY_ONSET;
    }

    public Disease(String diseaseName, RiskLevel riskLevel) {
        this.diseaseName = diseaseName;
        this.riskLevel = riskLevel;
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
