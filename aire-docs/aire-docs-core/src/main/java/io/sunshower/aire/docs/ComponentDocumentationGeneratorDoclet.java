package io.sunshower.aire.docs;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.UnknownBlockTagTree;
import com.sun.source.util.DocTrees;
import com.sun.source.util.SimpleDocTreeVisitor;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.ElementScanner9;
import javax.tools.Diagnostic;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class ComponentDocumentationGeneratorDoclet implements Doclet {
  private static final boolean OK = true;

  private DocTrees treeUtils;

  @Override
  public void init(Locale locale, Reporter reporter) {}

  @Override
  public String getName() {
    return getClass().getSimpleName();
  }

  @Override
  public Set<? extends Option> getSupportedOptions() {
    return Collections.emptySet();
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latest();
  }

  @Override
  public boolean run(DocletEnvironment environment) {
    treeUtils = environment.getDocTrees();
    ShowTags st = new ShowTags(System.out);
    st.show(environment.getSpecifiedElements());
    return OK;
  }

  /**
   * A scanner to search for elements with documentation comments, and to examine those comments for
   * custom tags.
   */
  class ShowTags extends ElementScanner9<Void, Integer> {
    final PrintStream out;

    ShowTags(PrintStream out) {
      this.out = out;
    }

    void show(Set<? extends Element> elements) {
      scan(elements, 0);
    }

    @Override
    public Void scan(Element e, Integer depth) {
      DocCommentTree dcTree = treeUtils.getDocCommentTree(e);
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

  /** A visitor to gather the block tags found in a comment. */
  class TagScanner extends SimpleDocTreeVisitor<Void, Void> {
    private final Map<String, List<String>> tags;

    TagScanner(Map<String, List<String>> tags) {
      this.tags = tags;
    }

    @Override
    public Void visitDocComment(DocCommentTree tree, Void p) {
      return visit(tree.getBlockTags(), null);
    }

    @Override
    public Void visitUnknownBlockTag(UnknownBlockTagTree tree, Void p) {
      String name = tree.getTagName();
      String content = tree.getContent().toString();
      tags.computeIfAbsent(name, n -> new ArrayList<>()).add(content);
      return null;
    }
  }
}
