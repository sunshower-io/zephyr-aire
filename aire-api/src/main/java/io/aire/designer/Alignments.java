package io.aire.designer;

public class Alignments {

  /** horizontal alignments */
  public static final Alignment LEFT =
      new DefaultAlignment(Alignment.Mode.Horizontal, "ALIGN_LEFT");

  public static final Alignment RIGHT =
      new DefaultAlignment(Alignment.Mode.Horizontal, "ALIGN_RIGHT");

  public static final Alignment CENTER =
      new DefaultAlignment(Alignment.Mode.Horizontal, "ALIGN_CENTER");

  /** vertical alignments */
  public static final Alignment TOP = new DefaultAlignment(Alignment.Mode.Vertical, "ALIGN_TOP");

  public static final Alignment MIDDLE =
      new DefaultAlignment(Alignment.Mode.Vertical, "ALIGN_MIDDLE");

  public static final Alignment BOTTOM =
      new DefaultAlignment(Alignment.Mode.Vertical, "ALIGN_BOTTOM");
}
