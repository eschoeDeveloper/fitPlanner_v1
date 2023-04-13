package com.fitplanner.core.jpa.converter;

import com.fitplanner.core.util.CryptoUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * package     : com.fitplanner.core.jpa.converter
 * file        : CryptoFieldConverter
 * author      : choeuiseung
 * date        : 2022/10/23
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/23                choeuiseung                 최초 생성
 * =======================================================
 */
@Converter
public class CryptoFieldConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {

        String encString = "";

        if(attribute == null || attribute.isEmpty()) return encString;

        try {
            encString = CryptoUtil.encryptAES256(attribute);
        } catch (Exception e) {
            e.printStackTrace();
            encString = "";
        }

        return encString;

    }

    @Override
    public String convertToEntityAttribute(String dbData) {

        String decString = "";

        if(dbData == null || dbData.isEmpty()) return decString;

        try {
            decString = CryptoUtil.decryptAES256(dbData);
        } catch (Exception e) {
            e.printStackTrace();
            decString = "";
        }

        return decString;

    }

}
