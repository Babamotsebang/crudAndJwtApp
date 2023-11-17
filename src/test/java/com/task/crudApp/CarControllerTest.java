package com.task.crudApp;

import com.task.crudApp.model.Car;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.sql.rowset.CachedRowSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CarControllerTest extends AbstractTest{

    @Before
    public void setUp() {
        super.setup();
    }

    @Test
    public void getAllCars() throws Exception {
        String uri = "/getAllCars";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
        String content = mvcResult.getResponse().getContentAsString();
        Car[] allCars = super.mapFromJson(content, Car[].class);
        assertTrue(allCars.length > 0);
    }

    @Test
    public void addCar() throws Exception {
        String uri = "/addCar";

        Car car = new Car();
        car.setId(1L);
        car.setModel("BMW-X5");
        car.setYearProduced("2023");
        String inputJson = super.mapToJson(car);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, inputJson);
    }

    @Test
    public void updateCar() throws Exception {
        String uri = "/getCarById/1";
        Car car = new Car();
        car.setId(1L);
        car.setModel("BMW-X6");
        car.setYearProduced("2023");
        String inputJson = super.mapToJson(car);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, car);
    }

}
