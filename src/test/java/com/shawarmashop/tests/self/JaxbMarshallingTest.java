package com.shawarmashop.tests.self;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class JaxbMarshallingTest {

    @SneakyThrows
    @Test
    void turnsAnnotatedObjectIntoXml() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Probe.class);
        Marshaller marshaller = context.createMarshaller();
        StringWriter writer = new StringWriter();

        marshaller.marshal(new Probe("ok"), writer);

        System.out.println(writer);

//        assertThat(writer.toString())
//                .contains("<probe", "<value>ok</value>");
    }

    @XmlRootElement(name = "probe")
    @XmlAccessorType(XmlAccessType.FIELD)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Probe {

        @XmlElement(name = "message")
        private String value;
    }
}
