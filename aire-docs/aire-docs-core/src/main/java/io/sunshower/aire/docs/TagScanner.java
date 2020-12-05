package io.sunshower.aire.docs;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.UnknownBlockTagTree;
import com.sun.source.util.SimpleDocTreeVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
