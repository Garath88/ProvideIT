package com.provideit.api.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

// Not really a necessary test
// this is just testing if Jackson marshalling/unmarshalling works and equals & toString methods.
public class ProductTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        //Ignore filter
        objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
    }

    @Test(timeout = 1000)
    public void testSerialization() throws JsonProcessingException {
        String expected = "{\"title\":\"title1\",\"price\":5.0,\"category\":\"category1\",\"description\":\"desc1\",\"image\":\"image1\"}";
        Product p1 = new Product(9L, "title1", 5.0, "category1", "desc1", "image1");
        String actual = objectMapper.writeValueAsString(p1);
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected, p1.toString());
    }

    @Test(timeout = 1000)
    public void testSerializationNullProperty() throws JsonProcessingException {
        String expected = "{\"title\":null,\"price\":5.0,\"category\":\"category1\",\"description\":\"desc1\",\"image\":\"image1\"}";
        Product p1 = new Product(9L, null, 5.0, "category1", "desc1", "image1");
        String actual = objectMapper.writeValueAsString(p1);
        Assert.assertEquals(expected, actual);
        String expectedEquals = "{\"title\":\"null\",\"price\":5.0,\"category\":\"category1\",\"description\":\"desc1\",\"image\":\"image1\"}";
        Assert.assertEquals(expectedEquals, p1.toString());
    }

    @Test(timeout = 1000)
    public void testDeserialization() throws JsonProcessingException {
        String json = "{\"id\":7.0,\"title\":\"title2\",\"price\":7.0,\"category\":\"category4\",\"description\":\"someDesc\",\"image\":\"imagelink\"}";
        Product actual = objectMapper.readValue(json, Product.class);
        Product expected = new Product(7L, "title2", 7.0, "category4", "someDesc", "imagelink");
        Assert.assertEquals(actual, expected);
        Assert.assertEquals(actual.title(), expected.title());
        Assert.assertEquals(actual.price(), expected.price());
        Assert.assertEquals(actual.category(), expected.category());
        Assert.assertEquals(actual.description(), expected.description());
        Assert.assertEquals(actual.image(), expected.image());
        Assert.assertEquals(actual.toString(), expected.toString());
    }

    @Test(timeout = 1000)
    public void testDeserializationMissingProperty() throws JsonProcessingException {
        String json = "{\"title\":null,\"price\":5.0,\"description\":\"desc1\",\"image\":\"image1\"}";
        Product actual = objectMapper.readValue(json, Product.class);
        Assert.assertNull(actual.category());
    }
}
