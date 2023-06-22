package com.blacol.mtrcau.util;

import gitee.blacol.myIdUtil.builder.ComplexIdBuilder;
import gitee.blacol.myIdUtil.entity.ComplexId;
import gitee.blacol.myIdUtil.entity.IdBuilderConfig;
import gitee.blacol.myIdUtil.exception.MissingNecessaryParameters;

import java.io.IOException;

public class IdUtil {
    private static ComplexIdBuilder builder=new ComplexIdBuilder();
    private static void getBuilder(IdBuilderConfig config) throws MissingNecessaryParameters {
        builder.setConfig(config);
    }
    public static ComplexId getComplexId(String prefix,String format,String filePath) throws IOException, MissingNecessaryParameters {
        IdBuilderConfig config=new IdBuilderConfig();
        config.put("prefix",prefix);
        config.put("suffix","");
        config.put("format",format);
        config.put("file_path",filePath);
        getBuilder(config);
        ComplexId build = builder.build();
        build.continueIndex();
        return build;
    }
}
