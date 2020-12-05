package io.sunshower.aire.docs;

import com.sun.source.util.DocTrees;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import lombok.Getter;

import javax.lang.model.SourceVersion;
import java.util.*;

public class ComponentDocumentationGeneratorDoclet implements Doclet {
  private static final boolean OK = true;

  @Getter private DocTrees treeUtils;

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
    DocumentationTagProcessor st = new DocumentationTagProcessor(this, System.out);
    st.show(environment.getSpecifiedElements());
    return OK;
  }
}
