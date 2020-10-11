package io.sunshower.aire.docs;

import org.junit.jupiter.api.Test;

import javax.tools.DocumentationTool;
import javax.tools.ToolProvider;

import java.io.PrintWriter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ComponentDocumentationGeneratorDocletTest {

  @Test
  void ensureDocumentationWorks() {

    DocumentationTool systemDocumentationTool = ToolProvider.getSystemDocumentationTool();
    String[] args =
        new String[] {
          "-sourcepath",
          "./src/test/java",
          "-subpackages",
          "io.sunshower.aire.docs.examples",
          "io.sunshower.aire.docs.examples",
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
