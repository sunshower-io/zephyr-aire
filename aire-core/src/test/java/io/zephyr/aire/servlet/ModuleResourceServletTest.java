package io.zephyr.aire.servlet;

import io.zephyr.aire.test.AireServletTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@AireServletTest
class ModuleResourceServletTest {

  private HttpServletRequest request;
  private HttpServletResponse response;

  @BeforeEach
  void setUp() {
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
  }

  @Test
  void ensureServletServesResourceFromCurrentContext() {}
}
