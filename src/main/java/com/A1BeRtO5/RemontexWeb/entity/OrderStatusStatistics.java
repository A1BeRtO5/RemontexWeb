package com.A1BeRtO5.RemontexWeb.entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderStatusStatistics {
    private long inRepairCount;
    private long urgentCount;
    private long readyCount;
    private long issuedCount;
    private long totalCount;
}
