package com.A1BeRtO5.RemontexWeb.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    IN_REPAIR("🔧 в ремонті"),
    URGENT("🔥 терміново"),
    READY("✅ готово"),
    ISSUED("📦 видано");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getValue() {
        return displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Цей метод навчить Jackson розуміти і англійські назви (ISSUED), і назви зі смайликами ("📦 видано")
    @JsonCreator
    public static OrderStatus fromValue(String value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.name().equalsIgnoreCase(value) || status.displayName.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}