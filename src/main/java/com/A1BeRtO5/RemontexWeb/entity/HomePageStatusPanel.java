package com.A1BeRtO5.RemontexWeb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class HomePageStatusPanel {
    private Long inRepairCount;
    private  Long urgentCount;
    private  Long overdueCount;
    private  Long readyCount;
    private  Long totalCount;
    private  Long issuedCount;
}
