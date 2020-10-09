package io.zephyr.aire.test.xpath;

import lombok.Getter;

@Getter
public final class Token {
  final int end;
  final int start;
  final StringBuilder program;

  public Token(StringBuilder program, int start, int end) {
    this.end = end;
    this.start = start;
    this.program = program;
  }

  @Override
  public String toString() {
    return String.format(
        "Token[matched='%s', start='%s', end='%s']",
        program.substring(start, end), start, end);
  }
}
