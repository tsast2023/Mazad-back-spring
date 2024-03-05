package com.example.ddashmanagement.Ennum;

public enum StatusVendeur {
   BLOQUER("bloqué"),
    DEBLOQUER("débloqué");

    private final String defaultValue;

    StatusVendeur(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    // Exemple d'utilisation d'une méthode pour obtenir une valeur par défaut globale
    public static StatusVendeur globalDefault() {
        return BLOQUER;
    }
}



