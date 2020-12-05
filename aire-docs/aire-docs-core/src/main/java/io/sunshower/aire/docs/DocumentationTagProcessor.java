package io.sunshower.aire.docs;

import lombok.val;

import javax.lang.model.element.Element;
import javax.lang.model.util.ElementScanner9;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class DocumentationTagProcessor extends ElementScanner9<Void, Integer> {
  final PrintStream out;
  private final ComponentDocumentationGeneratorDoclet documentationGenerator;

  DocumentationTagProcessor(
      ComponentDocumentationGeneratorDoclet documentationGenerator, PrintStream out) {
    this.out = out;
    this.documentationGenerator = documentationGenerator;
  }

  void show(Set<? extends Element> elements) {
    scan(elements, 0);
  }

  @Override
  public Void scan(Element e, Integer depth) {
    val dcTree = documentationGenerator.getTreeUtils().getDocCommentTree(e);
    if (dcTree != null) {
      String indent = "  ".repeat(depth);
      out.println(indent + "| " + e.getKind() + " " + e);
      Map<String, List<String>> tags = new TreeMap<>();
      new TagScanner(tags).visit(dcTree, null);
      tags.forEach(
          (t, l) -> {
            out.println(indent + "  @" + t);
            l.forEach(c -> out.println(indent + "    " + c));
          });
    }
    return super.scan(e, depth + 1);
  }
}
