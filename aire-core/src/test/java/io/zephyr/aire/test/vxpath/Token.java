package io.zephyr.aire.test.vxpath;

public final class Token {
  final int end;
  final int start;
  final CharSequence source;

  public Token(int end, int start, CharSequence source) {
    this.end = end;
    this.start = start;
    this.source = source;
  }
}
