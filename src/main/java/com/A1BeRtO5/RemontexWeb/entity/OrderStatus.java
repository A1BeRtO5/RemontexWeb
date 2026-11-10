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
}