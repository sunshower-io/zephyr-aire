package io.sunshower.aire.docs;

import io.sunshower.aire.docs.core.AireDocumentationTest;
import org.junit.jupiter.api.Test;

import javax.tools.DocumentationTool;
import javax.tools.ToolProvider;

import java.io.PrintWriter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@AireDocumentationTest
class ComponentDocumentationGeneratorDocletTest {

  @Test
  void ensureDocumentationWorks() {

    DocumentationTool systemDocumentationTool = ToolProvider.getSystemDocumentationTool();
    String[] args =
        new String[] {
          "-sourcepath",
          "./src/test/java",
          "-subpackages",
          "io.sunshower.aire.docs",
        };
    DocumentationTool.DocumentationTask task =
        systemDocumentationTool.getTask(
            new PrintWriter(System.out),
            null,
            null,
            ComponentDocumentationGeneratorDoclet.class,
            Arrays.asList(args),
            null);

    task.call();
  }
}
