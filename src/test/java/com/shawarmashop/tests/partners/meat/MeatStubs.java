package com.shawarmashop.tests.partners.meat;

import com.shawarmashop.tests.partners.wiremock.StubBuilder;
import com.shawarmashop.tests.partners.wiremock.WiremockAdminClient;
import com.shawarmashop.tests.partners.wiremock.WiremockStubBase;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;

import static com.shawarmashop.tests.partners.wiremock.StubBuilder.post;

public class MeatStubs extends WiremockStubBase {

    private static final String SOAP_PATH = "/soap/meat";
    private static final String SOAP_NAMESPACE = "http://shawarmashop.com/partners/meat";

    private static final JAXBContext JAXB_CONTEXT = createJaxbContext();

    public MeatStubs(WiremockAdminClient admin) {
        super(admin);
    }

    public void respondAccepted(MeatOrderResponse response) {
        admin.addMapping(post(SOAP_PATH)
                .withPriority(1)
                .willReturnXml(200, marshallToSoap(response.withStatus("ACCEPTED")))
        );
    }

    public void responseUnavailable(int ingredientId) {
        admin.addMapping(mappingForIngredient(ingredientId)
                .withPriority(1)
                .willReturnXml(503, "<error>meat unavailable</error>")
        );
    }

    private static StubBuilder mappingForIngredient(int id) {
        String suffix = "restock" + id + "-";
        String xpath = "//*[local-name()='orderRef' and starts-with(normalize-space(.), '" + suffix + "')]";
        return post(SOAP_PATH).withXpath(xpath);
    }

    private static String marshallToSoap(MeatOrderResponse response) {
        try {
            Marshaller marshaller = JAXB_CONTEXT.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(response, stringWriter);

            String rawXml = """
                    <?xml version="1.0" encoding="UTF-8"?>
                                        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                                          xmlns:m="%s">
                                          <soapenv:Body>
                                            %s
                                          </soapenv:Body>
                                        </soapenv:Envelope>
                    """;
            return rawXml.formatted(SOAP_NAMESPACE, stringWriter.toString().trim());
        } catch (JAXBException e) {
            throw new IllegalStateException("Не удалось создать SOAP ответ", e);
        }
    }

    private static JAXBContext createJaxbContext() {
        try {
            return JAXBContext.newInstance(MeatOrderResponse.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
