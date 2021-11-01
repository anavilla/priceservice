package com.priceservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.priceservice.model.entity.Price;
import com.priceservice.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.format.DateTimeFormatter;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PriceserviceApplicationTests {

	public static final long PRODUCT_ID = 35455L;
	public static final long BRAND_ID = 1L;
	public static final long PRICE_ID_1 = 1L;
	public static final long PRICE_ID_2 = 2L;
	public static final long PRICE_ID_3 = 3L;
	public static final long PRICE_ID_4 = 4L;

	public static final String BRAND_ID_PARAM = "brandId";
	public static final String PRODUCT_ID_PARAM = "productId";
	public static final String APP_TIMESTAMP_PARAM = "appTimestamp";
	public static final String APPLICATION_JSON = "application/json";

	public static final String BRAND_ID_PARAM_VALUE = "1";
	public static final String PRODUCT_ID_PARAM_VALUE = "35455";
	public static final String APPLICABLE_PRICE_URL = "/prices/applicable_price";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PriceRepository repository;

	private Price getExpectedApplicablePrice(Long priceId) {
		return repository.findById(priceId)
				.orElseThrow(()-> new IllegalStateException("Incorrect database state"));
	}

	private void checkExpectedPrice(ResultActions res, Price expected) throws Exception {
		res.andExpect(jsonPath("$.productId").value(PRODUCT_ID))
			.andExpect(jsonPath("$.brandId").value(BRAND_ID))
			.andExpect(jsonPath("$.price").value(expected.getPrice()))
			.andExpect(jsonPath("$.priceList").value(expected.getPriceList()))
			.andExpect(jsonPath("$.startDate").value(expected.getStartDate().format(DateTimeFormatter.ISO_DATE_TIME)))
			.andExpect(jsonPath("$.endDate").value(expected.getEndDate().format(DateTimeFormatter.ISO_DATE_TIME)));
	}

	@Test //Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
	void ifIsDay14At10shouldReturnPriceId1() throws Exception {
		Price expected = getExpectedApplicablePrice(PRICE_ID_1);
		ResultActions res = mockMvc.perform(get(APPLICABLE_PRICE_URL)
				.contentType(APPLICATION_JSON)
				.param(BRAND_ID_PARAM, BRAND_ID_PARAM_VALUE)
				.param(PRODUCT_ID_PARAM, PRODUCT_ID_PARAM_VALUE)
				.param(APP_TIMESTAMP_PARAM, "2020-06-14T10:00:00"))
				.andExpect(status().isOk());
		checkExpectedPrice(res, expected);
	}

	@Test //Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
	void ifIsDay14At16shouldReturnExpectedPriceId2() throws Exception {
		Price expected = getExpectedApplicablePrice(PRICE_ID_2);
		ResultActions res = mockMvc.perform(get(APPLICABLE_PRICE_URL)
				.contentType(APPLICATION_JSON)
				.param(BRAND_ID_PARAM, BRAND_ID_PARAM_VALUE)
				.param(PRODUCT_ID_PARAM, PRODUCT_ID_PARAM_VALUE)
				.param(APP_TIMESTAMP_PARAM, "2020-06-14T16:00:00"))
				.andExpect(status().isOk());
		checkExpectedPrice(res, expected);
	}

	@Test //Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
	void ifIsDay14At21shouldReturnExpectedPriceId1() throws Exception {
		Price expected = getExpectedApplicablePrice(PRICE_ID_1);
		ResultActions res = mockMvc.perform(get(APPLICABLE_PRICE_URL)
				.contentType(APPLICATION_JSON)
				.param(BRAND_ID_PARAM, BRAND_ID_PARAM_VALUE)
				.param(PRODUCT_ID_PARAM, PRODUCT_ID_PARAM_VALUE)
				.param(APP_TIMESTAMP_PARAM, "2020-06-14T21:00:00"))
				.andExpect(status().isOk());
		checkExpectedPrice(res, expected);
	}

	@Test //Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
	void ifIsDay15At10shouldReturnExpectedPriceId3() throws Exception {
		Price expected = getExpectedApplicablePrice(PRICE_ID_3);
		ResultActions res = mockMvc.perform(get(APPLICABLE_PRICE_URL)
				.contentType(APPLICATION_JSON)
				.param(BRAND_ID_PARAM, BRAND_ID_PARAM_VALUE)
				.param(PRODUCT_ID_PARAM, PRODUCT_ID_PARAM_VALUE)
				.param(APP_TIMESTAMP_PARAM, "2020-06-15T10:00:00"))
				.andExpect(status().isOk());
		checkExpectedPrice(res, expected);
	}

	@Test //Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
	void ifIsDay16At21shouldReturnExpectedPriceId4() throws Exception {
		Price expected = getExpectedApplicablePrice(PRICE_ID_4);
		ResultActions res = mockMvc.perform(get(APPLICABLE_PRICE_URL)
				.contentType(APPLICATION_JSON)
				.param(BRAND_ID_PARAM, BRAND_ID_PARAM_VALUE)
				.param(PRODUCT_ID_PARAM, PRODUCT_ID_PARAM_VALUE)
				.param(APP_TIMESTAMP_PARAM, "2020-06-16T21:00:00"))
				.andExpect(status().isOk());
		checkExpectedPrice(res, expected);
	}

}
