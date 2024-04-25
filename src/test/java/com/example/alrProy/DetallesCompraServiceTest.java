package com.example.alrProy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.alrProy.repositories.DetallesCompraRepository;
import com.example.alrProy.services.DetallesCompraServiceImpl;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class DetallesCompraServiceTest {

    @InjectMocks
    DetallesCompraServiceImpl detallesCompraService;

    @Mock
    DetallesCompraRepository detallesCompraRepository;

	@Test
	public void anhadirACompraTest() {

	}

}
