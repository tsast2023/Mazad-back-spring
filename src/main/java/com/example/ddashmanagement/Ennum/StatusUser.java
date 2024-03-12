package com.example.ddashmanagement.Ennum;

public enum StatusUser {
   BLOQUER("bloqué"),
    DEBLOQUER("débloqué");

    private final String defaultValue;

    StatusUser(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    // Exemple d'utilisation d'une méthode pour obtenir une valeur par défaut globale
    public static StatusUser globalDefault() {
        return BLOQUER;
    }
}



