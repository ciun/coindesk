package com.coindesk.demo.Serialzation;

import com.coindesk.demo.dto.Coindesk;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
public class CoindeskSerialization extends JsonSerializer<Coindesk>{

    @Override
    public void serialize(Coindesk coindesk, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName(coindesk.getCcyCode());
        gen.writeStartObject();
        gen.writeStringField("code", coindesk.getCoindesk().getCode());
        gen.writeStringField("symbol", coindesk.getCoindesk().getSymbol());
        gen.writeStringField("rate", coindesk.getCoindesk().getRate());
        gen.writeStringField("description", coindesk.getCoindesk().getDescription());
        gen.writeNumberField("rate_float", coindesk.getCoindesk().getRateFloat());
        gen.writeEndObject();
        gen.writeEndObject();
    }
}
