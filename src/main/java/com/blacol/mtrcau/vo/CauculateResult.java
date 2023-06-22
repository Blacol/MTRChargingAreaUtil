package com.blacol.mtrcau.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CauculateResult {
    private Integer number;//收费区编号
    private Integer position;//位于几环
    private Double distance;
}
