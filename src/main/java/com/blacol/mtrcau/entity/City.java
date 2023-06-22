package com.blacol.mtrcau.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

  private String id;
  private String name;
  private int x;
  private int y;
  private int ringLimit;
  private int areaType;
  private int chargingAreaDelta;
  private int distanceDelta;

}
