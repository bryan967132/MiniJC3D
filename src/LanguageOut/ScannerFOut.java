/* The following code was generated by JFlex 1.7.0 */

/* 1. Package e importaciones */
package LanguageOut;
import java_cup.runtime.Symbol;
import Painter.WordPainter;

@SuppressWarnings("unused")


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>src/LanguageOut/ScannerFOut.jflex</tt>
 */
public class ScannerFOut implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  6,  9,  9,  8,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     1, 36,  5, 15,  0, 34,  0,  0, 37, 38, 14, 32, 43, 33, 12, 13, 
     4,  4,  4,  4,  4,  4,  4,  4,  4,  4, 44, 45, 10, 35, 11,  0, 
     0,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3, 
     3,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3, 41,  7, 42,  0,  2, 
     0, 26,  3, 18, 21, 22, 29, 30, 25, 16,  3,  3, 19,  3, 17, 24, 
    31,  3, 27,  3, 28, 20, 23,  3,  3,  3,  3, 39,  0, 40,  0,  0, 
     0,  0,  0,  0,  0,  9,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\1\1\3\1\4\1\1\1\2"+
    "\1\5\1\6\1\7\1\10\1\1\7\3\1\11\1\12"+
    "\1\13\1\14\1\1\1\15\1\16\1\17\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\3\0\1\26\3\0\1\27"+
    "\1\30\1\31\1\32\2\0\1\3\1\33\6\3\1\34"+
    "\1\35\1\36\2\0\1\37\6\3\1\32\1\0\1\40"+
    "\1\41\2\3\1\42\1\3\1\0\1\3\1\43\1\3"+
    "\1\0\1\44\1\45\1\0\1\46";

  private static int [] zzUnpackAction() {
    int [] result = new int[84];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\56\0\134\0\212\0\270\0\346\0\u0114\0\56"+
    "\0\u0142\0\u0170\0\u019e\0\56\0\u01cc\0\u01fa\0\u0228\0\u0256"+
    "\0\u0284\0\u02b2\0\u02e0\0\u030e\0\56\0\56\0\56\0\u033c"+
    "\0\u036a\0\56\0\56\0\56\0\56\0\56\0\56\0\56"+
    "\0\56\0\56\0\212\0\u0398\0\u0114\0\56\0\u03c6\0\u03f4"+
    "\0\u0422\0\u03f4\0\u03f4\0\56\0\u0450\0\u047e\0\u04ac\0\u04da"+
    "\0\270\0\u0508\0\u0536\0\u0564\0\u0592\0\u05c0\0\u05ee\0\56"+
    "\0\56\0\u0398\0\u061c\0\u064a\0\270\0\u0678\0\u06a6\0\u06d4"+
    "\0\u0702\0\u0730\0\u075e\0\56\0\u078c\0\270\0\270\0\u07ba"+
    "\0\u07e8\0\270\0\u0816\0\u0844\0\u0872\0\270\0\u08a0\0\u08ce"+
    "\0\270\0\270\0\u08fc\0\56";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[84];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\2"+
    "\1\3\1\0\1\11\1\12\1\2\1\13\1\14\1\15"+
    "\1\16\1\5\1\17\4\5\1\20\3\5\1\21\1\5"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41"+
    "\1\42\57\0\1\3\6\0\1\3\47\0\1\43\1\5"+
    "\14\0\20\5\20\0\3\5\13\0\20\5\22\0\1\6"+
    "\7\0\1\44\41\0\5\45\1\46\1\0\1\47\46\45"+
    "\5\50\2\0\1\51\3\50\1\52\27\50\1\53\12\50"+
    "\43\0\1\54\27\0\1\55\1\56\57\0\1\57\37\0"+
    "\3\5\13\0\1\5\1\60\13\5\1\61\2\5\20\0"+
    "\3\5\13\0\11\5\1\62\6\5\20\0\3\5\13\0"+
    "\10\5\1\63\7\5\20\0\3\5\13\0\6\5\1\64"+
    "\11\5\20\0\3\5\13\0\3\5\1\65\14\5\20\0"+
    "\3\5\13\0\10\5\1\66\7\5\20\0\3\5\13\0"+
    "\13\5\1\67\4\5\61\0\1\70\55\0\1\71\16\0"+
    "\1\72\51\0\6\45\1\0\1\45\2\0\44\45\5\50"+
    "\2\0\1\51\3\50\1\52\50\50\1\0\1\50\2\0"+
    "\44\50\6\55\1\0\1\55\1\0\45\55\16\56\1\73"+
    "\37\56\21\0\1\74\36\0\3\5\13\0\14\5\1\75"+
    "\3\5\20\0\3\5\13\0\12\5\1\76\5\5\20\0"+
    "\3\5\13\0\1\77\17\5\20\0\3\5\13\0\14\5"+
    "\1\100\3\5\20\0\3\5\13\0\10\5\1\101\7\5"+
    "\20\0\3\5\13\0\14\5\1\102\3\5\20\0\3\5"+
    "\13\0\1\103\17\5\16\0\15\56\1\104\1\73\37\56"+
    "\22\0\1\105\35\0\3\5\13\0\13\5\1\106\4\5"+
    "\20\0\3\5\13\0\5\5\1\107\12\5\20\0\3\5"+
    "\13\0\4\5\1\110\13\5\20\0\3\5\13\0\12\5"+
    "\1\111\5\5\20\0\3\5\13\0\10\5\1\112\7\5"+
    "\20\0\3\5\13\0\1\5\1\113\16\5\41\0\1\114"+
    "\34\0\3\5\13\0\13\5\1\115\4\5\20\0\3\5"+
    "\13\0\14\5\1\116\3\5\20\0\3\5\13\0\14\5"+
    "\1\117\3\5\42\0\1\120\33\0\3\5\13\0\1\5"+
    "\1\121\16\5\20\0\3\5\13\0\15\5\1\122\2\5"+
    "\43\0\1\123\56\0\1\124\27\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2346];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\5\1\1\11\3\1\1\11\10\1\3\11"+
    "\2\1\11\11\3\0\1\11\3\0\2\1\1\11\1\1"+
    "\2\0\10\1\2\11\1\1\2\0\7\1\1\11\1\0"+
    "\6\1\1\0\3\1\1\0\2\1\1\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[84];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true iff the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true iff the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    WordPainter painter;
    public ScannerFOut(String input, WordPainter painter) {
        yychar = 0;
        this.zzReader = new java.io.BufferedReader(
            new java.io.StringReader(input)
        );
        this.painter = painter;
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public ScannerFOut(java.io.Reader in) {
      yychar = 0;
    this.zzReader = in;
  }



  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(TOK.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { painter.ERROR(yychar, yylength());
            } 
            // fall through
          case 39: break;
          case 2: 
            { 
            } 
            // fall through
          case 40: break;
          case 3: 
            { return new Symbol(TOK.TK_id,        yychar, yylength(), yytext());
            } 
            // fall through
          case 41: break;
          case 4: 
            { return new Symbol(TOK.TK_int,       yychar, yylength(), yytext());
            } 
            // fall through
          case 42: break;
          case 5: 
            { return new Symbol(TOK.TK_less,      yychar, yylength(), yytext());
            } 
            // fall through
          case 43: break;
          case 6: 
            { return new Symbol(TOK.TK_more,      yychar, yylength(), yytext());
            } 
            // fall through
          case 44: break;
          case 7: 
            { return new Symbol(TOK.TK_div,       yychar, yylength(), yytext());
            } 
            // fall through
          case 45: break;
          case 8: 
            { return new Symbol(TOK.TK_mult,      yychar, yylength(), yytext());
            } 
            // fall through
          case 46: break;
          case 9: 
            { return new Symbol(TOK.TK_plus,      yychar, yylength(), yytext());
            } 
            // fall through
          case 47: break;
          case 10: 
            { return new Symbol(TOK.TK_minus,     yychar, yylength(), yytext());
            } 
            // fall through
          case 48: break;
          case 11: 
            { return new Symbol(TOK.TK_mod,       yychar, yylength(), yytext());
            } 
            // fall through
          case 49: break;
          case 12: 
            { return new Symbol(TOK.TK_equ,       yychar, yylength(), yytext());
            } 
            // fall through
          case 50: break;
          case 13: 
            { return new Symbol(TOK.TK_lpar,      yychar, yylength(), yytext());
            } 
            // fall through
          case 51: break;
          case 14: 
            { return new Symbol(TOK.TK_rpar,      yychar, yylength(), yytext());
            } 
            // fall through
          case 52: break;
          case 15: 
            { return new Symbol(TOK.TK_lbrc,      yychar, yylength(), yytext());
            } 
            // fall through
          case 53: break;
          case 16: 
            { return new Symbol(TOK.TK_rbrc,      yychar, yylength(), yytext());
            } 
            // fall through
          case 54: break;
          case 17: 
            { return new Symbol(TOK.TK_lbrk,      yychar, yylength(), yytext());
            } 
            // fall through
          case 55: break;
          case 18: 
            { return new Symbol(TOK.TK_rbrk,      yychar, yylength(), yytext());
            } 
            // fall through
          case 56: break;
          case 19: 
            { return new Symbol(TOK.TK_comma,     yychar, yylength(), yytext());
            } 
            // fall through
          case 57: break;
          case 20: 
            { return new Symbol(TOK.TK_colon,     yychar, yylength(), yytext());
            } 
            // fall through
          case 58: break;
          case 21: 
            { return new Symbol(TOK.TK_semicolon, yychar, yylength(), yytext());
            } 
            // fall through
          case 59: break;
          case 22: 
            { return new Symbol(TOK.TK_string,    yychar, yylength(), yytext());
            } 
            // fall through
          case 60: break;
          case 23: 
            { return new Symbol(TOK.TK_include,   yychar, yylength(), yytext());
            } 
            // fall through
          case 61: break;
          case 24: 
            { return new Symbol(TOK.TK_lessequ,   yychar, yylength(), yytext());
            } 
            // fall through
          case 62: break;
          case 25: 
            { return new Symbol(TOK.TK_moreequ,   yychar, yylength(), yytext());
            } 
            // fall through
          case 63: break;
          case 26: 
            { painter.COMMENT(yychar, yylength());
            } 
            // fall through
          case 64: break;
          case 27: 
            { return new Symbol(TOK.RW_if,        yychar, yylength(), yytext());
            } 
            // fall through
          case 65: break;
          case 28: 
            { return new Symbol(TOK.TK_equequ,    yychar, yylength(), yytext());
            } 
            // fall through
          case 66: break;
          case 29: 
            { return new Symbol(TOK.TK_notequ,    yychar, yylength(), yytext());
            } 
            // fall through
          case 67: break;
          case 30: 
            { return new Symbol(TOK.TK_float,     yychar, yylength(), yytext());
            } 
            // fall through
          case 68: break;
          case 31: 
            { return new Symbol(TOK.RW_int,       yychar, yylength(), yytext());
            } 
            // fall through
          case 69: break;
          case 32: 
            { return new Symbol(TOK.RW_char,      yychar, yylength(), yytext());
            } 
            // fall through
          case 70: break;
          case 33: 
            { return new Symbol(TOK.RW_void,      yychar, yylength(), yytext());
            } 
            // fall through
          case 71: break;
          case 34: 
            { return new Symbol(TOK.RW_goto,      yychar, yylength(), yytext());
            } 
            // fall through
          case 72: break;
          case 35: 
            { return new Symbol(TOK.RW_float,     yychar, yylength(), yytext());
            } 
            // fall through
          case 73: break;
          case 36: 
            { return new Symbol(TOK.RW_return,    yychar, yylength(), yytext());
            } 
            // fall through
          case 74: break;
          case 37: 
            { return new Symbol(TOK.RW_printf,    yychar, yylength(), yytext());
            } 
            // fall through
          case 75: break;
          case 38: 
            { return new Symbol(TOK.RW_include,   yychar, yylength(), yytext());
            } 
            // fall through
          case 76: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
