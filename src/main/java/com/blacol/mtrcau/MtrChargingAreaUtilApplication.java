package com.blacol.mtrcau;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.blacol.mtrcau.mapper")
public class MtrChargingAreaUtilApplication {

    public static void main(String[] args) {
        SpringApplication.run(MtrChargingAreaUtilApplication.class, args);
    }

}
