package com.natusfarma.pc.itecvstotvs.util;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileStringUtil {

    public String FileToString(File file){
        try(InputStream in = new FileInputStream(file)){
            return IOUtils.toString(in, StandardCharsets.UTF_8);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
