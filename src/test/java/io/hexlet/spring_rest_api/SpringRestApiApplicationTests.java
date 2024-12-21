package io.hexlet.spring_rest_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hexlet.spring_rest_api.model.Product;
import io.hexlet.spring_rest_api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class SpringRestApiApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper om;

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testRead() throws Exception {
		var response1 = mockMvc.perform(get("/products"))
				.andExpect(status().isOk())
				.andReturn();
		var body1 = response1.getResponse().getContentAsString();
		assertThat(body1).isEqualTo("[]");

		Product product = new Product();
		product.setTitle("Nameless");
		product.setPrice(250);
		product.setDescription("The best product in the world!");
		product.setImage("https://resources.free.images.picture1.img");
		product.setAvailability(true);
		productRepository.save(product);

		var response2 = mockMvc.perform(get("/products"))
				.andExpect(status().isOk())
				.andReturn();
		var body2 = response2.getResponse().getContentAsString();
		assertThat(body2).contains("Nameless");
	}

	@Test
	public void testReadById() throws Exception {
		var request1 = get("/products/{id}", 9000);
		mockMvc.perform(request1)
				.andExpect(status().isNotFound())
				.andReturn();

		Product product = new Product();
		product.setTitle("Mystery");
		product.setPrice(760);
		product.setDescription("The best product in the world!");
		product.setImage("https://resources.free.images.picture2.img");
		product.setAvailability(false);
		productRepository.save(product);

		var request2 = get("/products/{id}", product.getId());
		var response = mockMvc.perform(request2)
				.andExpect(status().isOk())
				.andReturn();
		var body = response.getResponse().getContentAsString();
		assertThat(body).contains("Mystery");
	}

	@Test
	public void testCreate() throws Exception {
		Product product = new Product();
		product.setTitle("Toy");
		product.setPrice(1999);
		product.setDescription("The best product in the world!");
		product.setImage("https://resources.free.images.picture3.img");
		product.setAvailability(true);

		var request = post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(product));
		var response = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andReturn();

		var body = response.getResponse().getContentAsString();
		assertThat(body).contains("Toy");
		assertThat(productRepository.existsByTitle("Toy")).isTrue();
	}

	@Test
	public void testUpdate() throws Exception {
		Product product = new Product();
		product.setTitle("Car");
		product.setPrice(20999);
		product.setDescription("The best car in the world!");
		product.setImage("https://resources.free.images.picture4.img");
		product.setAvailability(false);
		productRepository.save(product);
		var notUpdated = productRepository.findById(product.getId());
		assertThat(notUpdated).isNotEmpty();
		assertThat(notUpdated.get().getTitle()).isEqualTo("Car");

		product.setTitle("Megacar");
		var request = put("/products/{id}", product.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(product));
		var response = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();
		var body = response.getResponse().getContentAsString();
		assertThat(body).contains("Megacar");
		var updated = productRepository.findById(product.getId());
		assertThat(updated).isNotEmpty();
		assertThat(updated.get().getTitle()).isEqualTo("Megacar");
	}

	@Test
	public void testDelete() throws Exception {
		Product product = new Product();
		product.setTitle("Nothing");
		product.setPrice(0);
		product.setDescription("Empty description");
		product.setImage("https://resources.free.images.nothing.img");
		product.setAvailability(false);
		productRepository.save(product);

		assertThat(productRepository.existsById(product.getId())).isEqualTo(true);

		var request = delete("/products/{id}", product.getId());
		mockMvc.perform(request)
				.andExpect(status().isNoContent());

		assertThat(productRepository.existsById(product.getId())).isEqualTo(false);
	}
}
